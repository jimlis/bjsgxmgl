package com.zj.project.xm.xmdwmd.dao;

import com.zj.platform.business.dict.domain.DictDO;
import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.platform.common.web.dao.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * <pre>
 * 项目基本信息-所有单位名单
 * </pre>
 * <small> 2018-10-09 21:32:05 | lijun</small>
 */
public interface XmDwmdDao extends Dao<XmDwmdDO> {
        public List<XmDwmdDO> getXmDwmdxxListByXmid(@Param("xmid") Long xmid);

        public  List<DictDO> getDwlx(@Param("xmid") Long xmid,@Param("lxmd") String lxmd);
}
