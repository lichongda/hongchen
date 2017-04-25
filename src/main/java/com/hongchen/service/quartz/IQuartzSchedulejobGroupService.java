package com.hongchen.service.quartz;

import com.baomidou.mybatisplus.service.IService;
import com.hongchen.entity.quartz.QuartzSchedulejobGroup;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongchen
 * @since 2017-04-13
 */
public interface IQuartzSchedulejobGroupService extends IService<QuartzSchedulejobGroup> {
    //停止定时器分组
     void congeal(Integer scheduleJobGroupId);

    //启动定时器分组
    void start(Integer scheduleJobGroupId);

    boolean deleteById(Integer scheduleJobGroupId);
}
