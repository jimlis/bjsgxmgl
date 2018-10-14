package com.zj.project.xm.xmdwmd.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmdwmd.dao.XmDwmdDao;
import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmdwmd.service.XmDwmdService;

/**
 * 
 * <pre>
 * 项目基本信息-所有单位名单
 * </pre>
 * <small> 2018-10-09 21:32:05 | lijun</small>
 */
@Service
public class XmDwmdServiceImpl extends BaseServiceImpl<XmDwmdDao, XmDwmdDO> implements XmDwmdService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmDwmdDO.class);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmDwmdDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }

    /**
     * 获取项目的单位名单信息
     * @param xmid 项目id
     * @return map  {"1":[],"2":"[],"3":""}
     */
    @Override
    public  Map<String,List<XmDwmdDO>> getXmDwmdxxByXmid(Long xmid){
                    if(xmid==null) throw  new CommonException("项目id不能为空");
                    List<XmDwmdDO> list=baseMapper.getXmDwmdxxListByXmid(xmid);
                    Map<String,List<XmDwmdDO>> map=new HashMap<String,List<XmDwmdDO>>();
                    if(CollectionUtils.isNotEmpty(list)){
                        list.forEach(xmDwmdDO->{
                            String intlxmd=xmDwmdDO.getIntlxmd();
                            if(map.containsKey(intlxmd)){
                                List<XmDwmdDO> newList=map.get(intlxmd);
                                                                   newList.add(xmDwmdDO);
                                                                    map.put(intlxmd,newList);
                            }else{
                                List<XmDwmdDO> newList=Lists.newArrayList();
                                newList.add(xmDwmdDO);
                                map.put(intlxmd,newList);
                            }
                        });
                        if(!map.containsKey("1")) {
                        	  map.put("1", Lists.newArrayList());
                        }
                        if(!map.containsKey("2")) {
                      	  map.put("2", Lists.newArrayList());
                        }
                        if(!map.containsKey("3")) {
                      	  map.put("3", Lists.newArrayList());
                        }
                        return  map;
                    }else{
                        map.put("1", Lists.newArrayList());
                        map.put("2", Lists.newArrayList());
                        map.put("3", Lists.newArrayList());
                        return  map;
                    }

    }

    /**
     * 获取项目的已建单位名单信息
     * @param xmid 项目id
     * @return
     */
    @Override
    public  List<XmDwmdDO> getXmYjDwmdByXmid(Long xmid){
        if(xmid==null) throw  new CommonException("项目id不能为空");
        return baseMapper.getXmDwmdxxListByXmid(xmid);
    }


    /**
     * 获取单位类型
     * @param xmid 项目id
     * @param lxmd 单位名单
     * @return
     */
    @Override
    public  List<DictDO> getDwlx(Long xmid, String lxmd){
        if(xmid==null) throw  new CommonException("项目id不能为空");
        if(lxmd==null) throw  new CommonException("单位名单不能为空");
        return  baseMapper.getDwlx(xmid,lxmd);
    }



}
