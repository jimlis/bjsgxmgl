package com.zj.project.xm.xmsgjd.swgwsg.domain;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.common.web.domain.BaseDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * <pre>
 * 施工进度-室外管网施工
 * </pre>
 * <small> 2018-10-14 10:20:56 | lijun</small>
 */
 @TableName("bj_xm_sgjd_swgwsg")
 @ApiModel(value = "XmSgjdSwgwsgDO",description = "施工进度-室外管网施工")
public class XmSgjdSwgwsgDO extends BaseDomain {
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
    /** 最新版csd图纸:文件地址id，逗号分隔多个文件 */
    @ApiModelProperty(value = "最新版csd图纸:文件地址id，逗号分隔多个文件 ",name = "chrcsdtz",dataType = "String",required = false,hidden=true)
    private String chrcsdtz;
    /** csd图纸通过审批日期 */
    @ApiModelProperty(value = "审批日期",name = "dtmsprq",dataType = "String",required = false,example="2018-10-12")
    private Date dtmsprq;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id ",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称 ",name = "chrbgrmc",dataType = "String",required = true)
    private String chrbgrmc;
    /** 室外管网类型id：code码配置 */
    @ApiModelProperty(value = "室外管网类型id：code码配置 ",name = "intlxid",dataType = "String",required = true)
    private String intlxid;
    /** 施工区域 */
    @ApiModelProperty(value = "施工区域 ",name = "chrsgqy",dataType = "String",required = true)
    private String chrsgqy;
    /** 完成量（百分比） */
    @ApiModelProperty(value = " 完成量（百分比） ",name = "intwcl",dataType = "Float",required = true)
    private Float intwcl;
    /** 完成情况：文件地址，多个英文逗号隔开 */
    @ApiModelProperty(value = "完成情况：文件地址，多个英文逗号隔开 ",name = "chrwcqk",dataType = "String",required = false,hidden=true)
    private String chrwcqk;
    /** 备注 */
    @ApiModelProperty(value = "备注 ",name = "chrzb",dataType = "String",required = false)
    private String chrbz;
    
    @TableField(exist=false)
    /**完成情况list*/
    @ApiModelProperty(value = "完成情况list",name = "wcqkList",dataType = "List",required = false)
    List<FileDO> wcqkList;

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
     * 设置：最新版csd图纸:文件地址id，逗号分隔多个文件
     */
    public void setChrcsdtz(String chrcsdtz) {
        this.chrcsdtz = chrcsdtz;
    }
    /**
     * 获取：最新版csd图纸:文件地址id，逗号分隔多个文件
     */
    public String getChrcsdtz() {
        return chrcsdtz;
    }
    /**
     * 设置：csd图纸通过审批日期
     */
    public void setDtmsprq(Date dtmsprq) {
        this.dtmsprq = dtmsprq;
    }
    /**
     * 获取：csd图纸通过审批日期
     */
    public Date getDtmsprq() {
        return dtmsprq;
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
     * 设置：室外管网类型id：code码配置
     */
    public void setIntlxid(String intlxid) {
        this.intlxid = intlxid;
    }
    /**
     * 获取：室外管网类型id：code码配置
     */
    public String getIntlxid() {
        return intlxid;
    }
    /**
     * 设置：施工区域
     */
    public void setChrsgqy(String chrsgqy) {
        this.chrsgqy = chrsgqy;
    }
    /**
     * 获取：施工区域
     */
    public String getChrsgqy() {
        return chrsgqy;
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
     * 设置：完成情况：文件地址，多个英文逗号隔开
     */
    public void setChrwcqk(String chrwcqk) {
        this.chrwcqk = chrwcqk;
    }
    /**
     * 获取：完成情况：文件地址，多个英文逗号隔开
     */
    public String getChrwcqk() {
        return chrwcqk;
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
     * 获取完成情况
     */
	public List<FileDO> getWcqkList() {
		return wcqkList;
	}
	
	 /**
     * 设置完成情况
     */
	public void setWcqkList(List<FileDO> wcqkList) {
		this.wcqkList = wcqkList;
	}
}
