package com.hongchen.service.quartz;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hongchen.entity.quartz.QuartzSchedulejob;
import com.hongchen.vo.PublicReturnVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongchen
 * @since 2017-04-13
 */
public interface IQuartzSchedulejobService extends IService<QuartzSchedulejob> {
     boolean insert(QuartzSchedulejob scheduleJob);
     void update(QuartzSchedulejob scheduleJob);
     int delete(Integer scheduleJobId);
     void congeal(Integer scheduleJobId);
     void thaw(Integer scheduleJobId);
     void restart(Integer scheduleJobId);
     void async();
     PublicReturnVo selectQuartzSchedulejobList(Page<QuartzSchedulejob>page, QuartzSchedulejob scheduleJob);
}
