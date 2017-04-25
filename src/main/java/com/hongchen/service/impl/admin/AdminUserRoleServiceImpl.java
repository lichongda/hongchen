package com.hongchen.service.impl.admin;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongchen.dao.admin.AdminUserRoleMapper;
import com.hongchen.entity.admin.AdminUserRole;
import com.hongchen.service.admin.IAdminUserRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
@Service
public class AdminUserRoleServiceImpl extends ServiceImpl<AdminUserRoleMapper, AdminUserRole> implements IAdminUserRoleService {
    @Override
    public int deleteRoleId(Integer roleId) {
        return baseMapper.deleteRoleId(roleId);
    }

    @Override
    public int deleteUserId(Integer userId) {
        return baseMapper.deleteUserId(userId);
    }

    @Override
    public int deleteUserIdRoleId( Integer userId, Integer roleId) {
        return baseMapper.deleteUserIdRoleId(userId, roleId);
    }
}
