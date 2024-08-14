package com.yc.controllers;

import com.yc.model.Person;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/chapter1")

public class Person2Controller {
    @RequestMapping(value = "/person2",method = {RequestMethod.GET,RequestMethod.POST})
    //@CrossOrigin("http://localhost:7070")
    public Person person1(int id, String name, int age){

        Person person = new Person(id, name, age);
        return person;
    }

    @PostMapping("/person3")
    public Person person2(@RequestBody Person person){

        return person;
    }

    @PostMapping(value = "/person6")
    public Person person6(@RequestBody Person person, HttpSession session, HttpServletRequest req){
        if (session.getAttribute("p")!=null){
            person = (Person) session.getAttribute("p");
            person.setAge(person.getAge()+1);
        }else {
            session.setAttribute("p",person);
        }
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext);
        Person p = (Person) session.getAttribute("p");
        return p;
    }

    @PostMapping(value = "/person7/{id}/{name}/{age}")
    public Person person7(@PathVariable int id, @PathVariable String name, @PathVariable int age){
        Person p = new Person(id, name, age);
        return p;
    }
}
