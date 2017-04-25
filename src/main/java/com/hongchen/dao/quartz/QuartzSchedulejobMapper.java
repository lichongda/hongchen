package com.hongchen.dao.quartz;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.entity.quartz.QuartzSchedulejob;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hongchen
 * @since 2017-04-13
 */
public interface QuartzSchedulejobMapper extends BaseMapper<QuartzSchedulejob> {
    List<QuartzSchedulejob> selectQuartzSchedulejobGroupId(Integer scheduleJobGroupId);
    List<QuartzSchedulejob> selectQuartzSchedulejobList(Page<QuartzSchedulejob>page, QuartzSchedulejob schedulejob);
    List<QuartzSchedulejob> selectQuartzSchedulejobAll();
}