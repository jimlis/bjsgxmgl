package com.zj.project.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.business.user.dao.UserDao;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.config.CacheConfiguration;
import com.zj.platform.common.config.ProjectConfig;
import com.zj.platform.common.security.DesUtils;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.util.MD5Utils;
import com.zj.platform.common.util.SpringContextHolder;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.platform.shiro.config.JWTConfig;
import com.zj.platform.shiro.util.JWTUtil;
import com.zj.project.api.pojo.vo.ApiUserVO;
import com.zj.project.api.pojo.vo.TokenVO;
import com.zj.project.api.service.ApiUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

@Service
public class ApiUserServiceImpl extends BaseServiceImpl<UserDao,UserDO> implements ApiUserService {
	/** Holder for lazy-init */
	private static class Holder {
		static final JWTConfig jwt = SpringContextHolder.getBean(ProjectConfig.class).getJwt();
		static final Cache logoutTokens = CacheConfiguration.dynaConfigCache("tokenExpires", 0, jwt.getRefreshTokenExpire(), 1000);
		static {
			JWTUtil.mykey = jwt.getUserPrimaryKey();
		}
	}

	@Override
    public TokenVO getToken(String mobile, String password) {
        UserDO user = getOne(new QueryWrapper<UserDO>().eq("mobile", mobile));
        if (null == user || !user.getPassword().equals(MD5Utils.encrypt(user.getMobile(), password))) {
            throw new MyApiException(EnumErrorCode.apiAuthorizationLoginFailed.getCodeStr());
        }
        return createToken(user);
    }
	
	@Override
    public TokenVO logonFree(String tel,String token) throws Exception {
		if(StringUtils.isEmpty(tel)||StringUtils.isEmpty(token)) {
   		 	throw new MyApiException("参数错误");
	   	 }
	   	 String tokenDe=DesUtils.decryption(token, "bjsgxmgl_lfj");
	   	 String mobile=tokenDe.split("_")[0];
	   	 if(!mobile.equals(tel)) {
	   		 throw new MyApiException("token校验失败");
	   	 }
         UserDO user = getOne(new QueryWrapper<UserDO>().eq("mobile", mobile));
         if (null == user) {
            throw new MyApiException(EnumErrorCode.apiAuthorizationLoginFailed.getCodeStr());
         }
         return createToken(user);
    }

    @Override
	public boolean verifyToken(String token, boolean refresh) {
    	String userId = null; 
    	UserDO user = null;
    	return StringUtils.isNotBlank(token)
    			&& (userId=JWTUtil.getUserId(token))!=null
    			&& notLogout(token)
    			&& (user=refresh?getOne(new QueryWrapper<UserDO>().eq("mobile", userId)):getById(userId))!=null
    			&& (refresh ? JWTUtil.verify(token, user.getMobile(), user.getId()+user.getPassword()) : JWTUtil.verify(token, userId, user.getMobile()+user.getPassword()));
	}

	@Override
	public TokenVO refreshToken(String mobile, String refreshToken) {
		UserDO user = null;
		if(StringUtils.isNotBlank(refreshToken)
				&& mobile.equals(JWTUtil.getUserId(refreshToken))
				&& notLogout(refreshToken)
				&& (user=getOne(new QueryWrapper<UserDO>().eq("mobile", mobile)))!=null
				&& JWTUtil.verify(refreshToken, user.getMobile(), user.getId()+user.getPassword())
				&& Holder.logoutTokens.putIfAbsent(refreshToken, null)==null) {
			return createToken(user);
		}
    	throw new MyApiException(EnumErrorCode.apiAuthorizationInvalid.getCodeStr());
	}

	@Override
	public Boolean logoutToken(String token, String refreshToken) {
		Boolean expire = Boolean.FALSE; String userId = null, mobile = null; 
		UserDO user = null;
		if(StringUtils.isNotBlank(token)
				&& (userId=JWTUtil.getUserId(token))!=null
				&& Holder.logoutTokens.get(token)==null
				&& (user=getById(userId))!=null
				&& JWTUtil.verify(token, userId, user.getMobile()+user.getPassword())
				&& Holder.logoutTokens.putIfAbsent(token, null)==null) {
			expire = Boolean.TRUE;
		}
		if(StringUtils.isNotBlank(refreshToken)
				&& (mobile=JWTUtil.getUserId(refreshToken))!=null
				&& Holder.logoutTokens.get(refreshToken)==null
				&& (user!=null || (user=getOne(new QueryWrapper<UserDO>().eq("mobile", mobile)))!=null)
				&& JWTUtil.verify(refreshToken, user.getMobile(), user.getId()+user.getPassword())
				&& Holder.logoutTokens.putIfAbsent(refreshToken, null)==null) {
			expire = Boolean.TRUE;
		}
		return expire;
	}

	private TokenVO createToken(UserDO user) {
        TokenVO vo = new TokenVO();
        vo.setToken(JWTUtil.sign(user.getId() + "", user.getMobile() + user.getPassword(), Holder.jwt.getExpireTime()));
        vo.setRefleshToken(JWTUtil.sign(user.getMobile(), user.getId() + user.getPassword(), Holder.jwt.getExpireTime()));
        vo.setTokenExpire(Holder.jwt.getExpireTime());
        vo.setRefreshTokenExpire(Holder.jwt.getRefreshTokenExpire());
        return vo;
	}
	
	private boolean notLogout(String token) {
		if(Holder.logoutTokens.get(token)!=null)
			throw new MyApiException(EnumErrorCode.apiAuthorizationLoggedout.getMsg());
		return true;
	}

	/**
	 * 获取ApiUserVO
	 * @param mobile
	 * @param password
	 * @param flag true-免登陆-不需要验证密码  false-正常登陆-需要验证密码
	 * @return
	 */
	@Override
	public ApiUserVO getApiUserVo(String mobile, String password,Boolean flag) {
		UserDO user = getOne(new QueryWrapper<UserDO>().eq("mobile", mobile));
		ApiUserVO apiUserVO=new ApiUserVO();
		if(null != user) {
			if(flag) {//免登陆
				BeanUtils.copyProperties(user,apiUserVO);
	        }else {
	        	if(user.getPassword().equals(MD5Utils.encrypt(user.getMobile(), password))) {
	        		BeanUtils.copyProperties(user,apiUserVO);
	        	}
	        }
		}
		return  apiUserVO;
	}
}
