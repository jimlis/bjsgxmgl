package com.zj.project.xm.xmclybspjl.service;

import com.zj.project.xm.xmclybspjl.domain.XmClybspjlDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 材料样板审批记录
 * </pre>
 * <small> 2018-10-13 18:35:59 | lijun</small>
 */
public interface XmClybspjlService extends BaseService<XmClybspjlDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmClybspjlDO> listByParmMap(Map<String, Object> parmMap);
    
	/**
	 * 保存材料样板审批记录信息
	 * @param xmClybspjlDO
	 * @param fileIds 文件ids
	 * @param xmClybspjlJszlJson 品牌和技术json串
	 * @param deleteJszlIds 删除的品牌技术资料ids
	 */
	public void saveXmZpjlxx(XmClybspjlDO xmClybspjlDO, String fileIds, String xmClybspjlJszlJson,String deleteJszlIds);
}
