package com.zj.project.xm.xmgqjdbj.dao;

import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zj.platform.common.web.dao.Dao;

/**
 * 
 * <pre>
 * 项目基本信息-主要工期节点比较
 * </pre>
 * <small> 2018-10-24 21:18:21 | lijun</small>
 */
public interface XmGqjdbjDao extends Dao<XmGqjdbjDO> {

	List<Map<String, Object>> getXmgqjdbjXx(@Param("intxmid")Long intxmid, @Param("lx") String lx);

}
