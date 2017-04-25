package com.hongchen.dao.admin;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hongchen.entity.admin.AdminRolePermission;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface AdminRolePermissionMapper  extends BaseMapper<AdminRolePermission> {
    int deleteBatch(List<AdminRolePermission>list);
    int  deleteRoleId(Integer roleId);
    int  deletePermissionId(Integer permissionId);
}