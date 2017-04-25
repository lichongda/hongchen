package com.hongchen.dao.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.entity.admin.AdminPermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface AdminPermissionMapper extends BaseMapper<AdminPermission> {
    List<AdminPermission> selectByUserId(@Param("userId")Integer userId);
    List<AdminPermission> selectPermissionMap(HashMap<String,Object> map);
    List<AdminPermission> querySubPermission(HashMap<String,Object> map);
    List<AdminPermission> selectPermissionAll(HashMap<String,Object> map);
    List<AdminPermission> selectPermissionRoleId(HashMap<String,Object> map);
    List<AdminPermission> selectPermissionRoleIds(@Param("roleId")Integer roleId);
    List<AdminPermission> selectPermissionPageParentId(Page<AdminPermission> page, Integer permissionParentId);
    List<AdminPermission> selectPermissionParentId(Integer permissionParentId);
    List<AdminPermission>  selectPermissionSuper(HashMap<String,Object> map);//超级管理员接口
}