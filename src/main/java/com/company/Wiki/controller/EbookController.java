package com.company.Wiki.controller;

import com.company.Wiki.domain.Ebook;
import com.company.Wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//用来返回字符串 @RestController
//用来返回页面 @Controller 前后端分离的项目基本上不用
@RestController
@RequestMapping("/ebook")
public class EbookController {

    //注入EbookService的服务
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public List<Ebook> list() {
        return ebookService.list();
    }
}
