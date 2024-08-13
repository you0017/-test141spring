package com.yc.web.controllers;

import com.yc.pojo.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yc.pojo.JsonModel;
@RestController
public class PersonController {

    @RequestMapping("/person.action")
    public String person(String name,int age){
        return "name:"+name+" age:"+age;
    }

    @RequestMapping("/person2.action")
    public String person2(@RequestBody Person person){
        return "name:"+person.getAge()+" age:"+person.getAge()+1;
    }

    @RequestMapping("/person3.action")
    public JsonModel person3(Person person){
        return new JsonModel(200,person,"");
    }
}
