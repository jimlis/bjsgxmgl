package com.zj.project.xm.xmclybspjl.dao;

import com.zj.project.xm.xmclybspjl.domain.XmClybspjlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zj.platform.common.web.dao.Dao;

/**
 * 
 * <pre>
 * 材料样板审批记录
 * </pre>
 * <small> 2018-10-13 18:35:59 | lijun</small>
 */
public interface XmClybspjlDao extends Dao<XmClybspjlDO> {
	
	List<Map<String, Object>> getSprSpsl(@Param("xmid") Long xmid);

}
