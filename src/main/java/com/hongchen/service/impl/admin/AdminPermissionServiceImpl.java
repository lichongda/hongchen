package com.hongchen.service.impl.admin;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongchen.constants.Constants;
import com.hongchen.controller.admin.AdminPermissionController;
import com.hongchen.dao.admin.AdminPermissionMapper;
import com.hongchen.entity.admin.AdminPermission;
import com.hongchen.entity.admin.AdminRolePermission;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.service.admin.IAdminPermissionService;
import com.hongchen.service.admin.IAdminRolePermissionService;
import com.hongchen.vo.Pages;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.PermissionVo;
import com.hongchen.vo.admin.UserVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
@Service
public class AdminPermissionServiceImpl extends ServiceImpl<AdminPermissionMapper, AdminPermission> implements IAdminPermissionService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPermissionServiceImpl.class);
    @Autowired
    private IAdminRolePermissionService adminRolePermissionService;

    @Override
    public List<AdminPermission> selectByUserId(Integer userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<AdminPermission> selectPermissionUserId(Integer userId) {
        HashMap<String, Object> mapParent=new HashMap<>();//先查询所有的父菜单
        mapParent.put("typeId", Constants.MENU_FUNCTION);//功能
        mapParent.put("parentId", Constants.MENU_PARENT_ID);//父菜单
        mapParent.put("userId", userId);
        List<AdminPermission> adminPermissionList = baseMapper.selectPermissionMap(mapParent);
        if(adminPermissionList != null && adminPermissionList.size() > 0){
            HashMap<String, Object> mapSub=new HashMap<>();//先查询所有的父菜单下面的子菜单
            mapSub.put("typeId", Constants.MENU_PARENT_ID);//功能
            mapSub.put("userId", userId);
            for(AdminPermission adminPermission: adminPermissionList){
                mapSub.put("parentId", adminPermission.getPermissionId());//父菜单
                List<AdminPermission> subAdminPermissionList = baseMapper.selectPermissionMap(mapSub);//子菜单
                adminPermission.setSubAdminPermission(subAdminPermissionList);
                //按钮
                HashMap<String, Object> buttonMap=new HashMap<>();//先查询所有的父菜单下面的子菜单
                buttonMap.put("typeId", Constants.MENU_BUTTON);//按钮
                buttonMap.put("userId", userId);
                for(AdminPermission buttonPermission: subAdminPermissionList){
                    buttonMap.put("parentId", buttonPermission.getPermissionId());//子菜单
                    List<AdminPermission> buttonPermissionList = baseMapper.selectPermissionMap(buttonMap);//子菜单
                    buttonPermission.setSubAdminPermission(buttonPermissionList);//添加按钮
                }
            }
        }
        return adminPermissionList;
    }

    @Override
    public List<AdminPermission> selectPermissionAll() {
        HashMap<String, Object> mapParent=new HashMap<>();//先查询所有的父菜单
        mapParent.put("typeId", Constants.MENU_FUNCTION);//功能
        mapParent.put("parentId", Constants.MENU_PARENT_ID);//父菜单
        List<AdminPermission> adminPermissionList = baseMapper.selectPermissionAll(mapParent);
        if(adminPermissionList != null && adminPermissionList.size() > 0){
            HashMap<String, Object> mapSub=new HashMap<>();//先查询所有的父菜单下面的子菜单
            mapSub.put("typeId", Constants.MENU_FUNCTION);//功能
            for(AdminPermission adminPermission: adminPermissionList){
                mapSub.put("parentId", adminPermission.getPermissionId());//父菜单
                List<AdminPermission> subAdminPermissionList = baseMapper.selectPermissionAll(mapSub);//子菜单
                adminPermission.setSubAdminPermission(subAdminPermissionList);
                //按钮
                HashMap<String, Object> buttonMap=new HashMap<>();//先查询所有的父菜单下面的子菜单
                buttonMap.put("typeId", Constants.MENU_BUTTON);//按钮
                for(AdminPermission buttonPermission: subAdminPermissionList){
                    buttonMap.put("parentId", buttonPermission.getPermissionId());//子菜单
                    List<AdminPermission> buttonPermissionList = baseMapper.selectPermissionAll(buttonMap);//子菜单
                    buttonPermission.setSubAdminPermission(buttonPermissionList);//添加按钮
                }
            }
        }

        return adminPermissionList;
    }

    @Override
    public List<AdminPermission> selectPermissionRoseId(Integer roleId) {
        HashMap<String, Object> mapParent=new HashMap<>();//先查询所有的父菜单

       // List<AdminPermission> adminPermissionList = baseMapper.selectPermissionRoleIds(roleId);
        mapParent.put("typeId", Constants.MENU_FUNCTION);//功能
        mapParent.put("parentId", Constants.MENU_PARENT_ID);//父菜单
        mapParent.put("roleId", roleId);
        List<AdminPermission> adminPermissionList = baseMapper.selectPermissionRoleId(mapParent);
        if(adminPermissionList != null && adminPermissionList.size() > 0){
            HashMap<String, Object> mapSub=new HashMap<>();//先查询所有的父菜单下面的子菜单
            mapSub.put("typeId", Constants.MENU_FUNCTION);//功能
            mapSub.put("roleId", roleId);
            for(AdminPermission adminPermission: adminPermissionList){
                mapSub.put("parentId", adminPermission.getPermissionId());//父菜单
                List<AdminPermission> subAdminPermissionList = baseMapper.selectPermissionRoleId(mapSub);//子菜单
                adminPermission.setSubAdminPermission(subAdminPermissionList);
                //按钮
                HashMap<String, Object> buttonMap=new HashMap<>();//先查询所有的父菜单下面的子菜单
                buttonMap.put("typeId", Constants.MENU_BUTTON);//按钮
                buttonMap.put("roleId", roleId);
                for(AdminPermission buttonPermission: subAdminPermissionList){
                    buttonMap.put("parentId", buttonPermission.getPermissionId());//子菜单
                    List<AdminPermission> buttonPermissionList = baseMapper.selectPermissionRoleId(buttonMap);//子菜单
                    buttonPermission.setSubAdminPermission(buttonPermissionList);//添加按钮
                }
            }
        }
        return adminPermissionList;
    }

    @Override
    public List<AdminPermission> refreshaAminPermission(Integer roleId, Integer permissionId) {
        List<AdminPermission> buttonPermissionList = null;
        HashMap<String, Object> buttonMap=new HashMap<>();//先查询所有的父菜单下面的子菜单
        buttonMap.put("typeId", Constants.MENU_BUTTON);//按钮
        buttonMap.put("parentId",permissionId);//子菜单
        if(roleId == Constants.SUPER_ADMINISTRATOR){//是否为超级管理员
            buttonPermissionList = baseMapper.selectPermissionSuper(buttonMap);
        }else{
            buttonMap.put("roleId", roleId);
            buttonPermissionList = baseMapper.selectPermissionRoleId(buttonMap);//按钮
        }
        return buttonPermissionList;
    }

    @Override
    public List<AdminPermission> queryParentPermission(Integer roleId) {
        HashMap<String, Object> mapParent=new HashMap<>();//先查询所有的父菜单
        mapParent.put("typeId", Constants.MENU_FUNCTION);//功能
        mapParent.put("parentId", Constants.MENU_PARENT_ID);//父菜单
        mapParent.put("roleId", roleId);
        List<AdminPermission> adminPermissionList = baseMapper.selectPermissionRoleId(mapParent);
        return adminPermissionList;
    }

    @Override
    public List<AdminPermission> querySubPermission(Integer roleId, Integer permissionId) {
        HashMap<String, Object> mapSub=new HashMap<>();//先查询所有的父菜单下面的子菜单
        mapSub.put("typeId", Constants.MENU_FUNCTION);//功能
        mapSub.put("roleId", roleId);
        mapSub.put("parentId", permissionId);//父菜单
        List<AdminPermission> subAdminPermissionList = baseMapper.selectPermissionRoleId(mapSub);//子菜单
        HashMap<String, Object> buttonMap=new HashMap<>();//先查询所有的父菜单下面的子菜单
        buttonMap.put("typeId", Constants.MENU_BUTTON);//按钮
        buttonMap.put("roleId", roleId);
        for(AdminPermission buttonPermission: subAdminPermissionList){
            buttonMap.put("parentId", buttonPermission.getPermissionId());//子菜单
            List<AdminPermission> buttonPermissionList = baseMapper.selectPermissionRoleId(buttonMap);//子菜单
            buttonPermission.setSubAdminPermission(buttonPermissionList);//添加按钮
        }
        return subAdminPermissionList;
    }

    @Override
    public PublicReturnVo selectPermissionPageParentId(Page<AdminPermission> page, Integer permissionParentId) {
        PublicReturnVo<List<AdminPermission>> vo = null;
        page.setRecords(baseMapper.selectPermissionPageParentId(page,permissionParentId));
        vo = PublicReturnVo.conversionPublicReturn(page);
        return vo;
    }

    @Override
    public int insert(PermissionVo permissionVo, Integer roleId) {
        AdminPermission adminPermission = new AdminPermission();
        adminPermission.setStatus(true);
        adminPermission.setCreateTime(new Date());
        adminPermission.setPermissionDescription(permissionVo.getPermissionDescription());
        adminPermission.setPermissionName(permissionVo.getPermissionName());
        adminPermission.setPermissionUrl(permissionVo.getPermissionUrl());
        adminPermission.setPermissionResource(permissionVo.getPermissionResource());
        adminPermission.setPermissionTypeId(permissionVo.getPermissionTypeId());

        if(permissionVo.getPermissionSubId() != null &&permissionVo.getPermissionSubId() != -1){
            adminPermission.setPermissionParentId(permissionVo.getPermissionSubId());
        }else {
            adminPermission.setPermissionParentId(permissionVo.getPermissionParentId());
        }

        int result =   baseMapper.insert(adminPermission);
        AdminRolePermission adminRolePermission = new AdminRolePermission();
        adminRolePermission.setPermissionId(adminPermission.getPermissionId());//需把当前的角色动态赋有权限
        adminRolePermission.setRoleId(roleId);
        adminRolePermissionService.insert(adminRolePermission);
        return result;
    }

    @Override
    public int update(PermissionVo permissionVo) {
        logger.info("permissionVo=" + permissionVo);
        AdminPermission adminPermission = new AdminPermission();
        adminPermission.setPermissionId(permissionVo.getPermissionId());
        adminPermission.setPermissionDescription(permissionVo.getPermissionDescription());
        if(!permissionVo.getOriginalPermissionName().equals(permissionVo.getPermissionName())){
            adminPermission.setPermissionName(permissionVo.getPermissionName());
        }

        if(permissionVo.getPermissionSubId() != null && permissionVo.getPermissionSubId() != -1){
            adminPermission.setPermissionParentId(permissionVo.getPermissionSubId());
        }else {
            adminPermission.setPermissionParentId(permissionVo.getPermissionParentId());
        }
        adminPermission.setPermissionUrl(StringUtils.isNotEmpty(permissionVo.getPermissionUrl())? permissionVo.getPermissionUrl(): "无");
        adminPermission.setPermissionResource(permissionVo.getPermissionResource());
        adminPermission.setPermissionTypeId(permissionVo.getPermissionTypeId());

        return baseMapper.updateById(adminPermission);
    }

    @Override
    public int delete(Integer permissionId) {
        int result = 0;
        AdminPermission adminPermission = baseMapper.selectById(permissionId);
        if(adminPermission != null && adminPermission.getPermissionParentId() == 1){
            List<AdminPermission>  list = querySubPermission(permissionId);//把相关的子菜单按钮删除
            if(list== null || list.size() <1){
                adminRolePermissionService.deletePermissionId(permissionId);
                result= baseMapper.deleteById(permissionId);
            }
        }


        if(adminPermission != null && adminPermission.getPermissionParentId() != 1){
            HashMap<String, Object> mapSub=new HashMap<>();
            mapSub.put("typeId", Constants.MENU_BUTTON);//按钮
            mapSub.put("parentId", permissionId);//父菜单
            List<AdminPermission>  list = baseMapper.querySubPermission(mapSub);//把相关的子菜单按钮删除
            adminRolePermissionService.deletePermissionId(permissionId);
            if(list!= null && list.size() >0){
                for(AdminPermission adminPermission1 : list){
                    baseMapper.deleteById(adminPermission1.getPermissionId());
                    adminRolePermissionService.deletePermissionId(adminPermission1.getPermissionId());
                }
            }
            result = baseMapper.deleteById(permissionId);
        }
        return result;
    }

    @Override
    public List<AdminPermission> querySubPermission(Integer permissionId) {
        HashMap<String, Object> mapSub=new HashMap<>();//先查询所有的父菜单下面的子菜单
        mapSub.put("typeId", Constants.MENU_FUNCTION);//功能
        mapSub.put("parentId", permissionId);//父菜单
        List<AdminPermission> subAdminPermissionList = baseMapper.querySubPermission(mapSub);//子菜单
        HashMap<String, Object> buttonMap=new HashMap<>();//先查询所有的父菜单下面的子菜单
        buttonMap.put("typeId", Constants.MENU_BUTTON);//按钮
        for(AdminPermission buttonPermission: subAdminPermissionList){
            buttonMap.put("parentId", buttonPermission.getPermissionId());//子菜单
            List<AdminPermission> buttonPermissionList = baseMapper.querySubPermission(buttonMap);//子菜单
            buttonPermission.setSubAdminPermission(buttonPermissionList);//添加按钮
        }
        return subAdminPermissionList;
    }

    @Override
    public AdminPermission selectById(Integer permissionId) {
        return baseMapper.selectById(permissionId);
    }

    @Override
    public List<AdminPermission> selectPermissionParentId(Integer permissionParentId) {
        return baseMapper.selectPermissionParentId(permissionParentId);
    }
}
