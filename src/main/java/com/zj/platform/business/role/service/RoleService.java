package com.zj.platform.business.role.service;

import com.zj.platform.business.role.domain.RoleDO;
import com.zj.platform.common.web.service.BaseService;

import java.io.Serializable;
import java.util.List;


public interface RoleService extends BaseService<RoleDO> {
	/**
     * 查找所有角色列表
     */
    List<RoleDO> findAll();
    
    /**
     * 根据用户id查询角色列表
     * @param id 用户id
     * @return 角色列表
     */
    List<RoleDO> findListByUserId(Serializable id);
}
