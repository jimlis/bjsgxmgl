package com.zj.project.gsgg.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.gsgg.dao.GsggNrDao;
import com.zj.project.gsgg.domain.GsggNrDO;
import com.zj.project.gsgg.service.GsggNrService;

/**
 * 
 * <pre>
 * 公司公告-内容
 * </pre>
 * <small> 2018-09-13 20:26:41 | lijun</small>
 */
@Service
public class GsggNrServiceImpl extends BaseServiceImpl<GsggNrDao, GsggNrDO> implements GsggNrService {
	
	public static TableInfo tableInfo = null;
	
    static {
    	tableInfo=TableInfoHelper.getTableInfo(GsggNrDO.class);
    }
    
    @Override
    public boolean updateById(GsggNrDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<GsggNrDO> updateWrapper=new UpdateWrapper<GsggNrDO>().eq("id", entity.getId());
    	fieldList.forEach(filed->{
    		if(filed.isCharSequence()&&FieldStrategy.NOT_EMPTY==filed.getFieldStrategy()) {
    			String value = (String)ReflectionKit.getMethodValue(entity, filed.getProperty());
        		updateWrapper.set(MyStringUtils.isEmptyString(value),filed.getColumn(),value);
    		}
    	});
    	return update(entity, updateWrapper);
    }
    
}
