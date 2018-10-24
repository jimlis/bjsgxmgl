package com.zj.project.xm.xmgqjdbj.domain;

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
 * 项目基本信息-主要工期节点比较
 * </pre>
 * <small> 2018-10-24 21:18:21 | lijun</small>
 */
 @TableName("bj_xm_gqjdbj")
 @ApiModel(value = "XmGqjdbjDO",description = " 项目基本信息-主要工期节点比较")
public class XmGqjdbjDO extends BaseDomain {
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
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目id",name = "intxmid",dataType = "Long",hidden = true,required = true)
    private Long intxmid;
    /** 节点类型 */
    @ApiModelProperty(value = "节点类型",name = "chrjdlx",dataType = "string",required = true)
    private String chrjdlx;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",hidden = true)
    private Integer intxh;
    /** 父级节点id */
    @ApiModelProperty(value = "父级节点id",name = "intfjdid",dataType = "Long",required = false)
    private Long intfjdid;
    /** 节点名称 */
    @ApiModelProperty(value = "节点名称",name = "chrjdmc",dataType = "String",required = true)
    private String chrjdmc;
    /** 计划完成时间 */
    @ApiModelProperty(value = "计划完成时间 ",name = "dtmjhwcsj",dataType = "String",required = false,example="2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmjhwcsj;
    /** 实际完成时间 */
    @ApiModelProperty(value = "实际完成时间 ",name = "dtmsjwcsj",dataType = "String",required = false,example="2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmsjwcsj;
    /** 时间提前与滞后比较 */
    @ApiModelProperty(value = "时间提前与滞后比较",name = "intsjbj",dataType = "Integer",hidden = false)
    private Integer intsjbj;

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
     * 设置：节点类型
     */
    public void setChrjdlx(String chrjdlx) {
        this.chrjdlx = chrjdlx;
    }
    /**
     * 获取：节点类型
     */
    public String getChrjdlx() {
        return chrjdlx;
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
     * 设置：父级节点id
     */
    public void setIntfjdid(Long intfjdid) {
        this.intfjdid = intfjdid;
    }
    /**
     * 获取：父级节点id
     */
    public Long getIntfjdid() {
        return intfjdid;
    }
    /**
     * 设置：节点名称
     */
    public void setChrjdmc(String chrjdmc) {
        this.chrjdmc = chrjdmc;
    }
    /**
     * 获取：节点名称
     */
    public String getChrjdmc() {
        return chrjdmc;
    }
    /**
     * 设置：计划完成时间
     */
    public void setDtmjhwcsj(Date dtmjhwcsj) {
        this.dtmjhwcsj = dtmjhwcsj;
    }
    /**
     * 获取：计划完成时间
     */
    public Date getDtmjhwcsj() {
        return dtmjhwcsj;
    }
    /**
     * 设置：实际完成时间
     */
    public void setDtmsjwcsj(Date dtmsjwcsj) {
        this.dtmsjwcsj = dtmsjwcsj;
    }
    /**
     * 获取：实际完成时间
     */
    public Date getDtmsjwcsj() {
        return dtmsjwcsj;
    }
    /**
     * 设置：时间提前与滞后比较
     */
    public void setIntsjbj(Integer intsjbj) {
        this.intsjbj = intsjbj;
    }
    /**
     * 获取：时间提前与滞后比较
     */
    public Integer getIntsjbj() {
        return intsjbj;
    }
}
