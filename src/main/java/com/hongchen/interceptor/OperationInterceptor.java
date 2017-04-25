package com.hongchen.interceptor;

import com.alibaba.fastjson.JSON;
import com.hongchen.annotation.NoOperator;
import com.hongchen.annotation.Operator;
import com.hongchen.entity.admin.AdminOperatorLog;
import com.hongchen.service.admin.IAdminOperatorLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 操作拦截
 * Created by lichongda on 2017/3/24.
 */
@Aspect()
@Component
public class OperationInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(OperationInterceptor.class);
    @Autowired
    private IAdminOperatorLogService adminOperatorLogService;
    //execution(* *(..))表示匹配所有方法
    //execution(public * com.hongchen.service.UserService.*(..))  表示匹配com.hongchen.service.UserService中所有的公有方法
    //execution(* com.hongchen.service..*.*(..)) 表示匹配com.hongchen.service包及其子包下的所有方法

    //如果拦截实现类一定要在application.yml中设置 spring:aop: proxy-target-class: true 否则拦截的是接口层
    //@Pointcut("execution(public * com.hongchen.service.impl.*.*(..))")
    @Pointcut("execution(public * com.hongchen.service.impl..*.*(..))")
    public void log() {

    }

/*    @Before("log()")
    public void  doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}", joinPoint.getArgs());
    }*/
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Object object = joinPoint.proceed();//环绕通知 执行目标方法
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        System.out.println("classname:" + method.getDeclaringClass().getName());
        System.out.println("isinterface:" + method.getDeclaringClass().isInterface());
        System.out.println("target:" + joinPoint.getTarget().getClass().getName());
        System.out.println("proxy:" + joinPoint.getThis().getClass().getName());
        System.out.println("method:" + method.getName());
        System.out.println(method.isAnnotationPresent(Operator.class));
        System.out.println(signature.getClass().isAnnotationPresent(NoOperator.class));
        //判断是否有自定义注解Operator
        if(method.isAnnotationPresent(Operator.class)&&!signature.getClass().isAnnotationPresent(NoOperator.class)){
            List<Object> lists = new ArrayList<Object>();
            for(Object object2 : joinPoint.getArgs()){//获取参数
                Class<?> executeClass = object2.getClass();
                if(executeClass.isAnnotationPresent(Operator.class)){//判断是否有自定义注解Operator
                    //lists.add(jsonUtil.toJson(object2));
                    lists.add(object2);//把参数值保存起来
                }else{
                    lists.add(object2);
                }
            }
            AdminOperatorLog _operator = new AdminOperatorLog();
            _operator.setCreateTime(new Date());
           // _operator.setAfterContent(jsonUtil.buildList(lists.size(), lists, null));
            _operator.setAfterContent(JSON.toJSONString(lists));
            if(method.getAnnotation(Operator.class).story().indexOf(":")>-1){//判断方法里面是否有自定义注解Operator story
                String story = method.getAnnotation(Operator.class).story();
                Operator cc = joinPoint.getTarget().getClass().getAnnotation(Operator.class);
                if(null!=cc){
                    String name = cc.name();
                    _operator.setStory(story.replace(":", name));
                }else{
                    _operator.setStory(story);
                }
            }else{
                _operator.setStory(method.getAnnotation(Operator.class).story());
            }
            _operator.setUserId(1);
            _operator.setUserName("李崇达");
            adminOperatorLogService.insert(_operator);
            logger.info("logger" +_operator.toString());
        }
        return object;
    }

   /* @After("log()")
    public void doAfter() {
        System.out.println();
    }*/

  /*  @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
    }*/
}
