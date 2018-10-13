package com.zj.project.xm.xmzlqxbg.service;

import com.zj.project.xm.xmzlqxbg.domain.XmZlqxbgDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 质量缺陷报告
 * </pre>
 * <small> 2018-10-13 10:46:20 | lijun</small>
 */
public interface XmZlqxbgService extends BaseService<XmZlqxbgDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmZlqxbgDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存项目质量缺陷报告信息
     * @param xmZlqxbgDO
     * @param fileIds 图片id，多个以逗号隔开
     */
    void saveXmZlqxbgXx(XmZlqxbgDO xmZlqxbgDO, String fileIds);
}
