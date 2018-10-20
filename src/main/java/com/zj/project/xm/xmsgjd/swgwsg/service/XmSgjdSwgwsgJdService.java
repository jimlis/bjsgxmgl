package com.zj.project.xm.xmsgjd.swgwsg.service;

import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgJdDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-室外管网施工-进度，通过更新日期与类型生成多条进度内容。
 * </pre>
 * <small> 2018-10-20 20:40:33 | lijun</small>
 */
public interface XmSgjdSwgwsgJdService extends BaseService<XmSgjdSwgwsgJdDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdSwgwsgJdDO> listByParmMap(Map<String, Object> parmMap);
}
