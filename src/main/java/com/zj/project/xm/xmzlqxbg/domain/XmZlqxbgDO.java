package com.zj.project.xm.xmzlqxbg.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;



/**
 * 
 * <pre>
 * 质量缺陷报告
 * </pre>
 * <small> 2018-10-13 10:46:20 | lijun</small>
 */
 @TableName("bj_xm_zlqxbg")
public class XmZlqxbgDO extends BaseDomain {
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
    /** 更新日期 */
    private Date dtmgxrq;
    /** 质量缺陷类型：1(土建)2（机电）3（装修）4（园林）5（其他） */
    private String intqxlx;
    /** 质量缺陷描述 */
    private String chrqxms;
    /** 质量缺陷位置 */
    private String chrqxwz;
    /** 备注 */
    private String chrbz;
    /** 施工负责单位：单位名单表id */
    private Long intsgdw;
    /** 通知施工方日期 */
    private Date dtmtzrq;
    /** 整改完成日期 */
    private Date dtmzgwcrq;
    /** 报告人id */
    private Long intbgrid;
    /** 报告人名称 */
    private String chrbgrmc;

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
     * 设置：更新日期
     */
    public void setDtmgxrq(Date dtmgxrq) {
        this.dtmgxrq = dtmgxrq;
    }
    /**
     * 获取：更新日期
     */
    public Date getDtmgxrq() {
        return dtmgxrq;
    }
    /**
     * 设置：质量缺陷类型：1(土建)2（机电）3（装修）4（园林）5（其他）
     */
    public void setIntqxlx(String intqxlx) {
        this.intqxlx = intqxlx;
    }
    /**
     * 获取：质量缺陷类型：1(土建)2（机电）3（装修）4（园林）5（其他）
     */
    public String getIntqxlx() {
        return intqxlx;
    }
    /**
     * 设置：质量缺陷描述
     */
    public void setChrqxms(String chrqxms) {
        this.chrqxms = chrqxms;
    }
    /**
     * 获取：质量缺陷描述
     */
    public String getChrqxms() {
        return chrqxms;
    }
    /**
     * 设置：质量缺陷位置
     */
    public void setChrqxwz(String chrqxwz) {
        this.chrqxwz = chrqxwz;
    }
    /**
     * 获取：质量缺陷位置
     */
    public String getChrqxwz() {
        return chrqxwz;
    }
    /**
     * 设置：备注
     */
    public void setChrbz(String chrbz) {
        this.chrbz = chrbz;
    }
    /**
     * 获取：备注
     */
    public String getChrbz() {
        return chrbz;
    }
    /**
     * 设置：施工负责单位：单位名单表id
     */
    public void setIntsgdw(Long intsgdw) {
        this.intsgdw = intsgdw;
    }
    /**
     * 获取：施工负责单位：单位名单表id
     */
    public Long getIntsgdw() {
        return intsgdw;
    }
    /**
     * 设置：通知施工方日期
     */
    public void setDtmtzrq(Date dtmtzrq) {
        this.dtmtzrq = dtmtzrq;
    }
    /**
     * 获取：通知施工方日期
     */
    public Date getDtmtzrq() {
        return dtmtzrq;
    }
    /**
     * 设置：整改完成日期
     */
    public void setDtmzgwcrq(Date dtmzgwcrq) {
        this.dtmzgwcrq = dtmzgwcrq;
    }
    /**
     * 获取：整改完成日期
     */
    public Date getDtmzgwcrq() {
        return dtmzgwcrq;
    }
    /**
     * 设置：报告人id
     */
    public void setIntbgrid(Long intbgrid) {
        this.intbgrid = intbgrid;
    }
    /**
     * 获取：报告人id
     */
    public Long getIntbgrid() {
        return intbgrid;
    }
    /**
     * 设置：报告人名称
     */
    public void setChrbgrmc(String chrbgrmc) {
        this.chrbgrmc = chrbgrmc;
    }
    /**
     * 获取：报告人名称
     */
    public String getChrbgrmc() {
        return chrbgrmc;
    }
}
