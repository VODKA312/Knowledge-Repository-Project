package com.company.Wiki.controller;

import com.company.Wiki.req.CategoryQueryReq;
import com.company.Wiki.req.CategorySaveReq;
import com.company.Wiki.resp.CategoryQueryResp;
import com.company.Wiki.resp.CommonResp;
import com.company.Wiki.resp.PageResp;
import com.company.Wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

//用来返回字符串 @RestController
//用来返回页面 @Controller 前后端分离的项目基本上不用
@RestController
@RequestMapping("/category")
public class CategoryController {

    //注入CategoryService的服务
    @Resource
    private CategoryService categoryService;


    @GetMapping("/all")
    public CommonResp all() {
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all();
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list")
    /**
     * 使用通用返回类接口：
     * 方便开发，管理接收消息
     * 如果为true，则弹出content的消息
     * 如果为false，则弹出message的消息
     * @Valid 表示开启校验规则
     */
    public CommonResp list(@Valid CategoryQueryReq req) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(req);
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
    public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}
