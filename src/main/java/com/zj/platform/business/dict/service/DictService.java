package com.zj.platform.business.dict.service;

import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.service.BaseService;

import java.util.List;



/**
 * <pre>
 * 数据字典
 * </pre>
 */
public interface DictService extends BaseService<DictDO> {
    
    List<DictDO> listType();

    String getName(String type, String value);

    /**
     * 获取爱好列表
     * 
     * @return
     * @param userDO
     */
    List<DictDO> getHobbyList(UserDO userDO);

    /**
     * 获取性别列表
     * 
     * @return
     */
    List<DictDO> getSexList();
}
