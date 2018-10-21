package com.zj.project.xm.xmgckyzfqk.domain;

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
 * 工程款与支付情况:工程款申请/支付情况
 * </pre>
 * <small> 2018-10-14 10:40:37 | lijun</small>
 */
 @TableName("bj_xm_gckyzfqk")
 @ApiModel(value = "XmGckyzfqkDO",description = "工程款与支付情况:工程款申请/支付情况")
public class XmGckyzfqkDO extends BaseDomain {
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
    @ApiModelProperty(value = "修改新增删除时间",name = "gxsj",dataType = "String",hidden = true,example = "eg:2018-10-12 09:05:26")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date gxsj;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目基本信息id",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 更新日期 */
    @ApiModelProperty(value = "更新日期",name = "dtmgxrq",dataType = "string",required = true,example = "2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmgxrq;
    /** 单位类型：1（顾问单位）2（施工单位）3（其他单位） */
    @ApiModelProperty(value = "单位类型：1（顾问单位）2（施工单位）3（其他单位）",name = "intdwlx",dataType = "Integer",required = true)
    private Integer intdwlx;
    @ApiModelProperty(value = "单位类型名称",name = "chrdwlx",dataType = "String",required = true)
    @TableField(exist=false)
    private String chrdwlx;
    /** 单位名称：单位名单表id */
    @ApiModelProperty(value = "单位名称：单位名单表id",name = "intdwmcid",dataType = "Long",required = true)
    private Long intdwmcid;
    /**单位名称名称*/
    @TableField(exist=false)
    @ApiModelProperty(value = "单位名称名称",name = "chrdwmcmc",dataType = "String",required = true)
    private String chrdwmc;
    /** 本次申请期数 */
    @ApiModelProperty(value = "本次申请期数",name = "intbcsqqs",dataType = "Integer",required = false)
    private Integer intbcsqqs;
    /** 本期申请金额 */
    @ApiModelProperty(value = "本期申请金额 ",name = "intbqsqje",dataType = "Float",required = false)
    private Float intbqsqje;
    /** 本期核实发放金额 */
    @ApiModelProperty(value = "本期核实发放金额 ",name = "intbqhsffje",dataType = "Float",required = false)
    private Float intbqhsffje;
    /** 合同金额 */
    @ApiModelProperty(value = "合同金额 ",name = "inthtje",dataType = "Float",required = false)
    private Float inthtje;
    /** 备注 */
    @ApiModelProperty(value = "备注 ",name = "chrbz",dataType = "String",required = false)
    private String chrbz;
    /** 支付证书：文件地址，多个以英文逗号隔开 */
    @ApiModelProperty(value = "支付证书：文件地址，多个以英文逗号隔开 ",name = "chrzfzs",dataType = "String",required = false,hidden=true)
    private String chrzfzs;
    /** 审批流程状态：code码配置 */
    @ApiModelProperty(value = "审批流程状态：code码配置 ",name = "intsplcztid",dataType = "String",required = false)
    private String intsplcztid;
    @TableField(exist=false)
    @ApiModelProperty(value = "审批流程状态 ",name = "chrsplczt",dataType = "String",required = false)
    private String chrsplczt;
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
     * 设置：单位类型：1（顾问单位）2（施工单位）3（其他单位）
     */
    public void setIntdwlx(Integer intdwlx) {
        this.intdwlx = intdwlx;
    }
    /**
     * 获取：单位类型：1（顾问单位）2（施工单位）3（其他单位）
     */
    public Integer getIntdwlx() {
        return intdwlx;
    }
    /**
     * 设置：单位名称：单位名单表id
     */
    public void setIntdwmcid(Long intdwmcid) {
        this.intdwmcid = intdwmcid;
    }
    /**
     * 获取：单位名称：单位名单表id
     */
    public Long getIntdwmcid() {
        return intdwmcid;
    }
    /**
     * 设置：本次申请期数
     */
    public void setIntbcsqqs(Integer intbcsqqs) {
        this.intbcsqqs = intbcsqqs;
    }
    /**
     * 获取：本次申请期数
     */
    public Integer getIntbcsqqs() {
        return intbcsqqs;
    }
    /**
     * 设置：本期申请金额
     */
    public void setIntbqsqje(Float intbqsqje) {
        this.intbqsqje = intbqsqje;
    }
    /**
     * 获取：本期申请金额
     */
    public Float getIntbqsqje() {
        return intbqsqje;
    }
    /**
     * 设置：本期核实发放金额
     */
    public void setIntbqhsffje(Float intbqhsffje) {
        this.intbqhsffje = intbqhsffje;
    }
    /**
     * 获取：本期核实发放金额
     */
    public Float getIntbqhsffje() {
        return intbqhsffje;
    }
    /**
     * 设置：合同金额
     */
    public void setInthtje(Float inthtje) {
        this.inthtje = inthtje;
    }
    /**
     * 获取：合同金额
     */
    public Float getInthtje() {
        return inthtje;
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
     * 设置：支付证书：文件地址，多个以英文逗号隔开
     */
    public void setChrzfzs(String chrzfzs) {
        this.chrzfzs = chrzfzs;
    }
    /**
     * 获取：支付证书：文件地址，多个以英文逗号隔开
     */
    public String getChrzfzs() {
        return chrzfzs;
    }
    /**
     * 设置：审批流程状态：code码配置
     */
    public void setIntsplcztid(String intsplcztid) {
        this.intsplcztid = intsplcztid;
    }
    /**
     * 获取：审批流程状态：code码配置
     */
    public String getIntsplcztid() {
        return intsplcztid;
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
	public String getChrdwlx() {
		return chrdwlx;
	}
	public void setChrdwlx(String chrdwlx) {
		this.chrdwlx = chrdwlx;
	}
	public String getChrdwmc() {
		return chrdwmc;
	}
	public void setChrdwmc(String chrdwmc) {
		this.chrdwmc = chrdwmc;
	}
	public String getChrsplczt() {
		return chrsplczt;
	}
	public void setChrsplczt(String chrsplczt) {
		this.chrsplczt = chrsplczt;
	}
}
