package com.zj.project.xm.xmxmcjdw.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmdwmd.service.XmDwmdService;
import com.zj.project.xm.xmxmcjdw.dao.XmXmcjdwDao;
import com.zj.project.xm.xmxmcjdw.domain.XmXmcjdwDO;
import com.zj.project.xm.xmxmcjdw.service.XmXmcjdwService;

/**
 * 
 * <pre>
 * 项目承建各方名称
 * </pre>
 * <small> 2018-09-25 22:20:27 | lijun</small>
 */
@Service
public class XmXmcjdwServiceImpl extends BaseServiceImpl<XmXmcjdwDao, XmXmcjdwDO> implements XmXmcjdwService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmXmcjdwDO.class);
    }

    @Autowired
    private XmDwmdService xmDwmdService;
    
    @Override
    public boolean updateById(XmXmcjdwDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmXmcjdwDO> updateWrapper=new UpdateWrapper<XmXmcjdwDO>().eq("id", entity.getId());
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
    public Collection<XmXmcjdwDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }

    /**
     * 保存项目承建单位和单位名单
     * @param xmXmcjdwDO
     * @param xmDwmdDO
     */
    @Override
    public void saveXmXmcjdwAndXmDwmd(XmXmcjdwDO xmXmcjdwDO, XmDwmdDO xmDwmdDO) {
        Long intxmid=xmXmcjdwDO.getIntxmid();
        if(intxmid==null) {
        	throw new CommonException("xmid不能为空");
        }
        
        XmXmcjdwDO xmXmcjdwDOOne=new XmXmcjdwDO();
        xmXmcjdwDOOne.setFcbz(1);
        xmXmcjdwDOOne.setIntxmid(intxmid);
        QueryWrapper<XmXmcjdwDO>  queryWapper=new QueryWrapper<XmXmcjdwDO>(xmXmcjdwDOOne);
        XmXmcjdwDO one = getOne(queryWapper);
        
       
        if(one!=null) {
        	xmXmcjdwDO.setId(one.getId());
        }
        
        Long xmXmcjdwId=xmXmcjdwDO.getId();
        
        
        Long xmDwmdId=xmDwmdDO.getId();
            if(xmXmcjdwId==null){
                xmXmcjdwDO.setGxsj(new Date());
                xmXmcjdwDO.setFcbz(1);
                save(xmXmcjdwDO);
            }else{
                updateById(xmXmcjdwDO);
            }

            String chrdwmc = xmDwmdDO.getChrdwmc();
            if(StringUtils.isNotEmpty(chrdwmc)) {//单位名称不能为空
            	 if(xmDwmdId==null){
                     xmDwmdDO.setGxsj(new Date());
                     xmDwmdDO.setFcbz(1);
                     xmDwmdService.save(xmDwmdDO);
                 }else{
                     xmDwmdService.updateById(xmDwmdDO);
                 }
            }
    }


}
