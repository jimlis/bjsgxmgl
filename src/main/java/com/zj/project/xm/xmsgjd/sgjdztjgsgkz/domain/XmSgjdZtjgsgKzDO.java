package com.zj.project.xm.xmsgjd.sgjdztjgsgkz.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;



/**
 * 
 * <pre>
 * 施工进度-主体施工扩展
 * </pre>
 * <small> 2018-11-05 19:27:20 | lijun</small>
 */
 @TableName("bj_xm_sgjd_ztjgsg_kz")
public class XmSgjdZtjgsgKzDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @TableId
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    private Integer fcbz;
    /** 修改新增删除时间 */
    private Date gxsj;
    /** 序号 */
    private Integer intxh;
    /** 项目基本信息id */
    private Long intxmid;
    /** 主体施工id */
    private Long intztsgid;
    /** 工期节点比较id */
    private Long intgqjdbjid;
    /** 节点名称 */
    private String chrjdmc;
    /** 完成时间日期 */
    private Date dtmwcsj;

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
     * 设置：项目基本信息id
     */
    public void setIntxmid(Long intxmid) {
        this.intxmid = intxmid;
    }
    /**
     * 获取：项目基本信息id
     */
    public Long getIntxmid() {
        return intxmid;
    }
    /**
     * 设置：主体施工id
     */
    public void setIntztsgid(Long intztsgid) {
        this.intztsgid = intztsgid;
    }
    /**
     * 获取：主体施工id
     */
    public Long getIntztsgid() {
        return intztsgid;
    }
    /**
     * 设置：工期节点比较id
     */
    public void setIntgqjdbjid(Long intgqjdbjid) {
        this.intgqjdbjid = intgqjdbjid;
    }
    /**
     * 获取：工期节点比较id
     */
    public Long getIntgqjdbjid() {
        return intgqjdbjid;
    }
    /**
     * 设置：节点名称
     */
    public void setChrjdmc(String chrjdmc) {
        this.chrjdmc = chrjdmc;
    }
    /**
     * 获取：节点名称
     */
    public String getChrjdmc() {
        return chrjdmc;
    }
    /**
     * 设置：完成时间日期
     */
    public void setDtmwcsj(Date dtmwcsj) {
        this.dtmwcsj = dtmwcsj;
    }
    /**
     * 获取：完成时间日期
     */
    public Date getDtmwcsj() {
        return dtmwcsj;
    }
}
