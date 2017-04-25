package com.hongchen.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.admin.AdminRole;
import com.hongchen.service.admin.IAdminPermissionService;
import com.hongchen.service.admin.IAdminRoleService;
import com.hongchen.util.AssertUtil;
import com.hongchen.util.WebUtil;
import com.hongchen.validator.First;
import com.hongchen.validator.Second;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.RoleVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lichongda on 2017/3/24.
 */
@RestController
@RequestMapping("/role/")
public class AdminRoleController {
    private static final Logger logger = LoggerFactory.getLogger(AdminRoleController.class);

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Autowired
    private IAdminRoleService adminRoleService;

    @Autowired
    private IAdminPermissionService aminPermissionService;



   @RequestMapping("query/adminRole")
   @RequiresPermissions("role:queryList")
   public ResponseBase<?> queryAdminRole(){
       ResponseBase<PublicReturnVo>  responseMessage = new ResponseBase<>();
       Page<AdminRole>page = WebUtil.getPage(5);
       PublicReturnVo pageResult  = adminRoleService.queryRoleAll(page);
       responseMessage.setData(pageResult);
       return responseMessage;
    }

    @RequestMapping("query/adminRoleAll")
    public ResponseBase<?> adminRoleAll( ){
        ResponseBase<List<AdminRole>>  responseMessage = new ResponseBase<>();
        List<AdminRole> adminRoleList = adminRoleService.selectRoleAll();

        responseMessage.setData(adminRoleList);
        return responseMessage;
    }

    @PostMapping("update/adminRole")
    @RequiresPermissions("role:edit")
    public ResponseBase<?> updateAdminRole(@Validated(Second.class) RoleVo roleVo, Integer userId){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        logger.info(roleVo.toString());
        adminRoleService.update(roleVo, userId);
        responseMessage.setData("操作成功");
        return responseMessage;
    }

    @GetMapping("del/adminRole")
    @RequiresPermissions("role:del")
    public ResponseBase<?> delAdminRole(Integer roleId){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        int result = adminRoleService.deleteById(roleId);
        responseMessage.setData("");
        return responseMessage;
    }

    @PostMapping("add/adminRole")
    @RequiresPermissions("role:add")
    public ResponseBase<?> addAdminRole(@Validated(First.class)RoleVo roleVo, HttpServletRequest request){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        logger.info(roleVo.toString());
        adminRoleService.insert(roleVo);
        responseMessage.setData("");
        return responseMessage;
    }







}
