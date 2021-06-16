package com.company.Wiki.controller;

import com.company.Wiki.req.EbookQueryReq;
import com.company.Wiki.req.EbookSaveReq;
import com.company.Wiki.resp.CommonResp;
import com.company.Wiki.resp.EbookQueryResp;
import com.company.Wiki.resp.PageResp;
import com.company.Wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

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
    public CommonResp list(EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    /**
     * 对电子书信息进行编辑
     * @param req
     * @return
     */
    @PostMapping("/save")
    //RequstBody 对应ptr中json方式的提交 如果是form表单形式(ptrp) 就不需要加任何注解
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
