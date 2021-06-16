package com.company.Wiki.service;

import com.company.Wiki.domain.Category;
import com.company.Wiki.domain.CategoryExample;
import com.company.Wiki.mapper.CategoryMapper;
import com.company.Wiki.req.CategoryQueryReq;
import com.company.Wiki.req.CategorySaveReq;
import com.company.Wiki.resp.CategoryQueryResp;
import com.company.Wiki.resp.PageResp;
import com.company.Wiki.util.CopyUtil;
import com.company.Wiki.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    //申明一个CategoryMapper
    //使用JDK自带的注入方式
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 使用雪花算法生成新增方法
     */
    @Resource
    private SnowFlake snowFlake;

    public List<CategoryQueryResp> all() {

        CategoryExample categoryExample = new CategoryExample();
        //排序
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);
        return list;
    }

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {
        //动态的查询数据
        PageHelper.startPage(req.getPage(),req.getSize());//每页查五条记录
        CategoryExample categoryExample = new CategoryExample();
        //排序
        categoryExample.setOrderByClause("sort asc");
        //创造where条件
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        //循环categorylist里的实体，让他转换为CategoryResp
        /**
         * 在日志中打印总页数和总行数
         */
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        /**
        List<CategoryResp> respList = new ArrayList<>();
        for (Category category : categoryList) {
            //原先用BeanUtils的方式进行赋值
            CategoryResp categoryResp = new CategoryResp();
            //使用Utils工具类拷贝属性
            BeanUtils.copyProperties(category,categoryResp);
            CategoryResp categoryResp = CopyUtil.copy(category, CategoryResp.class);
            respList.add(categoryResp);
        }
        */

        /**
         * 使用列表复制功能，简化for循环
         */
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        PageResp<CategoryQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 保存 首先对id进行判断，如果id为空就是新建一个 如果id有值就是对当前页面进行保存
     * @param req
     */
    public void save(CategorySaveReq req){
        //构建单体
        Category category = CopyUtil.copy(req,Category.class);
        //如果category.id有值，就是保存，如果category.id没有值，就是新增
        if(ObjectUtils.isEmpty(req.getId())){
            //新增 1.新增id(自增,uuid,雪花算法生成id) 2.将生成的id插入表中
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }
        else{
            //更新
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    public void delete(Long id){ //传入的值为id值
        categoryMapper.deleteByPrimaryKey(id);
    }
}
