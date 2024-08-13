package com.yc.web.controllers;

import com.yc.pojo.Person;
import org.springframework.web.bind.annotation.*;
import com.yc.pojo.JsonModel;
@RestController
public class PersonController {

    @GetMapping("/person.action")
    //@RequestMapping("/person.action")
    public String person(@RequestBody String name,int age){
        System.out.println("/person");
        return "name:"+name+" age:"+age;
    }

    //@RequestMapping("/person2.action")
    @PostMapping("/person2.action")
    public String person2(Person person){
        System.out.println("/person2");
        return "name:"+person.getAge()+" age:"+person.getAge()+1;
    }

    @PostMapping("/person3.action")
    public JsonModel person3(@RequestBody Person person){
        System.out.println("/person3");
        return new JsonModel(200,person,"");
    }
}
