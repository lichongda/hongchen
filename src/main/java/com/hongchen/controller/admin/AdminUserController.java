package com.hongchen.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.admin.AdminPermission;
import com.hongchen.entity.admin.AdminRole;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.service.LogService;
import com.hongchen.service.admin.IAdminPermissionService;
import com.hongchen.service.admin.IAdminRoleService;
import com.hongchen.service.admin.IAdminUserService;
import com.hongchen.util.AssertUtil;
import com.hongchen.util.WebUtil;
import com.hongchen.validator.First;
import com.hongchen.validator.Second;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.UserVo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lichongda on 2017/3/24.
 */
@RestController
@RequestMapping("/admin/")
public class AdminUserController {
    @Autowired
    private IAdminUserService adminUserService;

    @Autowired
    private IAdminPermissionService aminPermissionService;

    @Autowired
    private IAdminRoleService adminRoleService;

    @Resource
   private CredentialsMatcher credentialsMatcher;

   @RequestMapping("add/adminUser")
   @RequiresPermissions("user:add")
   public ResponseBase<?> addAdminUser(@Validated(First.class)UserVo userVo){
        ResponseBase<String> responseMessage = new ResponseBase<>();

        int bool = adminUserService.insert(userVo);
        if(bool !=0 ){
           return responseMessage.success("成功");
        }else {
            return  responseMessage.error("失败");
        }
    }

    public static void main(String[] args) {
        Md5Hash md5Hash=new Md5Hash("123456", "lichongda",2);
        System.out.println(md5Hash.toString());
    }

    @GetMapping("query/adminUserId")
    public ResponseBase<?> queryAdminUserId(Integer usersId){
        ResponseBase<HashMap> responseMessage = new ResponseBase<>();
        HashMap<String, Object> map = new HashMap<String, Object> ();
        AdminUser adminUser = adminUserService.selectById(usersId);
        List<AdminRole> adminRoleList =  adminRoleService.selectRoleAll();
        map.put("adminRoleList", adminRoleList);
        map.put("adminUser", adminUser);
        responseMessage.setData(map);
        return responseMessage;
    }

    @PostMapping("update/adminUser")
    @RequiresPermissions("user:edit")
    public ResponseBase<?> updateAdminUser(@Validated(Second.class)UserVo userVo){
        ResponseBase<Integer> responseMessage = new ResponseBase<>();
        int result = adminUserService.update(userVo);
        responseMessage.setData(result);
        return responseMessage;
    }

    @GetMapping("del/adminUser")
    @RequiresPermissions("user:del")
    public ResponseBase<?> delAdminUser(Integer usersId){
        AssertUtil.notEmpty(usersId, "需重新登陆获取");
        ResponseBase<Integer> responseMessage = new ResponseBase<>();
        int reuslt = adminUserService.delete(usersId);
        responseMessage.setData(reuslt);
        return responseMessage;
    }

    @GetMapping("test")
    public ResponseBase<?> test(){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        responseMessage.setData("dddd");
        return responseMessage;
    }

    @GetMapping("query/adminUser")
    @RequiresPermissions("user:query")
    public ResponseBase<?> queryAdminUser(AdminUser adminuser){
        ResponseBase<PublicReturnVo> responseMessage = new ResponseBase<>();
        Page<AdminUser>page = WebUtil.getPage(5);
        PublicReturnVo pageResult =  adminUserService.selectAdminUserAll(page);
        responseMessage.setData(pageResult);
        return responseMessage;
    }




}
