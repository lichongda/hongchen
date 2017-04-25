package com.hongchen.service.impl.admin;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongchen.dao.admin.AdminOperatorLogMapper;
import com.hongchen.entity.admin.AdminOperatorLog;
import com.hongchen.service.admin.IAdminOperatorLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongchen
 * @since 2017-03-31
 */
@Service
public class AdminOperatorLogServiceImpl extends ServiceImpl<AdminOperatorLogMapper, AdminOperatorLog> implements IAdminOperatorLogService {
	
}
