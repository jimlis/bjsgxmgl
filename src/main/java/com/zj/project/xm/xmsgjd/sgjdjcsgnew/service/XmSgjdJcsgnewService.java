package com.zj.project.xm.xmsgjd.sgjdjcsgnew.service;

import com.zj.project.xm.xmsgjd.sgjdjcsgnew.domain.XmSgjdJcsgnewDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-基础施工新
 * </pre>
 * <small> 2018-11-04 20:58:53 | lijun</small>
 */
public interface XmSgjdJcsgnewService extends BaseService<XmSgjdJcsgnewDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdJcsgnewDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存新的基础施工信息
     * @param XmSgjdJcsgnewDO
     * @fileIds 文件ids
     * @param dljcs  独立基础json
     * @param zjcs 桩基础json
     */
	void saveXmSgjdJcsgnewXx(XmSgjdJcsgnewDO xmSgjdJcsgnewDO,String fileIds,String dljcs, String zjcs);
	
	 /**
     * 根据项目id和施工位置获取基础施工信息
     * @param xmid 项目id
     * @param sgwzid 丝攻位置
     * @return XmSgjdJcsgnewDO
     */
	XmSgjdJcsgnewDO getXmSgjdJcsgnewByXmidAndSgwzid(Long xmid, Long sgwzid);
}
