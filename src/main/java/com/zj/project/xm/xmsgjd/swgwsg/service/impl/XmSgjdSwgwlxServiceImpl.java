package com.zj.project.xm.xmsgjd.swgwsg.service.impl;

import org.springframework.stereotype.Service;

import com.zj.project.xm.xmsgjd.swgwsg.dao.XmSgjdSwgwlxDao;
import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwlxDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwlxService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Map;

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
