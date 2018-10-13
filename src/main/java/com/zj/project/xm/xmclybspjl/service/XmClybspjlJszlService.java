package com.zj.project.xm.xmclybspjl.service;

import com.zj.project.xm.xmclybspjl.domain.XmClybspjlJszlDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 材料样板审批记录-品牌及技术资料
 * </pre>
 * <small> 2018-10-13 18:36:00 | lijun</small>
 */
public interface XmClybspjlJszlService extends BaseService<XmClybspjlJszlDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmClybspjlJszlDO> listByParmMap(Map<String, Object> parmMap);
}
