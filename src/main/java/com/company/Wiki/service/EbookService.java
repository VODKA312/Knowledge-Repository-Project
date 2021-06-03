package com.company.Wiki.service;

import com.company.Wiki.domain.Ebook;
import com.company.Wiki.domain.EbookExample;
import com.company.Wiki.mapper.EbookMapper;
import com.company.Wiki.req.EbookReq;
import com.company.Wiki.resp.EbookResp;
import com.company.Wiki.util.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    //申明一个EbookMapper
    //使用JDK自带的注入方式
    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        //创造where条件
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%"+req.getName()+"%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        //循环ebooklist里的实体，让他转换为EbookResp

        /**
        List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            //原先用BeanUtils的方式进行赋值
            EbookResp ebookResp = new EbookResp();
            //使用Utils工具类拷贝属性
            BeanUtils.copyProperties(ebook,ebookResp);
            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
            respList.add(ebookResp);
        }
        */

        /**
         * 使用列表复制功能，简化for循环
         */
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);
        return list;
    }
}
