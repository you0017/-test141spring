package com.yc.ioc.bean4_ioc_factorybean.other;

//此类是别的公司或组织提供的类，原来没有加注解
//现在在项目中使用，没法加注解
public class Banana implements Fruit{
    private String name;

    public Banana() {
        System.out.println("Banana()");
    }

    public Banana(String name) {
        System.out.println("Banana(String name)");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void getId() {

    }

    @Override
    public void setId() {

    }
}
