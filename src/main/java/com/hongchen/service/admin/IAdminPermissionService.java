package com.hongchen.service.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.entity.admin.AdminPermission;
import com.baomidou.mybatisplus.service.IService;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.PermissionVo;
import com.hongchen.vo.admin.UserVo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface IAdminPermissionService extends IService<AdminPermission> {
    List<AdminPermission> selectByUserId(Integer userId);
    List<AdminPermission> selectPermissionUserId(Integer userId);
    List<AdminPermission> selectPermissionAll();
    List<AdminPermission> selectPermissionRoseId(Integer roleId);
    List<AdminPermission> refreshaAminPermission(Integer roleId,  Integer permissionId);
    List<AdminPermission> queryParentPermission(Integer roleId);
    List<AdminPermission> querySubPermission(Integer roleId,  Integer permissionId);
    List<AdminPermission> querySubPermission(Integer permissionId);
    PublicReturnVo selectPermissionPageParentId(Page<AdminPermission> page, Integer permissionParentId);
    List<AdminPermission>  selectPermissionParentId(Integer permissionParentId);
    AdminPermission selectById(Integer permissionId);
    int insert(PermissionVo permissionVo,Integer roleId);
    int update(PermissionVo permissionVo);
    int delete( Integer permissionId);

}
