package com.company.Wiki.service;

import com.company.Wiki.domain.Test;
import com.company.Wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    //申明一个TestMapper
    //使用JDK自带的注入方式
    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
