package com.zj.platform.business.role.dao;

import com.zj.platform.business.role.domain.RoleMenuDO;
import com.zj.platform.common.web.dao.Dao;

import java.io.Serializable;
import java.util.List;


/**
 * 角色与菜单对应关系
 */
public interface RoleMenuDao extends Dao<RoleMenuDO> {
	
	List<Long> listMenuIdByRoleId(Serializable roleId);
	
	int removeByRoleId(Serializable roleId);
	
	int batchSave(List<RoleMenuDO> list);
}
