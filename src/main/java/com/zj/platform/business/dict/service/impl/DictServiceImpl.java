package com.zj.platform.business.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zj.platform.business.dict.dao.DictDao;
import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.business.dict.service.DictService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class DictServiceImpl extends BaseServiceImpl<DictDao, DictDO> implements DictService {
    @Autowired
    private DictDao dictDao;

    @Override
    public List<DictDO> listType() {
        return dictDao.listType();
    }

    @Override
    public String getName(String type, String value) {
        QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().eq("type",type).eq("value",value);
        DictDO selectOne = dictDao.selectOne(queryWrapper);
        return selectOne == null ? "" : selectOne.getName();
    }

    @Override
    public List<DictDO> getHobbyList(UserDO userDO) {
        QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().eq("type","hobby");
        List<DictDO> hobbyList = dictDao.selectList(queryWrapper);

        if (StringUtils.isNotEmpty(userDO.getHobby())) {
            String userHobbys[] = userDO.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (DictDO hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemarks("true");
                    break;
                }
            }
        }

        return hobbyList;
    }

    @Override
    public List<DictDO> getSexList() {
        QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().eq("type","sex");
        return dictDao.selectList(queryWrapper);
    }

    /**
     * 根据类型获取 数据字段集合
     * @param types 类型 多个类型以逗号隔开
     * @return  map  type为key 查询集合为value
     */
    @Override
    public Map<String, List<DictDO>> dictMap(String types) {
        Map<String, List<DictDO>> map= Maps.newHashMap();
        if(StringUtils.isEmpty(types)) return map;
        Arrays.stream(types.trim().split(",")).forEach(type->{
            QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().eq("type",type);
            List<DictDO> dictList= dictDao.selectList(queryWrapper);
            if(CollectionUtils.isNotEmpty(dictList)){
                map.put(type,dictList);
            }else{
                map.put(type, Lists.newArrayList());
            }
        });

        return map;
    }

    /**
     * 根据类型获取 数据字段集合
     * @param type 类型
     * @return
     */
    @Override
    public List<DictDO> dictList(String type) {
        QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().eq("type",type);
        return dictDao.selectList(queryWrapper);
    }

}
