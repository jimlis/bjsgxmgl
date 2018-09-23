package com.zj.project.gsgg.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zj.platform.common.web.domain.BaseDomain;

import java.util.Date;


/**
 * 
 * <pre>
 * 公司公告
 * </pre>
 * <small> 2018-09-13 20:25:05 | lijun</small>
 */
 @TableName("bj_gsgg")
public class GsggDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    @TableId
    private Long id;
    /** 逻辑废除：0（废除），1（正常） */
    private Integer fcbz;
    /** 修改新增删除时间 */
    private Date gxsj;
    /** 序号 */
    private Integer intxh;
    /** 标题 */
    private String chrbt;
    /** 发布时间 */
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    private Date dtmfbsj;
    /** 登记人部门id */
    private Long intdjrbm;
    /**登记人部门名称*/
    private String chrdjrbmmc;
    /** 登记人id */
    private Long intdjrid;
    /** 登记人名称 */
    private String chrdjrmc;
    /** 已查看次数 */
    private Integer intyckcs;
    /**公告类型：详情见sys_dict表gglx参数类型*/
    private  String chrlx;
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
     * 设置：标题
     */
    public void setChrbt(String chrbt) {
        this.chrbt = chrbt;
    }
    /**
     * 获取：标题
     */
    public String getChrbt() {
        return chrbt;
    }
    /**
     * 设置：发布时间
     */
    public void setDtmfbsj(Date dtmfbsj) {
        this.dtmfbsj = dtmfbsj;
    }
    /**
     * 获取：发布时间
     */
    public Date getDtmfbsj() {
        return dtmfbsj;
    }
    /**
     * 设置：登记人部门id
     */
    public void setIntdjrbm(Long intdjrbm) {
        this.intdjrbm = intdjrbm;
    }
    /**
     * 获取：登记人部门id
     */
    public Long getIntdjrbm() {
        return intdjrbm;
    }
    /**
     * 设置：登记人id
     */
    public void setIntdjrid(Long intdjrid) {
        this.intdjrid = intdjrid;
    }
    /**
     * 获取：登记人id
     */
    public Long getIntdjrid() {
        return intdjrid;
    }
    /**
     * 设置：登记人名称
     */
    public void setChrdjrmc(String chrdjrmc) {
        this.chrdjrmc = chrdjrmc;
    }
    /**
     * 获取：登记人名称
     */
    public String getChrdjrmc() {
        return chrdjrmc;
    }
    /**
     * 设置：已查看次数
     */
    public void setIntyckcs(Integer intyckcs) {
        this.intyckcs = intyckcs;
    }
    /**
     * 获取：已查看次数
     */
    public Integer getIntyckcs() {
        return intyckcs;
    }

    /**
     * 获取公告类型：详情见sys_dict表gglx参数类型
     * @return
     */
    public String getChrlx() {
        return chrlx;
    }

    /**
     * 设置公告类型：详情见sys_dict表gglx参数类型
     * @param chrlx
     */
    public void setChrlx(String chrlx) {
        this.chrlx = chrlx;
    }

    /**
     * 获取登记人部门名称
     */
    public String getChrdjrbmmc() {
        return chrdjrbmmc;
    }

    /**
     * 设置登记人部门 名称
     */
    public void setChrdjrbmmc(String chrdjrbmmc) {
        this.chrdjrbmmc = chrdjrbmmc;
    }
}
