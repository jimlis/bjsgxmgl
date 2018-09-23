package com.zj.platform.business.common.service;

import com.zj.platform.business.common.domain.UserOnline;
import org.apache.shiro.session.Session;

import java.util.Collection;
import java.util.List;


public interface SessionService {
	

	/**
	 * 查询在线用户集合
	 * @return 在线用户集合
	 */
	List<UserOnline> list();

	Collection<Session> sessionList();
	

	/**
	 * 设置sessionc超时时间
	 * @return true
	 */
	boolean forceLogout(String sessionId);
}
