package com.zj.project.xm.xmsgjd.ecjgzx.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmsgjd.ecjgzx.dao.XmSgjdEcjgzxWclDao;
import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxWclDO;
import com.zj.project.xm.xmsgjd.ecjgzx.service.XmSgjdEcjgzxWclService;

/**
 * 
 * <pre>
 * 施工进度-二次结构、装修等施工-完成量
 * </pre>
 * <small> 2018-10-13 20:45:57 | lijun</small>
 */
@Service
public class XmSgjdEcjgzxWclServiceImpl extends BaseServiceImpl<XmSgjdEcjgzxWclDao, XmSgjdEcjgzxWclDO> implements XmSgjdEcjgzxWclService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdEcjgzxWclDO.class);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdEcjgzxWclDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * <p>Title: 根据ecjgzxid和lcid获取完成量</p>  
     */
    @Override
    public List<XmSgjdEcjgzxWclDO> getXXmSgjdEcjgzxWclListByEcjgzxidAndLcid(Map<String,Object> map){
    	return baseMapper.getXXmSgjdEcjgzxWclListByEcjgzxidAndLcid(map);
    	
    }


}
