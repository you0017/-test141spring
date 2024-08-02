package com.yc.di.bean3_value_spEL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {
    // #:表示这是一个spEL表达式语言
    //T
    @Value("#{ T(java.lang.Math).random*1000}")
    private int id;

    @Value("#{ new String('hello world').toUpperCase()}")
    private String name;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
