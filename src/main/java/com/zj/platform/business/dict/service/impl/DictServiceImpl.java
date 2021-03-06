package com.zj.platform.business.dict.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zj.platform.business.dict.dao.DictDao;
import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.business.dict.service.DictService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;


@Service
public class DictServiceImpl extends BaseServiceImpl<DictDao, DictDO> implements DictService {
	
	public static TableInfo tableInfo = null;
	
    static {
    	tableInfo=TableInfoHelper.getTableInfo(DictDO.class);
    }
	
    @Autowired
    private DictDao dictDao;
    
    @Override
    public boolean updateById(DictDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<DictDO> updateWrapper=new UpdateWrapper<DictDO>().eq("id", entity.getId());
    	fieldList.forEach(filed->{
    		if(filed.isCharSequence()&&FieldStrategy.NOT_EMPTY==filed.getFieldStrategy()) {
    			String value = (String)ReflectionKit.getMethodValue(entity, filed.getProperty());
        		updateWrapper.set(MyStringUtils.isEmptyString(value),filed.getColumn(),value);
    		}
    	});
    	return update(entity, updateWrapper);
    }

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
            QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().eq("type",type).eq("delFlag", "0");
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
