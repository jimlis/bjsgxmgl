package com.zj.project.xm.xmsgjd.sgjdjcsgkz.service;

import com.zj.project.xm.xmsgjd.sgjdjcsgkz.domain.XmSgjdJcsgKzDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-基础施工扩展
 * </pre>
 * <small> 2018-11-04 20:55:42 | lijun</small>
 */
public interface XmSgjdJcsgKzService extends BaseService<XmSgjdJcsgKzDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdJcsgKzDO> listByParmMap(Map<String, Object> parmMap);
}
