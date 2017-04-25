package com.hongchen.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.admin.AdminOperatorLog;
import com.hongchen.entity.admin.AdminPermission;
import com.hongchen.service.admin.IAdminOperatorLogService;
import com.hongchen.service.admin.IAdminPermissionService;
import com.hongchen.util.WebUtil;
import com.hongchen.vo.Pages;
import com.hongchen.vo.PublicReturnVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lichongda on 2017/3/29.
 */
@RestController
@RequestMapping("/operatorLog")
public class AdminOperatorLogController {
    private static final Logger logger = LoggerFactory.getLogger(AdminOperatorLogController.class);
    @Autowired
    private IAdminOperatorLogService adminOperatorLogService;

    @Autowired
    private IAdminPermissionService aminPermissionService;

    @RequestMapping(value = "/query")
    public ResponseBase<?> query(HttpServletRequest request, Integer roleId,  Integer permissionId){
        ResponseBase<PublicReturnVo> responseMessage = new ResponseBase<>();

        PublicReturnVo<List<AdminOperatorLog>> vo = null;
        Page<AdminOperatorLog> page = WebUtil.getPage(5);
        Page pages =  adminOperatorLogService.selectPage(page);
        vo = PublicReturnVo.conversionPublicReturn(pages);
        responseMessage.setData(vo);
        return responseMessage;
    }

    @RequestMapping(value = "/del")
    //@RequiresPermissions("operatorLog:del")//权限管理;
    public ResponseBase<?> del(HttpServletRequest request, Integer id){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        boolean bool = adminOperatorLogService.deleteById(id);
        if(bool){
            return responseMessage.success("删除成功");
        }else {
            return responseMessage.error("删除失败");
        }
    }

}
