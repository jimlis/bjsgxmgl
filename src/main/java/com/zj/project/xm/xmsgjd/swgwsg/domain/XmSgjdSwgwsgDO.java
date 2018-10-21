package com.zj.project.xm.xmsgjd.swgwsg.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
 * 施工进度-室外管网施工
 * </pre>
 * <small> 2018-10-14 10:20:56 | lijun</small>
 */
 @TableName("bj_xm_sgjd_swgwsg")
 @ApiModel(value = "XmSgjdSwgwsgDO",description = "施工进度-室外管网施工")
public class XmSgjdSwgwsgDO extends BaseDomain {
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
    /** 更新日期 */
    @ApiModelProperty(value = "更新日期",name = "dtmgxrq",dataType = "string",required = true,example = "2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmgxrq;
    /** csd图纸通过审批日期 */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "审批日期",name = "dtmsprq",dataType = "String",required = false,example="2018-10-12")
    private Date dtmsprq;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id ",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称 ",name = "chrbgrmc",dataType = "String",required = true)
    private String chrbgrmc;
    
    @TableField(exist=false)
    /**施工进度listMap*/
    @ApiModelProperty(value = "施工进度listMap",name = "xmSgjdSwgwsgJdListMap",dataType = "map",required = false)
    Map<Long,List<XmSgjdSwgwsgJdDO>> xmSgjdSwgwsgJdListMap;
    
    @ApiModelProperty(value = "施工类型listMap",name = "xmSgjdSwgwsgLxMap",dataType = "map",required = false)
    /**施工类型listMap*/
    @TableField(exist=false)
    Map<Long,XmSgjdSwgwlxDO> xmSgjdSwgwsgLxMap;

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
     * 设置：csd图纸通过审批日期
     */
    public void setDtmsprq(Date dtmsprq) {
        this.dtmsprq = dtmsprq;
    }
    /**
     * 获取：csd图纸通过审批日期
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
	public Map<Long,List<XmSgjdSwgwsgJdDO>> getXmSgjdSwgwsgJdListMap() {
		return xmSgjdSwgwsgJdListMap;
	}
	public void setXmSgjdSwgwsgJdListMap(Map<Long,List<XmSgjdSwgwsgJdDO>> xmSgjdSwgwsgJdListMap) {
		this.xmSgjdSwgwsgJdListMap = xmSgjdSwgwsgJdListMap;
	}
	public Map<Long, XmSgjdSwgwlxDO> getXmSgjdSwgwsgLxMap() {
		return xmSgjdSwgwsgLxMap;
	}
	public void setXmSgjdSwgwsgLxMap(Map<Long, XmSgjdSwgwlxDO> xmSgjdSwgwsgLxMap) {
		this.xmSgjdSwgwsgLxMap = xmSgjdSwgwsgLxMap;
	}
    
}
