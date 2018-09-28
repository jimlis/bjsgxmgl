package com.zj.project.xmjb.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;



/**
 * 
 * <pre>
 * 项目承建各方名称
 * </pre>
 * <small> 2018-09-25 22:20:27 | lijun</small>
 */
 @TableName("bj_xm_xmcjdw")
public class XmXmcjdwDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @TableId
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    private Integer fcbz;
    /** 修改新增删除时间 */
    private Date gxsj;
    /** 项目id */
    private Long intxmid;
    /** 建设单位名称 */
    private String chrcjdwmc;
    /** 代建单位名称 */
    private String chrdjdwmc;

    /**
     * 设置：主键id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取：主键id
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：逻辑废除：0（废除），1（正常）
     */
    public void setFcbz(Integer fcbz) {
        this.fcbz = fcbz;
    }
    /**
     * 获取：逻辑废除：0（废除），1（正常）
     */
    public Integer getFcbz() {
        return fcbz;
    }
    /**
     * 设置：修改新增删除时间
     */
    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }
    /**
     * 获取：修改新增删除时间
     */
    public Date getGxsj() {
        return gxsj;
    }
    /**
     * 设置：项目id
     */
    public void setIntxmid(Long intxmid) {
        this.intxmid = intxmid;
    }
    /**
     * 获取：项目id
     */
    public Long getIntxmid() {
        return intxmid;
    }
    /**
     * 设置：建设单位名称
     */
    public void setChrcjdwmc(String chrcjdwmc) {
        this.chrcjdwmc = chrcjdwmc;
    }
    /**
     * 获取：建设单位名称
     */
    public String getChrcjdwmc() {
        return chrcjdwmc;
    }
    /**
     * 设置：代建单位名称
     */
    public void setChrdjdwmc(String chrdjdwmc) {
        this.chrdjdwmc = chrdjdwmc;
    }
    /**
     * 获取：代建单位名称
     */
    public String getChrdjdwmc() {
        return chrdjdwmc;
    }
}
