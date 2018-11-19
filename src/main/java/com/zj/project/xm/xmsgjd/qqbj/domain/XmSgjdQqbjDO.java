package com.zj.project.xm.xmsgjd.qqbj.domain;

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
 * 项目基本信息-施工进度-前期报建
 * </pre>
 * <small> 2018-11-19 19:53:41 | lijun</small>
 */
 @TableName("bj_xm_sgjd_qqbj")
 @ApiModel(value = "XmSgjdQqbjDO",description = "项目基本信息-施工进度-前期报建")
public class XmSgjdQqbjDO extends BaseDomain {
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
    /** 报告日期 */
    @ApiModelProperty(value = "报告日期",name = "dtmbgrq",dataType = "string",required = true,example = "2018-10-16")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmbgrq;
    /** 报建id：工期节点比较id */
    @ApiModelProperty(value = "报建id：工期节点比较id ",name = "intgqjdid",dataType = "Long",required = true)
    private Long intgqjdid;
    /** 工期节点名称*/
    @ApiModelProperty(value = "工期节点名称",name = "chrgqjdmc",dataType = "String",required = false)
    @TableField(exist=false)
    private String chrgqjdmc;
    /** 完成日期 */
    @ApiModelProperty(value = "完成日期",name = "dtmwcrq",dataType = "string",required = true,example = "2018-10-16")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmwcrq;
    /** 备注 */
    @ApiModelProperty(value = "备注",name = "chrzb",dataType = "string",required = false)
    private String chrbz;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称",name = "chrbgrmc",dataType = "string",required = true)
    private String chrbgrmc;
    /** 附件ids */
    @TableField(exist=false)
    @ApiModelProperty(value = "附件ids",name = "fileIds",dataType = "String",required = false)
    private String fileIds;

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
     * 设置：报建id：工期节点比较id
     */
    public void setIntgqjdid(Long intgqjdid) {
        this.intgqjdid = intgqjdid;
    }
    /**
     * 获取：报建id：工期节点比较id
     */
    public Long getIntgqjdid() {
        return intgqjdid;
    }
    /**
     * 设置：完成日期
     */
    public void setDtmwcrq(Date dtmwcrq) {
        this.dtmwcrq = dtmwcrq;
    }
    /**
     * 获取：完成日期
     */
    public Date getDtmwcrq() {
        return dtmwcrq;
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
	public String getChrgqjdmc() {
		return chrgqjdmc;
	}
	public void setChrgqjdmc(String chrgqjdmc) {
		this.chrgqjdmc = chrgqjdmc;
	}
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
}
