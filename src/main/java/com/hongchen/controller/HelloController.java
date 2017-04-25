package com.hongchen.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HelloController {
   
    @RequestMapping("/hello")
    public String hello(String params1,String params2){
       return "hello,Andy,params1="+params1+",params1="+params2;
    }

    /**
     * 此方法执行的时候，会抛出异常：
     * Session creation has been disabled for the current subject.
     * @return
     */
    @RequestMapping("/hello3")
    public String hello3(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        System.out.println(session);
        return"hello3,Andy";
    }

    @RequestMapping("/hello4")
    //@RequiresRoles("admin")
    @RequiresPermissions("user:add")//权限管理;
    public String hello4(){
        return "hello4 userInfo:add";
    }

    @RequestMapping("/hello5")
    @RequiresPermissions("user:query")//权限管理;
    public String hello5(){
        return "hello5 userInfo:query";
    }
    @RequestMapping("/hello6")
    @RequiresPermissions("user:del")//权限管理;
    public String hello6(){
        return "hello6 userInfo:add";
    }
    @RequestMapping("/hello7")
    @RequiresPermissions("userInfo:addi")//权限管理;
    public String hello7(){
        return "hello7 userInfo:del";
    }

}