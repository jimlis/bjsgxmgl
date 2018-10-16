package com.zj.project.xm.xmzfxcyzxys.domain;

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
 * 政府巡查与专项验收：政府部门巡查/专项验收记录
 * </pre>
 * <small> 2018-10-06 09:34:15 | lijun</small>
 */
 @TableName("bj_xm_zfxcyzxys")
 @ApiModel(value = "XmZfxcyzxysDO",description = "政府巡查与专项验收：政府部门巡查/专项验收记录")
public class XmZfxcyzxysDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @TableId
    @ApiModelProperty(value = "id",name = "id",dataType = "Long")
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    @ApiModelProperty(value = "逻辑废除：0（废除），1（正常）",name = "fcbz",dataType = "Integer")
    private Integer fcbz;
    /** 修改新增删除时间 */
    @ApiModelProperty(value = "修改新增删除时间",name = "gxsj",dataType = "Date")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date gxsj;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer")
    private Integer intxh;
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目基本信息id",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 更新日期 */
    @ApiModelProperty(value = "更新日期",name = "dtmgxrq",dataType = "string",required = true,example = "2018-10-16")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmgxrq;
    /** 巡查类别：code码 */
    @ApiModelProperty(value = "巡查类别",name = "intxclb",dataType = "string",required = true)
    private String intxclb;
    /** 巡查类别名称 */
    @TableField(exist=false)
    @ApiModelProperty(value = "巡查类别名称",name = "chrxclb",dataType = "string",required = true)
    private String chrxclb;
    /** 巡查部门：code码 */
    @ApiModelProperty(value = "巡查部门",name = "intxcbm",dataType = "string",required = true)
    private String intxcbm;
    /** 巡查部门名称 */
    @TableField(exist=false)
    @ApiModelProperty(value = "巡查部门",name = "chrxcbm",dataType = "string",required = true)
    private String chrxcbm;
    /** 巡查人员 */
    @ApiModelProperty(value = "巡查人员",name = "chrxcry",dataType = "string",required = true)
    private String chrxcry;
    /** 巡查日期 */
    @ApiModelProperty(value = "巡查日期",name = "dtmxcrq",dataType = "string",required = true,example = "2018-10-16")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmxcrq;
    /** 备注 */
    @ApiModelProperty(value = "备注",name = "chrzb",dataType = "string",required = false)
    private String chrzb;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称",name = "chrbgrmc",dataType = "string",required = true)
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
     * 设置：巡查类别：code码
     */
    public void setIntxclb(String intxclb) {
        this.intxclb = intxclb;
    }
    /**
     * 获取：巡查类别：code码
     */
    public String getIntxclb() {
        return intxclb;
    }
    /**
     * 设置：巡查部门：code码
     */
    public void setIntxcbm(String intxcbm) {
        this.intxcbm = intxcbm;
    }
    /**
     * 获取：巡查部门：code码
     */
    public String getIntxcbm() {
        return intxcbm;
    }
    /**
     * 设置：巡查人员
     */
    public void setChrxcry(String chrxcry) {
        this.chrxcry = chrxcry;
    }
    /**
     * 获取：巡查人员
     */
    public String getChrxcry() {
        return chrxcry;
    }
    /**
     * 设置：巡查日期
     */
    public void setDtmxcrq(Date dtmxcrq) {
        this.dtmxcrq = dtmxcrq;
    }
    /**
     * 获取：巡查日期
     */
    public Date getDtmxcrq() {
        return dtmxcrq;
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
	public String getChrxclb() {
		return chrxclb;
	}
	public void setChrxclb(String chrxclb) {
		this.chrxclb = chrxclb;
	}
	public String getChrxcbm() {
		return chrxcbm;
	}
	public void setChrxcbm(String chrxcbm) {
		this.chrxcbm = chrxcbm;
	}
    
}
