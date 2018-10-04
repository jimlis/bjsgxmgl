package com.zj.project.xm.xmzpms.service.impl;

import org.springframework.stereotype.Service;

import com.zj.project.xm.xmzpms.dao.XmZpmsDao;
import com.zj.project.xm.xmzpms.domain.XmZpmsDO;
import com.zj.project.xm.xmzpms.service.XmZpmsService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * <pre>
 * 项目基本信息-照片描述
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
@Service
public class XmZpmsServiceImpl extends BaseServiceImpl<XmZpmsDao, XmZpmsDO> implements XmZpmsService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmZpmsDO.class);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmZpmsDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


}
