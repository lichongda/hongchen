package com.hongchen.service.impl;

import org.springframework.stereotype.Service;

import com.hongchen.mapper.RoleMapper;
import com.hongchen.entity.Role;
import com.hongchen.service.IRoleService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Role 表数据服务层接口实现类
 *
 */
@Service
public class RoleServiceImpl extends SuperServiceImpl<RoleMapper, Role> implements IRoleService {


}