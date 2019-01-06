package com.zj.project.xm.xmbgsqjl.service;

import com.zj.project.xm.xmbgsqjl.domain.XmBgsqjlDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 
 * <pre>
 * 工程/顾问工作变更申请记录
 * </pre>
 * <small> 2018-10-13 16:22:31 | lijun</small>
 */
public interface XmBgsqjlService extends BaseService<XmBgsqjlDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmBgsqjlDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存工程/顾问变更记录
     * <p>Title: </p>  
     * <p>Description: </p> 
     * @param xmBgsqjlDO
     * @param fileIds
     */
	public void saveXmBgsqjlXx(XmBgsqjlDO xmBgsqjlDO, String fileIds);
	
	public List<Map<String,Object>> getSprSpsl(Long xmid);
}
