package com.zj.project.xm.xmzpms.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * <pre>
 * 项目基本信息-照片描述
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
 @TableName("bj_xm_zpms")
 @ApiModel(value = "XmZpmsDO",description = "项目基本信息-照片描述")
public class XmZpmsDO extends BaseDomain {
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
    /** 所属类型：1（照片记录），2（基础施工），3（主体结构施工），4（样板施工记录），5（质量缺陷报告），6（安全报告）7（巡查与验收） */
    @ApiModelProperty(value = "所属类型",name = "intsslx",dataType = "string",required = true)
    private String intsslx;
    /** 照片所属id:根据所属类型不同找不同表id */
    @ApiModelProperty(value = "照片所属id:根据所属类型不同找不同表id",name = "intzpssid",dataType = "Long",required = true)
    private Long intzpssid;
    /** 照片描述 */
    @ApiModelProperty(value = "照片描述",name = "chrzpms",dataType = "String",required = false)
    private String chrzpms;
    /**对应sys_file主键id*/
    @ApiModelProperty(value = "对应sys_file主键id",name = "intzpid",dataType = "Long",required = true)
    private  Long intzpid;

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
     * 设置：所属类型：1（照片记录），2（基础施工），3（主体结构施工），4（样板施工记录），5（质量缺陷报告），6（安全报告）7（巡查与验收）
     */
    public void setIntsslx(String intsslx) {
        this.intsslx = intsslx;
    }
    /**
     * 获取：所属类型：1（照片记录），2（基础施工），3（主体结构施工），4（样板施工记录），5（质量缺陷报告），6（安全报告）7（巡查与验收）
     */
    public String getIntsslx() {
        return intsslx;
    }
    /**
     * 设置：照片所属id:根据所属类型不同找不同表id
     */
    public void setIntzpssid(Long intzpssid) {
        this.intzpssid = intzpssid;
    }
    /**
     * 获取：照片所属id:根据所属类型不同找不同表id
     */
    public Long getIntzpssid() {
        return intzpssid;
    }
    /**
     * 设置：照片描述
     */
    public void setChrzpms(String chrzpms) {
        this.chrzpms = chrzpms;
    }
    /**
     * 获取：照片描述
     */
    public String getChrzpms() {
        return chrzpms;
    }

    /**
     * 获取:对应sys_file主键id
     * @return
     */
    public Long getIntzpid() {
        return intzpid;
    }

    /**
     * 设置：对应sys_file主键id
     * @param intzpid
     */
    public void setIntzpid(Long intzpid) {
        this.intzpid = intzpid;
    }
}
