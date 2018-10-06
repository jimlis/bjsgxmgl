package com.zj.project.api.pojo.vo;

import com.zj.platform.common.web.domain.BaseDomain;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * 用户
 */
public class ApiUserVO extends BaseDomain {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户真实姓名
     */
    private String name;

    /**
     * 部门
     */
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 排序号
     */
    private Long orderNum;
    /**
     * 角色集合
     */
    private List<Long> roleIds;
    /**
     * 性别
     */
    private Long sex;
    /**
     * 出身日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    /**
     * 现居住地
     */
    private String liveAddress;
    /**
     * 省份
     */
    private String province;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 所在地区
     */
    private String district;
    /**
     * 职务
     */
    private String chrzw;

    /**
     * 最新图片id
     */
    private Long fileId;


    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 获取：用户姓名
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置：用户姓名
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获取部门id
     */
    public Long getDeptId() {
        return deptId;
    }
    
    /**
     * 设置部门id
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    
    /**
     * 获取部门名称
     * @return
     */
    public String getDeptName() {
        return deptName;
    }
    
    /**
     * 设置部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }
    
    /**
     * 获取角色集合
     */
    public List<Long> getroleIds() {
        return roleIds;
    }
    
    /**
     * 设置用户集合
     */
    public void setroleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
    
    /**
     * 获取性别
     */
    public Long getSex() {
        return sex;
    }
    
    /**
     * 设置用户性别
     */
    public void setSex(Long sex) {
        this.sex = sex;
    }
    
    /**
     * 获取出生日期
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * 设置出生日期
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    
    /**
     * 获取居住地址
     */
    public String getLiveAddress() {
        return liveAddress;
    }
    
    /**
     * 设置居住地址
     */
    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }
    
    /**
     * 获取省份
     */
    public String getProvince() {
        return province;
    }
    
    /**
     * 设置省份
     */
    public void setProvince(String province) {
        this.province = province;
    }
    
    /**
     * 获取所在城市
     */
    public String getCity() {
        return city;
    }
    
    /**
     * 设置所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * 获取所在地区
     */
    public String getDistrict() {
        return district;
    }
    
    /**
     * 设置所在地区
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

    public String getChrzw() {
        return chrzw;
    }

    public void setChrzw(String chrzw) {
        this.chrzw = chrzw;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "ApiUserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", orderNum=" + orderNum +
                ", roleIds=" + roleIds +
                ", sex=" + sex +
                ", birth=" + birth +
                ", liveAddress='" + liveAddress + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", chrzw='" + chrzw + '\'' +
                ", fileId=" + fileId +
                '}';
    }
}
