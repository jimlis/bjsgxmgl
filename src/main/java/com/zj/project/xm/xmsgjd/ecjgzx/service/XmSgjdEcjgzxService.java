package com.zj.project.xm.xmsgjd.ecjgzx.service;

import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 二次结构装修：施工进度-二次结构、装修等施工
 * </pre>
 * <small> 2018-10-13 20:45:56 | lijun</small>
 */
public interface XmSgjdEcjgzxService extends BaseService<XmSgjdEcjgzxDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdEcjgzxDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存施工记录
     * @param xmSgjdEcjgzxDO
     * @param xmSgjdEcjgzxWclJson 完成量对象json
     * @param deleteWclIds 删除完成量ids
     */
	public void saveXmSgjdEcjgzxxx(XmSgjdEcjgzxDO xmSgjdEcjgzxDO, String xmSgjdEcjgzxWclJson,String deleteWclIds);
	
	/**
	 * 获取施工记录id
	 * @param xmSgjdEcjgzxId 二次施工进度id
	 * @param xmid 项目id
	 * @param did 栋id
	 * @param fwlx xz-新增  cx-查询
	 * 
	 */
	public XmSgjdEcjgzxDO getXmSgjdEcjgzxByParam(Long xmSgjdEcjgzxId, Long xmid, Long did,String fwlx);
}
