package com.zj.project.api.service;


import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.service.BaseService;
import com.zj.project.api.pojo.vo.ApiUserVO;
import com.zj.project.api.pojo.vo.TokenVO;


public interface ApiUserService extends BaseService<UserDO> {
    /** 申请token */
    TokenVO getToken(String uname, String passwd) ;
    /** 刷新token */
    TokenVO refreshToken(String uname, String refreshToken);
    /** 检查token是否有效：未超时、未注销*/
    boolean verifyToken(String token, boolean refresh);
    /** 注销token */
    Boolean logoutToken(String token, String refreshToken);
    /**
     * 获取ApiUserVO
     * @param mobile
     * @param password
     * @param flag true-免登陆-不需要验证密码  false-正常登陆-需要验证密码
     * @return
     */
    ApiUserVO getApiUserVo(String mobile, String password,Boolean flag);
    
    /**
     * <p>Title:免登录：根据手机号获取token </p>  
     * <p>Description: </p> 
     * @param tel
     * @param token
     * @return
     * @author zhujujun
     * @throws Exception 
     * @date:2018年10月27日 下午2:16:01
     */
	TokenVO logonFree(String tel, String token) throws Exception;
}
