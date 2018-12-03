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
import com.zj.project.xm.xmsgjd.swgwsg.dao.XmSgjdSwgwsgJdDao;
import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgJdDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwsgJdService;

/**
 * 
 * <pre>
 * 施工进度-室外管网施工-进度，通过更新日期与类型生成多条进度内容。
 * </pre>
 * <small> 2018-10-20 20:40:33 | lijun</small>
 */
@Service
public class XmSgjdSwgwsgJdServiceImpl extends BaseServiceImpl<XmSgjdSwgwsgJdDao, XmSgjdSwgwsgJdDO> implements XmSgjdSwgwsgJdService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdSwgwsgJdDO.class);
    }
    
    @Override
    public boolean updateById(XmSgjdSwgwsgJdDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmSgjdSwgwsgJdDO> updateWrapper=new UpdateWrapper<XmSgjdSwgwsgJdDO>().eq("id", entity.getId());
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
    public Collection<XmSgjdSwgwsgJdDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


}
