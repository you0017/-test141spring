package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean5_ioc_import.other.Apple;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

//原来的问题:import托管的bean的id名太长了
//需求:利用import注册Apple到spring容器时,修改beanid名

//import一次只调用一次，只能循环调用
public class FruitNameImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry)
    {
        /*//将要导入的类的类名首字母小谢后作为beanid
        System.out.println(importingClassMetadata);
        System.out.println(registry);

        String cname = importingClassMetadata.getClassName();
        cname = cname.substring(cname.lastIndexOf(".") + 1);
        String beanid = cname.substring(0, 1).toLowerCase() + cname.substring(1);
        boolean flag = registry.containsBeanDefinition(beanid);
        if (!flag){
            //创建一个bean的定义，存到registry(就是一个Map<String, BeanDefinition>)中
            //                                          beanid,bean定义
            RootBeanDefinition bean = new RootBeanDefinition(cname);
            registry.registerBeanDefinition(beanid, bean);
        }*/

        boolean b = registry.containsBeanDefinition(Apple.class.getName());
        String beanid = Apple.class.getSimpleName().substring(0, 1).toLowerCase() + Apple.class.getSimpleName().substring(1);
        if (b==false){
            //bean的定义信息对象
            //spring对bean的扫描和加载流程:先撒秒bean->线程间BeanDefinition对象->再去创建对象->考虑以来的顺序
            RootBeanDefinition bd = new RootBeanDefinition(Apple.class);
            registry.registerBeanDefinition(beanid,bd);
        }
    }
}
