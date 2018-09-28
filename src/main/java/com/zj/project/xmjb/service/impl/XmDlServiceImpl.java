package com.zj.project.xmjb.service.impl;

import org.springframework.stereotype.Service;

import com.zj.project.xmjb.dao.XmDlDao;
import com.zj.project.xmjb.domain.XmDlDO;
import com.zj.project.xmjb.service.XmDlService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * <pre>
 * 项目基本信息-栋楼,记录每栋楼名称及编号
 * </pre>
 * <small> 2018-09-28 22:39:21 | lijun</small>
 */
@Service
public class XmDlServiceImpl extends BaseServiceImpl<XmDlDao, XmDlDO> implements XmDlService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmDlDO.class);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmDlDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


}
