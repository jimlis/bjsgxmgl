package com.zj.project.xm.xmxmcjdw.service.impl;

import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmdwmd.service.XmDwmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.project.xm.xmxmcjdw.dao.XmXmcjdwDao;
import com.zj.project.xm.xmxmcjdw.domain.XmXmcjdwDO;
import com.zj.project.xm.xmxmcjdw.service.XmXmcjdwService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Date;
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

    @Autowired
    private XmDwmdService xmDwmdService;

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

    /**
     * 保存项目承建单位和单位名单
     * @param xmXmcjdwDO
     * @param xmDwmdDO
     */
    @Override
    public void saveXmXmcjdwAndXmDwmd(XmXmcjdwDO xmXmcjdwDO, XmDwmdDO xmDwmdDO) {
        Long xmXmcjdwId=xmXmcjdwDO.getId();
        Long xmDwmdId=xmDwmdDO.getId();
            if(xmXmcjdwId==null){
                xmXmcjdwDO.setGxsj(new Date());
                xmXmcjdwDO.setFcbz(1);
                save(xmXmcjdwDO);
            }else{
                updateById(xmXmcjdwDO);
            }


            if(xmDwmdId==null){
                xmDwmdDO.setGxsj(new Date());
                xmDwmdDO.setFcbz(1);
                xmDwmdService.save(xmDwmdDO);
            }else{
                xmDwmdService.updateById(xmDwmdDO);
            }
    }


}
