package com.hongchen.shiro;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 用于授权的Token对象：
 *
 * 用户身份即用户名；
 * 凭证即客户端传入的消息摘要。
 * @author lichongda --卷土红尘
 * @version v.0.1
 * @date 2017年2月25日
 */
public class StatelessAuthenticationToken implements AuthenticationToken{
    private static final long serialVersionUID = 1L;
    private String userName;//用户身份即用户名
    private Map<String,?> params;//参数.
    private String clientDigest;//凭证即客户端传入的消息摘要。
    private String tokenId;//加密
    private Boolean isLogin;  //是否登陆

    public StatelessAuthenticationToken() {
    }
    public StatelessAuthenticationToken(String userName, Map<String, ?> params, String clientDigest, Boolean isLogin) {
        super();
        this.userName = userName;
        this.params = params;
        this.clientDigest = clientDigest;
        this.isLogin = isLogin;
    }

    public StatelessAuthenticationToken(String userName,  String tokenId, Boolean isLogin) {
        super();
        this.userName = userName;
        this.tokenId = tokenId;
        this.isLogin = isLogin;
    }

    public StatelessAuthenticationToken(String userName, Map<String, ?> params, String clientDigest) {
        super();
        this.userName = userName;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    public StatelessAuthenticationToken(String userName, String clientDigest) {
        super();
        this.userName = userName;
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }
}
