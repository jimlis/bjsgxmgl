package com.zj.project.xm.xmgckyzfqk.dao;

import com.zj.project.xm.xmgckyzfqk.domain.XmGckyzfqkDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zj.platform.common.web.dao.Dao;

/**
 * 
 * <pre>
 * 工程款与支付情况:工程款申请/支付情况
 * </pre>
 * <small> 2018-10-14 10:40:37 | lijun</small>
 */
public interface XmGckyzfqkDao extends Dao<XmGckyzfqkDO> {

	List<Map<String, Object>> getSprSpsl(@Param("xmid") Long xmid);

}
