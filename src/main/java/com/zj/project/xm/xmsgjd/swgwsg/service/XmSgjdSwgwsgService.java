package com.zj.project.xm.xmsgjd.swgwsg.service;

import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-室外管网施工
 * </pre>
 * <small> 2018-10-14 10:20:56 | lijun</small>
 */
public interface XmSgjdSwgwsgService extends BaseService<XmSgjdSwgwsgDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdSwgwsgDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 获取施工进度-室外管网施工信息
     * @param xmid 项目id
     * @param gxrq 更新日期
     */
	public List<XmSgjdSwgwsgDO> getXmSgjdYlsgListByXmidAndGxrq(Long xmid, Date gxrq);
	
	/**
	 * 保存施工进度-室外管网施工信息
	 * @param xmSgjdSwgwsgDO
	 * @param fileIds 附件ids
	 * @param sglxAndJdJson 类型区域json串
	 * @param deleteSwgwlxIds 删除的项目室外管网施工类型ids
	 * @param deleteSwgwjdIds 删除的项目室外管网施工进度ids
	 */
	public void saveXmSgjdSwgwsgXx(XmSgjdSwgwsgDO xmSgjdSwgwsgDO, String fileIds,String sglxAndJdJson,String deleteSwgwlxIds,String deleteSwgwjdIds);
	
	 /**
     * 根据项目id获取室外网管信息
     * @param xmid 项目id
     * @param fwlx 访问类型 xz---新增 查询-cx
     * @param Long id 主键id
     * @return XmSgjdSwgwsgDO
     */
	XmSgjdSwgwsgDO getXmSgjdDtsbazsgByParam(Long xmid, String fwlx, Long id);
}
