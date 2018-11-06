package com.zj.project.xm.xmsgjd.sgjdztjgsgkz.dao;

import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.domain.XmSgjdZtjgsgKzDO;

import java.util.List;
import java.util.Map;

import com.zj.platform.common.web.dao.Dao;

/**
 * 
 * <pre>
 * 施工进度-主体施工扩展
 * </pre>
 * <small> 2018-11-05 19:27:20 | lijun</small>
 */
public interface XmSgjdZtjgsgKzDao extends Dao<XmSgjdZtjgsgKzDO> {
	public List<XmSgjdZtjgsgKzDO> getXmSgjdZtKzListByXmidAndZtsgid(Map<String,Object> map);
}
