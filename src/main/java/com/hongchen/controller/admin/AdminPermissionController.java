package com.hongchen.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.admin.AdminPermission;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.service.admin.IAdminPermissionService;
import com.hongchen.util.AssertUtil;
import com.hongchen.util.WebUtil;
import com.hongchen.validator.First;
import com.hongchen.validator.Second;
import com.hongchen.vo.PublicReturnVo;
import com.hongchen.vo.admin.PermissionVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lichongda on 2017/4/5.
 */
@RestController
@RequestMapping("/adminPermission/")
public class AdminPermissionController {
    private static final Logger logger = LoggerFactory.getLogger(AdminPermissionController.class);
    @Autowired
    private IAdminPermissionService aminPermissionService;
    //查询所有的权限以及相关用户的角色资源
    @RequestMapping({"/queryAdminPermission"})
    public ResponseBase<?> queryAdminPermissionAll(@RequestParam(value="roleId", required = false, defaultValue = "0") Integer roleId){
        logger.info("roleId= " + roleId);
        ResponseBase<HashMap> responseMessage = new ResponseBase<>();
        List<AdminPermission> permissionListAll =  aminPermissionService.selectPermissionAll();
        List<AdminPermission> permissionListUser =  null;
        if(roleId != 0){
            permissionListUser = aminPermissionService.selectPermissionRoseId(roleId);
        }
        HashMap<String,  List<AdminPermission>> map = new HashMap<String,  List<AdminPermission>> ();
        map.put("allList", permissionListAll);
        map.put("userList", permissionListUser);

        responseMessage.setData(map);
        return responseMessage;
    }

    @GetMapping("refresh/adminPermission")
    public ResponseBase<?>  refreshaAminPermission(Integer roleId,  Integer permissionId){
        ResponseBase<HashMap>  responseMessage = new ResponseBase<>();
        List<AdminPermission> permissionListRole =  aminPermissionService.refreshaAminPermission(roleId, permissionId);
        HashMap<String,  List<AdminPermission>> map = new HashMap<String,  List<AdminPermission>> ();
        map.put("roleList", permissionListRole);
        responseMessage.setData(map);
        return responseMessage;
    }

    //查询列表
    @RequestMapping({"/query/adminPermissionList"})
    @RequiresPermissions("menu:query")
    public ResponseBase<?> adminPermissionList(){
        ResponseBase<PublicReturnVo> responseMessage = new ResponseBase<>();
        Page<AdminPermission> page = WebUtil.getPage(5);
        PublicReturnVo  publicReturnVo =  aminPermissionService.selectPermissionPageParentId(page,1);

        responseMessage.setData(publicReturnVo);
        return responseMessage;
    }

    @GetMapping("query/adminPermissionButton")
    public ResponseBase<?>  queryPermissionButton(Integer roleId,  Integer permissionId){
        ResponseBase<List<AdminPermission>> responseMessage = new ResponseBase<>();
        //是否显示按钮
        List<AdminPermission> permissionButtons =  aminPermissionService.refreshaAminPermission(roleId, permissionId);
        responseMessage.setData(permissionButtons);
        return responseMessage;
    }



    //查询菜单，不传值默认为父菜单
    @RequestMapping({"/query/parentPermission"})
    public ResponseBase<?> queryParentPermission(@RequestParam(value="permissionId", required = false, defaultValue = "1") Integer permissionId){
        logger.info("permissionId= " + permissionId);
        ResponseBase<List<AdminPermission>> responseMessage = new ResponseBase<>();
        List<AdminPermission>  permissionList =  aminPermissionService.selectPermissionParentId(permissionId);
        responseMessage.setData(permissionList);
        return responseMessage;
    }

    //查询详情
    @RequestMapping({"/query/queryPermissionInfo"})
    public ResponseBase<?> queryPermissionInfo(Integer permissionId){
        logger.info("permissionId= " + permissionId);
        HashMap<String, Object> map = new HashMap<String, Object> ();
        ResponseBase<HashMap> responseMessage = new ResponseBase<>();
        AdminPermission adminPermission =  aminPermissionService.selectById(permissionId);

        List<AdminPermission>  parentPermissionList =  aminPermissionService.selectPermissionParentId(1);
        List<AdminPermission>  subPermissionList = null;
        if(adminPermission != null && adminPermission.getPermissionParentId() != 1 && adminPermission.getPermissionTypeId() == 1){//不等于父菜单也不等于按钮,初始化子菜单
            subPermissionList = aminPermissionService.querySubPermission(adminPermission.getPermissionParentId());
        }else if(adminPermission != null && adminPermission.getPermissionTypeId() == 2){//等于按钮
            AdminPermission parentPermission= aminPermissionService.selectById(adminPermission.getPermissionParentId());
            if(parentPermission != null){
                subPermissionList = aminPermissionService.querySubPermission(parentPermission.getPermissionParentId());
            }
        }

        map.put("adminPermission", adminPermission);
        map.put("parentPermissionList", parentPermissionList);
        map.put("subPermissionList", subPermissionList);
        responseMessage.setData(map);
        return responseMessage;
    }

    //查询子菜单
    @RequestMapping({"/query/subPermission"})
    public ResponseBase<?> querySubPermission(Integer roleId, Integer permissionId){
        ResponseBase<List<AdminPermission>> responseMessage = new ResponseBase<>();
        List<AdminPermission>  permissionList = aminPermissionService.querySubPermission(permissionId);
        responseMessage.setData(permissionList);
        return responseMessage;
    }
    @RequestMapping({"/add/adminPermission"})
    @RequiresPermissions("menu:add")
    public ResponseBase<?>addAdminPermission(@Validated(First.class) PermissionVo permissionVo, HttpServletRequest request){
        String roleId = request.getParameter("roleId");
        AssertUtil.notEmpty(roleId, "角色ID不能为空");
        ResponseBase<String> responseMessage = new ResponseBase<>();
        aminPermissionService.insert(permissionVo,Integer.parseInt(roleId));
        responseMessage.setData("成功");
        return responseMessage;
    }

    @RequestMapping({"/edit/adminPermission"})
    @RequiresPermissions("menu:edit")
    public ResponseBase<?>editAdminPermission(@Validated(Second.class) PermissionVo permissionVo){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        aminPermissionService.update(permissionVo);
        responseMessage.setData("成功");
        return responseMessage;
    }

    @RequestMapping({"/del/adminPermission"})
    @RequiresPermissions("menu:del")
    public ResponseBase<?>delAdminPermission(Integer permissionId){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        int result = aminPermissionService.delete(permissionId);
        if(result != 0){
            return  responseMessage.success("删除成功");
        }else {
            return  responseMessage.error("请先删除子菜单");
        }
    }

}
