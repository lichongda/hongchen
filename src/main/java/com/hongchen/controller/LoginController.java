package com.hongchen.controller;

import com.alibaba.fastjson.JSONObject;
import com.hongchen.constants.Constants;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.admin.AdminPermission;
import com.hongchen.entity.admin.AdminRole;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.service.admin.IAdminRoleService;
import com.hongchen.service.admin.IAdminUserService;
import com.hongchen.shiro.MyShiroRealm;
import com.hongchen.shiro.StatelessAuthenticationToken;
import com.hongchen.util.AssertUtil;
import com.hongchen.util.Captcha;
import com.hongchen.util.GifCaptcha;
import com.hongchen.util.redis.RedisUtil;
import com.hongchen.vo.admin.UserCredentials;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lichongda on 2017/3/29.
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private IAdminUserService adminUserService;

    @Autowired
    private IAdminRoleService adminRoleService;
    @RequestMapping(value = "/login")
    public ResponseBase<?> login(HttpServletRequest request, String userName, String password){
        System.out.print("userName= " + userName+ "  passwordssss== " + password);
        ResponseBase<UserCredentials> responseMessage = new ResponseBase<>();
        if(userName == null ){
            return  responseMessage.error( "帐号或密码不能为空aaa");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_name", userName);
        map.put("user_password", password);
        List<AdminUser> userList =  adminUserService.selectByMap(map);
        AssertUtil.notEmpty(userList, "账号或密码错误");
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUserId(userList.get(0).getUserId());
        List<AdminRole> roleList = adminRoleService.selectByUserId(userList.get(0).getUserId());
        if(roleList != null && roleList.size() > 0){
            userCredentials.setRoleId(roleList.get(0).getRoleId());
        }
        String tokenId =  RedisUtil.get(Constants.CACHE_TOKNID+userList.get(0).getUserId());//从缓存获取token
        userCredentials.setTokenId(tokenId);

        responseMessage.setData(userCredentials);
        return responseMessage;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value="/logout")
    public ResponseBase<?>  logout() {
        ResponseBase<String> responseMessage = new ResponseBase<>();
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println("SecurityUtils.getSubject().isAuthenticated()1:" + SecurityUtils.getSubject().isAuthenticated());
        currentUser.logout();
        System.out.println("SecurityUtils.getSubject().isAuthenticated()2:" + SecurityUtils.getSubject().isAuthenticated());
        return responseMessage.success("退出成功");
    }
}
