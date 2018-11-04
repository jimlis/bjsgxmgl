package com.zj.project.xm.xmsgjd.sgjdjcsgkz.domain;

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
 * 施工进度-基础施工扩展
 * </pre>
 * <small> 2018-11-04 20:55:42 | lijun</small>
 */
 @TableName("bj_xm_sgjd_jcsg_kz")
 @ApiModel(value = "XmSgjdJcsgKzDO",description = "施工进度-基础施工扩展")
public class XmSgjdJcsgKzDO extends BaseDomain {
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
    /** 基础施工id */
    @ApiModelProperty(value = "基础施工id",name = "intjcsgid",dataType = "Long",required = true)
    private Long intjcsgid;
    /** 类型：zjc、dljc */
    @ApiModelProperty(value = "类型：zjc、dljc",name = "chrjclx",dataType = "String",required = true)
    private String chrjclx;
    /** 基础名称 */
    @ApiModelProperty(value = "基础名称",name = "chrjcmc",dataType = "String",required = true)
    private String chrjcmc;
    /** 浇筑砼日期 */
    @ApiModelProperty(value = "浇筑砼日期",name = "dtmjzsrq",dataType = "String",hidden = true,example = "eg:2018-10-12 09:05:26")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmjzsrq;
    
    

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
     * 设置：基础施工id
     */
    public void setIntjcsgid(Long intjcsgid) {
        this.intjcsgid = intjcsgid;
    }
    /**
     * 获取：基础施工id
     */
    public Long getIntjcsgid() {
        return intjcsgid;
    }
    /**
     * 设置：类型：zjc、dljc
     */
    public void setChrjclx(String chrjclx) {
        this.chrjclx = chrjclx;
    }
    /**
     * 获取：类型：zjc、dljc
     */
    public String getChrjclx() {
        return chrjclx;
    }
    /**
     * 设置：基础名称
     */
    public void setChrjcmc(String chrjcmc) {
        this.chrjcmc = chrjcmc;
    }
    /**
     * 获取：基础名称
     */
    public String getChrjcmc() {
        return chrjcmc;
    }
    /**
     * 设置：浇筑砼日期
     */
    public void setDtmjzsrq(Date dtmjzsrq) {
        this.dtmjzsrq = dtmjzsrq;
    }
    /**
     * 获取：浇筑砼日期
     */
    public Date getDtmjzsrq() {
        return dtmjzsrq;
    }
}
