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
@TableName("quartz_schedulejob_group")
public class QuartzSchedulejobGroup extends Model<QuartzSchedulejobGroup> {

    private static final long serialVersionUID = 1L;

	@TableId(value = "schedule_job_groupId", type = IdType.AUTO)
	private Integer scheduleJobGroupId;
	@TableField("schedule_job_group_name")
	private String scheduleJobGroupName;
	@TableField("schedule_job_group_description")
	private String scheduleJobGroupDescription;
	private Integer status;
	@TableField("create_time")
	private Date createTime;


	public Integer getScheduleJobGroupId() {
		return scheduleJobGroupId;
	}

	public void setScheduleJobGroupId(Integer scheduleJobGroupId) {
		this.scheduleJobGroupId = scheduleJobGroupId;
	}

	public String getScheduleJobGroupName() {
		return scheduleJobGroupName;
	}

	public void setScheduleJobGroupName(String scheduleJobGroupName) {
		this.scheduleJobGroupName = scheduleJobGroupName;
	}

	public String getScheduleJobGroupDescription() {
		return scheduleJobGroupDescription;
	}

	public void setScheduleJobGroupDescription(String scheduleJobGroupDescription) {
		this.scheduleJobGroupDescription = scheduleJobGroupDescription;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public QuartzSchedulejobGroup(String scheduleJobGroupName){
		this.scheduleJobGroupName = scheduleJobGroupName;
	}

	public QuartzSchedulejobGroup(){

	}

	@Override
	protected Serializable pkVal() {
		return this.scheduleJobGroupId;
	}

}
