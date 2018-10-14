package com.zj.project.xm.xmgckyzfqk.service;

import com.zj.project.xm.xmgckyzfqk.domain.XmGckyzfqkDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 工程款与支付情况:工程款申请/支付情况
 * </pre>
 * <small> 2018-10-14 10:40:37 | lijun</small>
 */
public interface XmGckyzfqkService extends BaseService<XmGckyzfqkDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmGckyzfqkDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存工程款与支付情况信息
     * @param xmGckyzfqkDO
     * @param fileIds 文件ids
     */
	public void saveXmGckyzfqkXx(XmGckyzfqkDO xmGckyzfqkDO, String fileIds);
}
