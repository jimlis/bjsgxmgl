package com.zj.project.xm.xmsgjd.ecjgzx.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.platform.shiro.util.ShiroUtils;
import com.zj.project.xm.xmclybspjl.domain.XmClybspjlJszlDO;
import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;
import com.zj.project.xm.xmsgjd.ecjgzx.dao.XmSgjdEcjgzxDao;
import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxDO;
import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxWclDO;
import com.zj.project.xm.xmsgjd.ecjgzx.service.XmSgjdEcjgzxService;
import com.zj.project.xm.xmsgjd.ecjgzx.service.XmSgjdEcjgzxWclService;

/**
 * 
 * <pre>
 * 二次结构装修：施工进度-二次结构、装修等施工
 * </pre>
 * <small> 2018-10-13 20:45:56 | lijun</small>
 */
@Service
public class XmSgjdEcjgzxServiceImpl extends BaseServiceImpl<XmSgjdEcjgzxDao, XmSgjdEcjgzxDO> implements XmSgjdEcjgzxService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdEcjgzxDO.class);
    }
    
    @Autowired
    private  XmSgjdEcjgzxWclService xmSgjdEcjgzxWclService;
    
    @Autowired
    private  XmGqjdbjService xmGqjdbjService;
    
    @Override
	public XmSgjdEcjgzxDO getById(Serializable id) {
    	XmSgjdEcjgzxDO xmSgjdEcjgzxDO = super.getById(id);
    	if(xmSgjdEcjgzxDO!=null) {
    		//楼栋名称
    		Long intdid = xmSgjdEcjgzxDO.getIntdid();
    		XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intdid);
    		if(xmGqjdbjDO!=null) {
    			xmSgjdEcjgzxDO.setChrdmc(xmGqjdbjDO.getChrjdmc());
    		}
    		// 完成量
        	XmSgjdEcjgzxWclDO xmSgjdEcjgzxWclDO = new XmSgjdEcjgzxWclDO();
        	xmSgjdEcjgzxWclDO.setFcbz(1);
        	xmSgjdEcjgzxWclDO.setIntecjgzxid(xmSgjdEcjgzxDO.getId());
    		QueryWrapper<XmSgjdEcjgzxWclDO> queryWrapper = new QueryWrapper<XmSgjdEcjgzxWclDO>(xmSgjdEcjgzxWclDO)
    				.orderByAsc("intxh");
    		List<XmSgjdEcjgzxWclDO> list =xmSgjdEcjgzxWclService.list(queryWrapper);
    		xmSgjdEcjgzxDO.setXmSgjdEcjgzxWclList(list);
    	}

		return xmSgjdEcjgzxDO;
	}

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdEcjgzxDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存施工记录
     * @param xmSgjdEcjgzxDO
     * @param xmSgjdEcjgzxWclJson 完成量对象json
     * @param deleteWclIds 删除的完成量ids
     */
	@Override
	public void saveXmSgjdEcjgzxxx(XmSgjdEcjgzxDO xmSgjdEcjgzxDO, String xmSgjdEcjgzxWclJson,String deleteWclIds) {
		Long xmid = xmSgjdEcjgzxDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdEcjgzxDO.getId();
		if (id == null) {
			xmSgjdEcjgzxDO.setFcbz(1);
			xmSgjdEcjgzxDO.setGxsj(new Date());
			save(xmSgjdEcjgzxDO);
		} else {
			xmSgjdEcjgzxDO.setGxsj(new Date());
			updateById(xmSgjdEcjgzxDO);
		}

		id = xmSgjdEcjgzxDO.getId();
		
		List<XmSgjdEcjgzxWclDO> xmSgjdEcjgzxWclList = xmSgjdEcjgzxDO.getXmSgjdEcjgzxWclList();
		if(CollectionUtils.isNotEmpty(xmSgjdEcjgzxWclList)) {
			xmSgjdEcjgzxWclList.forEach(xmSgjdEcjgzxWclDO -> {
				Long xmSgjdEcjgzxWclId = xmSgjdEcjgzxWclDO.getId();
				String chrlc = xmSgjdEcjgzxWclDO.getChrlc();
				if(StringUtils.isNotEmpty(chrlc)) {
					if (xmSgjdEcjgzxWclId == null) {
						xmSgjdEcjgzxWclDO.setFcbz(1);
						xmSgjdEcjgzxWclDO.setGxsj(new Date());
						xmSgjdEcjgzxWclDO.setIntecjgzxid(xmSgjdEcjgzxDO.getId());
						xmSgjdEcjgzxWclService.save(xmSgjdEcjgzxWclDO);
					} else {
						xmSgjdEcjgzxWclDO.setGxsj(new Date());
						xmSgjdEcjgzxWclService.updateById(xmSgjdEcjgzxWclDO);
					}
				}
			});
		}
		
		//删除完成量
		if(StringUtils.isNotEmpty(deleteWclIds)) {
			Arrays.stream(deleteWclIds.trim().split(",")).forEach(wclId->{
				if(StringUtils.isNotEmpty(wclId)) {
					XmSgjdEcjgzxWclDO xmSgjdEcjgzxWclDO=new XmSgjdEcjgzxWclDO();
					xmSgjdEcjgzxWclDO.setId(Long.parseLong(wclId));
					xmSgjdEcjgzxWclDO.setFcbz(0);
					xmSgjdEcjgzxWclService.updateById(xmSgjdEcjgzxWclDO);
				}
			});
		}
		
		
		// 完成量
		if (StringUtils.isNotEmpty(xmSgjdEcjgzxWclJson) && !"[]".equals(xmSgjdEcjgzxWclJson)) {
			Gson gson = new Gson();
			List<XmSgjdEcjgzxWclDO> list = gson.fromJson(xmSgjdEcjgzxWclJson, new TypeToken<List<XmClybspjlJszlDO>>() {
			}.getType());
			if (CollectionUtils.isNotEmpty(list)) {
				list.forEach(xmSgjdEcjgzxWclDO -> {
					Long xmSgjdEcjgzxWclId = xmSgjdEcjgzxWclDO.getId();
					if (xmSgjdEcjgzxWclId == null) {
						xmSgjdEcjgzxWclDO.setFcbz(1);
						xmSgjdEcjgzxWclDO.setGxsj(new Date());
						xmSgjdEcjgzxWclDO.setIntecjgzxid(xmSgjdEcjgzxDO.getId());
						xmSgjdEcjgzxWclService.save(xmSgjdEcjgzxWclDO);
					} else {
						xmSgjdEcjgzxWclDO.setGxsj(new Date());
						xmSgjdEcjgzxWclService.updateById(xmSgjdEcjgzxWclDO);
					}
				});
			}
		}
	}
	
	/**
	 * 获取施工记录id
	 * @param xmSgjdEcjgzxId 二次施工进度id
	 * @param xmid 项目id
	 * @param did 栋id
	 * @param fwlx 发起类型 xz-新增  cx-查询
	 */
	@Override
	public XmSgjdEcjgzxDO getXmSgjdEcjgzxByParam(Long xmSgjdEcjgzxId, Long xmid, Long did,String fwlx) {
		if(xmSgjdEcjgzxId==null&&(xmid==null||did==null)) {
				return null;
		}
		
		UserDO appUserDO = ShiroUtils.getAppUserDO();
		
		XmSgjdEcjgzxDO xmSgjdEcjgzxDO=null;
		//主键存在 优先主键查询 否则查询最新记录
		if(xmSgjdEcjgzxId!=null) {
			xmSgjdEcjgzxDO=getById(xmSgjdEcjgzxId);
		}else {
			xmSgjdEcjgzxDO=new XmSgjdEcjgzxDO();
			xmSgjdEcjgzxDO.setFcbz(1);
			xmSgjdEcjgzxDO.setIntxmid(xmid);
			xmSgjdEcjgzxDO.setIntdid(did);
			QueryWrapper<XmSgjdEcjgzxDO> queryWrapper=new QueryWrapper<XmSgjdEcjgzxDO>(xmSgjdEcjgzxDO).orderByDesc("id");
			List<XmSgjdEcjgzxDO> list=list(queryWrapper);
			if(CollectionUtils.isNotEmpty(list)) {
				xmSgjdEcjgzxDO=list.get(0);
				
				//楼栋名称
	    		Long intdid = xmSgjdEcjgzxDO.getIntdid();
	    		XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intdid);
	    		if(xmGqjdbjDO!=null) {
	    			xmSgjdEcjgzxDO.setChrdmc(xmGqjdbjDO.getChrjdmc());
	    		}
	    		// 完成量
	        	XmSgjdEcjgzxWclDO xmSgjdEcjgzxWclDO = new XmSgjdEcjgzxWclDO();
	        	xmSgjdEcjgzxWclDO.setFcbz(1);
	        	xmSgjdEcjgzxWclDO.setIntecjgzxid(xmSgjdEcjgzxDO.getId());
	    		QueryWrapper<XmSgjdEcjgzxWclDO> wclQueryWrapper = new QueryWrapper<XmSgjdEcjgzxWclDO>(xmSgjdEcjgzxWclDO)
	    				.orderByAsc("intxh");
	    		List<XmSgjdEcjgzxWclDO> wclList =xmSgjdEcjgzxWclService.list(wclQueryWrapper);
	    		xmSgjdEcjgzxDO.setXmSgjdEcjgzxWclList(wclList);
			}else {//不存在 就构造一个新的
				
				if("xz".equals(fwlx)) {//新增
					xmSgjdEcjgzxDO.setChrbgrmc(appUserDO.getName());
					xmSgjdEcjgzxDO.setIntbgrid(appUserDO.getId());
					xmSgjdEcjgzxDO.setDtmgxrq(new Date());
				}
				
				xmSgjdEcjgzxDO.setIntxh(0);
				
				//楼栋名称
	    		Long intdid = xmSgjdEcjgzxDO.getIntdid();
	    		XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intdid);
	    		if(xmGqjdbjDO!=null) {
	    			xmSgjdEcjgzxDO.setChrdmc(xmGqjdbjDO.getChrjdmc());
	    		}
	    		// 完成量
	    		xmSgjdEcjgzxDO.setXmSgjdEcjgzxWclList(Lists.newArrayList());
			}
		}
		
		return xmSgjdEcjgzxDO;
	}


}
