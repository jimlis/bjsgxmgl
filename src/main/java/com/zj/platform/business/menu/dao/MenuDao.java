package com.zj.platform.business.menu.dao;

import com.zj.platform.business.menu.domain.MenuDO;
import com.zj.platform.common.web.dao.Dao;

import java.util.List;


/**
 * 菜单管理
 */
public interface MenuDao extends Dao<MenuDO> {
	
	List<MenuDO> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
}
