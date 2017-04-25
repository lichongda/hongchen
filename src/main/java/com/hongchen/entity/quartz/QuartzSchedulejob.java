package com.hongchen.entity.quartz;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author hongchen
 * @since 2017-04-13
 */
@TableName("quartz_schedulejob")
public class QuartzSchedulejob extends Model<QuartzSchedulejob> {

    private static final long serialVersionUID = 1L;
	/** 任务id */
	@TableId(value = "schedule_job_id", type = IdType.AUTO)
	private Integer scheduleJobId;
	/** 任务名称 */
	@TableField("schedule_job_name")
	private String scheduleJobName;

	@TableField("schedule_job_group_id")
	private Integer scheduleJobGroupId;

	/** 任务分组 */
	@TableField(exist = false)
	private QuartzSchedulejobGroup quartzSchedulejobGroup;

	private Integer status;
	/** 任务描述 */
	@TableField("schedule_job_description")
	private String scheduleJobDescription;
	@TableField("create_time")
	private Date createTime;
	/** 任务运行时间表达式 */
	@TableField("schedule_job_cron_expression")
	private String scheduleJobCronExpression;
	@TableField("schedule_job_method")
	private String scheduleJobMethod;
	@TableField("schedule_job_class")
	private String scheduleJobClass;


	public Integer getScheduleJobId() {
		return scheduleJobId;
	}

	public void setScheduleJobId(Integer scheduleJobId) {
		this.scheduleJobId = scheduleJobId;
	}

	public String getScheduleJobName() {
		return scheduleJobName;
	}

	public void setScheduleJobName(String scheduleJobName) {
		this.scheduleJobName = scheduleJobName;
	}

	public Integer getScheduleJobGroupId() {
		return scheduleJobGroupId;
	}

	public void setScheduleJobGroupId(Integer scheduleJobGroupId) {
		this.scheduleJobGroupId = scheduleJobGroupId;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getScheduleJobDescription() {
		return scheduleJobDescription;
	}

	public void setScheduleJobDescription(String scheduleJobDescription) {
		this.scheduleJobDescription = scheduleJobDescription;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getScheduleJobCronExpression() {
		return scheduleJobCronExpression;
	}

	public void setScheduleJobCronExpression(String scheduleJobCronExpression) {
		this.scheduleJobCronExpression = scheduleJobCronExpression;
	}

	public String getScheduleJobMethod() {
		return scheduleJobMethod;
	}

	public void setScheduleJobMethod(String scheduleJobMethod) {
		this.scheduleJobMethod = scheduleJobMethod;
	}

	public String getScheduleJobClass() {
		return scheduleJobClass;
	}

	public void setScheduleJobClass(String scheduleJobClass) {
		this.scheduleJobClass = scheduleJobClass;
	}

	public QuartzSchedulejobGroup getQuartzSchedulejobGroup() {
		return quartzSchedulejobGroup;
	}

	public void setQuartzSchedulejobGroup(QuartzSchedulejobGroup quartzSchedulejobGroup) {
		this.quartzSchedulejobGroup = quartzSchedulejobGroup;
	}

	public QuartzSchedulejob(){

	}

	public Integer getStatus() {
		return status;
	}

	public QuartzSchedulejob(Integer scheduleJobId, Integer status) {
		this.scheduleJobId = scheduleJobId;
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.scheduleJobId;
	}


}
