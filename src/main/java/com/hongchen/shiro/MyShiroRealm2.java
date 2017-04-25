package com.hongchen.shiro;

import com.hongchen.constants.Constants;
import com.hongchen.entity.admin.AdminPermission;
import com.hongchen.entity.admin.AdminRole;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.exception.HongchenException;
import com.hongchen.service.admin.IAdminPermissionService;
import com.hongchen.service.admin.IAdminRoleService;
import com.hongchen.service.admin.IAdminUserService;
import com.hongchen.util.AssertUtil;
import com.hongchen.util.TokenUtil;
import com.hongchen.util.redis.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
/**
 * Created by lichongda on 2017/3/29.
 */
//public class MyShiroRealm2 extends AuthorizingRealm {
    public class MyShiroRealm2 extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm2.class);

    @Autowired
    private IAdminUserService adminUserService;
    @Autowired
    private IAdminRoleService adminRoleService;
    @Autowired
    private IAdminPermissionService adminPermissionService;

    public IAdminPermissionService getAdminPermissionService() {
        return adminPermissionService;
    }

    /**
     * 仅支持StatelessToken 类型的Token，
     * 那么如果在StatelessAuthcFilter类中返回的是UsernamePasswordToken，那么将会报如下错误信息：
     * Please ensure that the appropriate Realm implementation is configured correctly or
     * that the realm accepts AuthenticationTokens of this type.StatelessAuthcFilter.isAccessAllowed()
     */
    //@Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    //得到密钥，此处硬编码一个.
    private String getKey(String userName) {
        return "andy123456";
    }

    /**
     * 认证信息.(身份验证)
     * :
     * Authentication 是用来验证用户身份
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        StatelessAuthenticationToken token = (StatelessAuthenticationToken)authcToken;
        Boolean isLogin = token.getLogin();
        Map<String, Object> map = new HashMap<String, Object>();
        if(token.getLogin()){//登陆
            map.put("user_name", token.getUserName());
        }else{
            map.put("user_id", token.getUserName());
        }

        String username = (String)token.getPrincipal();//不能为null,否则会报错的.
        //根据用户名获取密钥（和客户端的一样）
        String key = getKey(username);
        logger.info("key= " + key);

        //在服务器端生成客户端参数消息摘要
        String serverDigest;

      ;
        //获取用户的输入的账号.
       // String userName = (String)token.getPrincipal();
       // System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        List<AdminUser> userList =  adminUserService.selectByMap(map);
        AdminUser adminUser = null;
        if (userList.size() != 0) {
            adminUser = userList.get(0);
        }
        if (null == adminUser) {
            throw new HongchenException("帐号不存在！");
            //} else if (adminUser.getStatus() == 0) {
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            //throw new DisabledAccountException("帐号已经禁止登录！");
        } //else {
            //更新登录时间 last login time
           // user.setLastLoginTime(new Date());
           // adminUserService.updateById(user);
        //}

        if(isLogin){//登陆
            serverDigest = adminUser.getUserPassword();
            String tokenId =  RedisUtil.get(Constants.CACHE_TOKNID+adminUser.getUserId());//从缓存获取token
            if(StringUtils.isNotEmpty(tokenId)){
                RedisUtil.setMinutes(Constants.CACHE_TOKNID+adminUser.getUserId(), tokenId, Constants.CACHE_TOKENID_TIME);//设置Token
            }else{
                RedisUtil.setMinutes(Constants.CACHE_TOKNID+adminUser.getUserId(),  TokenUtil.getInstance().makeToken(), Constants.CACHE_TOKENID_TIME);//设置Token
            }

//            ValueOperations<String, AdminUser> operation = redisTemplate.opsForValue();
//            operation.set(Constants.CACHE_USER_ID+adminUser.getUserId(), adminUser, Constants.CACHE_TOKENID_TIME, TimeUnit.MINUTES);//缓存用户信息
        }else{
           String tokenId =  RedisUtil.get(Constants.CACHE_TOKNID+adminUser.getUserId());//从缓存获取token
           AssertUtil.notEmpty(tokenId, "需重新登陆获取");
            logger.info("tokenId= " + tokenId+ "    token.getTokenId()=" + token.getTokenId());
           if(!tokenId.equals(token.getTokenId())){
               AssertUtil.isFalse(true, "无效的token");
           }
            //serverDigest =  HmacSHA256Utils.digest(key, token.getParams());
            serverDigest = token.getTokenId();//先暂时不加密
        }

        logger.info(serverDigest+","+token.getCredentials());
        logger.info("username= " + username+ "    serverDigest=" + serverDigest);

        /*
        * 获取权限信息:这里没有进行实现，
        * 请自行根据UserInfo,Role,Permission进行实现；
        * 获取之后可以在前端for循环显示所有链接;
        */
        //userInfo.setPermissions(userService.findPermissions(user));

        //账号判断;
        //加密方式;
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                adminUser.getUserId(), //用户名
                serverDigest, //密码
               // ByteSource.Util.bytes("admin"),
              // ByteSource.Util.bytes(adminUser.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );

        //明文: 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
           /*   SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                   userInfo, //用户名
                   userInfo.getPassword(), //密码
                     getName()  //realm name
              );*/
       // return authenticationInfo;
        return authenticationInfo;
    }

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
        logger.info("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        //根据用户名查找角色，请根据需求实现
       // String userName = (String) principals.getPrimaryPrincipal();
        //AdminUser adminUser = (AdminUser) principals.getPrimaryPrincipal();
        Integer userId = (Integer) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo  authorizationInfo  = new SimpleAuthorizationInfo();
       // AdminUser token = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        //Integer userId = token.getUserId();
        //根据用户名查找角色，请根据需求实现
       // String userName = token.getUserName();
        logger.info("userName= " + userId);
       // UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        //根据用户ID查询角色（role），放入到Authorization里。
        List<AdminRole> roleList = adminRoleService.selectByUserId(userId);
        if(roleList != null && roleList.size() > 0){

            Set<String> roleSet = new HashSet<String>();
            for (AdminRole role : roleList) {
                roleSet.add(role.getRoleName());
            }
            authorizationInfo.setRoles(roleSet);



        }
        List<AdminPermission> permissionList = null;
        if(roleList.get(0).getRoleId() == Constants.SUPER_ADMINISTRATOR){//超级管理员
            permissionList = adminPermissionService.selectList(null);
        }else{
            //根据用户ID查询权限（permission），放入到Authorization里。
            permissionList = adminPermissionService.selectByUserId(userId);
        }


        Set<String> permissionSet = new HashSet<String>();
        for (AdminPermission permission : permissionList) {
            permissionSet.add(permission.getPermissionResource());
        }

        authorizationInfo.setStringPermissions(permissionSet);

        return authorizationInfo;
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        logger.info(" 清除授权的缓存");
        Cache cache = this.getAuthorizationCache();
        Set<Object>keys = cache.keys();
        for(Object key: keys){
            logger.info(" 授权缓存:"+ key+"--------------"+ cache.get(key)+"---------------------");
        }
        super.clearCachedAuthorizationInfo(principals);
   }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        logger.info(" 清除认证的缓存");
        Cache cache = this.getAuthenticationCache();
        Set<Object>keys = cache.keys();
        for(Object key: keys) {
            logger.info("认证缓存:" + key + "--------------" + cache.get(key) + "---------------------");
        }
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }



    /*
     * 清楚缓存中  当前用户的权限信息
     *
     */
    public void clearCachedAuthorizationInfo(Integer userId) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(userId, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    public static void main(String[] args) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(null, null);


    }
}
