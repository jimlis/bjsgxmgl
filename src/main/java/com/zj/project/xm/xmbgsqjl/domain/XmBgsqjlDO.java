package com.zj.project.xm.xmbgsqjl.domain;

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
 * 工程/顾问工作变更申请记录
 * </pre>
 * <small> 2018-10-13 16:22:31 | lijun</small>
 */
 @TableName("bj_xm_bgsqjl")
 @ApiModel(value = "XmBgsqjlDO",description = "程/顾问工作变更申请记录")
public class XmBgsqjlDO extends BaseDomain {
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
    /** 序号 */
    @ApiModelProperty(value = "序号 ",name = "intxh",dataType = "Integer",hidden = true)
    private Integer intxh;
    /** 项目基本信息id */
    @ApiModelProperty(value = "项目基本信息id ",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 更新日期 */
    @ApiModelProperty(value = "报告日期 ",name = "dtmgxrq",dataType = "string",example = "2018-10-13",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dtmgxrq;
    /** 变更申请类型：1（顾问变更）2（工程变更）3（其他） */
    @ApiModelProperty(value = "变更申请类型：1（顾问变更）2（工程变更）3（其他） ",name = "intbgsqlx",dataType = "Integer",required = true)
    private Integer intbgsqlx;
    /** 变更申请类型名称 */
    @ApiModelProperty(value = "变更申请类型名称",name = "chrbgsqlx",dataType = "String",required = true)
    @TableField(exist=false)
    private String chrbgsqlx;
    /** 变更申请编号 */
    @ApiModelProperty(value = "变更申请编号 ",name = "chrbgsqbh",dataType = "string")
    private String chrbgsqbh;
    /** 变更申请名称 */
    @ApiModelProperty(value = "变更申请名称 ",name = "chrbgsqmc",dataType = "string")
    private String chrbgsqmc;
    /** 是否取代之前变更申请：1（是）0（否） */
    @ApiModelProperty(value = "是否取代之前变更申请：1（是）0（否） ",name = "intsfqd",dataType = "Integer")
    private Integer intsfqd;
    /** 是否取代之前变更申请名称*/
    @TableField(exist=false)
    @ApiModelProperty(value = "是否取代之前变更申请：1（是）0（否） ",name = "chrsfqd",dataType = "String")
    private String chrsfqd;
    /**取替变更编号*/
    @ApiModelProperty(value = "是否取代之前变更申请：1（是）0（否） ",name = "chrqtbgbh",dataType = "String")
    private String chrqtbgbh;
    /** 变更详情 */
    @ApiModelProperty(value = "变更详情 ",name = "chrbgxq",dataType = "String")
    private String chrbgxq;
    /** 工期影响 */
    @ApiModelProperty(value = "工期影响 ",name = "intgqyx",dataType = "Float")
    private Float intgqyx;
    /** 变更估算 */
    @ApiModelProperty(value = "变更估算 ",name = "intbggs",dataType = "Float")
    private Float intbggs;
    /** 潜在金额变更总金额 */
    @ApiModelProperty(value = "潜在金额变更总金额 ",name = "intqzbgzje",dataType = "Float")
    private Float intqzbgzje;
    /** 合同占比 */
    @ApiModelProperty(value = "合同占比 ",name = "inthtzb",dataType = "Float")
    private Float inthtzb;
    /** 备注 */
    @ApiModelProperty(value = "备注 ",name = "chrbz",dataType = "String")
    private String chrbz;
    /** 变更申请文件：文件地址，多个逗号隔开 */
    @ApiModelProperty(value = "变更申请文件：文件地址，多个逗号隔开 ",name = "chrbgsqwj",dataType = "String")
    private String chrbgsqwj;
    /** 审批流程状态：code码表 */
    @ApiModelProperty(value = "审批流程状态：code码表 ",name = "intsplczt",dataType = "String")
    private String intsplczt;
    /** 审批流程状态名称 */
    @ApiModelProperty(value = "审批流程状态名称",name = "chrsplczt",dataType = "String")
    @TableField(exist=false)
    private String chrsplczt;
    /** 报告人id */
    @ApiModelProperty(value = "报告人id",name = "intbgrid",dataType = "Long",required=true)
    private Long intbgrid;
    /** 报告人名称 */
    @ApiModelProperty(value = "报告人名称  ",name = "chrbgrmc",dataType = "String",required=true)
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
     * 设置：变更申请类型：1（顾问变更）2（工程变更）3（其他）
     */
    public void setIntbgsqlx(Integer intbgsqlx) {
        this.intbgsqlx = intbgsqlx;
    }
    /**
     * 获取：变更申请类型：1（顾问变更）2（工程变更）3（其他）
     */
    public Integer getIntbgsqlx() {
        return intbgsqlx;
    }
    /**
     * 设置：变更申请编号
     */
    public void setChrbgsqbh(String chrbgsqbh) {
        this.chrbgsqbh = chrbgsqbh;
    }
    /**
     * 获取：变更申请编号
     */
    public String getChrbgsqbh() {
        return chrbgsqbh;
    }
    /**
     * 设置：变更申请名称
     */
    public void setChrbgsqmc(String chrbgsqmc) {
        this.chrbgsqmc = chrbgsqmc;
    }
    /**
     * 获取：变更申请名称
     */
    public String getChrbgsqmc() {
        return chrbgsqmc;
    }
    /**
     * 设置：是否取代之前变更申请：1（是）0（否）
     */
    public void setIntsfqd(Integer intsfqd) {
        this.intsfqd = intsfqd;
    }
    /**
     * 获取：是否取代之前变更申请：1（是）0（否）
     */
    public Integer getIntsfqd() {
        return intsfqd;
    }
    /**
     * 设置：变更详情
     */
    public void setChrbgxq(String chrbgxq) {
        this.chrbgxq = chrbgxq;
    }
    /**
     * 获取：变更详情
     */
    public String getChrbgxq() {
        return chrbgxq;
    }
    /**
     * 设置：工期影响
     */
    public void setIntgqyx(Float intgqyx) {
        this.intgqyx = intgqyx;
    }
    /**
     * 获取：工期影响
     */
    public Float getIntgqyx() {
        return intgqyx;
    }
    /**
     * 设置：变更估算
     */
    public void setIntbggs(Float intbggs) {
        this.intbggs = intbggs;
    }
    /**
     * 获取：变更估算
     */
    public Float getIntbggs() {
        return intbggs;
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
    /**
     * 设置：变更申请文件：文件地址，多个逗号隔开
     */
    public void setChrbgsqwj(String chrbgsqwj) {
        this.chrbgsqwj = chrbgsqwj;
    }
    /**
     * 获取：变更申请文件：文件地址，多个逗号隔开
     */
    public String getChrbgsqwj() {
        return chrbgsqwj;
    }
    /**
     * 设置：审批流程状态：code码表
     */
    public void setIntsplczt(String intsplczt) {
        this.intsplczt = intsplczt;
    }
    /**
     * 获取：审批流程状态：code码表
     */
    public String getIntsplczt() {
        return intsplczt;
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
	public String getChrbgsqlx() {
		return chrbgsqlx;
	}
	public void setChrbgsqlx(String chrbgsqlx) {
		this.chrbgsqlx = chrbgsqlx;
	}
	public String getChrsfqd() {
		return chrsfqd;
	}
	public void setChrsfqd(String chrsfqd) {
		this.chrsfqd = chrsfqd;
	}
	public String getChrsplczt() {
		return chrsplczt;
	}
	public void setChrsplczt(String chrsplczt) {
		this.chrsplczt = chrsplczt;
	}
	public Float getIntqzbgzje() {
		return intqzbgzje;
	}
	public void setIntqzbgzje(Float intqzbgzje) {
		this.intqzbgzje = intqzbgzje;
	}
	public Float getInthtzb() {
		return inthtzb;
	}
	public void setInthtzb(Float inthtzb) {
		this.inthtzb = inthtzb;
	}
	public String getChrqtbgbh() {
		return chrqtbgbh;
	}
	public void setChrqtbgbh(String chrqtbgbh) {
		this.chrqtbgbh = chrqtbgbh;
	}
}
