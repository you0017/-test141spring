package com.yc.myproxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;

/**
 * 自定义一个类加载器，用于加载代理类(YcJdbcProxy生成的代理对象字节码)
 * 此类加载器从class路径下扫描加载类
 */
public class YcClassLoader extends ClassLoader{
    /**
     * 字节码路径文件
     */
    private File classPathFile;
    public YcClassLoader(){
        //获取class路径
        String classPath = YcClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    /**
     * 根据双亲委派模型，自定义的类加载器必须重写此方法
     */
    @Override
    protected Class<?> findClass(String name) {
        //待类加载的类的全路径名
        String className = YcClassLoader.class.getPackage().getName() + "." + name;
        if(classPathFile != null){
            String path = null;
            try {
                //因为路径中有中文，已经被编码过，所以要用URLDecode反编码
                path = URLDecoder.decode(classPathFile.getPath(),"UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }
            //代理类的字节码文件
            File classFile = new File(path,name.replaceAll("\\.", "/")+".class");
            if(classFile.exists()){
                //如果字节码文件存在，则开始加载字节码
                try (FileInputStream in = new FileInputStream(classFile);
                     ByteArrayOutputStream out = new ByteArrayOutputStream();){
                    byte[] buff = new byte[1024];
                    int len = -1;
                    while((len = in.read(buff)) != -1){
                        out.write(buff,0,len);
                    }
                    out.flush();
                    //将读取到的字节数组转换为Class
                    return defineClass(className,out.toByteArray(),0,out.size());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
