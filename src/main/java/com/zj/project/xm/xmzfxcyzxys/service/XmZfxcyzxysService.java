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

    /**
     * 保存信息
     * @param xmZfxcyzxysDO 政府验收和巡查记录信息
     * @param fileIds  图片ids
     */
    void  saveXmZfxcyzxysxx(XmZfxcyzxysDO xmZfxcyzxysDO, String fileIds);
}
