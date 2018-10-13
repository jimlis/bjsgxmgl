package com.zj.project.xm.xmsgjd.jcsg.service;

import com.zj.project.xm.xmsgjd.jcsg.domain.XmSgjdJcsgDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-施工进度-基础施工
 * </pre>
 * <small> 2018-10-13 19:32:53 | lijun</small>
 */
public interface XmSgjdJcsgService extends BaseService<XmSgjdJcsgDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdJcsgDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存施工进度-基础施工
     * @param xmSgjdJcsgDO
     * @param fileIds 图片ids
     * @param xmZpmsJson 图片描述json
     */
	public void saveXmSgjdJcsgXx(XmSgjdJcsgDO xmSgjdJcsgDO, String fileIds, String xmZpmsJson);
}
