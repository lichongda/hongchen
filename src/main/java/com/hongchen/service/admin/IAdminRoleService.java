package com.hongchen.service.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.entity.admin.AdminRole;
import com.baomidou.mybatisplus.service.IService;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.RoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface IAdminRoleService extends IService<AdminRole> {
    List<AdminRole> selectByUserId(Integer userId);
    PublicReturnVo queryRoleAll(Page<AdminRole> page);
    List<AdminRole> selectRoleAll();
    int insert(RoleVo roleVo);
    int update(RoleVo roleVo, Integer userId);
    int deleteById(Integer roleId);
}
