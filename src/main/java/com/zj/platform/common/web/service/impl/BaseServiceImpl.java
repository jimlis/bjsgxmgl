package com.zj.platform.common.web.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.zj.platform.common.web.service.BaseService;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements BaseService<T> {
	private Logger logger = Logger.getLogger(BaseServiceImpl.class);

	@Autowired
	public SqlSessionTemplate sqlSessionTemplate;
	

	
	/**
	 * 将前端参数转换为单表中字段参数（表字段名和实体属性名不一致情况下）
	 * @param tableInfo  表、实体相关信息
	 * @param parmMap  前端参数
	 * @return  columnMap 字段参数 
	 */
	public Map<String,Object> parmToColumnMap(TableInfo tableInfo,Map<String,Object> parmMap){
		Map<String, Object> map = Maps.newHashMap();
							map.putAll(parmMap);
		return tableInfo.getFieldList().stream().filter(TableFieldInfo->map.containsKey(TableFieldInfo.getProperty())).collect(Collectors.toMap(TableFieldInfo::getColumn,tableFieldInfo->map.get(tableFieldInfo.getProperty()),(key1, key2) -> key2));
	}
	
	
}
