package com.zj.project.xm.xmsgjd.ylsg.service;

import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-园林施工
 * </pre>
 * <small> 2018-10-13 22:22:17 | lijun</small>
 */
public interface XmSgjdYlsgService extends BaseService<XmSgjdYlsgDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdYlsgDO> listByParmMap(Map<String, Object> parmMap);
    
    
    /**
     * 获取施工进度-园林施工信息
     * @param xmid 项目id
     * @param gxrq 更新日期
     */
	List<XmSgjdYlsgDO> getXmSgjdYlsgListByXmidAndGxrq(Long xmid, Date gxrq);
	
	/**
	 * 保存施工进度-园林施工信息
	 * @param xmSgjdYlsgDO
	 * @param fileIds 文件ids
	 */
	public void saveXmSgjdYlsgXx(XmSgjdYlsgDO xmSgjdYlsgDO, String fileIds);
}
