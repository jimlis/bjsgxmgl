package com.zj.project.xm.xmxkz.domain;

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
 * 项目基本信息-许可证
 * </pre>
 * <small> 2018-10-04 18:10:12 | lijun</small>
 */
 @TableName("bj_xm_xkz")
 @ApiModel(value = "XmXkzDO",description = "项目基本信息-许可证")
public class XmXkzDO extends BaseDomain {
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
    /** 序号 */
    @ApiModelProperty(value = "序号",name = "intxh",dataType = "Integer",hidden = true)
    private Integer intxh;
    /** 项目id */
    @ApiModelProperty(value = "项目id",name = "intxmid",dataType = "Long",hidden = true,required = true)
    private Long intxmid;
    /** 许可证类型:1（规划许可证），2（施工许可证） */
    @ApiModelProperty(value = "许可证类型:1（规划许可证），2（施工许可证）",name = "intxkzlx",dataType = "string",required = true)
    private String intxkzlx;
    /** 许可证编号 */
    @ApiModelProperty(value = "许可证编号",name = "chrxkzbh",dataType = "string",required = true)
    private String chrxkzbh;
    /** 发放部门 */
    @ApiModelProperty(value = "发放部门",name = "chrffbm",dataType = "string",required = true)
    private String chrffbm;
    /** 发证日期 */
    @ApiModelProperty(value = "发证日期",name = "dtmfzrq",dataType = "string",required = true,example = "2018-10-06")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmfzrq;
    /** 有效截止日期 */
    @ApiModelProperty(value = "有效截止日期",name = "dtmyxrq",dataType = "string",required = true,example = "2019-10-06")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmyxrq;

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
     * 设置：项目id
     */
    public void setIntxmid(Long intxmid) {
        this.intxmid = intxmid;
    }
    /**
     * 获取：项目id
     */
    public Long getIntxmid() {
        return intxmid;
    }
    /**
     * 设置：许可证类型:1（规划许可证），2（施工许可证）
     */
    public void setIntxkzlx(String intxkzlx) {
        this.intxkzlx = intxkzlx;
    }
    /**
     * 获取：许可证类型:1（规划许可证），2（施工许可证）
     */
    public String getIntxkzlx() {
        return intxkzlx;
    }
    /**
     * 设置：许可证编号
     */
    public void setChrxkzbh(String chrxkzbh) {
        this.chrxkzbh = chrxkzbh;
    }
    /**
     * 获取：许可证编号
     */
    public String getChrxkzbh() {
        return chrxkzbh;
    }
    /**
     * 设置：发放部门
     */
    public void setChrffbm(String chrffbm) {
        this.chrffbm = chrffbm;
    }
    /**
     * 获取：发放部门
     */
    public String getChrffbm() {
        return chrffbm;
    }
    /**
     * 设置：发证日期
     */
    public void setDtmfzrq(Date dtmfzrq) {
        this.dtmfzrq = dtmfzrq;
    }
    /**
     * 获取：发证日期
     */
    public Date getDtmfzrq() {
        return dtmfzrq;
    }
    /**
     * 设置：有效截止日期
     */
    public void setDtmyxrq(Date dtmyxrq) {
        this.dtmyxrq = dtmyxrq;
    }
    /**
     * 获取：有效截止日期
     */
    public Date getDtmyxrq() {
        return dtmyxrq;
    }
}
