package com.zj.project.xm.xmsgjd.sgjdztjgsgkz.service.impl;

import org.springframework.stereotype.Service;

import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.dao.XmSgjdZtjgsgKzDao;
import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.domain.XmSgjdZtjgsgKzDO;
import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.service.XmSgjdZtjgsgKzService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * <pre>
 * 施工进度-主体施工扩展
 * </pre>
 * <small> 2018-11-05 19:27:20 | lijun</small>
 */
@Service
public class XmSgjdZtjgsgKzServiceImpl extends BaseServiceImpl<XmSgjdZtjgsgKzDao, XmSgjdZtjgsgKzDO> implements XmSgjdZtjgsgKzService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdZtjgsgKzDO.class);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdZtjgsgKzDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


}
