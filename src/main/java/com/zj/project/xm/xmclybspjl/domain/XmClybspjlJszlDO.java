package com.zj.project.xm.xmclybspjl.domain;

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
 * 材料样板审批记录-品牌及技术资料
 * </pre>
 * <small> 2018-10-13 18:36:00 | lijun</small>
 */
 @TableName("bj_xm_clybspjl_jszl")
 @ApiModel(value = "XmClybspjlJszlDO",description = "材料样板审批记录-品牌及技术资料")
public class XmClybspjlJszlDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @ApiModelProperty(value = "id",name = "id",dataType = "Long")
    @TableId
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    @ApiModelProperty(value = "逻辑废除：0（废除），1（正常）",name = "fcbz",dataType = "Integer",hidden = true)
    private Integer fcbz;
    /** 修改新增删除时间 */
    @ApiModelProperty(value = "修改新增删除时间",name = "gxsj",dataType = "Date",hidden = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date gxsj;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 材料样板审批记录id:bj_clybspjl表 */
    @ApiModelProperty(value = "材料样板审批记录id:bj_clybspjl表id",name = "intclybspjlid",dataType = "Long",required = true)
    private Long intclybspjlid;
    /** 品牌 */
    @ApiModelProperty(value = "品牌",name = "chrpp",dataType = "String",required = false)
    private String chrpp;
    /** 技术资料 */
    @ApiModelProperty(value = "技术资料",name = "chrjscl",dataType = "String",required = false)
    private String chrjscl;

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
     * 设置：材料样板审批记录id:bj_clybspjl表
     */
    public void setIntclybspjlid(Long intclybspjlid) {
        this.intclybspjlid = intclybspjlid;
    }
    /**
     * 获取：材料样板审批记录id:bj_clybspjl表
     */
    public Long getIntclybspjlid() {
        return intclybspjlid;
    }
    /**
     * 设置：品牌
     */
    public void setChrpp(String chrpp) {
        this.chrpp = chrpp;
    }
    /**
     * 获取：品牌
     */
    public String getChrpp() {
        return chrpp;
    }
    /**
     * 设置：技术资料
     */
    public void setChrjscl(String chrjscl) {
        this.chrjscl = chrjscl;
    }
    /**
     * 获取：技术资料
     */
    public String getChrjscl() {
        return chrjscl;
    }
}
