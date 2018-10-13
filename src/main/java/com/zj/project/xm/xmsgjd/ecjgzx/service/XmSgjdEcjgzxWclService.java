package com.zj.project.xm.xmsgjd.ecjgzx.service;

import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxWclDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-二次结构、装修等施工-完成量
 * </pre>
 * <small> 2018-10-13 20:45:57 | lijun</small>
 */
public interface XmSgjdEcjgzxWclService extends BaseService<XmSgjdEcjgzxWclDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdEcjgzxWclDO> listByParmMap(Map<String, Object> parmMap);
}
