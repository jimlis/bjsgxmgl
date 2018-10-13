package com.zj.project.xm.xmdlcs.service.impl;

import org.springframework.stereotype.Service;

import com.zj.project.xm.xmdlcs.dao.XmDlCsDao;
import com.zj.project.xm.xmdlcs.domain.XmDlCsDO;
import com.zj.project.xm.xmdlcs.service.XmDlCsService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * <pre>
 * 项目基本信息-栋楼-层数,记录每层情况
 * </pre>
 * <small> 2018-10-13 20:26:30 | lijun</small>
 */
@Service
public class XmDlCsServiceImpl extends BaseServiceImpl<XmDlCsDao, XmDlCsDO> implements XmDlCsService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmDlCsDO.class);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmDlCsDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


}
