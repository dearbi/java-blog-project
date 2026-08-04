package com.example.demo.service;

import com.example.demo.model.BlogInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    public List<BlogInfoResponse> getList();

    BlogInfoResponse getBlogDetail(Integer blogId);
}
