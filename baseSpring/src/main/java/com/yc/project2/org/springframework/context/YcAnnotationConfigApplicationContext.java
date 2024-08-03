package com.yc.project2.org.springframework.context;

import com.yc.project2.org.springframework.annotation.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class YcAnnotationConfigApplicationContext implements YcApplicationContext {
    private Map<String, Object> beanMap = new HashMap<String, Object>(); //beanId和具体的实例
    private Map<String, YcBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();//beanId和类信息

    public YcAnnotationConfigApplicationContext(Class<?>... configClass) throws Exception {
        //解析配置类
        for (Class<?> aClass : configClass) {
            //解析配置类中的@Bean注解
            parseBeanAnnotation(aClass);
            ///解析配置类中的@ComponentScan注解中的value,得到要扫描的包
            //待扫描的包的路径不为空，则递归扫描
            if (aClass.isAnnotationPresent(YcComponentScan.class)) {
                YcComponentScan ycComponentScan = aClass.getAnnotation(YcComponentScan.class);
                String[] basePackage = ycComponentScan.value();
                if (basePackage == null || basePackage.length <= 0) {
                    basePackage = ycComponentScan.basePackages();
                }
                if (basePackage == null || basePackage.length <= 0) {
                    basePackage = new String[1];
                    //当前这个配置类的路径值为扫描路径
                    basePackage[0] = aClass.getPackage().getName();
                }
                for (String basePackage1 : basePackage) {
                    //递归扫描
                    System.out.println("扫描路径：" + basePackage1);
                }
                //待扫描的包的路径不为空,则递归扫描
                recursiveLoadBeanDefinition(basePackage);
            }



            //读取类的信息，创建beanDefinition对象
        }
        try {
            //再ioc
            createBean();
            //再di
            doDi();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void doDi() throws Exception {
        //循环BeanDefinitionMap,创建bean
        for (Map.Entry<String, Object> entry : this.beanMap.entrySet()) {
            //获取beanId
            String beanId = entry.getKey();
            //获取bd
            Object beanObject = entry.getValue();
            //查看这个对象的属性上是否存在di注解
            Field[] fields = beanObject.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(YcResource.class)) {
                    //取出YcResource注解的value值，这个值是beanId,代表要装配的beanId
                    String toDiBeanId = field.getAnnotation(YcResource.class).value();
                    Object obj = getToDiBeanId(toDiBeanId);
                    //设置此field为可访问的
                    field.setAccessible(true);
                    field.set(beanObject, obj);//this.userDao = userDao
                }else if (field.isAnnotationPresent(YcAutowired.class)){
                    //TODO: 1.取出属性的类型 2.先从beanMap中看是否有这种类型的bean，有则返回，没有则看beanDefinitionMap中是否有，如果有则创建，没有则报错
                }
            }
        }
    }

    private Object getToDiBeanId(String toDiBeanId) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //如果beanMap中有，则直接返回
        if (this.beanMap.containsKey(toDiBeanId)){
            return this.beanMap.get(toDiBeanId);
        }
        //判断beanDefinitionMap中是否有此BeanId，如果有，还可以创建，，如果没有则报错
        if (this.beanDefinitionMap.containsKey(toDiBeanId)){
            throw new RuntimeException("beanId:"+toDiBeanId+"不存在");
        }
        YcBeanDefinition bd = this.beanDefinitionMap.get(toDiBeanId);
        if (bd.isLazy()){
            String classPath = bd.getClassInfo();
            Object beanObj = Class.forName(classPath).newInstance();
            this.beanMap.put(toDiBeanId,beanObj);
            return beanObj;
        }
        if (bd.getScope().equalsIgnoreCase("prototype")){
            String classPath = bd.getClassInfo();
            Object beanObj = Class.forName(classPath).newInstance();
            //原型模式每次getBean就要获取一次，bean每次都要创建，所以没有存到beanMap中
            return beanObj;
        }
        return null;
    }

    private void createBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //循环BeanDefinitionMap中的bean,创建bean
        for (Map.Entry<String, YcBeanDefinition> stringYcBeanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            //获取beanId
            String beanId = stringYcBeanDefinitionEntry.getKey();
            //获取bd
            YcBeanDefinition bd = stringYcBeanDefinitionEntry.getValue();
            //判断bd中是否存在lazy属性
            if (!bd.isLazy() && !bd.getScope().equalsIgnoreCase("prototype")){
                //创建bean
                String classInfo = bd.getClassInfo();
                //创建bean的方式有多种，这里只用到了通过无参构造方法创建的方式
                Object beanObj = Class.forName(classInfo).newInstance();
                this.beanMap.put(beanId, beanObj);


            }
        }
    }

    /**
     * 递归扫描basePackages中的路径，加载所有的托管类信息到BeanDefinitionMap中
     *
     * @param basePackage
     */
    private void recursiveLoadBeanDefinition(String[] basePackage) {
        //循环此数组
        for (String basePackageName : basePackage) {
            //加载包下.class文件->流->文件路径->com/yc/Person.class
            //替换包路径中的.为 /
            String packagePath = basePackageName.replaceAll("\\.", "/");
            //利用jvm加载包中的class文件
            //线程上下文加载器
            try {
                Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
                while (files.hasMoreElements()) {
                    URL url = files.nextElement();
                    System.out.println("正在扫描的路径:" + URLDecoder.decode(url.getPath(),"utf-8"));
                    //扫描这个包下的所有文件，筛选出.class文件
                    findPackageClasses(url.getFile(), basePackageName);//basePackageName时带.的报名
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 递归扫描basePackages中的路径，加载所有的托管类信息到BeanDefinitionMap中
     *
     * @param packagePath 待扫描的包的绝对路径 : /c:/xxx/xxx
     * @param packageName com.yc:
     */
    private void findPackageClasses(String packagePath, String packageName) {
        if (packagePath.startsWith("/")) {
            packagePath = packagePath.substring(1);
        }
        try {
            packagePath = URLDecoder.decode(packagePath, "utf-8"); // 防止路径中文，统一转utf-8
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        //取这个packagePath路径下的所有文件(包括子包
        File file = new File(packagePath);
        //取所有的.class文件
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".class") || pathname.isDirectory()) {
                    return true;
                }
                return false;
            }
        });
        //System.out.println(files);

        //循环files，如果是文件，则加载，如果是目录，则递归扫描
        if (files != null && files.length > 0) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    //文件  则jvm加载
                    URLClassLoader uc = new URLClassLoader(new URL[]{});
                    //
                    try {
                        Class cls = uc.loadClass(packageName + "." + file1.getName().replace(".class", ""));
                        //判断这个cls类上是否有ioc注解，如果有，则创建一个BeanDefinition对象，存到BeanDefinitionMap中
                        if (cls.isAnnotationPresent(YcComponent.class) ||
                                cls.isAnnotationPresent(YcService.class)
                                || cls.isAnnotationPresent(YcRepository.class)
                                || cls.isAnnotationPresent(YcController.class)) {
                            YcBeanDefinition bd = new YcBeanDefinition();
                            if (cls.isAnnotationPresent(YcLazy.class)) {
                                YcLazy ycLazy = (YcLazy) cls.getAnnotation(YcLazy.class);
                                boolean b = ycLazy.value();
                                bd.setLazy(b);
                            }
                            if (cls.isAnnotationPresent(YcScope.class)) {
                                YcScope ycScope = (YcScope) cls.getAnnotation(YcScope.class);
                                bd.setScope(ycScope.value());
                            }
                            bd.setClassInfo(packageName + "." + file1.getName().replace(".class", ""));
                            //获取beanId
                            String beanId = getBeanId(cls);
                            beanDefinitionMap.put(beanId, bd);
                            //beanMap.put(beanId, cls.newInstance());
                            System.out.println("beanId:" + beanId);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    //目录
                    findPackageClasses(file1.getAbsolutePath(), packageName + "." + file1.getName());
                }
            }
        }
    }

    private String getBeanId(Class cls) {
        String beanId = "";
        YcComponent ycComponent = (YcComponent) cls.getAnnotation(YcComponent.class);
        YcRepository ycRepository = (YcRepository) cls.getAnnotation(YcRepository.class);
        YcService ycService = (YcService) cls.getAnnotation(YcService.class);
        YcController ycController = (YcController) cls.getAnnotation(YcController.class);
        if (ycComponent != null) {
            beanId = ycComponent.value();
        } else if (ycRepository != null) {
            beanId = ycRepository.value();
        } else if (ycService != null) {
            beanId = ycService.value();
        } else if (ycController != null) {
            beanId = ycController.value();
        }
        //如果按value()取不到，则取类名首字母小写
        if (beanId.equals("")||beanId==null) {
            beanId = cls.getSimpleName().substring(0, 1).toLowerCase() + cls.getSimpleName().substring(1);
        }
        return beanId;
    }

    private void parseBeanAnnotation(Class<?> aClass) {
        try{
            Object o = aClass.newInstance();
            String clzName = aClass.getSimpleName();    //类名
            YcBeanDefinition bd = new YcBeanDefinition();
            bd.setLazy(false);//懒加载
            bd.setScope("SINGLETON");//单例
            bd.setClassInfo(aClass.getName());//类信息 也就是包名+类
            String beanId = clzName.substring(0, 1).toLowerCase() + clzName.substring(1);
            beanDefinitionMap.put(beanId, bd);
            beanMap.put(beanId, o);


            //在这个配置类cls中查找所有@YcBean注解的方法，解析它们，托管对应的类的对象
            Method[] ms = aClass.getDeclaredMethods();
            //只取有@YcBean注解的方法
            for (Method m : ms) {
                if (m.isAnnotationPresent(YcBean.class)) {
                    //解析@YcBean注解
                    YcBean ycBean = m.getAnnotation(YcBean.class);
                    String beanName = ycBean.value();
                    if (beanName.equals("")) {
                        beanName = m.getName();
                    }
                    //激活方法
                    Object obj = m.invoke(o, null);
                    this.beanMap.put(beanName, obj);
                    //创建beanDefinition对象
                    YcBeanDefinition bd1 = new YcBeanDefinition();
                    bd1.setLazy(false);//懒加载
                    bd1.setScope("SINGLETON");
                    bd1.setClassInfo(obj.getClass().getName());
                    this.beanDefinitionMap.put(beanName, bd1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanId) {
        YcBeanDefinition bd = this.beanDefinitionMap.get(beanId);
        if (bd == null){
            throw new RuntimeException("beanId:"+beanId+"不存在");
        }
        String scope = bd.getScope();
        if ("prototype".equals(scope)){
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
                this.beanMap.put(beanId,obj);
                doDi();
                this.beanMap.remove(beanId);
                return obj;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        if (beanMap.containsKey(beanId)){
            return beanMap.get(beanId);
        }
        if (bd.isLazy()){
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
                beanMap.put(beanId,obj);
                doDi();
                return obj;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
