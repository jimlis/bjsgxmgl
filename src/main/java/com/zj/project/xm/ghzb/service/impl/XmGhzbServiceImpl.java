package com.zj.project.xm.ghzb.service.impl;

import org.springframework.stereotype.Service;

import com.zj.project.xm.ghzb.dao.XmGhzbDao;
import com.zj.project.xm.ghzb.domain.XmGhzbDO;
import com.zj.project.xm.ghzb.service.XmGhzbService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * <pre>
 * 项目基本信息-规划指标数据
 * </pre>
 * <small> 2018-10-04 13:41:37 | lijun</small>
 */
@Service
public class XmGhzbServiceImpl extends BaseServiceImpl<XmGhzbDao, XmGhzbDO> implements XmGhzbService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmGhzbDO.class);
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


}
