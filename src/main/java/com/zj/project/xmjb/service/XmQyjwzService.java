package com.zj.project.xmjb.service;

import com.zj.project.xmjb.domain.XmQyjwzDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 所在区域及位置
 * </pre>
 * <small> 2018-10-01 22:46:44 | lijun</small>
 */
public interface XmQyjwzService extends BaseService<XmQyjwzDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmQyjwzDO> listByParmMap(Map<String, Object> parmMap);

    /**
     * 保存项目区域位置信息
     * @param xmQyjwzDO  项目区域位置
     */
    void saveXmQyjwzxx(XmQyjwzDO xmQyjwzDO);
}
