package com.zj.project.xm.xmaqbg.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zj.platform.common.web.domain.BaseDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * <pre>
 * 安全报告
 * </pre>
 * <small> 2018-10-13 17:00:14 | lijun</small>
 */
 @TableName("bj_xm_aqbg")
 @ApiModel(value = "XmAqbgDO",description = "安全报告")
public class XmAqbgDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @TableId
    @ApiModelProperty(value = "id",name = "id",dataType = "Long")
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    @ApiModelProperty(value = "逻辑废除：0（废除），1（正常）",name = "fcbz",dataType = "Integer",hidden = true)
    private Integer fcbz;
    /** 修改新增删除时间 */
    @ApiModelProperty(value = "修改新增删除时间 ",name = "gxsj",dataType = "Date",hidden = true)
    private Date gxsj;
    /** 序号 */
    @ApiModelProperty(value = "序号 ",name = "intxh",dataType = "Integer",hidden = true)
    private Integer intxh;
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目基本信息id ",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 更新日期 */
    @ApiModelProperty(value = "更新日期 ",name = "dtmgxrq",dataType = "string",example = "2018-10-13",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmgxrq;
    /** 安全问题描述 */
    @ApiModelProperty(value = "安全问题描述 ",name = "chraqwtbs",dataType = "string",required = true)
    private String chraqwtbs;
    /** 安全问题位置 */
    @ApiModelProperty(value = "安全问题位置  ",name = "chraqwtwz",dataType = "string",required = true)
    private String chraqwtwz;
    /** 备注 */
    @ApiModelProperty(value = "备注  ",name = "chrzb",dataType = "string",required = false)
    private String chrzb;
    /** 施工负责单位：单位名单表施工类别id */
    @ApiModelProperty(value = "施工负责单位：单位名单表施工类别id",name = "intsgdw",dataType = "Long",required = true)
    private Long intsgdw;
    /** 施工负责单位名称 */
    @ApiModelProperty(value = "施工负责单位名称",name = "chrsgdw",dataType = "String")
    @TableField(exist=false)
    private String chrsgdw;
    /** 通知施工方日期 */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "通知施工方日期 ",name = "dtmtzrq",dataType = "string",example = "2018-10-13",required = true)
    private Date dtmtzrq;
    /** 完成整改日期 */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "整改完成日期 ",name = "dtmwczgrq",dataType = "string",example = "2018-10-13",required = true)
    private Date dtmwczgrq;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id ",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称 ",name = "chrbgrmc",dataType = "string",required = true)
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
     * 设置：安全问题描述
     */
    public void setChraqwtbs(String chraqwtbs) {
        this.chraqwtbs = chraqwtbs;
    }
    /**
     * 获取：安全问题描述
     */
    public String getChraqwtbs() {
        return chraqwtbs;
    }
    /**
     * 设置：安全问题位置
     */
    public void setChraqwtwz(String chraqwtwz) {
        this.chraqwtwz = chraqwtwz;
    }
    /**
     * 获取：安全问题位置
     */
    public String getChraqwtwz() {
        return chraqwtwz;
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
    /**
     * 设置：施工负责单位：单位名单表施工类别id
     */
    public void setIntsgdw(Long intsgdw) {
        this.intsgdw = intsgdw;
    }
    /**
     * 获取：施工负责单位：单位名单表施工类别id
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
     * 设置：完成整改日期
     */
    public void setDtmwczgrq(Date dtmwczgrq) {
        this.dtmwczgrq = dtmwczgrq;
    }
    /**
     * 获取：完成整改日期
     */
    public Date getDtmwczgrq() {
        return dtmwczgrq;
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
    
    /**
     * 获取施工单位名称
     */
	public String getChrsgdw() {
		return chrsgdw;
	}
	
	/**
     * 设置施工单位名称
     */
	public void setChrsgdw(String chrsgdw) {
		this.chrsgdw = chrsgdw;
	}
}
