package com.zj.project.xm.xmxkz.service;

import com.zj.project.xm.xmxkz.domain.XmXkzDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-许可证
 * </pre>
 * <small> 2018-10-04 18:10:12 | lijun</small>
 */
public interface XmXkzService extends BaseService<XmXkzDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmXkzDO> listByParmMap(Map<String, Object> parmMap);

    /*
     *保存项目许可证信息
     * @param XmXkzDO 许可证信息
     * @param fileIds 相关附件信息
     */
    void saveXmXkzxx(XmXkzDO xmXkzDO, String fileIds);
}
