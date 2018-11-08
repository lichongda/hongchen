
package com.hongchen.service.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.UserVo;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface IAdminUserService extends IService<AdminUser> {
    PublicReturnVo selectAdminUserList(Page<AdminUser> page);

    PublicReturnVo selectAdminUserAll(Page<AdminUser>  page,String userName);


    int insert(UserVo userVo);

    int update(UserVo userVo);

    int delete(Integer userId);

    AdminUser queryUserId(Integer id);
}
