package com.zj.platform.business.user.dao;

import com.zj.platform.business.user.domain.UserRoleDO;
import com.zj.platform.common.web.dao.Dao;

import java.io.Serializable;
import java.util.List;


/**
 * 用户与角色对应关系
 */
public interface UserRoleDao extends Dao<UserRoleDO> {

	List<Long> listRoleId(Serializable userId);

	int removeByUserId(Serializable userId);

	int batchSave(List<UserRoleDO> list);

	int batchRemoveByUserId(Long[] ids);
}
