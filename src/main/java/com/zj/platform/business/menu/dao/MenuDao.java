package com.zj.platform.business.menu.dao;

import java.util.List;

import com.zj.platform.business.menu.domain.MenuDO;
import com.zj.platform.common.web.dao.Dao;


/**
 * 菜单管理
 */
public interface MenuDao extends Dao<MenuDO> {
	
	List<MenuDO> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
	
}
