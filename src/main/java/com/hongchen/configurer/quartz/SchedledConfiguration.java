package com.hongchen.configurer.quartz;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * Created by lichongda on 2017/4/14.
 */
@Configuration
public class  SchedledConfiguration {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SpringJobFactory springJobFactory;
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //schedulerFactoryBean.setJobFactory(springJobFactory);
        //System.out.println("schedulerFactoryBean=" + schedulerFactoryBean);
        //Scheduler scheduler = schedulerFactoryBean.getScheduler();
        //System.out.println("scheduler2=" + scheduler);

        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        System.out.println("scheduler1=" + schedulerFactoryBean().getScheduler());
        return schedulerFactoryBean().getScheduler();
    }


}
