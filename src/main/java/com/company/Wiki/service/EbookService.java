package com.company.Wiki.service;

import com.company.Wiki.domain.Ebook;
import com.company.Wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    //申明一个EbookMapper
    //使用JDK自带的注入方式
    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }
}
