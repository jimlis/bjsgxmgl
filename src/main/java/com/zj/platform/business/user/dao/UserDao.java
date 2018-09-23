package com.zj.platform.business.user.dao;


import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.dao.Dao;

/**
 * 用户
 */
public interface UserDao extends Dao<UserDO> {
	
	Long[] listAllDept();

}
