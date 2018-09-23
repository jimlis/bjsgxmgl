package com.zj.platform.business.dept.service;

import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.dept.domain.DeptDO;
import com.zj.platform.common.web.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 */
public interface DeptService extends BaseService<DeptDO> {
    
	/**
	 * 获取部门树
	 * @return Tree
	 */
	Tree<DeptDO> getTree();
	
	/**
	 * 检查部门下是否有用户
	 * @param deptId 部门id
	 * @return true-不存在 false-存在
	 */
	boolean checkDeptHasUser(Long deptId);
	
	/**
	 * 根据部门id获取下一级部门和人员信息
	 * @param deptId 部门id
	 * @return 部门、人员集合
	 */
	List<Map<String,Object>> getNextDeptAndUser(Long deptId);
}
