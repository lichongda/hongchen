package com.hongchen.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongchen.entity.RemoteSsh;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hongchen.entity.quartz.QuartzSchedulejob;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hongchen
 * @since 2018-10-31
 */
public interface RemoteSshMapper extends BaseMapper<RemoteSsh> {
    List<RemoteSsh> selectRemoteSshList(Page<RemoteSsh> page, RemoteSsh remoteSsh);
}