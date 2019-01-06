package com.zj.project.xm.xmgqjdbj.service;

import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import  com.zj.platform.common.web.service.BaseService;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 
 * <pre>
 * 项目基本信息-主要工期节点比较
 * </pre>
 * <small> 2018-10-24 21:18:21 | lijun</small>
 */
public interface XmGqjdbjService extends BaseService<XmGqjdbjDO> {

    public boolean removeByParmMap(Map<String, Object> parmMap);

    public Collection<XmGqjdbjDO> listByParmMap(Map<String, Object> parmMap);
    
    /**
     * <p>Title:批量保存项目工期节点信息 </p>  
     * <p>Description: </p> 
     * @param xmid 项目id
     * @param jdlx 节点类型
     * @param gqjdbjJson 工期对象json、
     * @param deleteGqjdbjIds 删除工期对象ids
     * @date:2018年10月24日 下午10:20:17
     */
	void saveBatchXmGqjdbjXx(Long xmid, String jdlx, String gqjdbjJson, String deleteGqjdbjIds);
	
	/**
     * <p>Title: 删除节点类型</p>  
     * <p>Description: </p> 
     * @param gqjdbjId
     * @author zhujujun
     * @date:2018年10月25日 下午9:09:22
     */
	void deleteGqjdbjById(Long gqjdbjId);
	
	/**
     * <p>Title:获取主体施工信息 </p>  
     * <p>Description: </p> 
     * @param gqjdbjid
     * @param xmid
     * @return
     * @author zhujujun
     * @date:2018年11月6日 上午1:07:50
     */
	List<XmGqjdbjDO> getXmGqjdbjZtList(Long gqjdbjid, Long xmid);
	
	 /**
     * <p>Title:获取专项竣工验收信息 </p>  
     * <p>Description: </p> 
     * @param xmid
     * @return
     * @author zhujujun
     * @date:2018年11月6日 上午1:07:50
     */
	Map<String, List<XmGqjdbjDO>> getXmGqjdbjZxjgMapListByXmid(Long xmid);
	
	 /**
     * <p>Title:批量保存项目专项竣工工期节点信息 </p>  
     * <p>Description: </p> 
     * @param xmid 项目id
     * @param gqjdbjJson 工期对象json、
     * @param deleteGqjdbjIds 删除工期对象ids
     * @date:2018年10月24日 下午10:20:17
     */
	void batchSaveXmZxjgGqjdbjXx(Long xmid, String gqjdbjJson, String deleteGqjdbjIds);
	
	/**
     * <p>Title: 根据类型获取项目工期节点信息</p>  
     * <p>Description: </p> 
     * @param intxmid 项目id
     * @param lx 类型  1：已完成节点 2：已延误未完成数据
     * @return 
     * @author zhujujun
     * @date:2019年1月6日 下午3:10:14
     */
	List<Map<String, Object>> getXmgqjdbjXx(Long intxmid, String lx);
}
