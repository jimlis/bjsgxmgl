package com.zj.project.xm.xmdl.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;



/**
 * 
 * <pre>
 * 项目基本信息-栋楼,记录每栋楼名称及编号
 * </pre>
 * <small> 2018-09-28 22:39:21 | lijun</small>
 */
 @TableName("bj_xm_dl")
public class XmDlDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @TableId
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    private Integer fcbz;
    /** 修改新增删除时间 */
    private Date gxsj;
    /** 项目id：bj_xmjb表 */
    private Long intxmid;
    /** 序号 */
    private Integer intxh;
    /** 栋楼名称 */
    private String chrdlmc;
    /** 栋楼介绍 */
    private String chrdljs;
    /** 层数 */
    private Integer intcs;

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
     * 设置：项目id：bj_xmjb表
     */
    public void setIntxmid(Long intxmid) {
        this.intxmid = intxmid;
    }
    /**
     * 获取：项目id：bj_xmjb表
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
     * 设置：栋楼名称
     */
    public void setChrdlmc(String chrdlmc) {
        this.chrdlmc = chrdlmc;
    }
    /**
     * 获取：栋楼名称
     */
    public String getChrdlmc() {
        return chrdlmc;
    }
    /**
     * 设置：栋楼介绍
     */
    public void setChrdljs(String chrdljs) {
        this.chrdljs = chrdljs;
    }
    /**
     * 获取：栋楼介绍
     */
    public String getChrdljs() {
        return chrdljs;
    }
    /**
     * 设置：层数
     */
    public void setIntcs(Integer intcs) {
        this.intcs = intcs;
    }
    /**
     * 获取：层数
     */
    public Integer getIntcs() {
        return intcs;
    }
}
