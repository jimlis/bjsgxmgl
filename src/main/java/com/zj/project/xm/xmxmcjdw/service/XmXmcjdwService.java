package com.zj.project.xm.xmxmcjdw.service;

import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmxmcjdw.domain.XmXmcjdwDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目承建各方名称
 * </pre>
 * <small> 2018-09-25 22:20:27 | lijun</small>
 */
public interface XmXmcjdwService extends BaseService<XmXmcjdwDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmXmcjdwDO> listByParmMap(Map<String, Object> parmMap);

    void saveXmXmcjdwAndXmDwmd(XmXmcjdwDO xmXmcjdwDO, XmDwmdDO xmDwmdDO);
}
