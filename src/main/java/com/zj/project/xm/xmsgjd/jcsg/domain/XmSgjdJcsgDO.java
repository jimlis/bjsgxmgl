package com.zj.project.xm.xmsgjd.jcsg.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * <pre>
 * 项目基本信息-施工进度-基础施工
 * </pre>
 * <small> 2018-10-13 19:32:53 | lijun</small>
 */
 @TableName("bj_xm_sgjd_jcsg")
 @ApiModel(value = "XmSgjdJcsgDO",description = "项目基本信息-施工进度-基础施工")
public class XmSgjdJcsgDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @TableId
    @ApiModelProperty(value = "id",name = "id",dataType = "Long")
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    @ApiModelProperty(value = "废除标志",name = "fcbz",dataType = "Integer",hidden = true)
    private Integer fcbz;
    /** 修改新增删除时间 */
    @ApiModelProperty(value = "更新时间",name = "gxsj",dataType = "String",hidden = true,example = "eg:2018-10-12 09:05:26")
    private Date gxsj;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目基本信息id",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 报告日期 */
    @ApiModelProperty(value = "报告日期",name = "dtmbgrq",dataType = "string",required = true,example = "2018-10-12")
    private Date dtmbgrq;
    /** 施工位置：栋楼表id */
    @ApiModelProperty(value = "施工位置：栋楼表id",name = "intsgwzid",dataType = "Long",required = true)
    private Long intsgwzid;
    /** 施工位置描述 */
    @ApiModelProperty(value = "施工位置描述",name = "chrsgwzms",dataType = "String",required = true)
    private Long chrsgwzms;
    /** 基础类型：1（独立基础），2（筏板），3（桩基础） */
    @ApiModelProperty(value = "基础类型：1（独立基础），2（筏板），3（桩基础）",name = "intjclx",dataType = "Integer",required = true)
    private Integer intjclx;
    /** 如果基础类型为桩基础则需要填总数量 */
    @ApiModelProperty(value = "如果基础类型为桩基础则需要填总数量",name = "intzjczsl",dataType = "Integer",required = false)
    private Integer intzjczsl;
    /** 如果基础类型为桩基础则需要填完成数量 */
    @ApiModelProperty(value = "如果基础类型为桩基础则需要填完成数量",name = "intzjcwcl",dataType = "Integer",required = false)
    private Integer intzjcwcl;
    /** 完成量（百分比） */
    @ApiModelProperty(value = "完成量（百分比）",name = "intwcl",dataType = "Float",required = false)
    private Float intwcl;
    /** 浇筑砼日期 */
    @ApiModelProperty(value = "浇筑砼日期 ",name = "dtmjzqrq",dataType = "string",required = false,example = "2018-10-12")
    private Date dtmjzqrq;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id ",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称 ",name = "chrbgrmc",dataType = "String",required = true)
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
     * 设置：报告日期
     */
    public void setDtmbgrq(Date dtmbgrq) {
        this.dtmbgrq = dtmbgrq;
    }
    /**
     * 获取：报告日期
     */
    public Date getDtmbgrq() {
        return dtmbgrq;
    }
    /**
     * 设置：施工位置：栋楼表id
     */
    public void setIntsgwzid(Long intsgwzid) {
        this.intsgwzid = intsgwzid;
    }
    /**
     * 获取：施工位置：栋楼表id
     */
    public Long getIntsgwzid() {
        return intsgwzid;
    }
    /**
     * 设置：基础类型：1（独立基础），2（筏板），3（桩基础）
     */
    public void setIntjclx(Integer intjclx) {
        this.intjclx = intjclx;
    }
    /**
     * 获取：基础类型：1（独立基础），2（筏板），3（桩基础）
     */
    public Integer getIntjclx() {
        return intjclx;
    }
    /**
     * 设置：如果基础类型为桩基础则需要填总数量
     */
    public void setIntzjczsl(Integer intzjczsl) {
        this.intzjczsl = intzjczsl;
    }
    /**
     * 获取：如果基础类型为桩基础则需要填总数量
     */
    public Integer getIntzjczsl() {
        return intzjczsl;
    }
    /**
     * 设置：如果基础类型为桩基础则需要填完成数量
     */
    public void setIntzjcwcl(Integer intzjcwcl) {
        this.intzjcwcl = intzjcwcl;
    }
    /**
     * 获取：如果基础类型为桩基础则需要填完成数量
     */
    public Integer getIntzjcwcl() {
        return intzjcwcl;
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
     * 设置：浇筑砼日期
     */
    public void setDtmjzqrq(Date dtmjzqrq) {
        this.dtmjzqrq = dtmjzqrq;
    }
    /**
     * 获取：浇筑砼日期
     */
    public Date getDtmjzqrq() {
        return dtmjzqrq;
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
     * 获取 施工位置描述
     */
	public Long getChrsgwzms() {
		return chrsgwzms;
	}
	
	/**
     * 设置 施工位置描述
     */
	public void setChrsgwzms(Long chrsgwzms) {
		this.chrsgwzms = chrsgwzms;
	}
    
}
