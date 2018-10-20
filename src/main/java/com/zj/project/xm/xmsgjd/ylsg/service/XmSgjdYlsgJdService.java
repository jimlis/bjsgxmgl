package com.zj.project.xm.xmsgjd.ylsg.service;

import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgJdDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-园林施工-进度
 * </pre>
 * <small> 2018-10-20 23:35:49 | lijun</small>
 */
public interface XmSgjdYlsgJdService extends BaseService<XmSgjdYlsgJdDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdYlsgJdDO> listByParmMap(Map<String, Object> parmMap);
}
