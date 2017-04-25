package com.hongchen.util.spring;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.NoSuchMessageException;

/**
 * @ClassName: Springs
 * @Description: 获取spring容器自动初始化的那个Application
 * @author saga
 * @date 2016年7月7日 上午9:57:32
 */
public class Springs implements ApplicationContextAware {
	public static final int AUTOWIRE_NO = 0;
	 
	public static final int AUTOWIRE_BY_NAME = 1;
 
	public static final int AUTOWIRE_BY_TYPE = 2;
 
	public static final int AUTOWIRE_CONSTRUCTOR = 3;
	
	/**
	 * 日志记录器
	 */
	protected static Logger logger = LoggerFactory.getLogger(Springs.class);
    
    private static ApplicationContext applicationContext;
    
    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    public void setApplicationContext(ApplicationContext applicationContext) {

        Springs.applicationContext = applicationContext;
    }
    
    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {

        checkApplicationContext();
        return applicationContext;
    }
    
    /**
     * 动态为某个beanclass 注入spring环境中的bean
     * @param beanClass
     * @param autowireMode
     * @param dependencyCheck
     * @return
     */
    public static Object autowire(Class<?> beanClass,int autowireMode,boolean dependencyCheck ) {
    	return getApplicationContext().getAutowireCapableBeanFactory().autowire(beanClass, autowireMode, dependencyCheck);
    }
    
    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {

        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }
    
    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型. 如果同Class类型配置了多个Bean，只返回第一个
     */
    public static <T> T getBean(Class<T> clazz) {

        checkApplicationContext();
        try {
        return applicationContext.getBean(clazz);
        } catch (Exception ex) {
        	return null;
        }
        
    }
    
    /**
     * 获取一个在SpringContext定义的消息资源的某个消息
     * 
     * @return
     */
    public static String getMessage(String code, String defaultMessage, Locale locale,
        Object... args) {

    	try{
    		return getApplicationContext().getMessage(code, args, defaultMessage, locale);
    	}catch(NoSuchMessageException ex){
    		logger.error(ex.getMessage(), ex);
    	}
    	return code;
    }
    
    /**
     * 获取一个在SpringContext定义的消息资源的某个消息
     * 
     * @return
     */
    public static String getMessage(String code, Locale locale, Object... args) {
    	try{
    		return getApplicationContext().getMessage(code, args, locale);
    	}catch(NoSuchMessageException ex){
    		logger.error(ex.getMessage(), ex);
    	}
    	return code;
    }
    
    public static String getMessage(String code, Object... args) {

        return getMessage(code, Locale.CHINA, args);
    }
    
    
    private static void checkApplicationContext() {

        if (applicationContext == null) {
            throw new IllegalStateException(
                    "applicaitonContext未注入,请在applicationContext.xml中定义Springs");
        }
    }
}
