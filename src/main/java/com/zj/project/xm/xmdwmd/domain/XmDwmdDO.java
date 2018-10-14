package com.zj.project.xm.xmdwmd.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;



/**
 * 
 * <pre>
 * 项目基本信息-所有单位名单
 * </pre>
 * <small> 2018-10-09 21:32:05 | lijun</small>
 */
 @TableName("bj_xm_dwmd")
 @ApiModel(value = "XmDwmdDO",description = "项目基本信息-所有单位名单")
public class XmDwmdDO extends BaseDomain {
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
    /** 项目id:bj_xmjb表 */
    @ApiModelProperty(value = "项目id:bj_xmjb表 ",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 序号 */
    @ApiModelProperty(value = "序号 ",name = "intxh",dataType = "Integer",hidden = true)
    private Integer intxh;
    /** 类型名单：1（顾问单位），2（施工单位），3（其他工作单位名称） */
    @ApiModelProperty(value = "类型名单：1（顾问单位），2（施工单位），3（其他工作单位名称） ",name = "intlxmd",dataType = "String",required = true)
    private String intlxmd;
    /** 单位类型id：code表码 */
    @ApiModelProperty(value = "单位类型id：code表码  ",name = "intdwlxid",dataType = "String",required = true)
    private String intdwlxid;
    @ApiModelProperty(value = "单位类型名称 ",name = "chrdwlxmc",dataType = "String",required = true)
    @TableField(exist=false)
    private String chrdwlxmc;
    /** 单位名称 */
    @ApiModelProperty(value = "单位名称  ",name = "chrdwmc",dataType = "String",required = true)
    private String chrdwmc;

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
     * 设置：项目id:bj_xmjb表
     */
    public void setIntxmid(Long intxmid) {
        this.intxmid = intxmid;
    }
    /**
     * 获取：项目id:bj_xmjb表
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
     * 设置：类型名单：1（顾问单位），2（施工单位），3（其他工作单位名称）
     */
    public void setIntlxmd(String intlxmd) {
        this.intlxmd = intlxmd;
    }
    /**
     * 获取：类型名单：1（顾问单位），2（施工单位），3（其他工作单位名称）
     */
    public String getIntlxmd() {
        return intlxmd;
    }
    /**
     * 设置：单位类型id：code表码
     */
    public void setIntdwlxid(String intdwlxid) {
        this.intdwlxid = intdwlxid;
    }
    /**
     * 获取：单位类型id：code表码
     */
    public String getIntdwlxid() {
        return intdwlxid;
    }
    /**
     * 设置：单位名称
     */
    public void setChrdwmc(String chrdwmc) {
        this.chrdwmc = chrdwmc;
    }
    /**
     * 获取：单位名称
     */
    public String getChrdwmc() {
        return chrdwmc;
    }
    
    /**
	 * 获取单位类型名称
	 */
	public String getChrdwlxmc() {
		return chrdwlxmc;
	}
	/**
	 * 设置单位类型名称
	 */
	public void setChrdwlxmc(String chrdwlxmc) {
		this.chrdwlxmc = chrdwlxmc;
	}
    
    
}
