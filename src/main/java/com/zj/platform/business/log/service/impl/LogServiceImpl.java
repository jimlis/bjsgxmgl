package com.zj.platform.business.log.service.impl;

import com.zj.platform.business.log.dao.LogDao;
import com.zj.platform.business.log.domain.LogDO;
import com.zj.platform.business.log.service.LogService;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;



@Service
public class LogServiceImpl extends BaseServiceImpl<LogDao, LogDO> implements LogService {

}
