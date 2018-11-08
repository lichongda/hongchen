package com.hongchen.service.impl.admin;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongchen.dao.admin.AdminUserMapper;
import com.hongchen.dao.quartz.QuartzSchedulejobMapper;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.entity.admin.AdminUserRole;
import com.hongchen.service.admin.IAdminUserRoleService;
import com.hongchen.service.admin.IAdminUserService;
import com.hongchen.vo.Pages;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.UserVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
@Service
public class AdminUserServiceImpl  extends ServiceImpl<AdminUserMapper, AdminUser> implements IAdminUserService{
    private static final Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);
    @Autowired
    private IAdminUserRoleService adminUserRoleService;


    public PublicReturnVo selectAdminUserList(Page<AdminUser> page) {
        PublicReturnVo<List<AdminUser>> vo = null;
        page.setRecords(baseMapper.selectAdminUserList(page));
        vo = PublicReturnVo.conversionPublicReturn(page);
        return vo;
    }

    @Override
    public AdminUser findByUsername(String name) {
        return  baseMapper.findByUsername(name);
    }

    @Override
    public PublicReturnVo selectAdminUserAll(Page<AdminUser> page) {
        PublicReturnVo<List<AdminUser>> vo = null;
       page.setRecords(baseMapper.selectAdminUserAll(page));
        vo = PublicReturnVo.conversionPublicReturn(page);
        return vo;
    }

    @Override

    public int insert(UserVo userVo) {
        AdminUser adminUser = new AdminUser();
        adminUser.setUserName(userVo.getUserName());
        Md5Hash md5Hash=new Md5Hash(userVo.getUserPassword(), userVo.getUserName(),2);
        //adminUser.setUserPassword(md5Hash.toString());
        adminUser.setUserPassword(userVo.getUserPassword());
        adminUser.setNickName(userVo.getNickName());
        adminUser.setUserStatus(true);
        adminUser.setCreateTime(new Date());

        int result = baseMapper.insert(adminUser);
        if(StringUtils.isNotEmpty(userVo.getRoleId())){
            AdminUserRole adminUserRole = new AdminUserRole();
            adminUserRole.setRoleId(Integer.parseInt(userVo.getRoleId()));
            logger.info("adminUser.getUserId():" + adminUser.getUserId());
            adminUserRole.setUserId(adminUser.getUserId());
            adminUserRoleService.insert(adminUserRole);
        }
        return result;
    }

    @Override
    public int update(UserVo userVo) {
        logger.info("userVo=" + userVo.toString());
        AdminUser adminUser = new AdminUser();
        if(!userVo.getUserName().equals(userVo.getOriginalUserName())){
            adminUser.setUserName(userVo.getUserName());
        }
        //adminUser.setUserPassword(userVo.getUserPassword());
        adminUser.setNickName(userVo.getNickName());
        adminUser.setCreateTime(new Date());
        adminUser.setUserId(userVo.getUsersId());
        int result = baseMapper.updateById(adminUser);
        if(StringUtils.isNotEmpty(userVo.getOriginalRoleId()) ){
            if(!userVo.getOriginalRoleId().equals(userVo.getRoleId())){
                adminUserRoleService.deleteUserIdRoleId(userVo.getUsersId(), Integer.parseInt(userVo.getOriginalRoleId()));
                AdminUserRole adminUserRole = new AdminUserRole();
                adminUserRole.setUserId(userVo.getUsersId());
                adminUserRole.setRoleId(Integer.parseInt(userVo.getRoleId()));
                adminUserRoleService.insert(adminUserRole);
            }
        }else if (StringUtils.isNotEmpty(userVo.getRoleId())){
            AdminUserRole adminUserRole = new AdminUserRole();
            adminUserRole.setUserId(userVo.getUsersId());
            adminUserRole.setRoleId(Integer.parseInt(userVo.getRoleId()));
            adminUserRoleService.insert(adminUserRole);
        }


        return result;
    }

    @Override
    public int delete(Integer userId) {
        adminUserRoleService.deleteUserId(userId);
       int result = baseMapper.deleteById(userId);
        return result;
    }

    @Override
    public AdminUser queryUserId(Integer id) {
        return baseMapper.queryUserId(id);
    }
}
