package com.zj.project.xm.xmsgjd.swgwsg.service.impl;

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
import com.zj.project.xm.xmsgjd.swgwsg.dao.XmSgjdSwgwlxDao;
import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwlxDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwlxService;

/**
 * 
 * <pre>
 * 施工进度-室外管网类型，没个项目有多个室外管网类型。
 * </pre>
 * <small> 2018-10-20 20:40:32 | lijun</small>
 */
@Service
public class XmSgjdSwgwlxServiceImpl extends BaseServiceImpl<XmSgjdSwgwlxDao, XmSgjdSwgwlxDO> implements XmSgjdSwgwlxService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdSwgwlxDO.class);
    }
    
    @Override
    public boolean updateById(XmSgjdSwgwlxDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmSgjdSwgwlxDO> updateWrapper=new UpdateWrapper<XmSgjdSwgwlxDO>().eq("id", entity.getId());
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
    public Collection<XmSgjdSwgwlxDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


}
