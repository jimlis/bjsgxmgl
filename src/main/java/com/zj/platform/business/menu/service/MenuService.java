package com.zj.platform.business.menu.service;

import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.menu.domain.MenuDO;
import com.zj.platform.common.web.service.BaseService;

import java.util.List;
import java.util.Set;



public interface MenuService extends BaseService<MenuDO> {
	
	 /**
     * 根据用户id获取树形目录、菜单
     * @param id 用户id
     * @return 树形菜单
     */
    Tree<MenuDO> getSysMenuTree(Long id);
    
    /**
     * 根据用户id获取树形目录、菜单
     * @param id 用户id
     * @return 树形菜单
     */
    List<Tree<MenuDO>> listMenuTree(Long id);
    
    /**
     * 查询所有菜单
     * @return 树形菜单
     */
    Tree<MenuDO> getTree();
    
    /**
     * 根据角色id(roleId)获取树形菜单
     * @param id 用户id
     * @return 树形菜单
     */
    Tree<MenuDO> getTree(Long id);
    
    /**
     * 根据用户id获取树形目录、菜单、按钮
     * @param userId 用户id
     * @return 树形菜单
     */
    Set<String> listPerms(Long userId);
    
}
