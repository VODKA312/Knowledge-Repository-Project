package com.company.Wiki.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 传入页数和行数两个参数
 */
public class PageReq {

    @NotNull(message = "页码不能为空")
    private int page; //当前页数

    @NotNull(message = "每页条数不能为空！！！！")
    @Max(value = 100,message = "每页查询最大条数为10")
    private int size; //当前行数

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}