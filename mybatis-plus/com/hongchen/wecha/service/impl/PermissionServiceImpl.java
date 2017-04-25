package com.hongchen.service.impl;

import org.springframework.stereotype.Service;

import com.hongchen.mapper.PermissionMapper;
import com.hongchen.entity.Permission;
import com.hongchen.service.IPermissionService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Permission 表数据服务层接口实现类
 *
 */
@Service
public class PermissionServiceImpl extends SuperServiceImpl<PermissionMapper, Permission> implements IPermissionService {


}