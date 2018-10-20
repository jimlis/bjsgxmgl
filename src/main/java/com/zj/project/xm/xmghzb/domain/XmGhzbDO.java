package com.zj.project.xm.xmghzb.domain;

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
 * 项目基本信息-规划指标数据
 * </pre>
 * <small> 2018-10-04 18:35:49 | lijun</small>
 */
 @TableName("bj_xm_ghzb")
 @ApiModel(value = "XmGhzbDO",description = "项目基本信息-规划指标数据")
public class XmGhzbDO extends BaseDomain {
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
    @ApiModelProperty(value = "序号  ",name = "intxh",dataType = "Integer",required = false,hidden=true)
    private Integer intxh;
    /** 项目id:bj_xmjb */
    @ApiModelProperty(value = "项目id:bj_xmjb表 ",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 指标名称 */
    @ApiModelProperty(value = "指标名称  ",name = "chrzbmc",dataType = "String",required = true)
    private String chrzbmc;
    /** 指标值 */
    @ApiModelProperty(value = "指标值  ",name = "chrzbz",dataType = "String",required = true)
    private String chrzbz;
    /** 值单位 */
    @ApiModelProperty(value = "值单位 ",name = "chrzdw",dataType = "String",required = false)
    private String chrzdw;

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
     * 设置：项目id:bj_xmjb
     */
    public void setIntxmid(Long intxmid) {
        this.intxmid = intxmid;
    }
    /**
     * 获取：项目id:bj_xmjb
     */
    public Long getIntxmid() {
        return intxmid;
    }
    /**
     * 设置：指标名称
     */
    public void setChrzbmc(String chrzbmc) {
        this.chrzbmc = chrzbmc;
    }
    /**
     * 获取：指标名称
     */
    public String getChrzbmc() {
        return chrzbmc;
    }
    /**
     * 设置：指标值
     */
    public void setChrzbz(String chrzbz) {
        this.chrzbz = chrzbz;
    }
    /**
     * 获取：指标值
     */
    public String getChrzbz() {
        return chrzbz;
    }
    /**
     * 设置：值单位
     */
    public void setChrzdw(String chrzdw) {
        this.chrzdw = chrzdw;
    }
    /**
     * 获取：值单位
     */
    public String getChrzdw() {
        return chrzdw;
    }
}
