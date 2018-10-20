package com.zj.project.xm.xmzpjl.domain;

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
 * 项目基本信息-照片记录
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
 @TableName("bj_xm_zpjl")
 @ApiModel(value = "XmZpjlDO",description = "项目基本信息-照片记录")
public class XmZpjlDO extends BaseDomain {
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
    @ApiModelProperty(value = "修改新增删除时间",name = "gxsj",dataType = "Date",hidden = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date gxsj;
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目基本信息id",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 报告日期 */
    @ApiModelProperty(value = "报告日期",name = "dtmbgrq",dataType = "string",required = true,example = "2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmbgrq;
    /** 1（整体形象进度），2（栋楼形象进度），3（隐蔽工程形象进度） */
    @ApiModelProperty(value = "1（整体形象进度），2（栋楼形象进度），3（隐蔽工程形象进度）",name = "intbglb",dataType = "Integer",required = true)
    private Integer intbglb;
    /** 1（整体形象进度），2（栋楼形象进度），3（隐蔽工程形象进度） */
    @TableField(exist=false)
    @ApiModelProperty(value = "1（整体形象进度），2（栋楼形象进度），3（隐蔽工程形象进度）",name = "chrbglb",dataType = "string",required = true)
    private String chrbglb;
    /** 根据页面提交存储 */
    @ApiModelProperty(value = "根据页面提交存储",name = "chrpswz",dataType = "string",required = true)
    private String chrpswz;
    /** 位置描述 */
    @TableField(exist=false)
    @ApiModelProperty(value = "位置描述",name = "chrpswzms",dataType = "string",required = true)
    private String chrpswzms;
    /**其他描述*/
    @ApiModelProperty(value = "其他描述",name = "chrqtms",dataType = "string",required = true)
    private String chrqtms;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id",name = "intbgrid",dataType = "Long",required = false)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称",name = "chrbgrmc",dataType = "String",required = false)
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
     * 设置：1（整体形象进度），2（栋楼形象进度），3（隐蔽工程形象进度）
     */
    public void setIntbglb(Integer intbglb) {
		this.intbglb = intbglb;
	}
    /**
     * 获取：1（整体形象进度），2（栋楼形象进度），3（隐蔽工程形象进度）
     */
    public Integer getIntbglb() {
        return intbglb;
    }
    /**
     * 设置：根据页面提交存储
     */
    public void setChrpswz(String chrpswz) {
        this.chrpswz = chrpswz;
    }
    /**
     * 获取：根据页面提交存储
     */
    public String getChrpswz() {
        return chrpswz;
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
	public String getChrbglb() {
		return chrbglb;
	}
	public void setChrbglb(String chrbglb) {
		this.chrbglb = chrbglb;
	}
	public String getChrpswzms() {
		return chrpswzms;
	}
	public void setChrpswzms(String chrpswzms) {
		this.chrpswzms = chrpswzms;
	}
	/**
	 * 获取其他描述
	 */
	public String getChrqtms() {
		return chrqtms;
	}
	/**
	 * 设置其他描述
	 */
	public void setChrqtms(String chrqtms) {
		this.chrqtms = chrqtms;
	}
	
    
    
    
}
