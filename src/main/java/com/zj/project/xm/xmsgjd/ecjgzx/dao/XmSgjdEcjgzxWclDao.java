package com.zj.project.xm.xmsgjd.ecjgzx.dao;

import java.util.List;
import java.util.Map;

import com.zj.platform.common.web.dao.Dao;
import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxWclDO;

/**
 * 
 * <pre>
 * 施工进度-二次结构、装修等施工-完成量
 * </pre>
 * <small> 2018-10-13 20:45:57 | lijun</small>
 */
public interface XmSgjdEcjgzxWclDao extends Dao<XmSgjdEcjgzxWclDO> {
	
	public List<XmSgjdEcjgzxWclDO> getXXmSgjdEcjgzxWclListByEcjgzxidAndLcid(Map<String,Object> map);

}
