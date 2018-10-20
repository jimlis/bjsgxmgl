package com.zj.project.xm.xmghzb.service;

import com.zj.project.xm.xmghzb.domain.XmGhzbDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-规划指标数据
 * </pre>
 * <small> 2018-10-04 18:35:49 | lijun</small>
 */
public interface XmGhzbService extends BaseService<XmGhzbDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmGhzbDO> listByParmMap(Map<String, Object> parmMap);

    /**
     * 批量保存项目的规划信息
     * @param xmid 项目id
     * @param ghzbJson 项目规划指标对象数组json字符串
     * @param deleteGhzbIds 删除项目规划指标ids
     */
    void saveBatchXmGhzbxx(Long xmid, String ghzbJson,String deleteGhzbIds);
}
