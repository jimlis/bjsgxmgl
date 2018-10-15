package com.zj.project.xm.xmqyjwz.domain;

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
 * 所在区域及位置
 * </pre>
 * <small> 2018-10-01 22:46:44 | lijun</small>
 */
 @TableName("bj_xm_qyjwz")
 @ApiModel(value = "XmQyjwzDO",description = "所在区域及位置")
public class XmQyjwzDO extends BaseDomain {
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
    /** 项目id:bj_xmjb表 */
    @ApiModelProperty(value = "项目id:bj_xmjb表 ",name = "intxmid",dataType = "Long",required = true)
    private Long intxmid;
    /** 区域 */
    @ApiModelProperty(value = "区域",name = "chrqy",dataType = "String",required = true)
    private String chrqy;
    /** 位置 */
    @ApiModelProperty(value = "位置",name = "chrwz",dataType = "String",required = true)
    private String chrwz;
    /** 轨道交通 */
    @ApiModelProperty(value = "轨道交通",name = "chrgdjt",dataType = "String",required = true)
    private String chrgdjt;
    /** 周围状况 */
    @ApiModelProperty(value = "周围状况",name = "chrzwzk",dataType = "String",required = true)
    private String chrzwzk;

    @TableField(exist = false)
    @ApiModelProperty(value = "关联附件ids，多个以逗号隔开",name = "fileIds",dataType = "String",required = false)
    private  String fileIds;

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
     * 设置：区域
     */
    public void setChrqy(String chrqy) {
        this.chrqy = chrqy;
    }
    /**
     * 获取：区域
     */
    public String getChrqy() {
        return chrqy;
    }
    /**
     * 设置：位置
     */
    public void setChrwz(String chrwz) {
        this.chrwz = chrwz;
    }
    /**
     * 获取：位置
     */
    public String getChrwz() {
        return chrwz;
    }
    /**
     * 设置：轨道交通
     */
    public void setChrgdjt(String chrgdjt) {
        this.chrgdjt = chrgdjt;
    }
    /**
     * 获取：轨道交通
     */
    public String getChrgdjt() {
        return chrgdjt;
    }
    /**
     * 设置：周围状况
     */
    public void setChrzwzk(String chrzwzk) {
        this.chrzwzk = chrzwzk;
    }
    /**
     * 获取：周围状况
     */
    public String getChrzwzk() {
        return chrzwzk;
    }

    /**
     * 获取文件ids
     */
    public String getFileIds() {
        return fileIds;
    }

    /**
     * 设置文件ids
     */
    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }
}
