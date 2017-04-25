package com.hongchen.controller.quartz;
import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.admin.AdminRole;
import com.hongchen.entity.admin.AdminUser;
import com.hongchen.entity.quartz.QuartzSchedulejob;
import com.hongchen.entity.quartz.QuartzSchedulejobGroup;
import com.hongchen.service.quartz.IQuartzSchedulejobGroupService;
import com.hongchen.service.quartz.IQuartzSchedulejobService;
import com.hongchen.util.WebUtil;
import com.hongchen.vo.PublicReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="quartz")
public class ScheduleJobController {
    @Autowired
    IQuartzSchedulejobService schedulejobService;

    @Autowired
    IQuartzSchedulejobGroupService schedulejobGroupService;

    @RequestMapping("add/quartz/schedulejob")
    public ResponseBase<?> addScheduleJob(QuartzSchedulejob schedulejob){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        schedulejob.setScheduleJobGroupId(1);
        schedulejob.setCreateTime(new Date());
        schedulejobService.insert(schedulejob);
        responseMessage.setData("");
        return responseMessage;
    }

    @RequestMapping("del/quartz/schedulejob")
    public ResponseBase<?> delAdminRole(Integer scheduleJobId){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        schedulejobService.delete(scheduleJobId);
        return responseMessage;
    }

    @RequestMapping("edit/quartz/schedulejob")
    public ResponseBase<?> editScheduleJob(QuartzSchedulejob schedulejob ){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        schedulejobService.update(schedulejob);
        responseMessage.setData("修改成功");
        return responseMessage;
    }

    @RequestMapping("start/quartz/schedulejob")
    public ResponseBase<?> startScheduleJob(Integer scheduleJobId){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        schedulejobService.thaw(scheduleJobId);
        return responseMessage;
    }

    @RequestMapping("stop/quartz/schedulejob")
    public ResponseBase<?> stopScheduleJob(Integer scheduleJobId){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        schedulejobService.congeal(scheduleJobId);
        return responseMessage;
    }

    @RequestMapping("reboot/quartz/schedulejob")
    public ResponseBase<?> rebootScheduleJob(Integer scheduleJobId){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        schedulejobService.restart(scheduleJobId);
        return responseMessage;
    }

    @RequestMapping("async/quartz/schedulejob")
    public ResponseBase<?> asyncScheduleJob(){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        schedulejobService.async();
        return responseMessage;
    }

    @GetMapping("query/quartz/schedulejobList")
    public ResponseBase<?> queryScheduleJob(){
        ResponseBase<PublicReturnVo> responseMessage = new ResponseBase<>();
        Page<QuartzSchedulejob> page = WebUtil.getPage(5);
        PublicReturnVo pageResult =  schedulejobService.selectQuartzSchedulejobList(page, null);
        responseMessage.setData(pageResult);
        return responseMessage;
    }


    @GetMapping("query/quartz/schedulejob")
    public ResponseBase<?> queryScheduleJob(Integer scheduleJobId){
        ResponseBase<HashMap> responseMessage = new ResponseBase<>();
        HashMap<String, Object> map = new HashMap<String, Object> ();
        QuartzSchedulejob quartzSchedulejob = schedulejobService.selectById(scheduleJobId);
        List<QuartzSchedulejobGroup>listAll = schedulejobGroupService.selectList(null);
        map.put("jobInfo", quartzSchedulejob);
        map.put("jobGroup", listAll);
        responseMessage.setData(map);
        return responseMessage;
    }

    @GetMapping("query/quartz/schedulejobInfo")
    public ResponseBase<?> queryScheduleJobInfo(Integer scheduleJobId){
        ResponseBase<QuartzSchedulejob> responseMessage = new ResponseBase<>();
        QuartzSchedulejob quartzSchedulejob = schedulejobService.selectById(scheduleJobId);
        responseMessage.setData(quartzSchedulejob);
        return responseMessage;
    }
}
