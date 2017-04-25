package com.hongchen.service.impl.quartz;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.annotation.Operator;
import com.hongchen.dao.quartz.QuartzSchedulejobMapper;
import com.hongchen.entity.quartz.QuartzSchedulejob;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongchen.enums.StatusType;
import com.hongchen.exception.HongchenException;
import com.hongchen.quartz.SchedulerFactory;
import com.hongchen.service.quartz.IQuartzSchedulejobService;
import com.hongchen.util.BeanUtil;
import com.hongchen.vo.PublicReturnVo;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
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
public class QuartzSchedulejobServiceImpl extends ServiceImpl<QuartzSchedulejobMapper, QuartzSchedulejob> implements IQuartzSchedulejobService{
    @Autowired
    private Scheduler scheduler;

    @Transactional
    @Operator(name="添加定时器", story = "添加定时器")
    @Override
    public boolean insert(QuartzSchedulejob scheduleJob) {
        verifySchedule(scheduleJob);//验证是否通过验证
        SchedulerFactory.verifyTrigger(scheduleJob);//验证执行方法是否正确
        scheduleJob.setStatus(StatusType.CONGEAL.getValue());
       baseMapper.insert(scheduleJob);
        /*if(scheduleJob.getQuartzSchedulejobGroup().getStatus()==StatusType.CONGEAL.getValue()){
            throw new HongchenException("启动失败,请对所在定时器类型进行启动操作.");
        }*/
      //SchedulerFactory.add(_scheduleJob,true, scheduler);//添加到执行队列中
        return  true;
    }

    @Override
    @Transactional
    @Operator(story = "修改定时器信息")
    public void update(QuartzSchedulejob scheduleJob) {
        verifySchedule(scheduleJob);//执行方法验证是否通过验证
        SchedulerFactory.verifyTrigger(scheduleJob);//验证表达式是否正确
        QuartzSchedulejob quartzSchedulejob = baseMapper.selectById(scheduleJob.getScheduleJobId());
        if(quartzSchedulejob == null){
            throw new HongchenException("修改错误,没有这条定时器");
        }
       baseMapper.updateById(scheduleJob);
    }

    @Override
    public int delete(Integer scheduleJobId) {
        QuartzSchedulejob quartzSchedulejob = baseMapper.selectById(scheduleJobId);
        if(quartzSchedulejob == null){
            throw new HongchenException("删除错误,没有这条定时器");
        }
        SchedulerFactory.del(quartzSchedulejob, scheduler);//删除定时器
        return  baseMapper.deleteById(scheduleJobId);
    }

    @Override
    @Operator(story = "停止定时器")
    public void congeal(Integer scheduleJobId) {
        QuartzSchedulejob quartzSchedulejob = baseMapper.selectById(scheduleJobId);
        if(quartzSchedulejob == null){
            throw new HongchenException("停止错误,没有这条定时器");
        }
        if(quartzSchedulejob.getQuartzSchedulejobGroup().getStatus()==StatusType.CONGEAL.getValue()){
            throw new HongchenException("停止失败,请对所在定时器类型进行启动操作.");
        }
        verifySchedule(quartzSchedulejob);//执行方法验证是否通过验证
        SchedulerFactory.stop(quartzSchedulejob, scheduler);//在队列中停止
        quartzSchedulejob.setStatus(StatusType.CONGEAL.getValue());
        baseMapper.updateById(quartzSchedulejob);
    }

    @Override
    @Transactional
    @Operator(story = "启动定时器")
    public void thaw(Integer scheduleJobId) {
        QuartzSchedulejob _scheduleJob = baseMapper.selectById(scheduleJobId);
        if(_scheduleJob == null){
            throw new HongchenException("启动错误,没有这条定时器");
        }
        verifySchedule(_scheduleJob);//执行方法验证是否通过验证
        SchedulerFactory.verifyTrigger(_scheduleJob);//验证表达式是否正确
        if(_scheduleJob.getQuartzSchedulejobGroup().getStatus()==StatusType.CONGEAL.getValue()){
            throw new HongchenException("启动失败,请对所在定时器类型进行启动操作.");
        }
        SchedulerFactory.add(_scheduleJob,true, scheduler);//添加到执行队列中

        _scheduleJob.setStatus(StatusType.NORMAL.getValue());
        baseMapper.updateById(_scheduleJob);

    }

    @Transactional
    @Operator(story = "重启定时器")
    public void restart(Integer scheduleJobId) {
        QuartzSchedulejob _scheduleJob = baseMapper.selectById(scheduleJobId);
        if(_scheduleJob == null){
            throw new HongchenException("重启错误,没有这条定时器");
        }
        if(_scheduleJob.getQuartzSchedulejobGroup().getStatus()==StatusType.CONGEAL.getValue()){
            throw new HongchenException("重启失败,请对所在定时器类型进行启动操作.");
        }
        verifySchedule(_scheduleJob);//执行方法验证是否通过验证
        SchedulerFactory.verifyTrigger(_scheduleJob);//验证表达式是否正确
        SchedulerFactory.reStart(_scheduleJob, scheduler);//重启定时器
        _scheduleJob.setStatus(StatusType.NORMAL.getValue());//修改为运行中
        baseMapper.updateById(_scheduleJob);
    }

    @Override
    @Transactional
    @Operator(story = "同步定时器")
    public void async() {
        List<QuartzSchedulejob> remoteList =  baseMapper.selectQuartzSchedulejobAll();

        for(QuartzSchedulejob scheduleJob : remoteList){
            verifySchedule(scheduleJob);//执行方法验证是否通过验证
            SchedulerFactory.verifyTrigger(scheduleJob);//验证表达式是否正确
            if(scheduleJob.getStatus()==StatusType.CONGEAL.getValue()){
                SchedulerFactory.add(scheduleJob,false, scheduler);//添加到执行队列中
            }else if(scheduleJob.getStatus()==StatusType.NORMAL.getValue()){
                SchedulerFactory.add(scheduleJob,true, scheduler);//添加到执行队列中
            }
        }
    }

    public void verifySchedule(QuartzSchedulejob scheduleJob){
        Class<?> class1 = BeanUtil.classExists(scheduleJob.getScheduleJobClass());
        if(class1!=null){
            Method method = null;
            if(null!=(method=BeanUtil.methodExists(class1,scheduleJob.getScheduleJobMethod()))){
                if(!BeanUtil.parameterTypesExists(method)){
                    throw new HongchenException("执行方法中不能存有任何参数.");
                }
            }else{
                throw new HongchenException("执行方法不存在此调用类中.");
            }
        }else{
            throw new HongchenException("调用类不存在此系统中.");
        }
    }

    @Override
    public PublicReturnVo selectQuartzSchedulejobList(Page<QuartzSchedulejob> page, QuartzSchedulejob scheduleJob) {
        PublicReturnVo<List<QuartzSchedulejob>> vo = null;
        page.setRecords(baseMapper.selectQuartzSchedulejobList(page, scheduleJob));
        vo = PublicReturnVo.conversionPublicReturn(page);
        return vo;
    }
    //    public boolean ckAsync() {
//        QuartzSchedulejob scheduleJob = new QuartzSchedulejob();
//        scheduleJob.setRows(Integer.valueOf(String.valueOf(count(new ScheduleJob()))));
//        List<QuartzSchedulejob> remoteList = (List<QuartzSchedulejob>) super.select(scheduleJob);
//        List<QuartzSchedulejob> localList = SchedulerFactory.list();
//        if(remoteList.size()!=localList.size()){
//            return false;
//        }else{
//            for(int i =0;i<remoteList.size();i++){
//                QuartzSchedulejob remoteSch = remoteList.get(i);
//                QuartzSchedulejob localSch = localList.get(i);
//                if(remoteSch.getStatus()!=localSch.getStatus()){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

}
