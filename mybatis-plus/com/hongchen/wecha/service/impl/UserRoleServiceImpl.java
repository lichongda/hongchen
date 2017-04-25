package com.hongchen.service.impl;

import org.springframework.stereotype.Service;

import com.hongchen.mapper.UserRoleMapper;
import com.hongchen.entity.UserRole;
import com.hongchen.service.IUserRoleService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * UserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends SuperServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {


}