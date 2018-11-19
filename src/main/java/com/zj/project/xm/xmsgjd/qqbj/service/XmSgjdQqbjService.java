package com.zj.project.xm.xmsgjd.qqbj.service;

import com.zj.project.xm.xmsgjd.qqbj.domain.XmSgjdQqbjDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-施工进度-前期报建
 * </pre>
 * <small> 2018-11-19 19:53:41 | lijun</small>
 */
public interface XmSgjdQqbjService extends BaseService<XmSgjdQqbjDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmSgjdQqbjDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * 保存信息
     * @param xmSgjdQqbjDO 前期报建信息
     * @param fileIds  附件ids
     */
	void saveXmSgjdQqbjXx(XmSgjdQqbjDO xmSgjdQqbjDO, String fileIds);
	
	/**
     * 获取前期报建记录
     * @param xmid 项目id
     * @param gqjdid 工期节点id
     * @param fwlx "xz"-新增  "cx"-查询
     * @return
     */
	XmSgjdQqbjDO getXmSgjdQqbj(Long xmid, Long gqjdid, String fwlx);
}
