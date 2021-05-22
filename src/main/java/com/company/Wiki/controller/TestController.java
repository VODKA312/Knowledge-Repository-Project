package com.company.Wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

//用来返回字符串 @RestController
//用来返回页面 @Controller 前后端分离的项目基本上不用
@RestController
public class TestController {

    //在resource下的properties文件下读自定义配置项的值
    //:value 默认配置项为value
    //优先级：配置项读到的值 > 默认配置项值
    @Value("${test.hello:TEST}")
    private String TestHello;

    /**
     * 地址：http://127.0.0.1:8088/hello
     * 在/hello页面下使用hello()方法，返回语句hello,world
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        return "Hello World " + TestHello; //读取值
    }

    @PostMapping("/hello/name")
    public String helloName(String Name){
        return "hello,Your Name is "+Name;
    }
}
