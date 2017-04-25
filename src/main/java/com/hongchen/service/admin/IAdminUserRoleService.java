package com.hongchen.service.admin;

import com.hongchen.entity.admin.AdminUserRole;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface IAdminUserRoleService extends IService<AdminUserRole> {
    int deleteRoleId(Integer roleId);
    int deleteUserId(Integer userId);
    int deleteUserIdRoleId(@Param("userId")Integer userId, @Param("roleId")Integer roleId);
}
