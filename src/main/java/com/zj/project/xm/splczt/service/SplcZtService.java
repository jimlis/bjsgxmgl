package com.zj.project.xm.splczt.service;

import com.zj.project.xm.splczt.domain.SplcZtDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 审批流程状态
 * </pre>
 * <small> 2018-10-30 22:57:34 | lijun</small>
 */
public interface SplcZtService extends BaseService<SplcZtDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<SplcZtDO> listByParmMap(Map<String, Object> parmMap);
}
