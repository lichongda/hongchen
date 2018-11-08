package com.hongchen.service.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.admin.AdminUser;
import com.baomidou.mybatisplus.service.IService;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
public interface IAdminUserService extends IService<AdminUser>{
    PublicReturnVo selectAdminUserList(Page<AdminUser>  page);
    PublicReturnVo selectAdminUserAll(Page<AdminUser>  page);
    AdminUser findByUsername(String name);
    int insert(UserVo userVo);
    int update(UserVo userVo);
    int delete(Integer userId);
    AdminUser queryUserId(Integer id);
}
