package com.hongchen.controller;

import com.hongchen.constants.Constants;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.admin.AdminPermission;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.service.LogService;
import com.hongchen.service.admin.IAdminPermissionService;
import com.hongchen.service.admin.IAdminUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lichongda on 2017/3/24.
 */
@RestController
public class indexController {
    @Autowired
    private LogService logService;
    @Autowired
    IAdminPermissionService adminPermissionService;
    @RequestMapping({"/index"})
    public ResponseBase<?> index(Integer userId, Integer roleId){
        ResponseBase<List<AdminPermission> > responseMessage = new ResponseBase<>();
        List<AdminPermission> adminPermissionList = null;
        if(roleId == Constants.SUPER_ADMINISTRATOR){
            adminPermissionList = adminPermissionService.selectPermissionAll();
        }else{
            adminPermissionList =  adminPermissionService.selectPermissionUserId(userId);
        }
        responseMessage.setData(adminPermissionList);
        return responseMessage;
    }

    public static void main(String[] args) {
        System.out.println(ByteSource.Util.bytes("123456"));
    }

}
