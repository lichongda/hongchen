package com.hongchen.service.admin;

import com.hongchen.entity.admin.AdminRolePermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface IAdminRolePermissionService   extends IService<AdminRolePermission>{
    int deleteBatch(List<AdminRolePermission> list);
    int deleteRoleId(Integer roleId);
    int deletePermissionId(Integer permissionId);
}
