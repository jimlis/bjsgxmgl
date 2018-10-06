package com.zj.platform.business.dict.service;

import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.service.BaseService;

import java.util.List;
import java.util.Map;


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

    /**
     * 根据类型获取 数据字段集合
     * @param types 类型 多个类型以逗号隔开
     * @return  map  type为key 查询集合为value
     */
    Map<String, List<DictDO>> dictMap(String types);

    /**
     * 根据类型获取 数据字段集合
     * @param type 类型
     * @return
     */
    List<DictDO> dictList(String type);
}
