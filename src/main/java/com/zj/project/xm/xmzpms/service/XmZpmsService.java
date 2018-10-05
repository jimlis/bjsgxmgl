package com.zj.project.xm.xmzpms.service;

import com.zj.project.xm.xmzpms.domain.XmZpmsDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-照片描述
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
public interface XmZpmsService extends BaseService<XmZpmsDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmZpmsDO> listByParmMap(Map<String, Object> parmMap);
}
