package com.zj.platform.business.user.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;

/**
 * 用户角色
 */
@TableName("sys_user_role")
public class UserRoleDO extends BaseDomain {
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;
    
    /**
     * 获取用户角色id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * 设置用户角色id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * 获取用户id
     */
    public Long getUserId() {
        return userId;
    }
    
    /**
     * 设置用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    /**
     * 获取角色id
     */
    public Long getRoleId() {
        return roleId;
    }
    
    /**
     * 设置角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
