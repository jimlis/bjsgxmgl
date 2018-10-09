package com.zj.project.xm.xmdwmd.service;

import com.zj.platform.business.dict.domain.DictDO;
import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-所有单位名单
 * </pre>
 * <small> 2018-10-09 21:32:05 | lijun</small>
 */
public interface XmDwmdService extends BaseService<XmDwmdDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmDwmdDO> listByParmMap(Map<String, Object> parmMap);

    /**
     * 获取项目的单位名单信息
     * @param xmid 项目id
     * @return map  {"1":[],"2":"[],"3":""}
     */
    Map<String,List<XmDwmdDO>> getXmDwmdxxByXmid(Long xmid);

    /**
     * 获取项目的已建单位名单信息
     * @param xmid 项目id
     * @return
     */
    List<XmDwmdDO> getXmYjDwmdByXmid(Long xmid);

    /**
     * 获取单位类型
     * @param xmid 项目id
     * @param lxmd 单位名单
     * @return
     */
    List<DictDO> getDwlx(Long xmid, String lxmd);
}
