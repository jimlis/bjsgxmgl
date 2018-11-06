package com.zj.project.xm.xmsgjd.sgjdztjgsgkz.service;

import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.domain.XmSgjdZtjgsgKzDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-主体施工扩展
 * </pre>
 * <small> 2018-11-05 19:27:20 | lijun</small>
 */
public interface XmSgjdZtjgsgKzService extends BaseService<XmSgjdZtjgsgKzDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdZtjgsgKzDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * <p>Title: 根据Xmid和Ztsgid获取扩展id</p>  
     */
	List<XmSgjdZtjgsgKzDO> getXmSgjdZtKzListByXmidAndZtsgid(Map<String, Object> map);
}
