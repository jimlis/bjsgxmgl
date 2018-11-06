package com.zj.project.xm.xmsgjd.sgjdjcsgnew.service.impl;

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
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;
import com.zj.project.xm.xmsgjd.sgjdjcsgkz.domain.XmSgjdJcsgKzDO;
import com.zj.project.xm.xmsgjd.sgjdjcsgkz.service.XmSgjdJcsgKzService;
import com.zj.project.xm.xmsgjd.sgjdjcsgnew.dao.XmSgjdJcsgnewDao;
import com.zj.project.xm.xmsgjd.sgjdjcsgnew.domain.XmSgjdJcsgnewDO;
import com.zj.project.xm.xmsgjd.sgjdjcsgnew.service.XmSgjdJcsgnewService;

/**
 * 
 * <pre>
 * 施工进度-基础施工新
 * </pre>
 * <small> 2018-11-04 20:58:53 | lijun</small>
 */
@Service
public class XmSgjdJcsgnewServiceImpl extends BaseServiceImpl<XmSgjdJcsgnewDao, XmSgjdJcsgnewDO> implements XmSgjdJcsgnewService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdJcsgnewDO.class);
    }
    
    @Autowired
    private XmSgjdJcsgKzService xmSgjdJcsgKzService;
    
    @Autowired
    private XmGqjdbjService xmGqjdbjService;
    
    @Autowired
    private FileService fileService;

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdJcsgnewDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存新的基础施工信息
     * @param XmSgjdJcsgnewDO
     * @fileIds 文件ids
     * @param dljcs  独立基础json
     * @param zjcs 桩基础json
     */
    @Override
    public void saveXmSgjdJcsgnewXx(XmSgjdJcsgnewDO xmSgjdJcsgnewDO,String fileIds,String dljcs,String zjcs) {
    	Long id = xmSgjdJcsgnewDO.getId();
    	Long intxmid = xmSgjdJcsgnewDO.getIntxmid();
    	if(intxmid==null) {
    		throw new MyApiException("xmid不能为空");
    	}
    	if(id==null) {
    		xmSgjdJcsgnewDO.setGxsj(new Date());
    		xmSgjdJcsgnewDO.setFcbz(1);
    		save(xmSgjdJcsgnewDO);
    	}else {
    		xmSgjdJcsgnewDO.setGxsj(new Date());
    		updateById(xmSgjdJcsgnewDO);
    	}
    	
    	Long newId=xmSgjdJcsgnewDO.getId();
    	
    	
    	// 更新图片信息
		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fileArr = fileIds.trim().split(",");
			for (String fileid : fileArr) {
				if (StringUtils.isNotEmpty(fileid.trim())) {
					FileDO fileDO = new FileDO();
					fileDO.setId(Long.parseLong(fileid.trim()));
					fileDO.setBusType(tableInfo.getTableName());
					fileDO.setBusId(id);
					fileService.updateById(fileDO);
				}
			}
		}
    	
    	Gson gson=new Gson();
    	//新增独立基础
    	if(StringUtils.isNotBlank(dljcs)&&!"[]".equals(dljcs)) {
    		List<XmSgjdJcsgKzDO> list = gson.fromJson(dljcs, new TypeToken<List<XmSgjdJcsgKzDO>>() {
    				}.getType());
    		if(CollectionUtils.isNotEmpty(list)) {
    			list.forEach(one->{
    				Long tzId=one.getId();
    				if(tzId==null) {
    					one.setGxsj(new Date());
    					one.setFcbz(1);
    					one.setIntxmid(intxmid);
    					one.setIntjcsgid(newId);
    					one.setChrjclx("dljc");
    					xmSgjdJcsgKzService.save(one);
    					}else {
    						one.setGxsj(new Date());
    						xmSgjdJcsgKzService.updateById(one);
    					}
    			});
    		}
    	}
    	
    	
    	//新增桩基础
    	if(StringUtils.isNotBlank(zjcs)&&!"[]".equals(zjcs)) {
    		List<XmSgjdJcsgKzDO> list = gson.fromJson(zjcs, new TypeToken<List<XmSgjdJcsgKzDO>>() {
    				}.getType());
    		if(CollectionUtils.isNotEmpty(list)) {
    			list.forEach(one->{
    				Long tzId=one.getId();
    				if(tzId==null) {
    					one.setGxsj(new Date());
    					one.setFcbz(1);
    					one.setIntxmid(intxmid);
    					one.setIntjcsgid(newId);
    					one.setChrjclx("zjc");
    					xmSgjdJcsgKzService.save(one);
    					}else {
    						one.setGxsj(new Date());
    						xmSgjdJcsgKzService.updateById(one);
    					}
    			});
    		}
    	}
    }
    
    /**
     * 根据项目id和施工位置获取基础施工信息
     * @param xmid 项目id
     * @param sgwzid 丝攻位置
     * @return XmSgjdJcsgnewDO
     */
    @Override 
    public  XmSgjdJcsgnewDO getXmSgjdJcsgnewByXmidAndSgwzid(Long xmid,Long sgwzid) {
    	if(xmid==null) {
    		return null;
    	}
    	if(sgwzid==null) {
    		return null;
    	}
    	XmSgjdJcsgnewDO xmSgjdJcsgnewDO=new XmSgjdJcsgnewDO();
    	xmSgjdJcsgnewDO.setIntxmid(xmid);
    	xmSgjdJcsgnewDO.setIntsgwzid(sgwzid);
    	xmSgjdJcsgnewDO.setFcbz(1);
    	QueryWrapper<XmSgjdJcsgnewDO> queryWrapper=new QueryWrapper<XmSgjdJcsgnewDO>(xmSgjdJcsgnewDO).orderByDesc("id");
    	List<XmSgjdJcsgnewDO> list=list(queryWrapper);
    	if(CollectionUtils.isEmpty(list)) {
    		xmSgjdJcsgnewDO.setDljcs(Lists.newArrayList());
    		xmSgjdJcsgnewDO.setZjcs(Lists.newArrayList());
    		//施工位置
    		if(sgwzid!=null) {
    			if(sgwzid.equals(-1L)) {
    				xmSgjdJcsgnewDO.setChrsgwz("其他");
    			}else {
    				XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(sgwzid);
        			if(xmGqjdbjDO!=null) {
        				xmSgjdJcsgnewDO.setChrsgwz(xmGqjdbjDO.getChrjdmc());
        			}
    			}
    		}
    		return xmSgjdJcsgnewDO;
    	}else {
    		XmSgjdJcsgnewDO newObj=list.get(0);
    		
    		//施工位置
    		Long intsgwzid = newObj.getIntsgwzid();
    		if(intsgwzid!=null) {
    			if(intsgwzid.equals(-1L)) {
    				newObj.setChrsgwz("其他");
    			}else {
    				XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intsgwzid);
        			if(xmGqjdbjDO!=null) {
        				newObj.setChrsgwz(xmGqjdbjDO.getChrjdmc());
        			}
    			}
    		}
    		
    		//完成
    		Integer intsfwc = newObj.getIntsfwc();
    		if(intsfwc!=null) {
    			String chrsfwc="";
    			if(intsfwc.equals(0)){
    				chrsfwc="未完成";
    			}else if(intsfwc.equals(1)) {
    				chrsfwc="完成";
    			}
    			newObj.setChrsfwc(chrsfwc);
    		}
    		
    		//查询装基础
    		XmSgjdJcsgKzDO zjqObj=new XmSgjdJcsgKzDO();
    		zjqObj.setIntjcsgid(newObj.getId());
    		zjqObj.setFcbz(1);
    		zjqObj.setChrjclx("zjc");
    		QueryWrapper<XmSgjdJcsgKzDO> zjcWrapper=new QueryWrapper<XmSgjdJcsgKzDO>(zjqObj).orderByAsc("id");
    		List<XmSgjdJcsgKzDO> zjcList = xmSgjdJcsgKzService.list(zjcWrapper);
    		if(CollectionUtils.isNotEmpty(zjcList)) {
    			zjcList.forEach(one->{
    				one.setId(null);
    			});
    			newObj.setZjcs(zjcList);
    		}
    		
    		//独立基础
    		XmSgjdJcsgKzDO dljcObj=new XmSgjdJcsgKzDO();
    		dljcObj.setIntjcsgid(newObj.getId());
    		dljcObj.setFcbz(1);
    		dljcObj.setChrjclx("dljc");
    		QueryWrapper<XmSgjdJcsgKzDO> dljcWrapper=new QueryWrapper<XmSgjdJcsgKzDO>(dljcObj).orderByAsc("id");
    		List<XmSgjdJcsgKzDO> dljcList = xmSgjdJcsgKzService.list(dljcWrapper);
    		if(CollectionUtils.isNotEmpty(dljcList)) {
    			dljcList.forEach(one->{
    				one.setId(null);
    			});
    			newObj.setDljcs(dljcList);
    		}
    		
    		
    		return newObj;
    	}
    	
    	
    }


}
