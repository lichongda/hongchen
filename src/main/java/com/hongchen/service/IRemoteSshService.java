package com.hongchen.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.entity.RemoteSsh;
import com.baomidou.mybatisplus.service.IService;
import com.hongchen.entity.quartz.QuartzSchedulejob;
import com.hongchen.vo.PublicReturnVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongchen
 * @since 2018-10-31
 */
public interface IRemoteSshService extends IService<RemoteSsh> {
    boolean insert(RemoteSsh remoteSsh);
    void update(RemoteSsh remoteSsh);
    int delete(Integer id);
    PublicReturnVo selectRemoteSshList(Page<RemoteSsh> page, RemoteSsh scheduleJob);
    String executeCommand(Integer[]ids);
}
