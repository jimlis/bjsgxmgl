package com.zj.project.xm.xmzpjl.service;

import com.zj.project.xm.xmzpjl.domain.XmZpjlDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-照片记录
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
public interface XmZpjlService extends BaseService<XmZpjlDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmZpjlDO> listByParmMap(Map<String, Object> parmMap);

    /**
     * 保存项目照片记录信息
     * @param xmZpjlDO 照片记录信息
     * @param fileIds 图片ids 多个以逗号隔开
     * @param xmZpmsJson 照片对应描述对象json串
     */
    void saveXmZpjlxx(XmZpjlDO xmZpjlDO, String fileIds, String xmZpmsJson);

    /**
     * 根据项目id 获取项目照片map集合
     * @param xmid 项目id
     * @return
     */
    Map<String,Object> getXmZpjlMapByXmid(Long xmid);
}
