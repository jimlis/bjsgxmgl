package com.zj.project.xm.xmdl.service;

import com.zj.project.xm.xmdl.domain.XmDlDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-栋楼,记录每栋楼名称及编号
 * </pre>
 * <small> 2018-09-28 22:39:21 | lijun</small>
 */
public interface XmDlService extends BaseService<XmDlDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmDlDO> listByParmMap(Map<String, Object> parmMap);
}
