package com.zj.project.xm.xmghzb.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.common.web.exception.MyApiException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.zj.project.xm.xmghzb.dao.XmGhzbDao;
import com.zj.project.xm.xmghzb.domain.XmGhzbDO;
import com.zj.project.xm.xmghzb.service.XmGhzbService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 项目基本信息-规划指标数据
 * </pre>
 * <small> 2018-10-04 18:35:49 | lijun</small>
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

    /**
     * 批量保存项目的规划信息
     * @param xmid
     * @param json
     */
    @Override
    public void saveBatchXmGhzbxx(Long xmid,String json){
        if(xmid==null){
            throw  new MyApiException("44005");
        }

        Gson gson=new Gson();
        List<XmGhzbDO> list = gson.fromJson(json, new TypeToken<List<XmGhzbDO>>() {
        }.getType());

        if(CollectionUtils.isNotEmpty(list)){
            list.forEach(xmGhzbDO->{
                Long id=xmGhzbDO.getId();
                if(id==null){
                    xmGhzbDO.setFcbz(1);
                    xmGhzbDO.setGxsj(new Date());
                    save(xmGhzbDO);
                }else{
                    xmGhzbDO.setGxsj(new Date());
                    updateById(xmGhzbDO);
                }
            });
        }

       // saveOrUpdateBatch(list);
    }


}
