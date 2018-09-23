package com.zj.platform.business.config.service;


import com.zj.platform.business.config.domain.ConfigDO;
import com.zj.platform.common.web.service.BaseService;

import java.util.List;
import java.util.Map;


public interface ConfigService extends BaseService<ConfigDO> {
    ConfigDO getByKey(String k);

    String getValuByKey(String k);
    
    void updateKV(Map<String, String> kv);
    
    List<ConfigDO> findListByKvType(int kvType);
}
