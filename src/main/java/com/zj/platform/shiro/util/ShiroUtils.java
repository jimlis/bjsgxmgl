package com.zj.platform.shiro.util;

import com.zj.platform.business.user.dao.UserDao;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.util.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {
	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}
	
	// api
	private final static UserDao userDao = SpringContextHolder.getBean(UserDao.class);

	public static UserDO getAppUserDO() {
	    String jwt = (String)getSubjct().getPrincipal();
	    String userId = JWTUtil.getUserId(jwt);
	    return userDao.selectById(userId);
	}
	
	// admin
	public static UserDO getSysUser() {
		return (UserDO)getSubjct().getPrincipal();
	}
	public static Long getUserId() {
		return getSysUser().getId();
	}
	
	public static void logout() {
		getSubjct().logout();
	}
}
