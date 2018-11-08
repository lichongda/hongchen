package com.hongchen.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.entity.RemoteSsh;
import com.hongchen.entity.quartz.QuartzSchedulejob;
import com.hongchen.entity.quartz.QuartzSchedulejobGroup;
import com.hongchen.service.IRemoteSshService;
import com.hongchen.util.WebUtil;
import com.hongchen.vo.PublicReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hongchen
 * @since 2018-10-31
 */
@RestController
@RequestMapping(value="remoteSsh")
public class RemoteSshController {
    @Autowired
    IRemoteSshService iRemoteSshService;

    @RequestMapping("add/remoteSsh")
    public ResponseBase<?> addRemoteSsh(RemoteSsh remoteSsh){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        remoteSsh.setCreateTime(new Date());
        remoteSsh.setUpdateTime(new Date());
        iRemoteSshService.insert(remoteSsh);
        responseMessage.setData("添加成功");
        return responseMessage;
    }

    @RequestMapping("del/remoteSsh")
    public ResponseBase<?> delRemoteSsh(Integer id){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        iRemoteSshService.deleteById(id);
        responseMessage.setData("修改成功");
        return responseMessage;
    }

    @RequestMapping("edit/remoteSsh")
    public ResponseBase<?> editScheduleJob(RemoteSsh remoteSsh){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        iRemoteSshService.update(remoteSsh);
        remoteSsh.setUpdateTime(new Date());
        responseMessage.setData("修改成功");
        return responseMessage;
    }

    @GetMapping("query/remoteSsh")
    public ResponseBase<?> queryScheduleJob(Integer id){
        ResponseBase<RemoteSsh> responseMessage = new ResponseBase<>();
        RemoteSsh remoteSsh = iRemoteSshService.selectById(id);
        responseMessage.setData(remoteSsh);
        return responseMessage;
    }

    @GetMapping("query/remoteSshList")
    public ResponseBase<?> queryScheduleJob(RemoteSsh remoteSsh){
        ResponseBase<PublicReturnVo> responseMessage = new ResponseBase<>();
        Page<RemoteSsh> page = WebUtil.getPage(5);
        PublicReturnVo pageResult =  iRemoteSshService.selectRemoteSshList(page, remoteSsh);
        responseMessage.setData(pageResult);
        return responseMessage;
    }

    @GetMapping("execute/command")
    public ResponseBase<?> executeCommand(Integer[]ids){
        ResponseBase<String> responseMessage = new ResponseBase<>();
        String message = iRemoteSshService.executeCommand(ids);
        responseMessage.setData(message);
        return responseMessage;
    }

}
