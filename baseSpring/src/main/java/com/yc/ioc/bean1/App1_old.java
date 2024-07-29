package com.yc.ioc.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1_old {
    public static void main(String[] args) {
        //1.以前的方法:所有的对象都是硬编码    2.手工装配:对象的依赖关系
        //这个对象的生命周期是程序员自己管理
        /*Address a = new Address("北京", "朝阳", "100000");
        Student s = new Student("张三", 18, a);
        //System.out.println(s);
        s.setId(1);
        s.setName("李四");
        s.setAddress(a);*/
        //以上就是控制不反转，自己管理对象的生命周期
        //缺点:1.对象创建比较麻烦 2.对象之间依赖关系比较复杂 3.对象之间耦合度高

        //2.引入spring框架来管理对象生命周期
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Address address = (Address) context.getBean("addr");
        System.out.println(address);
        Address address2 = context.getBean("addr", Address.class);
        System.out.println(address2);
        //默认情况下spring创建对象是单例
        System.out.println(address.hashCode());
        System.out.println(address2.hashCode());

        Student s = context.getBean("student", Student.class);
        System.out.println(s);

        //使用基于文件系统的xml容器类
        /*ApplicationContext ac = new FileSystemXmlApplicationContext("F:\\java\\java\\。，。\\三期\\test141spring\\baseSpring\\src\\main\\resources\\beans.xml");
        ApplicationContext ac2 = new FileSystemXmlApplicationContext("classpath:beans.xml");

        Address add3 = ac.getBean("addr", Address.class);
        System.out.println(add3);
        Address add4 = ac2.getBean("addr", Address.class);
        System.out.println(add4);*/


        //第三种方案:
        /*Resource resource = new ClassPathResource("beans.xml");//读取了classpath下的文件，没有启动
        BeanFactory ac3 = new XmlBeanFactory(resource);
        Address add5 = (Address) ac3.getBean("addr");
        System.out.println(add5);*/

        //以上两个点:1.ioc容器的创建  2.IOC过程
    }
}
