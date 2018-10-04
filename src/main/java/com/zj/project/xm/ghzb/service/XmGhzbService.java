package com.zj.project.xm.ghzb.service;

import com.zj.project.xm.ghzb.domain.XmGhzbDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-规划指标数据
 * </pre>
 * <small> 2018-10-04 13:41:37 | lijun</small>
 */
public interface XmGhzbService extends BaseService<XmGhzbDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmGhzbDO> listByParmMap(Map<String, Object> parmMap);
}
