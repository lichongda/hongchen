package com.hongchen.dao.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.entity.admin.AdminRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hongchen.entity.admin.AdminUser;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    List<AdminRole> selectByUserId(Integer userId);
    List<AdminRole> queryRoleAll(Page<AdminRole> page);
    List<AdminRole> selectRoleAll();
    List<AdminUser> queryUserRole(Integer roleId);
}