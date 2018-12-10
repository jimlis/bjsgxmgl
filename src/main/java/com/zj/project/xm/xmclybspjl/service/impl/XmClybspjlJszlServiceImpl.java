package com.zj.project.xm.xmclybspjl.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmclybspjl.dao.XmClybspjlJszlDao;
import com.zj.project.xm.xmclybspjl.domain.XmClybspjlJszlDO;
import com.zj.project.xm.xmclybspjl.service.XmClybspjlJszlService;

/**
 * 
 * <pre>
 * 材料样板审批记录-品牌及技术资料
 * </pre>
 * <small> 2018-10-13 18:36:00 | lijun</small>
 */
@Service
public class XmClybspjlJszlServiceImpl extends BaseServiceImpl<XmClybspjlJszlDao, XmClybspjlJszlDO> implements XmClybspjlJszlService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmClybspjlJszlDO.class);
    }
    
    @Override
    public boolean updateById(XmClybspjlJszlDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmClybspjlJszlDO> updateWrapper=new UpdateWrapper<XmClybspjlJszlDO>().eq("id", entity.getId());
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
    public Collection<XmClybspjlJszlDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


}
