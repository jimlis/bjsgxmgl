package com.zj.platform.business.role.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zj.platform.business.role.domain.RoleDO;
import com.zj.platform.common.web.dao.Dao;

/**
 * 角色
 */
public interface RoleDao extends Dao<RoleDO> {
	
	List<Map<String,Object>> checkPerms(@Param("userId")Long userId,@Param("roleName")String roleName,
			@Param("menuName")String menuName,@Param("perms")String perms);
}
