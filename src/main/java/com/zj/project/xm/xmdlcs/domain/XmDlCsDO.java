package com.zj.project.xm.xmdlcs.domain;

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
 * 项目基本信息-栋楼-层数,记录每层情况
 * </pre>
 * <small> 2018-10-13 20:26:30 | lijun</small>
 */
 @TableName("bj_xm_dl_cs")
 @ApiModel(value = "XmDlCsDO",description = "项目基本信息-栋楼-层数,记录每层情况")
public class XmDlCsDO extends BaseDomain {
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
    /** 栋楼id：bj_xm_dl表 */
    @ApiModelProperty(value = "栋楼id：bj_xm_dl表",name = "intxmdlid",dataType = "Long",required = true)
    private Long intxmdlid;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 层名称 */
    @ApiModelProperty(value = "层名称",name = "chrcmc",dataType = "String",required = true)
    private String chrcmc;
    /** 层介绍 */
    @ApiModelProperty(value = "层介绍",name = "chrcjs",dataType = "String",required = false)
    private String chrcjs;

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
     * 设置：栋楼id：bj_xm_dl表
     */
    public void setIntxmdlid(Long intxmdlid) {
        this.intxmdlid = intxmdlid;
    }
    /**
     * 获取：栋楼id：bj_xm_dl表
     */
    public Long getIntxmdlid() {
        return intxmdlid;
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
     * 设置：层名称
     */
    public void setChrcmc(String chrcmc) {
        this.chrcmc = chrcmc;
    }
    /**
     * 获取：层名称
     */
    public String getChrcmc() {
        return chrcmc;
    }
    /**
     * 设置：层介绍
     */
    public void setChrcjs(String chrcjs) {
        this.chrcjs = chrcjs;
    }
    /**
     * 获取：层介绍
     */
    public String getChrcjs() {
        return chrcjs;
    }
}
