package com.zj.project.xm.xmsgjd.ylsg.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;



/**
 * 
 * <pre>
 * 施工进度-园林施工-进度
 * </pre>
 * <small> 2018-10-20 23:35:49 | lijun</small>
 */
 @TableName("bj_xm_sgjd_ylsg_jd")
public class XmSgjdYlsgJdDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @TableId
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    private Integer fcbz;
    /** 修改新增删除时间 */
    private Date gxsj;
    /** 园林施工id:bj_xm_sgjd_ylsg表 */
    private Long intylsgid;
    /** 序号 */
    private Integer intxh;
    /** 园林施工类型名称 */
    private String intlxmc;
    /** 完成量（百分比） */
    private Float intwcl;
    /** 备注 */
    private String chrzb;

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
     * 设置：园林施工id:bj_xm_sgjd_ylsg表
     */
    public void setIntylsgid(Long intylsgid) {
        this.intylsgid = intylsgid;
    }
    /**
     * 获取：园林施工id:bj_xm_sgjd_ylsg表
     */
    public Long getIntylsgid() {
        return intylsgid;
    }
    /**
     * 设置：序号
     */
    public void setIntxh(Integer intxh) {
        this.intxh = intxh;
    }
    /**
     * 获取：序号
     */
    public Integer getIntxh() {
        return intxh;
    }
    /**
     * 设置：园林施工类型名称
     */
    public void setIntlxmc(String intlxmc) {
        this.intlxmc = intlxmc;
    }
    /**
     * 获取：园林施工类型名称
     */
    public String getIntlxmc() {
        return intlxmc;
    }
    /**
     * 设置：完成量（百分比）
     */
    public void setIntwcl(Float intwcl) {
        this.intwcl = intwcl;
    }
    /**
     * 获取：完成量（百分比）
     */
    public Float getIntwcl() {
        return intwcl;
    }
    /**
     * 设置：备注
     */
    public void setChrzb(String chrzb) {
        this.chrzb = chrzb;
    }
    /**
     * 获取：备注
     */
    public String getChrzb() {
        return chrzb;
    }
}
