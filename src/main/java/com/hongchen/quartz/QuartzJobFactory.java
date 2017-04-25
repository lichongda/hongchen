package com.hongchen.quartz;
import com.hongchen.entity.quartz.QuartzSchedulejob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @Version:			[ v1.0 ]
 * @Description:   		[ 定时器执行类 ]
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job{
	
	private static final Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);
	private static Map<String, Class<?>> maps = new HashMap<String, Class<?>>();
	
	
	@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
		QuartzSchedulejob scheduleJob = (QuartzSchedulejob)context.getMergedJobDataMap().get("scheduleJob");
		logger.info("执行任务："+scheduleJob.getScheduleJobName());
        try {
        	String className = scheduleJob.getScheduleJobClass();
			Class<?> exec = null;
			if(maps.containsKey(className)){
				exec = maps.get(className);
			}else{
				ClassLoader clazz = Thread.currentThread().getContextClassLoader();
	        	if(null==clazz)clazz = QuartzJobFactory.class.getClassLoader();
				exec=clazz.loadClass(className);
				maps.put(className, exec);
			}
			try {
				Method method = exec.getMethod(scheduleJob.getScheduleJobMethod());
				method.invoke(exec.newInstance());
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
}
