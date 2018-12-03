package com.zj.project.xm.xmghzb.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmghzb.dao.XmGhzbDao;
import com.zj.project.xm.xmghzb.domain.XmGhzbDO;
import com.zj.project.xm.xmghzb.service.XmGhzbService;

/**
 * 
 * <pre>
 * 项目基本信息-规划指标数据
 * </pre>
 * <small> 2018-10-04 18:35:49 | lijun</small>
 */
@Service
public class XmGhzbServiceImpl extends BaseServiceImpl<XmGhzbDao, XmGhzbDO> implements XmGhzbService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmGhzbDO.class);
    }
    
    @Override
    public boolean updateById(XmGhzbDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmGhzbDO> updateWrapper=new UpdateWrapper<XmGhzbDO>().eq("id", entity.getId());
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
    public Collection<XmGhzbDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }

    /**
     * 批量保存项目的规划信息
     * @param xmid 项目id
     * @param ghzbJson 项目规划指标对象数组json字符串
     * @param deleteGhzbIds 删除项目规划指标ids
     */
    @Override
    public void saveBatchXmGhzbxx(Long xmid,String ghzbJson,String deleteGhzbIds){
        if(xmid==null){
            throw  new MyApiException("44005");
        }

        Gson gson=new Gson();
        List<XmGhzbDO> list = gson.fromJson(ghzbJson, new TypeToken<List<XmGhzbDO>>() {
        }.getType());
        
        //保存规划指标
        if(CollectionUtils.isNotEmpty(list)){
            list.forEach(xmGhzbDO->{
                Long id=xmGhzbDO.getId();
                String chrzbmc=xmGhzbDO.getChrzbmc();
                String chrzbz=xmGhzbDO.getChrzbz();
                if(StringUtils.isNotEmpty(chrzbmc)&&StringUtils.isNotEmpty(chrzbz)) {
                	 if(id==null){
                         xmGhzbDO.setFcbz(1);
                         xmGhzbDO.setGxsj(new Date());
                         xmGhzbDO.setIntxmid(xmid);
                         save(xmGhzbDO);
                     }else{
                         xmGhzbDO.setGxsj(new Date());
                         updateById(xmGhzbDO);
                     }
                }
            });
        }
        
        //删除规划指标
        if(StringUtils.isNotEmpty(deleteGhzbIds)) {
        	Arrays.stream(deleteGhzbIds.trim().split(",")).forEach(ghzbId->{
        		if(StringUtils.isNotEmpty(ghzbId)) {
        			XmGhzbDO xmGhzbDO=new XmGhzbDO();
        			xmGhzbDO.setId(Long.parseLong(ghzbId));
        			xmGhzbDO.setFcbz(0);
        			xmGhzbDO.setGxsj(new Date());
        			updateById(xmGhzbDO);
        		}
        	});
        }

    }


}
