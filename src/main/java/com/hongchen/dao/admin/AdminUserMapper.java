package com.hongchen.dao.admin;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.hongchen.entity.admin.AdminUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 管理员 Mapper 接口
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface AdminUserMapper  extends BaseMapper<AdminUser> {
    /**
     * <p>
     * 查询 state 状态，用户列表，分页显示
     * </p>
     *
     * @param page
     *            翻页对象，可以作为 xml 参数直接使用，传递参数 Pages 即自动分页
     *            状态
     * @return
     */
    List<AdminUser> selectAdminUserList(Pagination page);
    /*
    * 根据用户名查询
    * */
    AdminUser  findByUsername(String name);

    List<AdminUser> selectAdminUserAll(Pagination page);

    AdminUser queryUserId(Integer id);
}