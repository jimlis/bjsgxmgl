package com.zj.project.xm.xmsgjd.dtsbazsg.service;

import com.zj.project.xm.xmsgjd.dtsbazsg.domain.XmSgjdDtsbazsgDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-电梯设备安装施工
 * </pre>
 * <small> 2018-10-13 21:17:22 | lijun</small>
 */
public interface XmSgjdDtsbazsgService extends BaseService<XmSgjdDtsbazsgDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdDtsbazsgDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存项目施工记录电梯设备安装信息
     * @param xmSgjdDtsbazsgDO
     * @param fileIds
     */
	public void saveXmSgjdDtsbazsgXx(XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO, String fileIds);
}
