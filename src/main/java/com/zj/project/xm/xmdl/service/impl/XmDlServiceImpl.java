package com.zj.project.xm.xmdl.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmdl.dao.XmDlDao;
import com.zj.project.xm.xmdl.domain.XmDlDO;
import com.zj.project.xm.xmdl.service.XmDlService;

/**
 * 
 * <pre>
 * 项目基本信息-栋楼,记录每栋楼名称及编号
 * </pre>
 * <small> 2018-09-28 22:39:21 | lijun</small>
 */
@Service
public class XmDlServiceImpl extends BaseServiceImpl<XmDlDao, XmDlDO> implements XmDlService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmDlDO.class);
    }
    
    @Override
    public boolean updateById(XmDlDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmDlDO> updateWrapper=new UpdateWrapper<XmDlDO>().eq("id", entity.getId());
    	fieldList.forEach(filed->{
    		if(filed.isCharSequence()&&FieldStrategy.NOT_EMPTY==filed.getFieldStrategy()) {
    			String value = (String)ReflectionKit.getMethodValue(entity, filed.getProperty());
        		updateWrapper.set(MyStringUtils.isEmptyString(value),filed.getColumn(),value);
    		}
    	});
    	return update(entity, updateWrapper);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmDlDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 根据xmid获取项目栋楼和层数信息
     * @param intxmid 项目id
     * @return
     */
    @Override
    public List<Map<String,Object>> getXmDlAndCsByXmid(Long intxmid){
    	if(intxmid==null) {
    		throw new MyApiException("项目id不能为空");
    	}
    	 List<Map<String,Object>>  listMap=Lists.newArrayList();
    	 XmDlDO xmDlDO=new XmDlDO();
         xmDlDO.setFcbz(1);
         xmDlDO.setIntxmid(intxmid);
         QueryWrapper<XmDlDO> queryWrapper=new QueryWrapper<XmDlDO>(xmDlDO).orderByAsc("intxh");
         List<XmDlDO> list = list(queryWrapper);
         if(CollectionUtils.isNotEmpty(list)) {
        	 list.forEach(xmDlDo->{
        		 
        		 Map<String,Object> map=Maps.newHashMap();
        		 map.put("text",xmDlDo.getChrdlmc());
        		 map.put("value",xmDlDo.getId());
        		 Integer intcs=xmDlDo.getIntcs();
        		 List<Map<String,Object>> childrenList=Lists.newArrayList();
        		 if(intcs==0) {
        			 map.put("children", childrenList);
        		 }else {
        			 for(int i=1;i<=intcs;i++){
        				 Map<String,Object> childrenMap=Maps.newHashMap();
        				 childrenMap.put("text",""+i+"层");
        				 childrenMap.put("value",i);
        				 childrenList.add(childrenMap);
        			 }
        			 map.put("children", childrenList);
        		 }
        		 listMap.add(map);
        	 });
        	 return listMap;
         }else {
        	 return listMap;
         }
         
    	
    }


}
