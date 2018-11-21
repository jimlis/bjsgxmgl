package com.zj.project.xm.xmsgjd.ecjgzx.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import  com.zj.platform.common.web.service.BaseService;
import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxWclDO;


/**
 * 
 * <pre>
 * 施工进度-二次结构、装修等施工-完成量
 * </pre>
 * <small> 2018-10-13 20:45:57 | lijun</small>
 */
public interface XmSgjdEcjgzxWclService extends BaseService<XmSgjdEcjgzxWclDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdEcjgzxWclDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * <p>Title: 根据ecjgzxid和lcid获取完成量</p>  
     */
	List<XmSgjdEcjgzxWclDO> getXXmSgjdEcjgzxWclListByEcjgzxidAndLcid(Map<String, Object> map);
}
