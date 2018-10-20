package com.zj.project.xm.xmsgjd.swgwsg.service;

import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwlxDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-室外管网类型，没个项目有多个室外管网类型。
 * </pre>
 * <small> 2018-10-20 20:40:32 | lijun</small>
 */
public interface XmSgjdSwgwlxService extends BaseService<XmSgjdSwgwlxDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdSwgwlxDO> listByParmMap(Map<String, Object> parmMap);
}
