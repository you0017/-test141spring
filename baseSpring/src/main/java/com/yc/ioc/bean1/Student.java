package com.yc.ioc.bean1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@Repository
public class Student {
    @Value("张三")
    private String name;
    private Integer id;
    private Address address;
}
