package com.zj.project.xm.xmsgjd.ecjgzx.domain;

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
 * 施工进度-二次结构、装修等施工-完成量
 * </pre>
 * <small> 2018-10-13 20:45:57 | lijun</small>
 */
 @TableName("bj_xm_sgjd_ecjgzx_wcl")
 @ApiModel(value = "XmSgjdEcjgzxWclDO",description = "施工进度-二次结构、装修等施工-完成量")
public class XmSgjdEcjgzxWclDO extends BaseDomain {
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
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "修改新增删除时间",name = "gxsj",dataType = "Date",hidden = true)
    private Date gxsj;
    /** 二次结构、装修施工id:bj_sgjd_ecjgzx表id */
    @ApiModelProperty(value = "二次结构、装修施工id:bj_sgjd_ecjgzx表id",name = "intecjgzxid",dataType = "Long",required = true)
    private Long intecjgzxid;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 楼层 */
    @ApiModelProperty(value = "楼层",name = "intlc",dataType = "Integer",required = true)
    private Integer intlc;
    /** 门窗（百分比） */
    @ApiModelProperty(value = "门窗（百分比）",name = "intmc",dataType = "Float",required = true)
    private Float intmc;
    /** 防水（百分比） */
    @ApiModelProperty(value = "防水（百分比）",name = "intfs",dataType = "Float",required = true)
    private Float intfs;
    /** 内保温（百分比） */
    @ApiModelProperty(value = "内保温（百分比）",name = "intnbw",dataType = "Float",required = true)
    private Float intnbw;
    /** 砌体（百分比） */
    @ApiModelProperty(value = "砌体（百分比）",name = "intqt",dataType = "Float",required = true)
    private Float intqt;
    /** 装修（百分比） */
    @ApiModelProperty(value = "装修（百分比）",name = "intzx",dataType = "Float",required = true)
    private Float intzx;

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
     * 设置：二次结构、装修施工id:bj_sgjd_ecjgzx表id
     */
    public void setIntecjgzxid(Long intecjgzxid) {
        this.intecjgzxid = intecjgzxid;
    }
    /**
     * 获取：二次结构、装修施工id:bj_sgjd_ecjgzx表id
     */
    public Long getIntecjgzxid() {
        return intecjgzxid;
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
     * 设置：楼层
     */
    public void setIntlc(Integer intlc) {
        this.intlc = intlc;
    }
    /**
     * 获取：楼层
     */
    public Integer getIntlc() {
        return intlc;
    }
    /**
     * 设置：门窗（百分比）
     */
    public void setIntmc(Float intmc) {
        this.intmc = intmc;
    }
    /**
     * 获取：门窗（百分比）
     */
    public Float getIntmc() {
        return intmc;
    }
    /**
     * 设置：防水（百分比）
     */
    public void setIntfs(Float intfs) {
        this.intfs = intfs;
    }
    /**
     * 获取：防水（百分比）
     */
    public Float getIntfs() {
        return intfs;
    }
    /**
     * 设置：内保温（百分比）
     */
    public void setIntnbw(Float intnbw) {
        this.intnbw = intnbw;
    }
    /**
     * 获取：内保温（百分比）
     */
    public Float getIntnbw() {
        return intnbw;
    }
    /**
     * 设置：砌体（百分比）
     */
    public void setIntqt(Float intqt) {
        this.intqt = intqt;
    }
    /**
     * 获取：砌体（百分比）
     */
    public Float getIntqt() {
        return intqt;
    }
    /**
     * 设置：装修（百分比）
     */
    public void setIntzx(Float intzx) {
        this.intzx = intzx;
    }
    /**
     * 获取：装修（百分比）
     */
    public Float getIntzx() {
        return intzx;
    }
}
