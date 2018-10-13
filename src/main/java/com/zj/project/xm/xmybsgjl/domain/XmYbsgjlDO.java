package com.zj.project.xm.xmybsgjl.domain;

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
 * 样板施工记录
 * </pre>
 * <small> 2018-10-13 00:54:47 | lijun</small>
 */
 @TableName("bj_xm_ybsgjl")
 @ApiModel(value = "XmYbsgjlDO",description = "样板施工记录")
public class XmYbsgjlDO extends BaseDomain {
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
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目id:bj_xmjb表 ",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 序号 */
    @ApiModelProperty(value = "序号 ",name = "intxh",dataType = "Integer",hidden = true)
    private Integer intxh;
    /** 更新日期 */
    @ApiModelProperty(value = "报告日期 ",name = "dtmgxrq",dataType = "string",example = "2018-10-13",required = true)
    private Date dtmgxrq;
    /** 样板施工完成日期 */
    @ApiModelProperty(value = "样板施工完成日期 ",name = "dtmwcrq",dataType = "string",example = "2018-10-13",required = true)
    private Date dtmwcrq;
    /** 现场样板类型code码：1（土建），2（机电），3（装修），4（园林），5（其他） */
    @ApiModelProperty(value = "现场样板类型code码：1（土建），2（机电），3（装修），4（园林），5（其他）  ",name = "intyblx",dataType = "string",required = true)
    private String intyblx;
    /** 样板描述 */
    @ApiModelProperty(value = "样板描述 ",name = "chrybms",dataType = "string")
    private String chrybms;
    /** 样板位置 */
    @ApiModelProperty(value = "样板位置 ",name = "chrybwz",dataType = "string")
    private String chrybwz;
    /** 通过审批日期 */
    @ApiModelProperty(value = "通过审批日期 ",name = "dtmsprq",dataType = "string",example = "2018-10-13",required = true)
    private Date dtmsprq;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id ",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称 ",name = "chrbgrmc",dataType = "string",required = true)
    private String chrbgrmc;
    /**备注*/
    @ApiModelProperty(value = "备注 ",name = "chrbz",dataType = "string",required = false)
    private  String chrbz;
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
     * 设置：更新日期
     */
    public void setDtmgxrq(Date dtmgxrq) {
        this.dtmgxrq = dtmgxrq;
    }
    /**
     * 获取：更新日期
     */
    public Date getDtmgxrq() {
        return dtmgxrq;
    }
    /**
     * 设置：样板施工完成日期
     */
    public void setDtmwcrq(Date dtmwcrq) {
        this.dtmwcrq = dtmwcrq;
    }
    /**
     * 获取：样板施工完成日期
     */
    public Date getDtmwcrq() {
        return dtmwcrq;
    }
    /**
     * 设置：现场样板类型code码：1（土建），2（机电），3（装修），4（园林），5（其他）
     */
    public void setIntyblx(String intyblx) {
        this.intyblx = intyblx;
    }
    /**
     * 获取：现场样板类型code码：1（土建），2（机电），3（装修），4（园林），5（其他）
     */
    public String getIntyblx() {
        return intyblx;
    }
    /**
     * 设置：样板描述
     */
    public void setChrybms(String chrybms) {
        this.chrybms = chrybms;
    }
    /**
     * 获取：样板描述
     */
    public String getChrybms() {
        return chrybms;
    }
    /**
     * 设置：样板位置
     */
    public void setChrybwz(String chrybwz) {
        this.chrybwz = chrybwz;
    }
    /**
     * 获取：样板位置
     */
    public String getChrybwz() {
        return chrybwz;
    }
    /**
     * 设置：通过审批日期
     */
    public void setDtmsprq(Date dtmsprq) {
        this.dtmsprq = dtmsprq;
    }
    /**
     * 获取：通过审批日期
     */
    public Date getDtmsprq() {
        return dtmsprq;
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

    /**
     * 获取备注
     */
    public String getChrbz() {
        return chrbz;
    }

    /**
     * 设置备注
     */
    public void setChrbz(String chrbz) {
        this.chrbz = chrbz;
    }
}
