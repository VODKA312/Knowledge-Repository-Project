package com.company.Wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//用来返回字符串 @RestController
//用来返回页面 @Controller 前后端分离的项目基本上不用
@RestController
public class TestController {

    /**
     * 地址：http://127.0.0.1:8088/hello
     * 在/hello页面下使用hello()方法，返回语句hello,world
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        return "hello,world";
    }
}
