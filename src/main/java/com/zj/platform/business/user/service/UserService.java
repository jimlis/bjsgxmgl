package com.zj.platform.business.user.service;

import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.dept.domain.DeptDO;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.business.user.vo.UserVO;
import com.zj.platform.common.web.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;


public interface UserService extends BaseService<UserDO> {
    /**
     * 保存用户信息
     * @param user 用户信息
     * @param fileids 文件ids
     * @return
     */
    boolean saveUser(UserDO user, String fileids);

    /**
     * 根据参数判断用户是否存在
     * @return true--存在 false-不存
     */
    boolean exit(Map<String, Object> params);

    Set<String> listRoles(Long userId);
    
    /**
     * 修改密码
     */
    int resetPwd(UserVO userVO, UserDO userDO);

    int adminResetPwd(UserVO userVO);

    Tree<DeptDO> getTree();

    /**
     * 更新个人信息
     * 
     * @param userDO
     * @param  fileids 文件ids
     * @return
     */
    int updatePersonal(UserDO userDO,String fileids);


}
