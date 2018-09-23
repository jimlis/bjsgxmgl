package com.zj.platform.business.config.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.business.config.dao.ConfigDao;
import com.zj.platform.business.config.domain.ConfigDO;
import com.zj.platform.business.config.service.ConfigService;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ConfigServiceImpl extends BaseServiceImpl<ConfigDao, ConfigDO> implements ConfigService {
    
    @Override
    public ConfigDO getByKey(String k) {
        ConfigDO entity = new ConfigDO();
                          entity.setK(k);
        QueryWrapper<ConfigDO> queryWrapper=new QueryWrapper<ConfigDO>(entity);
        return baseMapper.selectOne(queryWrapper);
    }
    
    @Override
    public String getValuByKey(String k) {
        ConfigDO bean = this.getByKey(k);
        return bean == null ? "" : bean.getV();
    }
    
    @Override
    public void updateKV(Map<String, String> kv) {
        for(Map.Entry<String, String> entry : kv.entrySet()){
            ConfigDO config = this.getByKey(entry.getKey());
            config.setV(entry.getValue());
            super.updateById(config);
        }
    }
    
    @Override
    public List<ConfigDO> findListByKvType(int kvType){
        ConfigDO configDO = new ConfigDO();
        configDO.setKvType(kvType);
        QueryWrapper<ConfigDO> ew = new QueryWrapper<ConfigDO>(configDO);
        List<ConfigDO> list = super.list(ew);
        return list;
    }
    
    
}
