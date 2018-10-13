package com.zj.project.xm.xmsgjd.ecjgzx.domain;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * <pre>
 * 二次结构装修：施工进度-二次结构、装修等施工
 * </pre>
 * <small> 2018-10-13 20:45:56 | lijun</small>
 */
 @TableName("bj_xm_sgjd_ecjgzx")
 @ApiModel(value = "XmSgjdEcjgzxDO",description = "二次结构装修：施工进度-二次结构、装修等施工")
public class XmSgjdEcjgzxDO extends BaseDomain {
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
    private Date gxsj;
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目基本信息id",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",required = false,hidden = true)
    private Integer intxh;
    /** 更新日期 */
    @ApiModelProperty(value = "更新日期",name = "dtmgxrq",dataType = "String",required = false,hidden = true,example="2018-10-12")
    private Date dtmgxrq;
    /** 栋楼id：栋楼表id */
    @ApiModelProperty(value = "栋楼id：栋楼表id ",name = "intdid",dataType = "Long",required = true)
    private Long intdid;
    /**备注*/
    @ApiModelProperty(value = "备注 ",name = "chrbz",dataType = "String",required = false)
    private String chrbz;
    
    /**
     * 施工进度-二次结构、装修等施工-完成量集合信息
     */
    @TableField(exist=false)
    private List<XmSgjdEcjgzxWclDO>  xmSgjdEcjgzxWclList;

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
     * 设置：栋楼id：栋楼表id
     */
    public void setIntdid(Long intdid) {
        this.intdid = intdid;
    }
    /**
     * 获取：栋楼id：栋楼表id
     */
    public Long getIntdid() {
        return intdid;
    }
    
    /**
     * 获取施工进度-二次结构、装修等施工-完成量集合信息
     */
	public List<XmSgjdEcjgzxWclDO> getXmSgjdEcjgzxWclList() {
		return xmSgjdEcjgzxWclList;
	}
	
	/**
     * 设置施工进度-二次结构、装修等施工-完成量集合信息
     */
	public void setXmSgjdEcjgzxWclList(List<XmSgjdEcjgzxWclDO> xmSgjdEcjgzxWclList) {
		this.xmSgjdEcjgzxWclList = xmSgjdEcjgzxWclList;
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
