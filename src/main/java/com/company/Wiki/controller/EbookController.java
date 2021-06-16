package com.company.Wiki.controller;

import com.company.Wiki.req.EbookReq;
import com.company.Wiki.resp.CommonResp;
import com.company.Wiki.resp.EbookResp;
import com.company.Wiki.resp.PageResp;
import com.company.Wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//用来返回字符串 @RestController
//用来返回页面 @Controller 前后端分离的项目基本上不用
@RestController
@RequestMapping("/ebook")
public class EbookController {

    //注入EbookService的服务
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    /**
     * 使用通用返回类接口：
     * 方便开发，管理接收消息
     * 如果为true，则弹出content的消息
     * 如果为false，则弹出message的消息
     */
    public CommonResp list(EbookReq req) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
