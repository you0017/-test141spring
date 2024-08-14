package com.yc.controllers;

import com.yc.model.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class Person3Ctroller {

    @PostMapping("/upload")
    public Person handlerFileUpload(@RequestParam("file")MultipartFile file,String username){
        if (!file.isEmpty()){
            try {
                //获取文件名
                String fileName = file.getOriginalFilename();
                //设置文件保存路径(类路径 -> tomcat 的bin
                String filePath = "path/to/save/" + fileName;
                //保存文件
                file.transferTo(new File(filePath));

                Person p = new Person();
                p.setAge(1);
                p.setId(1);
                p.setName(username);
                return p;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            return null;
        }
        return null;
    }
}
