package com.zj.project.xm.xmdlcs.service;

import com.zj.project.xm.xmdlcs.domain.XmDlCsDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-栋楼-层数,记录每层情况
 * </pre>
 * <small> 2018-10-13 20:26:30 | lijun</small>
 */
public interface XmDlCsService extends BaseService<XmDlCsDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmDlCsDO> listByParmMap(Map<String, Object> parmMap);
}
