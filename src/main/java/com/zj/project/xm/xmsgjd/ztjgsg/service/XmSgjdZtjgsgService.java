package com.zj.project.xm.xmsgjd.ztjgsg.service;

import com.zj.project.xm.xmsgjd.ztjgsg.domain.XmSgjdZtjgsgDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-主体结构施工
 * </pre>
 * <small> 2018-10-13 20:03:27 | lijun</small>
 */
public interface XmSgjdZtjgsgService extends BaseService<XmSgjdZtjgsgDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdZtjgsgDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存主体施工信息
     * @param xmSgjdZtjgsgDO
     * @param fileIds 图片IDS
     * @param xmZpmsJson 图片描述对象json串
     */
	public void saveXmSgjdZtjgsgXx(XmSgjdZtjgsgDO xmSgjdZtjgsgDO, String fileIds, String xmZpmsJson);
	
	 /**
     * 保存新的主体施工信息
     * @param xmSgjdZtjgsgDO
     * @fileIds 文件ids
     * @param ztjdsJson  主体节点json
     */
	void saveNewXmSgjdZtsgXx(XmSgjdZtjgsgDO xmSgjdZtjgsgDO, String fileIds, String ztjdsJson);
	
	 /**
	 * 根据项目id和施工位置获取主体结构施工信息
	 * @param xmid 项目id
	 * @param sgwzd 施工位置
	 * @return XmSgjdJcsgnewDO
	 */
	XmSgjdZtjgsgDO getXmSgjdZtsgByXmidAndSgwzid(Long xmid, Long sgwzd);
}
