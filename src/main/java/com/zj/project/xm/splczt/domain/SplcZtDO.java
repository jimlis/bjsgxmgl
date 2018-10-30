package com.zj.project.xm.splczt.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * <pre>
 * 审批流程状态
 * </pre>
 * <small> 2018-10-30 22:57:34 | lijun</small>
 */
 @TableName("bj_splc_zt")
 @ApiModel(value = "SplcZtDO",description = "审批流程状态")
public class SplcZtDO extends BaseDomain {
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
    @ApiModelProperty(value = "修改新增删除时间 ",name = "gxsj",dataType = "Date",hidden = true)
    private Date gxsj;
    /** 父节点id */
    @ApiModelProperty(value = "父节点id ",name = "intfjdid",dataType = "Long",hidden = true)
    private Long intfjdid;
    /** 序号 */
    @ApiModelProperty(value = "序号 ",name = "intxh",dataType = "Integer",hidden = true)
    private Integer intxh;
    /** 节点名称 */
    @ApiModelProperty(value = "节点名称 ",name = "chrjdmc",dataType = "String",hidden = true)
    private String chrjdmc;
    /** 审批人id */
    @ApiModelProperty(value = "审批人id ",name = "intsprid",dataType = "Long",hidden = true)
    private Long intsprid;
    /** 审批人名称 */
    @ApiModelProperty(value = "审批人名称  ",name = "chrsprmc",dataType = "String",required=true)
    private String chrsprmc;
    /** 钉钉UserId，用于发消息 */
    @ApiModelProperty(value = "钉钉UserId，用于发消息  ",name = "chruserid",dataType = "String")
    private String chruserid;
    /** 项目id（预留） */
    @ApiModelProperty(value = "项目id（预留） ",name = "intxmid",dataType = "Long")
    private Long intxmid;
    /** 审批流程类型 */
    @ApiModelProperty(value = "审批流程类型 ",name = "chrsplclx",dataType = "String",required=true)
    private String chrsplclx;

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
     * 设置：父节点id
     */
    public void setIntfjdid(Long intfjdid) {
        this.intfjdid = intfjdid;
    }
    /**
     * 获取：父节点id
     */
    public Long getIntfjdid() {
        return intfjdid;
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
     * 设置：审批人id
     */
    public void setIntsprid(Long intsprid) {
        this.intsprid = intsprid;
    }
    /**
     * 获取：审批人id
     */
    public Long getIntsprid() {
        return intsprid;
    }
    /**
     * 设置：审批人名称
     */
    public void setChrsprmc(String chrsprmc) {
        this.chrsprmc = chrsprmc;
    }
    /**
     * 获取：审批人名称
     */
    public String getChrsprmc() {
        return chrsprmc;
    }
    /**
     * 设置：钉钉UserId，用于发消息
     */
    public void setChruserid(String chruserid) {
        this.chruserid = chruserid;
    }
    /**
     * 获取：钉钉UserId，用于发消息
     */
    public String getChruserid() {
        return chruserid;
    }
    /**
     * 设置：项目id（预留）
     */
    public void setIntxmid(Long intxmid) {
        this.intxmid = intxmid;
    }
    /**
     * 获取：项目id（预留）
     */
    public Long getIntxmid() {
        return intxmid;
    }
    /**
     * 设置：审批流程类型
     */
    public void setChrsplclx(String chrsplclx) {
        this.chrsplclx = chrsplclx;
    }
    /**
     * 获取：审批流程类型
     */
    public String getChrsplclx() {
        return chrsplclx;
    }
}
