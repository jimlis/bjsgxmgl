package com.zj.project.xm.xmsgjd.ylsg.domain;

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
 * 施工进度-园林施工-进度
 * </pre>
 * <small> 2018-10-20 23:35:49 | lijun</small>
 */
 @TableName("bj_xm_sgjd_ylsg_jd")
 @ApiModel(value = "XmSgjdYlsgJdDO",description = "施工进度-园林施工-进度")
public class XmSgjdYlsgJdDO extends BaseDomain {
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
    /** 园林施工id:bj_xm_sgjd_ylsg表 */
    @ApiModelProperty(value = "园林施工id:bj_xm_sgjd_ylsg表",name = "intylsgid",dataType = "Long",required = true)
    private Long intylsgid;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 园林施工类型名称 */
    @ApiModelProperty(value = "园林施工类型名称",name = "chrlxmc",dataType = "String",required = true)
    private String chrlxmc;
    /** 完成量（百分比） */
    @ApiModelProperty(value = " 完成量（百分比）",name = "intwcl",dataType = "Float",required = false)
    private Float intwcl;
    /** 备注 */
    @ApiModelProperty(value = "备注 ",name = "chrzb",dataType = "String",required = false)
    private String chrzb;
    
    @TableField(exist=false)
    @ApiModelProperty(value = "完成情况附件ids ",name = "fileIds",dataType = "String",required = false)
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
     * 设置：园林施工id:bj_xm_sgjd_ylsg表
     */
    public void setIntylsgid(Long intylsgid) {
        this.intylsgid = intylsgid;
    }
    /**
     * 获取：园林施工id:bj_xm_sgjd_ylsg表
     */
    public Long getIntylsgid() {
        return intylsgid;
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
     * 设置：园林施工类型名称
     */
    public void setChrlxmc(String chrlxmc) {
        this.chrlxmc = chrlxmc;
    }
    /**
     * 获取：园林施工类型名称
     */
    public String getChrlxmc() {
        return chrlxmc;
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
    public void setChrzb(String chrzb) {
        this.chrzb = chrzb;
    }
    /**
     * 获取：备注
     */
    public String getChrzb() {
        return chrzb;
    }
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
}
