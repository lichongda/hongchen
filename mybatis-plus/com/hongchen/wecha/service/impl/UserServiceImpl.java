package com.hongchen.service.impl;

import org.springframework.stereotype.Service;

import com.hongchen.mapper.UserMapper;
import com.hongchen.entity.User;
import com.hongchen.service.IUserService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements IUserService {


}