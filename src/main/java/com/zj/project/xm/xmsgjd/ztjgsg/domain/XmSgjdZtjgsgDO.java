package com.zj.project.xm.xmsgjd.ztjgsg.domain;

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
 * 施工进度-主体结构施工
 * </pre>
 * <small> 2018-10-13 20:03:27 | lijun</small>
 */
 @TableName("bj_xm_sgjd_ztjgsg")
 @ApiModel(value = "XmSgjdZtjgsgDO",description = "施工进度-主体结构施工")
public class XmSgjdZtjgsgDO extends BaseDomain {
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
    private Date gxsj;
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目基本信息id",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 报告日期 */
    @ApiModelProperty(value = "报告日期",name = "dtmbgrq",dataType = "string",required = true,example = "2018-10-12")
    private Date dtmbgrq;
    /** 施工位置（栋）:栋楼表id */
    @ApiModelProperty(value = "施工位置（栋）:栋楼表id",name = "intsgwzd",dataType = "Long",required = true)
    private Long intsgwzd;
    /** 施工位置（层）：层表id */
    @ApiModelProperty(value = "施工位置（层）：层表id",name = "intsgwzc",dataType = "Long",required = true)
    private Long intsgwzc;
    /** 浇筑砼日期 */
    @ApiModelProperty(value = "浇筑砼日期",name = "dtmjzqrq",dataType = "string",required = false,example = "2018-10-12")
    private Date dtmjzqrq;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称",name = "chrbgrmc",dataType = "String",required = true)
    private String chrbgrmc;

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
     * 设置：报告日期
     */
    public void setDtmbgrq(Date dtmbgrq) {
        this.dtmbgrq = dtmbgrq;
    }
    /**
     * 获取：报告日期
     */
    public Date getDtmbgrq() {
        return dtmbgrq;
    }
    /**
     * 设置：施工位置（栋）:栋楼表id
     */
    public void setIntsgwzd(Long intsgwzd) {
        this.intsgwzd = intsgwzd;
    }
    /**
     * 获取：施工位置（栋）:栋楼表id
     */
    public Long getIntsgwzd() {
        return intsgwzd;
    }
    /**
     * 设置：施工位置（层）：层表id
     */
    public void setIntsgwzc(Long intsgwzc) {
        this.intsgwzc = intsgwzc;
    }
    /**
     * 获取：施工位置（层）：层表id
     */
    public Long getIntsgwzc() {
        return intsgwzc;
    }
    /**
     * 设置：浇筑砼日期
     */
    public void setDtmjzqrq(Date dtmjzqrq) {
        this.dtmjzqrq = dtmjzqrq;
    }
    /**
     * 获取：浇筑砼日期
     */
    public Date getDtmjzqrq() {
        return dtmjzqrq;
    }
    /**
     * 设置：报告人id
     */
    public void setIntbgrid(Long intbgrid) {
        this.intbgrid = intbgrid;
    }
    /**
     * 获取：报告人id
     */
    public Long getIntbgrid() {
        return intbgrid;
    }
    /**
     * 设置：报告人名称
     */
    public void setChrbgrmc(String chrbgrmc) {
        this.chrbgrmc = chrbgrmc;
    }
    /**
     * 获取：报告人名称
     */
    public String getChrbgrmc() {
        return chrbgrmc;
    }
}
