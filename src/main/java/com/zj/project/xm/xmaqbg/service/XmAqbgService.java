package com.zj.project.xm.xmaqbg.service;

import com.zj.project.xm.xmaqbg.domain.XmAqbgDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 安全报告
 * </pre>
 * <small> 2018-10-13 17:00:14 | lijun</small>
 */
public interface XmAqbgService extends BaseService<XmAqbgDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmAqbgDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存安全报告信息
     * @param xmAqbgDO
     * @param fileIds
     */
	public void saveXmAqbgDOXx(XmAqbgDO xmAqbgDO, String fileIds);
}
