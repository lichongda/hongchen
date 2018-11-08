package com.hongchen.service.impl.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongchen.controller.admin.AdminRoleController;
import com.hongchen.dao.admin.AdminRoleMapper;
import com.hongchen.dao.admin.AdminRolePermissionMapper;
import com.hongchen.entity.admin.AdminRole;
import com.hongchen.entity.admin.AdminRolePermission;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.service.admin.IAdminRolePermissionService;
import com.hongchen.service.admin.IAdminRoleService;
import com.hongchen.service.admin.IAdminUserRoleService;
import com.hongchen.shiro.MyShiroRealm2;
import com.hongchen.shiro.StatelessAuthenticationToken;
import com.hongchen.vo.Pages;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.RoleVo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.CachingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {
    private static final Logger logger = LoggerFactory.getLogger(AdminRoleServiceImpl.class);
    @Autowired
    private IAdminRolePermissionService adminRolePermissionService;

    @Autowired
    private IAdminUserRoleService adminUserRoleService;

    @Autowired MyShiroRealm2 myShiroRealm2;

    @Override
    public List<AdminRole> selectByUserId(Integer userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public PublicReturnVo queryRoleAll(Page<AdminRole> page ) {
        PublicReturnVo<List<AdminRole>> vo = null;
        page.setRecords(baseMapper.queryRoleAll(page));
        vo = PublicReturnVo.conversionPublicReturn(page);
        return vo;
    }

    @Override
    public List<AdminRole> selectRoleAll() {
      return baseMapper.selectRoleAll();
    }


    @Override
    @Transactional
    public int insert(RoleVo roleVo) {
        AdminRole adminRole = new AdminRole();
        adminRole.setRoleName(roleVo.getRoleName());
        adminRole.setRoleDescription(roleVo.getRoleDescription());
        adminRole.setCreateTime(new Date());
        adminRole.setStatus(true);
        int result = baseMapper.insert(adminRole);
        logger.info("result = " + result +"  id=" + adminRole.getRoleId());
        if(org.apache.commons.lang.StringUtils.isEmpty(roleVo.getAddId())){
            return result;
        }
        String []ids = roleVo.getAddId().split(",");
        List<AdminRolePermission>list = getRolePermission(adminRole.getRoleId(), ids);
        adminRolePermissionService.insertBatch(list);
        return result;
    }

    @Override
    public int update(RoleVo roleVo, Integer userId) {
        AdminRole adminRole = new AdminRole();
        if(!roleVo.getOriginalRoleName().equals(roleVo.getRoleName())){
            adminRole.setRoleName(roleVo.getRoleName());
        }
        adminRole.setRoleDescription(roleVo.getRoleDescription());
        adminRole.setCreateTime(new Date());
        adminRole.setStatus(true);
        adminRole.setRoleId(roleVo.getRoleId());
        int result = baseMapper.updateById(adminRole);
        EntityWrapper ew=new EntityWrapper();
        ew.setEntity(new AdminRole());
       // int result =   baseMapper.update(adminRole, ew);

        if(org.apache.commons.lang.StringUtils.isNotEmpty(roleVo.getAddId())){
            String []ids = roleVo.getAddId().split(",");
            List<AdminRolePermission>list = getRolePermission(roleVo.getRoleId(), ids);
            adminRolePermissionService.insertBatch(list);//新增
        }
        if(org.apache.commons.lang.StringUtils.isNotEmpty(roleVo.getDelId())){
            String []ids = roleVo.getDelId().split(",");
            List<AdminRolePermission>list = getRolePermission(roleVo.getRoleId(), ids);
            adminRolePermissionService.deleteBatch(list);//删除
        }

        clearCachedAuthorizationInfo(userId);//更新缓存
        return result;
    }

    private  List<AdminRolePermission> getRolePermission(int roleId, String []ids){
        int size = ids.length;
        List<AdminRolePermission>list = new ArrayList<AdminRolePermission>();
        for(int i=0; i< size; i++){
            AdminRolePermission adminRolePermission = new AdminRolePermission();
            adminRolePermission.setPermissionId(Integer.parseInt(ids[i]));
            adminRolePermission.setRoleId(roleId);
            list.add(adminRolePermission);
        }
        return list;
    }

    @Override
    @Transactional
    public int deleteById(Integer roleId) {
        adminRolePermissionService.deleteRoleId(roleId);
        adminUserRoleService.deleteRoleId(roleId);
        return baseMapper.deleteById(roleId);
    }

    /**
     * 重新加载权限
     */
    public void clearCachedAuthorizationInfo(Integer userId){
        myShiroRealm2.clearCachedAuthorizationInfo(userId);
    }
}
