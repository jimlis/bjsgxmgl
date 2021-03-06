package com.zj.project.xm.xmbgsqjl.dao;

import com.zj.project.xm.xmbgsqjl.domain.XmBgsqjlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zj.platform.common.web.dao.Dao;

/**
 * 
 * <pre>
 * 工程/顾问工作变更申请记录
 * </pre>
 * <small> 2018-10-13 16:22:31 | lijun</small>
 */
public interface XmBgsqjlDao extends Dao<XmBgsqjlDO> {

	List<Map<String, Object>> getSprSpsl(@Param("xmid")Long xmid);

}
