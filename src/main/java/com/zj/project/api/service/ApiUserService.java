package com.zj.project.api.service;


import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.service.BaseService;
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
}
