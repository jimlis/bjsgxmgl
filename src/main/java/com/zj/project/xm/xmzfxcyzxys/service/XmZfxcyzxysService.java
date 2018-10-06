package com.zj.project.xm.xmzfxcyzxys.service;

import com.zj.project.xm.xmzfxcyzxys.domain.XmZfxcyzxysDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 政府巡查与专项验收：政府部门巡查/专项验收记录
 * </pre>
 * <small> 2018-10-06 09:34:15 | lijun</small>
 */
public interface XmZfxcyzxysService extends BaseService<XmZfxcyzxysDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmZfxcyzxysDO> listByParmMap(Map<String, Object> parmMap);
}
