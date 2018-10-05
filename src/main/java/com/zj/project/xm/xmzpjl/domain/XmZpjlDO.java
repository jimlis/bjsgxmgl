package com.zj.project.xm.xmzpjl.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;



/**
 * 
 * <pre>
 * 项目基本信息-照片记录
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
 @TableName("bj_xm_zpjl")
public class XmZpjlDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @TableId
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    private Integer fcbz;
    /** 修改新增删除时间 */
    private Date gxsj;
    /** 项目基本信息id */
    private Long intxmid;
    /** 序号 */
    private Integer intxh;
    /** 报告日期 */
    private Date dtmbgrq;
    /** 1（整体形象进度），2（栋楼形象进度），3（隐蔽工程形象进度） */
    private String intbglb;
    /** 根据页面提交存储 */
    private String chrpswz;
    /** 报告人id */
    private Long intbgrid;
    /** 报告人名称 */
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
    public void setIntbglb(String intbglb) {
        this.intbglb = intbglb;
    }
    /**
     * 获取：1（整体形象进度），2（栋楼形象进度），3（隐蔽工程形象进度）
     */
    public String getIntbglb() {
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
}
