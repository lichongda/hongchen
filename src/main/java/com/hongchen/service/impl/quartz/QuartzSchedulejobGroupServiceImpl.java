package com.hongchen.service.impl.quartz;

import com.hongchen.annotation.Operator;
import com.hongchen.dao.quartz.QuartzSchedulejobGroupMapper;
import com.hongchen.dao.quartz.QuartzSchedulejobMapper;
import com.hongchen.entity.quartz.QuartzSchedulejob;
import com.hongchen.entity.quartz.QuartzSchedulejobGroup;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongchen.enums.StatusType;
import com.hongchen.exception.HongchenException;
import com.hongchen.quartz.SchedulerFactory;
import com.hongchen.service.quartz.IQuartzSchedulejobGroupService;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongchen
 * @since 2017-04-13
 */
@Service
public class QuartzSchedulejobGroupServiceImpl extends ServiceImpl<QuartzSchedulejobGroupMapper, QuartzSchedulejobGroup> implements IQuartzSchedulejobGroupService {
    @Autowired
    private QuartzSchedulejobMapper quartzSchedulejobMapper;

    @Autowired
    private Scheduler scheduler;
    @Override
    @Transactional
    @Operator(story="停止定时器分组")
    public void congeal(Integer scheduleJobGroupId) {
        QuartzSchedulejobGroup quartzSchedulejobGroup = baseMapper.selectById(scheduleJobGroupId);
        QuartzSchedulejob scheduleJob = new QuartzSchedulejob();
        scheduleJob.setQuartzSchedulejobGroup(quartzSchedulejobGroup);
        List<QuartzSchedulejob> list = (List<QuartzSchedulejob>) quartzSchedulejobMapper.selectQuartzSchedulejobGroupId(quartzSchedulejobGroup.getScheduleJobGroupId());
        for(QuartzSchedulejob scheduleJob2 : list){//把定时器分组下的所有定时器都停止掉
            scheduleJob2.setQuartzSchedulejobGroup(quartzSchedulejobGroup);
            SchedulerFactory.stop(scheduleJob2, scheduler);
            scheduleJob2.setStatus(StatusType.CONGEAL.getValue());//1冻结 0 启动 2 删除
            quartzSchedulejobMapper.updateById(scheduleJob2);//冻结


        }
        quartzSchedulejobGroup.setStatus(StatusType.CONGEAL.getValue());//冻结
        baseMapper.updateById(quartzSchedulejobGroup);
    }

    @Override
    @Transactional
    @Operator(story="启动定时器分组")
    public void start(Integer scheduleJobGroupId) {
        QuartzSchedulejobGroup quartzSchedulejobGroup = baseMapper.selectById(scheduleJobGroupId);
        QuartzSchedulejob scheduleJob = new QuartzSchedulejob();
        scheduleJob.setQuartzSchedulejobGroup(quartzSchedulejobGroup);
        List<QuartzSchedulejob> list = (List<QuartzSchedulejob>) quartzSchedulejobMapper.selectQuartzSchedulejobGroupId(quartzSchedulejobGroup.getScheduleJobGroupId());
        for(QuartzSchedulejob scheduleJob2 : list){//把定时器分组下的所有定时器都停止掉
            SchedulerFactory.add(scheduleJob2,true, scheduler);//添加到执行队列中
            scheduleJob2.setStatus(StatusType.NORMAL.getValue());//1冻结 0 启动 2 删除
            quartzSchedulejobMapper.updateById(scheduleJob2);//启动
        }
        quartzSchedulejobGroup.setStatus(StatusType.NORMAL.getValue());//启动
        baseMapper.updateById(quartzSchedulejobGroup);
    }

    @Override
    public boolean deleteById(Integer scheduleJobGroupId) {
        List<QuartzSchedulejob> list = (List<QuartzSchedulejob>) quartzSchedulejobMapper.selectQuartzSchedulejobGroupId(scheduleJobGroupId);
        if(list != null && list.size() > 0){
            throw new HongchenException("请先删除该分组下面的任务.");
        }
        return true;
    }
}
