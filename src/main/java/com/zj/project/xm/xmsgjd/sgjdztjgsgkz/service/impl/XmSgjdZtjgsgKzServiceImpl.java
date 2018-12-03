package com.zj.project.xm.xmsgjd.sgjdztjgsgkz.service.impl;

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
import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.dao.XmSgjdZtjgsgKzDao;
import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.domain.XmSgjdZtjgsgKzDO;
import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.service.XmSgjdZtjgsgKzService;

/**
 * 
 * <pre>
 * 施工进度-主体施工扩展
 * </pre>
 * <small> 2018-11-05 19:27:20 | lijun</small>
 */
@Service
public class XmSgjdZtjgsgKzServiceImpl extends BaseServiceImpl<XmSgjdZtjgsgKzDao, XmSgjdZtjgsgKzDO> implements XmSgjdZtjgsgKzService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdZtjgsgKzDO.class);
    }
    
    @Override
    public boolean updateById(XmSgjdZtjgsgKzDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmSgjdZtjgsgKzDO> updateWrapper=new UpdateWrapper<XmSgjdZtjgsgKzDO>().eq("id", entity.getId());
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
    public Collection<XmSgjdZtjgsgKzDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * <p>Title: 根据Xmid和Ztsgid获取扩展id</p>  
     */
    @Override
    public List<XmSgjdZtjgsgKzDO> getXmSgjdZtKzListByXmidAndZtsgid(Map<String,Object> map){
    	return baseMapper.getXmSgjdZtKzListByXmidAndZtsgid(map);
    	
    }


}
