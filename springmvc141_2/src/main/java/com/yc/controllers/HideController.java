package com.yc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//控制器:接受请求,处理，返回视图名
//springmvc  接到视图名 -> InternalResourceViewResolver(内部资源视图解析器   “WEB-INF/views"+xx+".html"
@Controller
public class HideController {
    //请求: /a
    @RequestMapping("/a")
    public String hide(){
        return "hide";      //请求的资源[/springmvc141_2_war_exploded/WEB-INF/views/hide.html] 不可用
        //return "statics_a";      //请求的资源[/springmvc141_2_war_exploded/WEB-INF/views/hide.html] 不可用
    }
}
//