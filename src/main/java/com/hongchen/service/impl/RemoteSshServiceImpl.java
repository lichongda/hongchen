package com.hongchen.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.annotation.Operator;
import com.hongchen.dao.RemoteSshMapper;
import com.hongchen.entity.RemoteSsh;
import com.hongchen.entity.quartz.QuartzSchedulejob;
import com.hongchen.enums.StatusType;
import com.hongchen.exception.HongchenException;
import com.hongchen.quartz.SchedulerFactory;
import com.hongchen.service.IRemoteSshService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongchen.util.linux.ConnectionSSH;
import com.hongchen.util.linux.IpAddress;
import com.hongchen.util.linux.RemoteExecuteCommand;
import com.hongchen.vo.PublicReturnVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongchen
 * @since 2018-10-31
 */
@Service
public class RemoteSshServiceImpl extends ServiceImpl<RemoteSshMapper, RemoteSsh> implements IRemoteSshService {
    @Transactional
    @Operator(story = "修改服务器信息")
    @Override
    public void update(RemoteSsh remoteSsh) {
        RemoteSsh remoteSsh1 = baseMapper.selectById(remoteSsh.getId());
        if(remoteSsh1 == null){
            throw new HongchenException("修改错误,没有这条记录");
        }
        baseMapper.updateById(remoteSsh);
    }

    @Override
    public int delete(Integer id) {
        RemoteSsh remoteSsh1 = baseMapper.selectById(id);
        if(remoteSsh1 == null){
            throw new HongchenException("删除错误,没有这条定时器");
        }
        return  baseMapper.deleteById(id);
    }

    @Override
    public PublicReturnVo selectRemoteSshList(Page<RemoteSsh> page, RemoteSsh remoteSsh) {
        PublicReturnVo<List<RemoteSsh>> vo = null;
        page.setRecords(baseMapper.selectRemoteSshList(page, remoteSsh));
        vo = PublicReturnVo.conversionPublicReturn(page);
        return vo;
    }

    @Transactional
    @Operator(name="添加远程服务器", story = "添加远程服务器")
    @Override
    public boolean insert(RemoteSsh remoteSsh) {
        baseMapper.insert(remoteSsh);
       return true;
    }

    @Override
    public String executeCommand(Integer[] ids) {
        int size = ids.length;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<size;i++){
            RemoteSsh remoteSsh = baseMapper.selectById(ids[i]);
            if(remoteSsh.getConnectionType() ==1){ //密钥连接
                IpAddress ipAddress = new IpAddress();
                ipAddress.setIp(remoteSsh.getServerIp());
                ipAddress.setPort(remoteSsh.getServerPort());
                ipAddress.setCommand(remoteSsh.getCommand());
                ipAddress.setKeyName(remoteSsh.getKeyName());
                ipAddress.setUserName(remoteSsh.getServerAccount());
                String message = ConnectionSSH.execute(ipAddress);
                stringBuilder.append(message);
            }else {
                //密码连接
                IpAddress ipAddress = new IpAddress();
                ipAddress.setIp(remoteSsh.getServerIp());
                ipAddress.setPort(remoteSsh.getServerPort());
                ipAddress.setCommand(remoteSsh.getCommand());
                ipAddress.setKeyName(remoteSsh.getKeyName());
                ipAddress.setUserName(remoteSsh.getServerAccount());
                ipAddress.setUserPassword(remoteSsh.getServerPassword());
                String message = RemoteExecuteCommand.execute(ipAddress);
                stringBuilder.append(message);
            }

        }

        return stringBuilder.toString();
    }
}
