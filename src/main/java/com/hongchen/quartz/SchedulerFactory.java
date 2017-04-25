package com.hongchen.quartz;

import com.hongchen.entity.quartz.QuartzSchedulejob;
import com.hongchen.entity.quartz.QuartzSchedulejobGroup;
import com.hongchen.enums.StatusType;
import com.hongchen.exception.HongchenException;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @Version:			[ v1.0 ]
 * @Description:   		[ 定时器工场类 ]
 */
@Component
public class SchedulerFactory {
	private static  Logger logger =  LoggerFactory.getLogger(SchedulerFactory.class);
	//@Autowired
	//private  SchedulerFactoryBean schedulerFactoryBean ;


	public static CronScheduleBuilder verifyTrigger(QuartzSchedulejob scheduleJob){
		CronScheduleBuilder scheduleBuilder= null;
		try {
			scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getScheduleJobCronExpression());
		} catch (Exception e) {
			throw new HongchenException("表达式不正确,请重新输入.");
		}
		return scheduleBuilder;
	}
	
	public static List<QuartzSchedulejob> list(Scheduler scheduler) {
		List<QuartzSchedulejob> jobList = new ArrayList<QuartzSchedulejob>();
		try {
			//Scheduler scheduler = schedulerFactoryBean.getScheduler();
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					QuartzSchedulejob scheduleJob = new QuartzSchedulejob();
					scheduleJob.setScheduleJobId(Integer.parseInt(jobKey.getName()));
					//scheduleJob.setScheduleJobGroup(new ScheduleJobGroup(jobKey.getGroup()));

					scheduleJob.setQuartzSchedulejobGroup(new QuartzSchedulejobGroup(jobKey.getGroup()));
					scheduleJob.setScheduleJobDescription(String.valueOf(trigger.getKey()));
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					scheduleJob.setStatus(triggerState.name().equals("NORMAL")? StatusType.NORMAL.getValue():StatusType.CONGEAL.getValue());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						scheduleJob.setScheduleJobCronExpression(cronExpression);
					}
					jobList.add(scheduleJob);
				}
			}
		} catch (Exception e) {
			logger.error("error",e);
			throw new HongchenException("获取失败");
		}
		return jobList;
	}
	
	public synchronized static void add(QuartzSchedulejob scheduleJob, boolean start, Scheduler scheduler){
		//Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(String.valueOf(scheduleJob.getScheduleJobId()), String.valueOf(scheduleJob.getQuartzSchedulejobGroup().getScheduleJobGroupId()));//组合名称（定时器名称+分组名称）
		
		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger=null;
		try {
			trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e) {
			logger.error("error",e);
			throw new HongchenException("表达式不正确,请重新输入.");
		}
		// 不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
					.withIdentity(String.valueOf(scheduleJob.getScheduleJobId()), String.valueOf(scheduleJob.getQuartzSchedulejobGroup().getScheduleJobGroupId())).build();
			jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
			
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder= verifyTrigger(scheduleJob);
			
			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger().withIdentity(String.valueOf(scheduleJob.getScheduleJobId()), scheduleJob.getQuartzSchedulejobGroup().getScheduleJobGroupId().toString())
					.withSchedule(scheduleBuilder).build();
			
			try {
				scheduler.scheduleJob(jobDetail, trigger);
				if(!start){
					stop(scheduleJob, scheduler);
				}
			} catch (SchedulerException e) {
				logger.error("error",e);
				throw new HongchenException(e.getMessage());
			}
		} else {
			// Trigger已存在，那么更新相应的定时设置
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = verifyTrigger(scheduleJob);
			
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			try {
				scheduler.rescheduleJob(triggerKey, trigger);
				if(!start){
					stop(scheduleJob, scheduler);
				}
			} catch (SchedulerException e) {
				logger.error("error",e);
				throw new HongchenException("trigger执行失败.");
			}
		}
	}
	
	public static void stop(QuartzSchedulejob scheduleJob, Scheduler scheduler) {
		try {
			//Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(String.valueOf(scheduleJob.getScheduleJobId()), String.valueOf(scheduleJob.getQuartzSchedulejobGroup().getScheduleJobGroupId()));
			scheduler.pauseJob(jobKey);
		} catch (Exception e) {
			logger.error("error",e);
			throw new HongchenException("定时器停止失败.");
		}
	}
	public static void reStart(QuartzSchedulejob scheduleJob, Scheduler scheduler){
		try {
			//Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(String.valueOf(scheduleJob.getScheduleJobId()), String.valueOf(scheduleJob.getQuartzSchedulejobGroup().getScheduleJobGroupId()));
			scheduler.resumeJob(jobKey);
		} catch (Exception e) {
			logger.error("error",e);
			throw new HongchenException("定时器重启失败.");
		}
	}
	
	public static void del(QuartzSchedulejob scheduleJob, Scheduler scheduler) {
		try {
			//Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(String.valueOf(scheduleJob.getScheduleJobId()), String.valueOf(scheduleJob.getQuartzSchedulejobGroup().getScheduleJobGroupId()));
			scheduler.deleteJob(jobKey);
		} catch (Exception e) {
			logger.error("error",e);
			throw new HongchenException("定时器删除失败.");
		}
		
	}
}
