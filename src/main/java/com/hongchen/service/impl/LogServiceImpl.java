package com.hongchen.service.impl;

import com.hongchen.annotation.Operator;
import com.hongchen.service.LogService;
import org.springframework.stereotype.Service;

/**
 * Created by lichongda on 2017/3/24.
 */
@Operator(name = "日志记录")
@Service
public class LogServiceImpl implements LogService{
    @Override
    @Operator(story = "添加log")
    public void add(String name) {
        System.out.println("add()");
    }

    @Override
    public void del() {
        System.out.println("del()");
    }
}
