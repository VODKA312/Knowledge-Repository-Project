package com.company.Wiki.resp;

import java.util.List;

/**
 * 使用泛型来接收数据
 * @param <T>
 */
public class PageResp<T> {
    private long total; //返回总数

    private List<T> list; //返回一个列表，里面容纳的数值类型是泛型

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResp{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}