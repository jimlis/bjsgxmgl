package com.zj.project.xm.xmzpjl.service;

import com.zj.project.xm.xmzpjl.domain.XmZpjlDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-照片记录
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
public interface XmZpjlService extends BaseService<XmZpjlDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmZpjlDO> listByParmMap(Map<String, Object> parmMap);
}
