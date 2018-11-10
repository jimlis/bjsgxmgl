package com.zj.project.xm.xmsgjd.sgjdecjgzxqt.service;

import com.zj.project.xm.xmsgjd.sgjdecjgzxqt.domain.XmSgjdEcjgzxQtDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 施工进度-二次结构装修-其他
 * </pre>
 * <small> 2018-11-10 14:07:31 | lijun</small>
 */
public interface XmSgjdEcjgzxQtService extends BaseService<XmSgjdEcjgzxQtDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdEcjgzxQtDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存施工进度-二次结构装修-其他 
     * @param xmSgjdEcjgzxQtDO
     * @param fileIds 文件ids
     */
	public void saveXmSgjdEcjgzxQtxx(XmSgjdEcjgzxQtDO xmSgjdEcjgzxQtDO, String fileIds);
}
