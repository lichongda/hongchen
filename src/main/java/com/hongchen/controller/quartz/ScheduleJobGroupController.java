package com.hongchen.controller.quartz;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.constants.Constants;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.quartz.QuartzSchedulejobGroup;
import com.hongchen.enums.StatusType;
import com.hongchen.service.quartz.IQuartzSchedulejobGroupService;
import com.hongchen.util.WebUtil;
import com.hongchen.vo.PublicReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value="quartz/")
public class ScheduleJobGroupController{
	@Autowired
	private IQuartzSchedulejobGroupService schedulejobGroupService;

	@PostMapping("add/quartz/schedulejobGroup")
	public ResponseBase<?> addScheduleJobGroup(QuartzSchedulejobGroup schedulejobGroup){
		ResponseBase<String> responseMessage = new ResponseBase<>();
		schedulejobGroup.setCreateTime(new Date());
		schedulejobGroup.setStatus(StatusType.CONGEAL.getValue());//默认为停止状态
		boolean result = schedulejobGroupService.insert(schedulejobGroup);
		if (result) {
			responseMessage.setData("新增成功");
		}else{
			responseMessage.setCode(Constants.SYSTEM_ERROR);
			responseMessage.setError("新增失败");
		}
		return responseMessage;
	}

	@GetMapping("del/quartz/schedulejobGroup")
	public ResponseBase<?> delScheduleJobGroup(Integer scheduleJobGroupId){
		ResponseBase<String> responseMessage = new ResponseBase<>();
		schedulejobGroupService.deleteById(scheduleJobGroupId);
		return responseMessage;
	}

	@PostMapping("edit/quartz/scheduleJobGroup")
	public ResponseBase<?> editScheduleJobGroup(QuartzSchedulejobGroup schedulejobGroup ){
		ResponseBase<String> responseMessage = new ResponseBase<>();
		boolean result = schedulejobGroupService.updateById(schedulejobGroup);
		if(result){
			responseMessage.setData("修改成功");
		}else {
			responseMessage.setError("修改失败");
			responseMessage.setCode(Constants.SYSTEM_ERROR);
		}
		return responseMessage;
	}

	@RequestMapping("query/quartz/scheduleJobGroupList")
	public ResponseBase<?> queryScheduleJobGroupList(QuartzSchedulejobGroup schedulejobGroup ){
		PublicReturnVo<List<QuartzSchedulejobGroup>> vo = null;
		ResponseBase<PublicReturnVo> responseMessage = new ResponseBase<>();
		Page<QuartzSchedulejobGroup> page = WebUtil.getPage(5);
		Page<QuartzSchedulejobGroup>pages =  schedulejobGroupService.selectPage(page);
		vo = PublicReturnVo.conversionPublicReturn(pages);
		responseMessage.setData(vo);
		return responseMessage;
	}

	@GetMapping("query/quartz/scheduleJobGroup")
	public ResponseBase<?> editScheduleJobGroup(Integer scheduleJobGroupId){
		ResponseBase<QuartzSchedulejobGroup> responseMessage = new ResponseBase<>();
		QuartzSchedulejobGroup schedulejobGroup2 = schedulejobGroupService.selectById(scheduleJobGroupId);
		responseMessage.setData(schedulejobGroup2);
		return responseMessage;
	}

	@RequestMapping("query/quartz/scheduleJobGroupAll")
	public ResponseBase<?> queryScheduleJobGroupAll(QuartzSchedulejobGroup schedulejobGroup ){

		ResponseBase<List<QuartzSchedulejobGroup> > responseMessage = new ResponseBase<>();

		List<QuartzSchedulejobGroup> list = schedulejobGroupService.selectList(null);
		responseMessage.setData(list);
		return responseMessage;
	}


	@RequestMapping("start/quartz/schedulejobGroup")
	public ResponseBase<?> startScheduleJobGroup(Integer scheduleJobGroupId){
		ResponseBase<String> responseMessage = new ResponseBase<>();
		QuartzSchedulejobGroup quartzSchedulejobGroup = schedulejobGroupService.selectById(scheduleJobGroupId);
		quartzSchedulejobGroup.setStatus(StatusType.NORMAL.getValue());
		schedulejobGroupService.updateById(quartzSchedulejobGroup);
		responseMessage.setData("成功");
		return responseMessage;
	}

	@RequestMapping("stop/quartz/schedulejobGroup")
	public ResponseBase<?> stopScheduleJobGroup(Integer scheduleJobGroupId){
		ResponseBase<String> responseMessage = new ResponseBase<>();
		schedulejobGroupService.congeal(scheduleJobGroupId);
		responseMessage.setData("成功");
		return responseMessage;
	}
	


}
