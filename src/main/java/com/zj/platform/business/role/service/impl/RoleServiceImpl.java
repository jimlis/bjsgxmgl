package com.zj.platform.business.role.service.impl;


import com.zj.platform.business.role.dao.RoleDao;
import com.zj.platform.business.role.dao.RoleMenuDao;
import com.zj.platform.business.role.domain.RoleDO;
import com.zj.platform.business.role.domain.RoleMenuDO;
import com.zj.platform.business.role.service.RoleService;
import com.zj.platform.business.user.dao.UserDao;
import com.zj.platform.business.user.dao.UserRoleDao;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, RoleDO> implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    RoleMenuDao roleMenuMapper;
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;
    
    /**
     * 查找所有角色列表
     * @return 角色列表
     */
    @Cacheable(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Override
    public List<RoleDO> findAll() {
        List<RoleDO> roles = list(null);
        return roles;
    }
    
    /**
     * 根据用户id查询角色列表
     * @param userId 用户id
     * @return 角色列表
     */
    @Override
    public List<RoleDO> findListByUserId(Serializable userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<RoleDO> roles = list(null);
        for (RoleDO roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(roleDO.getId(), roleId)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    @Override
    public boolean save(RoleDO role) {
        int count = baseMapper.insert(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getId();
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        //删除原来的角色菜单
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
        	//批量插入角色菜单
            roleMenuMapper.batchSave(rms);
        }
        return retBool(count);
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    @Override
    public boolean removeById(Serializable id) {
        int count = baseMapper.deleteById(id);
        //删除原来的角色菜单
        roleMenuMapper.removeByRoleId(id);
        return retBool(count);
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    @Override
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        int count = baseMapper.deleteBatchIds(ids);
        //删除原来的角色菜单
        if(ids!=null&&ids.size()>0){
            ids.forEach(id->roleMenuMapper.removeByRoleId(id));
        }
        return retBool(count);
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Override
    public boolean updateById(RoleDO role) {
        int r = baseMapper.updateById(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getId();
        //删除原来的角色菜单
        roleMenuMapper.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
        	//批量插入角色菜单
            roleMenuMapper.batchSave(rms);
        }
        return retBool(r);
    }

}
