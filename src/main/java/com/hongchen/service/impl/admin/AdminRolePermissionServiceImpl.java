package com.hongchen.service.impl.admin;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongchen.dao.admin.AdminRolePermissionMapper;
import com.hongchen.entity.admin.AdminRolePermission;
import com.hongchen.service.admin.IAdminRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
@Service
public class AdminRolePermissionServiceImpl extends ServiceImpl<AdminRolePermissionMapper, AdminRolePermission> implements IAdminRolePermissionService  {
    @Override
    public int deleteBatch(List<AdminRolePermission> list) {
      return  baseMapper.deleteBatch(list);
    }

    @Override
    public int deleteRoleId(Integer roleId) {
        return baseMapper.deleteRoleId(roleId);
    }

    @Override
    public int deletePermissionId(Integer permissionId) {
        return baseMapper.deletePermissionId(permissionId);
    }
}
