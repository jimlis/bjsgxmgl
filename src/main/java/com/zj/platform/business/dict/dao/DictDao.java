package com.zj.platform.business.dict.dao;

import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.common.web.dao.Dao;

import java.util.List;


/**
 * 数据字典
 */
public interface DictDao extends Dao<DictDO> {

    List<DictDO> listType();
    
}
