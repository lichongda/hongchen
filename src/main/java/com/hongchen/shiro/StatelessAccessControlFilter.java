package com.hongchen.shiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.hongchen.constants.Constants;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.exception.HongchenException;
import com.hongchen.util.AssertUtil;
import com.hongchen.util.WebUtil;
import com.hongchen.util.json.JsonUtil;
import com.hongchen.util.redis.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 访问控制过滤器
 * @author lichongda --卷土红尘
 * @version v.0.1
 * @date 2017年2月25日
 */
public class StatelessAccessControlFilter extends AccessControlFilter{
    private static final Logger logger = LoggerFactory.getLogger(StatelessAccessControlFilter.class);


    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     *
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     *
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，回往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
           throws Exception {

        String requestURL = getPathWithinApplication(request);//获取url
        if(requestURL.endsWith("favicon.ico")){
            return  true;
        }
       System.out.println("StatelessAuthcFilter.isAccessAllowed()");
       String token = request.getParameter("hongchenToken");
       String userId = request.getParameter("userId");
       boolean isAccessAllowed = false;
       if(StringUtils.isNotEmpty(token) && StringUtils.isNotEmpty(userId)  ){
           String cacheToken = RedisUtil.get("token:hongchen_"+ userId);
           if(StringUtils.isNotEmpty(cacheToken)&& token.equals(cacheToken)){
               isAccessAllowed = true;
           }
       }
     return isAccessAllowed;
    }
 
    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
       System.out.println("StatelessAuthcFilter.onAccessDenied()");
       //1、客户端生成的消息摘要

       String clientDigest = request.getParameter("digest");
        String tokenId = request.getParameter("tokenId");
       //2、客户端传入的用户身份
       String username = request.getParameter("userName");
        String userId = request.getParameter("userId");
       String password = request.getParameter("userPassword");
       String requestURL = getPathWithinApplication(request);//获取url
       Boolean isLogin = false;
        System.out.println( "returnUrl:" + requestURL);
        //3、客户端请求的参数列表
       Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());
       params.remove("digest");//为什么要移除呢？签名或者消息摘要算法的时候不能包含digest.

        logger.info("StatelessAuthenticationToken(username= " + username+ "  clientDigest= "+ clientDigest);
       //4、生成无状态Token
        //用户名，参数，客户端需验证的密码
        try {
           StatelessAuthenticationToken token = new StatelessAuthenticationToken(username,params,clientDigest);
            //4、如当前URL匹配拦截器名字（URL模式）
            if (requestURL.endsWith("login")) {//如为登陆，就只对密码进行加密
                AssertUtil.notEmpty(username,"用户名不能为空.");
                token.setClientDigest(password);
                token.setLogin(true);
            }else {
                AssertUtil.notEmpty(tokenId,"请先登陆获取授权.");
                AssertUtil.notEmpty(userId,"用户ID不能为空.");
                token.setTokenId(tokenId);
                token.setUserName(userId);
                token.setClientDigest(tokenId);
                token.setLogin(false);
            }
           //5、委托给Realm进行登录
          // Subject subject =  SecurityUtils.getSubject();
           Subject subject = getSubject(request, response);
            subject.login(token);
       }catch (HongchenException ex){
            failure(response, ex.getMessage(), 5);
           return false;
       } catch (UnauthorizedException e){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            failure(response, e.getMessage(), httpResponse.SC_UNAUTHORIZED);
            return false;
        }catch (IncorrectCredentialsException e){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            failure(response, "密码错误", httpResponse.SC_UNAUTHORIZED);
        }catch (Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            if(e.getCause() != null ){
                failure(response,e.getCause().getMessage(), Constants.SHIRO_ERROR);
            }else{
                failure(response, e.getMessage(), httpResponse.SC_UNAUTHORIZED);
            }
           e.printStackTrace();
           //6、登录失败
            //登录失败时默认返回401 状态码
           return false;//就直接返回给请求者.
       }
       return true;
    }
   private void failure(ServletResponse response, String message, int code){
       HttpServletResponse httpResponse = (HttpServletResponse) response;
       ResponseBase<String> responseMessage = new ResponseBase<>();
       responseMessage.setCode(code);//错误码
       responseMessage.setError(message);
       WebUtil.responseWrit(httpResponse, responseMessage);
    }
}