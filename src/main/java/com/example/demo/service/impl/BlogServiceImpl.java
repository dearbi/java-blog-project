package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.mapper.BlogInfoMapper;
import com.example.demo.model.BlogInfo;
import com.example.demo.model.BlogInfoResponse;
import com.example.demo.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Override
    public List<BlogInfoResponse> getList() {
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(new
                LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getDeleteFlag, 0).orderByDesc(BlogInfo::getId));
        List<BlogInfoResponse> blogInfoResponses =
                blogInfos.stream().map(blogInfo -> {
                    BlogInfoResponse response = new BlogInfoResponse();
                    BeanUtils.copyProperties(blogInfo, response);
                    return response;
                }).collect(Collectors.toList());
        return blogInfoResponses;
    }

    @Override
    public BlogInfoResponse getBlogDetail(Integer blogId) {
        BlogInfoResponse blogInfoResponse = new BlogInfoResponse();
        BlogInfo blogInfo = getBlogInfo(blogId);
        BeanUtils.copyProperties(blogInfo, blogInfoResponse);
        return blogInfoResponse;
    }

    private BlogInfo getBlogInfo(Integer blogId) {
        return blogInfoMapper.selectOne(new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getDeleteFlag, 0).eq(BlogInfo::getId, blogId));
    }
}
