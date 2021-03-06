package com.zj.platform.business.file.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.platform.common.web.domain.BaseDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 文件
 */
@TableName("sys_file")
@ApiModel(value = "FileDO",description = " 文件")
public class FileDO extends BaseDomain {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    //
    @TableId
    @ApiModelProperty(value = "id",name = "id",dataType = "Long")
    private Long id;
    /**
     * 业务表名
     */
    @ApiModelProperty(value = "业务表名",name = "busType",dataType = "String",required = false)
    private  String busType;
    /**
     * 业务id
     */
    @ApiModelProperty(value = "业务id",name = "busId",dataType = "Long",required = false)
    private  Long busId;
    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型",name = "type",dataType = "String",required = false)
    private String type;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称",name = "fileName",dataType = "String",required = false)
    private  String fileName;
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小",name = "fileSize",dataType = "String",required = false)
    private  Long fileSize;
    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id",name = "createUserId",dataType = "Long",required = false)
    private  Long createUserId;
    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称",name = "createUserName",dataType = "String",required = false)
    private  String createUserName;
    /**
     * 创建人部门id
     */
    @ApiModelProperty(value = "创建人部门id",name = "createDeptId",dataType = "Long",required = false)
    private  Long createDeptId;
    /***
     * 创建人部门名称
     */
    @ApiModelProperty(value = "创建人部门名称",name = "createDeptName",dataType = "String",required = false)
    private  String createDeptName;
    /**
     * URL地址
     */
    @ApiModelProperty(value = "URL地址",name = "url",dataType = "String",required = false)
    private String url;

    // 创建时间
    private Date createDate;

    public FileDO() {

    }

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：文件类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：文件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：URL地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：URL地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 获取：业务表名
     */
    public String getBusType() {
        return busType;
    }

    /**
     * 设置：业务表名
     */
    public void setBusType(String busType) {
        this.busType = busType;
    }

    /**
     * 获取：业务id
     */
    public Long getBusId() {
        return busId;
    }

    /**
     * 设置：业务表名
     */
    public void setBusId(Long busId) {
        this.busId = busId;
    }

    /**
     * 获取：文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置：文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取：文件大小
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 设置：文件大小
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 获取：创建人id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：创建人id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建人名称
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置：创建人名称
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取：创建人部门id
     */
    public Long getCreateDeptId() {
        return createDeptId;
    }

    /**
     * 设置：创建人部门id
     */
    public void setCreateDeptId(Long createDeptId) {
        this.createDeptId = createDeptId;
    }

    /**
     * 获取：创建人部门名称
     */
    public String getCreateDeptName() {
        return createDeptName;
    }

    /**
     * 设置：创建人部门名称
     */
    public void setCreateDeptName(String createDeptName) {
        this.createDeptName = createDeptName;
    }

    @Override
    public String toString() {
        return "FileDO{" +
                "id=" + id +
                ", busType='" + busType + '\'' +
                ", busId=" + busId +
                ", type=" + type +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", createUserId=" + createUserId +
                ", createUserName='" + createUserName + '\'' +
                ", createDeptId=" + createDeptId +
                ", createDeptName='" + createDeptName + '\'' +
                ", url='" + url + '\'' +
                ", createDate=" + createDate +
                '}';
    }


}
