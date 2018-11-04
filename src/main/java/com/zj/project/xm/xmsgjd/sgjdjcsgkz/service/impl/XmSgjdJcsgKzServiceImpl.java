package com.zj.project.xm.xmsgjd.sgjdjcsgkz.service.impl;

import org.springframework.stereotype.Service;

import com.zj.project.xm.xmsgjd.sgjdjcsgkz.dao.XmSgjdJcsgKzDao;
import com.zj.project.xm.xmsgjd.sgjdjcsgkz.domain.XmSgjdJcsgKzDO;
import com.zj.project.xm.xmsgjd.sgjdjcsgkz.service.XmSgjdJcsgKzService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * <pre>
 * 施工进度-基础施工扩展
 * </pre>
 * <small> 2018-11-04 20:55:42 | lijun</small>
 */
@Service
public class XmSgjdJcsgKzServiceImpl extends BaseServiceImpl<XmSgjdJcsgKzDao, XmSgjdJcsgKzDO> implements XmSgjdJcsgKzService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdJcsgKzDO.class);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdJcsgKzDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


}
