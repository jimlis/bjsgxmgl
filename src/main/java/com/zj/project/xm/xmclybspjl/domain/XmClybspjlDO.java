package com.zj.project.xm.xmclybspjl.domain;

import java.util.Date;
import java.util.List;

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
 * 材料样板审批记录
 * </pre>
 * <small> 2018-10-13 18:35:59 | lijun</small>
 */
 @TableName("bj_xm_clybspjl")
 @ApiModel(value = "XmClybspjlDO",description = " 材料样板审批记录")
public class XmClybspjlDO extends BaseDomain {
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
    @ApiModelProperty(value = "修改新增删除时间",name = "gxsj",dataType = "Date",hidden = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date gxsj;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目基本信息id",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 更新日期 */
    @ApiModelProperty(value = "报告日期",name = "dtmbgrq",dataType = "string",required = true,example = "2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmgxrq;
    /** 材料样板类型：1（土建）2（机电）3（装修）4（园林）5（其他） */
    @ApiModelProperty(value = "材料样板类型：1（土建）2（机电）3（装修）4（园林）5（其他",name = "intclyblx",dataType = "Integer",required = true)
    private Integer intclyblx;
    /** 施工负责单位：单位名单表施工类别id */
    @ApiModelProperty(value = "施工负责单位：单位名单表施工类别id",name = "intsgdw",dataType = "Long",required = true)
    private Long intsgdw;
    /** 是否代替品：1（是），0（否） */
    @ApiModelProperty(value = "是否代替品：1（是），0（否）",name = "intsfdtp",dataType = "Integer",required = false)
    private Integer intsfdtp;
    /** 材料样板名称 */
    @ApiModelProperty(value = "材料样板名称",name = "chrybmc",dataType = "String",required = false)
    private String chrybmc;
    /** 材料使用位置 */
    @ApiModelProperty(value = "材料使用位置",name = "chrybwz",dataType = "String",required = false)
    private String chrybwz;
    /** 规范标准 */
    @ApiModelProperty(value = "规范标准",name = "chrgfbz",dataType = "String",required = false)
    private String chrgfbz;
    /** 备注 */
    @ApiModelProperty(value = "备注",name = "chrbz",dataType = "String",required = false)
    private String chrbz;
    /** 材料样板文件：文件地址id，多个英文逗号隔开 */
    @ApiModelProperty(value = "材料样板文件：文件地址id，多个英文逗号隔开",name = "chrcyybwj",dataType = "String",required = false,hidden=true)
    private String chrcyybwj;
    /** 审批流程状态：code码配置 */
    @ApiModelProperty(value = "审批流程状态：code码配置",name = "intsplczt",dataType = "String",required = true)
    private String intsplczt;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称",name = "chrbgrmc",dataType = "String",required = true)
    private String chrbgrmc;
    
    /**
     * 材料样板审批记录对应品牌及技术资料集合
     */
    @TableField(exist=false)
    private List<XmClybspjlJszlDO> xmClybspjlJszlList;

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
     * 设置：材料样板类型：1（土建）2（机电）3（装修）4（园林）5（其他）
     */
    public void setIntclyblx(Integer intclyblx) {
        this.intclyblx = intclyblx;
    }
    /**
     * 获取：材料样板类型：1（土建）2（机电）3（装修）4（园林）5（其他）
     */
    public Integer getIntclyblx() {
        return intclyblx;
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
     * 设置：是否代替品：1（是），0（否）
     */
    public void setIntsfdtp(Integer intsfdtp) {
        this.intsfdtp = intsfdtp;
    }
    /**
     * 获取：是否代替品：1（是），0（否）
     */
    public Integer getIntsfdtp() {
        return intsfdtp;
    }
    /**
     * 设置：材料样板名称
     */
    public void setChrybmc(String chrybmc) {
        this.chrybmc = chrybmc;
    }
    /**
     * 获取：材料样板名称
     */
    public String getChrybmc() {
        return chrybmc;
    }
    /**
     * 设置：材料使用位置
     */
    public void setChrybwz(String chrybwz) {
        this.chrybwz = chrybwz;
    }
    /**
     * 获取：材料使用位置
     */
    public String getChrybwz() {
        return chrybwz;
    }
    /**
     * 设置：规范标准
     */
    public void setChrgfbz(String chrgfbz) {
        this.chrgfbz = chrgfbz;
    }
    /**
     * 获取：规范标准
     */
    public String getChrgfbz() {
        return chrgfbz;
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
     * 设置：材料样板文件：文件地址id，多个英文逗号隔开
     */
    public void setChrcyybwj(String chrcyybwj) {
        this.chrcyybwj = chrcyybwj;
    }
    /**
     * 获取：材料样板文件：文件地址id，多个英文逗号隔开
     */
    public String getChrcyybwj() {
        return chrcyybwj;
    }
    /**
     * 设置：审批流程状态：code码配置
     */
    public void setIntsplczt(String intsplczt) {
        this.intsplczt = intsplczt;
    }
    /**
     * 获取：审批流程状态：code码配置
     */
    public String getIntsplczt() {
        return intsplczt;
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
     * 获取材料样板审批记录对应品牌及技术资料集合
     */
	public List<XmClybspjlJszlDO> getXmClybspjlJszlList() {
		return xmClybspjlJszlList;
	}
	
	/**
     * 设置材料样板审批记录对应品牌及技术资料集合
     */
	public void setXmClybspjlJszlList(List<XmClybspjlJszlDO> xmClybspjlJszlList) {
		this.xmClybspjlJszlList = xmClybspjlJszlList;
	}
    
}
