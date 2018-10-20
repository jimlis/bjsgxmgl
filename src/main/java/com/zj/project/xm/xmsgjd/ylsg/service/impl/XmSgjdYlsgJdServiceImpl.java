package com.zj.project.xm.xmsgjd.ylsg.service.impl;

import org.springframework.stereotype.Service;

import com.zj.project.xm.xmsgjd.ylsg.dao.XmSgjdYlsgJdDao;
import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgJdDO;
import com.zj.project.xm.xmsgjd.ylsg.service.XmSgjdYlsgJdService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * <pre>
 * 施工进度-园林施工-进度
 * </pre>
 * <small> 2018-10-20 23:35:49 | lijun</small>
 */
@Service
public class XmSgjdYlsgJdServiceImpl extends BaseServiceImpl<XmSgjdYlsgJdDao, XmSgjdYlsgJdDO> implements XmSgjdYlsgJdService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdYlsgJdDO.class);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdYlsgJdDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


}
