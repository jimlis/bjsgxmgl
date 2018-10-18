package com.zj.project.xm.xmsgjd.dtsbazsg.domain;

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
 * 施工进度-电梯设备安装施工
 * </pre>
 * <small> 2018-10-13 21:17:22 | lijun</small>
 */
 @TableName("bj_xm_sgjd_dtsbazsg")
 @ApiModel(value = "XmSgjdDtsbazsgDO",description = "施工进度-电梯设备安装施工")
public class XmSgjdDtsbazsgDO extends BaseDomain {
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
    /** 施工位置：栋楼表id */
    @ApiModelProperty(value = "施工位置：栋楼表id ",name = "intsgwz",dataType = "Long",required = true)
    private Long intsgwz;
    /** 施工位置*/
    @ApiModelProperty(value = "施工位置",name = "chrsgwz",dataType = "String",required = true)
    @TableField(exist=false)
    private String chrsgwz;
    /** 电梯编号 */
    @ApiModelProperty(value = "电梯编号",name = "chrdtbh",dataType = "String",required = true)
    private String chrdtbh;
    /** 电梯设备到货日期 */
    @ApiModelProperty(value = "电梯设备到货日期",name = "dtmdhrq",dataType = "String",required = true,example="2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmdhrq;
    /** 电梯井道移交日期 */
    @ApiModelProperty(value = "电梯井道移交日期",name = "dtmyjrq",dataType = "String",required = true,example="2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmyjrq;
    /** 电梯轨道完成日期 */
    @ApiModelProperty(value = "电梯轨道完成日期",name = "dtmwcrq",dataType = "String",required = true,example="2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmwcrq;
    /** 电梯门套完成比例(百分比) */
    @ApiModelProperty(value = "电梯门套完成比例(百分比)",name = "intwcbl",dataType = "Float",required = true)
    private Float intwcbl;
    /** 机房设备安装比例(百分比) */
    @ApiModelProperty(value = "机房设备安装比例(百分比)",name = "intazbl",dataType = "Float",required = true)
    private Float intazbl;
    /** 电梯试运行日期 */
    @ApiModelProperty(value = "电梯试运行日期",name = "dtmyxrq",dataType = "String",required = true,example="2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmyxrq;
    /** 电梯通过验收日期 */
    @ApiModelProperty(value = "电梯通过验收日期",name = "dtmysrq",dataType = "String",required = true,example="2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmysrq;
    /** 电梯验收合格证明:存放文件表id，多个用英文逗号隔开 */
    @ApiModelProperty(value = "电梯验收合格证明:存放文件表id，多个用英文逗号隔开",name = "chrhgzm",dataType = "String",required = false,hidden=true)
    private String chrhgzm;
    /** 合格证明到期日期 */
    @ApiModelProperty(value = "合格证明到期日期",name = "dtmdqrq",dataType = "String",required = true,example="2018-10-12")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmdqrq;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id",name = "intbgrid",dataType = "Long",required = true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称",name = "chrbgrmc",dataType = "String",required = true)
    private String chrbgrmc;
    /** 备注 */
    @ApiModelProperty(value = "备注 ",name = "chrbz",dataType = "String",required = false)
    private String chrbz;

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
     * 设置：施工位置：栋楼表id
     */
    public void setIntsgwz(Long intsgwz) {
        this.intsgwz = intsgwz;
    }
    /**
     * 获取：施工位置：栋楼表id
     */
    public Long getIntsgwz() {
        return intsgwz;
    }
    /**
     * 设置：电梯编号
     */
    public void setChrdtbh(String chrdtbh) {
        this.chrdtbh = chrdtbh;
    }
    /**
     * 获取：电梯编号
     */
    public String getChrdtbh() {
        return chrdtbh;
    }
    /**
     * 设置：电梯设备到货日期
     */
    public void setDtmdhrq(Date dtmdhrq) {
        this.dtmdhrq = dtmdhrq;
    }
    /**
     * 获取：电梯设备到货日期
     */
    public Date getDtmdhrq() {
        return dtmdhrq;
    }
    /**
     * 设置：电梯井道移交日期
     */
    public void setDtmyjrq(Date dtmyjrq) {
        this.dtmyjrq = dtmyjrq;
    }
    /**
     * 获取：电梯井道移交日期
     */
    public Date getDtmyjrq() {
        return dtmyjrq;
    }
    /**
     * 设置：电梯轨道完成日期
     */
    public void setDtmwcrq(Date dtmwcrq) {
        this.dtmwcrq = dtmwcrq;
    }
    /**
     * 获取：电梯轨道完成日期
     */
    public Date getDtmwcrq() {
        return dtmwcrq;
    }
    /**
     * 设置：电梯门套完成比例(百分比)
     */
    public void setIntwcbl(Float intwcbl) {
        this.intwcbl = intwcbl;
    }
    /**
     * 获取：电梯门套完成比例(百分比)
     */
    public Float getIntwcbl() {
        return intwcbl;
    }
    /**
     * 设置：机房设备安装比例(百分比)
     */
    public void setIntazbl(Float intazbl) {
        this.intazbl = intazbl;
    }
    /**
     * 获取：机房设备安装比例(百分比)
     */
    public Float getIntazbl() {
        return intazbl;
    }
    /**
     * 设置：电梯试运行日期
     */
    public void setDtmyxrq(Date dtmyxrq) {
        this.dtmyxrq = dtmyxrq;
    }
    /**
     * 获取：电梯试运行日期
     */
    public Date getDtmyxrq() {
        return dtmyxrq;
    }
    /**
     * 设置：电梯通过验收日期
     */
    public void setDtmysrq(Date dtmysrq) {
        this.dtmysrq = dtmysrq;
    }
    /**
     * 获取：电梯通过验收日期
     */
    public Date getDtmysrq() {
        return dtmysrq;
    }
    /**
     * 设置：电梯验收合格证明:存放文件表id，多个用英文逗号隔开
     */
    public void setChrhgzm(String chrhgzm) {
        this.chrhgzm = chrhgzm;
    }
    /**
     * 获取：电梯验收合格证明:存放文件表id，多个用英文逗号隔开
     */
    public String getChrhgzm() {
        return chrhgzm;
    }
    /**
     * 设置：合格证明到期日期
     */
    public void setDtmdqrq(Date dtmdqrq) {
        this.dtmdqrq = dtmdqrq;
    }
    /**
     * 获取：合格证明到期日期
     */
    public Date getDtmdqrq() {
        return dtmdqrq;
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
     * 设置：备注
     */
    public void setChrbz(String chrbz) {
        this.chrbz = chrbz;
    }
    /**
     * 获取：备注
     */
    public String getChrbz() {
        return chrbz;
    }
	public String getChrsgwz() {
		return chrsgwz;
	}
	public void setChrsgwz(String chrsgwz) {
		this.chrsgwz = chrsgwz;
	}
}
