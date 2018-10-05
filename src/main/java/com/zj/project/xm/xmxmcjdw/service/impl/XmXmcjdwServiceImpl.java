package com.zj.project.xm.xmxmcjdw.service.impl;

import org.springframework.stereotype.Service;

import com.zj.project.xm.xmxmcjdw.dao.XmXmcjdwDao;
import com.zj.project.xm.xmxmcjdw.domain.XmXmcjdwDO;
import com.zj.project.xm.xmxmcjdw.service.XmXmcjdwService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Map;

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


}
