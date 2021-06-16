package com.company.Wiki.service;

import com.company.Wiki.domain.Ebook;
import com.company.Wiki.domain.EbookExample;
import com.company.Wiki.mapper.EbookMapper;
import com.company.Wiki.req.EbookReq;
import com.company.Wiki.resp.EbookResp;
import com.company.Wiki.resp.PageResp;
import com.company.Wiki.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    //申明一个EbookMapper
    //使用JDK自带的注入方式
    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookResp> list(EbookReq req) {
        //动态的查询数据
        PageHelper.startPage(req.getPage(),req.getSize());//每页查五条记录
        EbookExample ebookExample = new EbookExample();
        //创造where条件
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        //对条件进行判断，参数不为空的话才对他进行模糊查询
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+req.getName()+"%");
        }
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        //循环ebooklist里的实体，让他转换为EbookResp
        /**
         * 在日志中打印总页数和总行数
         */
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

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

        PageResp<EbookResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }
}
