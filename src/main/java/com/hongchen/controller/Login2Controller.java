package com.hongchen.controller;

import com.alibaba.fastjson.JSONObject;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.service.admin.IAdminUserService;
import com.hongchen.shiro.StatelessAuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lichongda on 2017/3/29.
 */
@RestController
@RequestMapping("1234")
public class Login2Controller {
    private static final Logger logger = LoggerFactory.getLogger(Login2Controller.class);
    @Autowired
    private IAdminUserService adminUserService;
    @RequestMapping(value = "/login")
    public ResponseBase<?> login(HttpServletRequest request, String userName, String password){

        ResponseBase<String> responseMessage = new ResponseBase<>();
        if(userName == null ){
            return  responseMessage.error( "帐号或密码不能为空");
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password, false);
            SecurityUtils.getSubject().login(token);
            responseMessage.setData("OK");
            SecurityUtils.getSubject().getSession().getId();
            logger.info("SecurityUtils.getSubject().getSession().getId()= " +  JSONObject.toJSONString(SecurityUtils.getSubject().getSession()));
            logger.info("SecurityUtils.getSubject().getSession().getId()= " +SecurityUtils.getSubject().getSession().getId());
            responseMessage.success("登录成功");
        }catch (IncorrectCredentialsException e1){
            return  responseMessage.error( "帐号或密码不正确！");
        }catch(ExcessiveAttemptsException e2){
            return responseMessage.error( "登录次数过多，请稍后重试！");
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
           return responseMessage.error( "登录失败");
        }
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
