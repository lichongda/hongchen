package com.hongchen.dao.admin;

import com.hongchen.entity.admin.AdminUserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface AdminUserRoleMapper extends BaseMapper<AdminUserRole> {
    int deleteRoleId(Integer roleId);
    int deleteUserId(Integer userId);
    int deleteUserIdRoleId(@Param("userId")Integer userId, @Param("roleId")Integer roleId);

}