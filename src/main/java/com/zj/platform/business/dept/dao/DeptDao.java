package com.zj.platform.business.dept.dao;

import java.util.List;
import java.util.Map;

import com.zj.platform.business.dept.domain.DeptDO;
import com.zj.platform.common.web.dao.Dao;
import org.apache.ibatis.annotations.Param;


/**
 * 部门管理
 */
public interface DeptDao extends Dao<DeptDO> {
	
	Long[] listParentDept();
	
	int getDeptUserNumber(Long deptId);
	
	/**
	 * 根据部门id获取下一级部门和人员信息
	 * @param deptId 部门id
	 * @return 部门、人员集合
	 */
	List<Map<String,Object>> getNextDeptAndUser(@Param("deptId") Long deptId);
}
