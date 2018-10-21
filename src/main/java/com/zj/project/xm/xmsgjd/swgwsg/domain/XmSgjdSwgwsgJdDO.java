package com.zj.project.xm.xmsgjd.swgwsg.domain;

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
 * 施工进度-室外管网施工-进度，通过更新日期与类型生成多条进度内容。
 * </pre>
 * <small> 2018-10-20 20:40:33 | lijun</small>
 */
 @TableName("bj_xm_sgjd_swgwsg_jd")
 @ApiModel(value = "XmSgjdSwgwsgJdDO",description = "施工进度-室外管网施工-进度，通过更新日期与类型生成多条进度内容。")
public class XmSgjdSwgwsgJdDO extends BaseDomain {
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
    /** 室外管网施工id:bj_xm_sgjd_swgwsg表，用于关联更新日期 */
    @ApiModelProperty(value = "室外管网施工id:bj_xm_sgjd_swgwsg表，用于关联更新日期",name = "intswgwsgid",dataType = "Long",required = true)
    private Long intswgwsgid;
    /** 室外管网类型id：bj_xm_sgjd_swgwlx表，用于关联具体类型 */
    @ApiModelProperty(value = "室外管网类型id：bj_xm_sgjd_swgwlx表，用于关联具体类型 ",name = "intswgwlxid",dataType = "Long",required = true)
    private Long intswgwlxid;
    /**室外管网类型名称*/
    @ApiModelProperty(value = "室外管网类型名称 ",name = "chrswgwlxid",dataType = "String",required = true)
    @TableField(exist=false)
    private String chrswgwlxid;
    /** 施工区域 */
    @ApiModelProperty(value = "施工区域 ",name = "chrsgqy",dataType = "chrsgqy",required = false)
    private String chrsgqy;
    /** 完成量（百分比） */
    @ApiModelProperty(value = "完成量（百分比） ",name = "intwcl",dataType = "Float",required = false)
    private Float intwcl;
    /** 备注 */
    @ApiModelProperty(value = "备注",name = "chrbz",dataType = "String",required = false)
    private String chrbz;
    @ApiModelProperty(value = "完全情况关联附件ids",name = "fileIds",dataType = "String",required = false)
    @TableField(exist=false)
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
     * 设置：室外管网施工id:bj_xm_sgjd_swgwsg表，用于关联更新日期
     */
    public void setIntswgwsgid(Long intswgwsgid) {
        this.intswgwsgid = intswgwsgid;
    }
    /**
     * 获取：室外管网施工id:bj_xm_sgjd_swgwsg表，用于关联更新日期
     */
    public Long getIntswgwsgid() {
        return intswgwsgid;
    }
    /**
     * 设置：室外管网类型id：bj_xm_sgjd_swgwlx表，用于关联具体类型
     */
    public void setIntswgwlxid(Long intswgwlxid) {
        this.intswgwlxid = intswgwlxid;
    }
    /**
     * 获取：室外管网类型id：bj_xm_sgjd_swgwlx表，用于关联具体类型
     */
    public Long getIntswgwlxid() {
        return intswgwlxid;
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
     * 获取完成情况附件ids
     */
	public String getFileIds() {
		return fileIds;
	}
	/**
     * 设置完成情况附件ids
     */
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	/**
	 * 获取室外管网类型名称
	 */
	public String getChrswgwlxid() {
		return chrswgwlxid;
	}
	/**
	 * 设置室外管网类型名称
	 */
	public void setChrswgwlxid(String chrswgwlxid) {
		this.chrswgwlxid = chrswgwlxid;
	}
    
	
    
}
