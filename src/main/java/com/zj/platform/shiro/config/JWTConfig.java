package com.zj.platform.shiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *JWT配置
 */
@Component
public class JWTConfig {
	
	@Autowired(required=false)
	@Value("${jwt.userPrimaryKey}")
    private String userPrimaryKey;
    /**
     * jwt过期时间,默认2小时，单位为毫秒
     */
	@Autowired(required=false)
	@Value("${jwt.expireTime}")
    private Long expireTime = 7200000L;
    /**
     *  refresh_token过期时间，默认7天，单位为毫秒
     */
	@Autowired(required=false)
	@Value("${jwt.refreshTokenExpire}")
    private Long refreshTokenExpire = 604800000L;

    public String getUserPrimaryKey() {
        return userPrimaryKey;
    }

    public void setUserPrimaryKey(String userPrimaryKey) {
        this.userPrimaryKey = userPrimaryKey;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Long getRefreshTokenExpire() {
		return refreshTokenExpire;
	}

	public void setRefreshTokenExpire(Long refreshTokenExpire) {
		this.refreshTokenExpire = refreshTokenExpire;
	}

	@Override
    public String toString() {
        return "JWTConfig [userPrimaryKey=" + userPrimaryKey + ", expireTime=" + expireTime + ", refreshTokenExpre=" + refreshTokenExpire + "]";
    }

}
