package com.yc.controllers;


import com.yc.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@Controller
public class PersonController {

    @RequestMapping("/person1")
    public Person person1(int id, String name, int age){

        Person person = new Person(1, "张三", 22);
        return person;
    }
}
