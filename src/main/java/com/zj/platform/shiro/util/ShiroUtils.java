package com.zj.platform.shiro.util;

import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.business.user.service.UserService;
import com.zj.platform.common.util.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {
	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}
	
	// api
	private final static UserService userService = SpringContextHolder.getBean(UserService.class);

	public static UserDO getAppUserDO() {
	    String jwt = (String)getSubjct().getPrincipal();
	    String userId = JWTUtil.getUserId(jwt);
	    return userService.getById(userId);
	}
	
	// admin
	public static UserDO getSysUser() {
		return  userService.getById(( (UserDO)getSubjct().getPrincipal()).getId());
	}
	public static Long getUserId() {
		return getSysUser().getId();
	}
	
	public static void logout() {
		getSubjct().logout();
	}
}
