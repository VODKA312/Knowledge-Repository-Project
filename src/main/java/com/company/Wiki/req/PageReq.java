package com.company.Wiki.req;

/**
 * 传入页数和行数两个参数
 */
public class PageReq {
    private int page; //当前页数

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