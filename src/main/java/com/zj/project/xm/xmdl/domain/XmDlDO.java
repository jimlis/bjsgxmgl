package com.zj.project.xm.xmdl.domain;

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
 * 项目基本信息-栋楼,记录每栋楼名称及编号
 * </pre>
 * <small> 2018-09-28 22:39:21 | lijun</small>
 */
 @TableName("bj_xm_dl")
 @ApiModel(value = "XmDlDO",description = "项目基本信息-栋楼,记录每栋楼名称及编号")
public class XmDlDO extends BaseDomain {
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
    /** 项目id：bj_xmjb表 */
    @ApiModelProperty(value = "项目id：bj_xmjb表",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 栋楼名称 */
    @ApiModelProperty(value = "栋楼名称",name = "chrdlmc",dataType = "String",required = true)
    private String chrdlmc;
    /** 栋楼介绍 */
    @ApiModelProperty(value = "栋楼介绍",name = "chrdljs",dataType = "String",required = false)
    private String chrdljs;
    /** 层数 */
    @ApiModelProperty(value = "层数",name = "intcs",dataType = "Integer",required = true)
    private Integer intcs;

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
     * 设置：项目id：bj_xmjb表
     */
    public void setIntxmid(Long intxmid) {
        this.intxmid = intxmid;
    }
    /**
     * 获取：项目id：bj_xmjb表
     */
    public Long getIntxmid() {
        return intxmid;
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
     * 设置：栋楼名称
     */
    public void setChrdlmc(String chrdlmc) {
        this.chrdlmc = chrdlmc;
    }
    /**
     * 获取：栋楼名称
     */
    public String getChrdlmc() {
        return chrdlmc;
    }
    /**
     * 设置：栋楼介绍
     */
    public void setChrdljs(String chrdljs) {
        this.chrdljs = chrdljs;
    }
    /**
     * 获取：栋楼介绍
     */
    public String getChrdljs() {
        return chrdljs;
    }
    /**
     * 设置：层数
     */
    public void setIntcs(Integer intcs) {
        this.intcs = intcs;
    }
    /**
     * 获取：层数
     */
    public Integer getIntcs() {
        return intcs;
    }
}
