package com.zj.platform.business.config.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;

import java.util.Date;


/**
 * 系统配置（参数表）
 */
@TableName("sys_config")
public class ConfigDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /**  */
    @TableId
    private Long id;
    /** 键 */
    private String k;
    /** 值 */
    private String v;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    private Date createTime;
    /** 类型 */
    private Integer kvType;
    
    
    @Override
    public String toString() {
        return "ConfigDO{" +
                "id=" + id +
                ", k='" + k + '\'' +
                ", v='" + v + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", kvType=" + kvType +
                '}';
    }
    
    /**
     * 获取kv类型
     */
    public Integer getKvType() {
        return kvType;
    }
    
    /**
     * 设置kv类型
     */
    public void setKvType(Integer kvType) {
        this.kvType = kvType;
    }
    
    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * 获取：id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * 设置：id
     */
    public void setK(String k) {
        this.k = k;
    }
    
    /**
     * 获取：键
     */
    public String getK() {
        return k;
    }
    
    /**
     * 设置：键
     */
    public void setV(String v) {
        this.v = v;
    }
    
    /**
     * 获取：值
     */
    public String getV() {
        return v;
    }
    
    /**
     * 设置：值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置：备注
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

}
