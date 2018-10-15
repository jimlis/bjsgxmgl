package com.zj.project.xm.xmybsgjl.service;

import com.zj.project.xm.xmybsgjl.domain.XmYbsgjlDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 
 * <pre>
 * 样板施工记录
 * </pre>
 * <small> 2018-10-13 00:54:47 | lijun</small>
 */
public interface XmYbsgjlService extends BaseService<XmYbsgjlDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmYbsgjlDO> listByParmMap(Map<String, Object> parmMap);

    /**
     * 保存项样板施工记录
     * @param xmYbsgjlDO
     * @param fileIds 图片ids 多个以逗号隔开
     */
    void saveXmYbsgjlXx(XmYbsgjlDO xmYbsgjlDO, String fileIds);
    
    /**
     * 获取材料样板List对象
     * @param xmid 项目id
     * @return
     */
	List<XmYbsgjlDO> getXmYbsgjlListByXmid(Long xmid);
}
