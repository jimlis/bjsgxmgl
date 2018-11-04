package com.zj.project.xm.xmsgjd.sgjdjcsgnew.domain;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zj.platform.common.web.domain.BaseDomain;
import com.zj.project.xm.xmsgjd.sgjdjcsgkz.domain.XmSgjdJcsgKzDO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * <pre>
 * 施工进度-基础施工新
 * </pre>
 * <small> 2018-11-04 20:58:53 | lijun</small>
 */
 @TableName("bj_xm_sgjd_jcsgnew")
 @ApiModel(value = "XmSgjdJcsgnewDO",description = "施工进度-基础施工新")
public class XmSgjdJcsgnewDO extends BaseDomain {
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
    /** 报告日期 */
    @ApiModelProperty(value = "报告日期 ",name = "dtmbgrq",dataType = "String",hidden = true,example = "eg:2018-10-12 09:05:26")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmbgrq;
    /** 施工位置：栋楼表id，-1代表其他使用下面描述字段 */
    @ApiModelProperty(value = "施工位置：栋楼表id，-1代表其他使用下面描述字段 ",name = "intsgwzid",dataType = "Long",required = true)
    private Long intsgwzid;
    /** 施工位置：栋楼表id，-1代表其他使用下面描述字段 */
    @ApiModelProperty(value = "施工位置：栋楼表id，-1代表其他使用下面描述字段 ",name = "chrsgwz",dataType = "String",required = true)
    private String chrsgwz;
    /** 施工位置描述 */
    @ApiModelProperty(value = "施工位置描述 ",name = "chrsgwzms",dataType = "String",required = false)
    private String chrsgwzms;
    /** 是否完成：0（未完成），1（完成） */
    @ApiModelProperty(value = "是否完成：0（未完成），1（完成） ",name = "intsfwc",dataType = "Integer",required = true)
    private Integer intsfwc;
    /** 是否完成：0（未完成），1（完成） */
    @ApiModelProperty(value = "是否完成：0（未完成），1（完成） ",name = "intsfwc",dataType = "String",required = true)
    private String chrsfwc;
    /** 完成日期 */
    @ApiModelProperty(value = "完成日期 ",name = "dtmwcrq",dataType = "String",hidden = true,example = "eg:2018-10-12 09:05:26")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmwcrq;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id ",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称 ",name = "chrbgrmc",dataType = "String",required = true)
    private String chrbgrmc;
    /**独立基础*/
    @ApiModelProperty(value = "独立基础集合 ",name = "dljcs",dataType = "List",required = false)
    @TableField(exist=false)
    private List<XmSgjdJcsgKzDO> dljcs;
    /**桩基础*/
    @ApiModelProperty(value = "桩基础集合 ",name = "zjcs",dataType = "List",required = false)
    @TableField(exist=false)
    private List<XmSgjdJcsgKzDO> zjcs;

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
     * 设置：施工位置：栋楼表id，-1代表其他使用下面描述字段
     */
    public void setIntsgwzid(Long intsgwzid) {
        this.intsgwzid = intsgwzid;
    }
    /**
     * 获取：施工位置：栋楼表id，-1代表其他使用下面描述字段
     */
    public Long getIntsgwzid() {
        return intsgwzid;
    }
    /**
     * 设置：施工位置描述
     */
    public void setChrsgwzms(String chrsgwzms) {
        this.chrsgwzms = chrsgwzms;
    }
    /**
     * 获取：施工位置描述
     */
    public String getChrsgwzms() {
        return chrsgwzms;
    }
    /**
     * 设置：是否完成：0（未完成），1（完成）
     */
    public void setIntsfwc(Integer intsfwc) {
        this.intsfwc = intsfwc;
    }
    /**
     * 获取：是否完成：0（未完成），1（完成）
     */
    public Integer getIntsfwc() {
        return intsfwc;
    }
    /**
     * 设置：完成日期
     */
    public void setDtmwcrq(Date dtmwcrq) {
        this.dtmwcrq = dtmwcrq;
    }
    /**
     * 获取：完成日期
     */
    public Date getDtmwcrq() {
        return dtmwcrq;
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
	public List<XmSgjdJcsgKzDO> getDljcs() {
		return dljcs;
	}
	public void setDljcs(List<XmSgjdJcsgKzDO> dljcs) {
		this.dljcs = dljcs;
	}
	public List<XmSgjdJcsgKzDO> getZjcs() {
		return zjcs;
	}
	public void setZjcs(List<XmSgjdJcsgKzDO> zjcs) {
		this.zjcs = zjcs;
	}
	public String getChrsgwz() {
		return chrsgwz;
	}
	public void setChrsgwz(String chrsgwz) {
		this.chrsgwz = chrsgwz;
	}
	public String getChrsfwc() {
		return chrsfwc;
	}
	public void setChrsfwc(String chrsfwc) {
		this.chrsfwc = chrsfwc;
	}
	
	
}
