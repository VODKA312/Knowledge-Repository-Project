package com.company.Wiki.service;

import com.company.Wiki.domain.Demo;
import com.company.Wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    //申明一个DemoMapper
    //使用JDK自带的注入方式
    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list() {
        return demoMapper.selectByExample(null);
    }
}
