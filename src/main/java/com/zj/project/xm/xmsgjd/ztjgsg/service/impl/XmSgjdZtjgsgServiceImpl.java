package com.zj.project.xm.xmsgjd.ztjgsg.service.impl;

import java.io.Serializable;
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
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.platform.shiro.util.ShiroUtils;
import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;
import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.domain.XmSgjdZtjgsgKzDO;
import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.service.XmSgjdZtjgsgKzService;
import com.zj.project.xm.xmsgjd.ztjgsg.dao.XmSgjdZtjgsgDao;
import com.zj.project.xm.xmsgjd.ztjgsg.domain.XmSgjdZtjgsgDO;
import com.zj.project.xm.xmsgjd.ztjgsg.service.XmSgjdZtjgsgService;
import com.zj.project.xm.xmzpms.domain.XmZpmsDO;
import com.zj.project.xm.xmzpms.service.XmZpmsService;

/**
 * 
 * <pre>
 * 施工进度 - 主体结构施工
 * </pre>
 * 
 * <small> 2018-10-13 20:03:27 | lijun</small>
 */
@Service
public class XmSgjdZtjgsgServiceImpl extends BaseServiceImpl<XmSgjdZtjgsgDao, XmSgjdZtjgsgDO>
		implements XmSgjdZtjgsgService {

	public static TableInfo tableInfo = null;

	static {
		tableInfo = TableInfoHelper.getTableInfo(XmSgjdZtjgsgDO.class);
	}
	
	@Autowired
	private FileService fileService;

	@Autowired
	private XmZpmsService xmZpmsService;
	
	@Autowired
	private XmGqjdbjService xmGqjdbjService;
	
	@Autowired
	private XmSgjdZtjgsgKzService xmSgjdZtjgsgKzService;
	
	
	
	
	@Override
	public XmSgjdZtjgsgDO getById(Serializable id) {
		XmSgjdZtjgsgDO xmSgjdZtjgsgDO=super.getById(id);
		if(xmSgjdZtjgsgDO!=null) {
			Long intsgwzd = xmSgjdZtjgsgDO.getIntsgwzd();
			if(intsgwzd!=null) {
				XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intsgwzd);
					if(xmGqjdbjDO!=null) {
						xmSgjdZtjgsgDO.setChrShowAddress(xmGqjdbjDO.getChrjdmc());
					}
			}
			
			Long intsgwzc = xmSgjdZtjgsgDO.getIntsgwzc();
			if(intsgwzc!=null) {
				XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intsgwzc);
					if(xmGqjdbjDO!=null) {
						xmSgjdZtjgsgDO.setChrsgwzc(xmGqjdbjDO.getChrjdmc());
					}
			}
			
		}
		return xmSgjdZtjgsgDO;
	}
	
	@Override
	public boolean removeByParmMap(Map<String, Object> parmMap) {
		parmMap = parmToColumnMap(tableInfo, parmMap);
		Assert.notEmpty(parmMap, "过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
		return super.removeByMap(parmMap);
	}

	@Override
	public Collection<XmSgjdZtjgsgDO> listByParmMap(Map<String, Object> parmMap) {
		parmMap = parmToColumnMap(tableInfo, parmMap);
		return listByMap(parmMap);
	}

	/**
	 * 保存主体施工信息
	 * 
	 * @param xmSgjdZtjgsgDO
	 * @param fileIds
	 *            图片IDS
	 * @param xmZpmsJson
	 *            图片描述对象json串
	 */
	@Override
	public void saveXmSgjdZtjgsgXx(XmSgjdZtjgsgDO xmSgjdZtjgsgDO, String fileIds, String xmZpmsJson) {
		Long xmid = xmSgjdZtjgsgDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdZtjgsgDO.getId();
		if (id == null) {
			xmSgjdZtjgsgDO.setFcbz(1);
			xmSgjdZtjgsgDO.setGxsj(new Date());
			save(xmSgjdZtjgsgDO);
		} else {
			xmSgjdZtjgsgDO.setGxsj(new Date());
			updateById(xmSgjdZtjgsgDO);
		}

		id = xmSgjdZtjgsgDO.getId();

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

		// 保存照片描述信息
		if (StringUtils.isNotEmpty(xmZpmsJson) && !"[]".equals(xmZpmsJson)) {
			Gson gson = new Gson();
			List<XmZpmsDO> list = gson.fromJson(xmZpmsJson, new TypeToken<List<XmZpmsDO>>() {
			}.getType());
			if (CollectionUtils.isNotEmpty(list)) {
				list.forEach(xmZpmsDO -> {
					Long xmZpmsId = xmZpmsDO.getId();
					if (xmZpmsId == null) {
						xmZpmsDO.setFcbz(1);
						xmZpmsDO.setGxsj(new Date());
						xmZpmsDO.setIntxmid(xmid);
						xmZpmsDO.setIntzpssid(xmSgjdZtjgsgDO.getId());
						xmZpmsService.save(xmZpmsDO);
					} else {
						xmZpmsDO.setGxsj(new Date());
						xmZpmsService.updateById(xmZpmsDO);
					}
				});
			}
		}
	}
	
	
	
	 /**
     * 保存新的主体施工信息
     * @param xmSgjdZtjgsgDO
     * @fileIds 文件ids
     * @param ztjdsJson  主体节点json
     */
    @Override
    public void saveNewXmSgjdZtsgXx(XmSgjdZtjgsgDO xmSgjdZtjgsgDO,String fileIds,String ztjdsJson) {
    	Long id = xmSgjdZtjgsgDO.getId();
    	Long intxmid = xmSgjdZtjgsgDO.getIntxmid();
    	if(intxmid==null) {
    		throw new MyApiException("xmid不能为空");
    	}
    	if(id==null) {
    		xmSgjdZtjgsgDO.setGxsj(new Date());
    		xmSgjdZtjgsgDO.setFcbz(1);
    		save(xmSgjdZtjgsgDO);
    	}else {
    		xmSgjdZtjgsgDO.setGxsj(new Date());
    		updateById(xmSgjdZtjgsgDO);
    	}
    	
    	Long newId=xmSgjdZtjgsgDO.getId();
    	
    	
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
    	//新增主体节点
    	if(StringUtils.isNotBlank(ztjdsJson)&&!"[]".equals(ztjdsJson)) {
    		List<XmSgjdZtjgsgKzDO> list = gson.fromJson(ztjdsJson, new TypeToken<List<XmSgjdZtjgsgKzDO>>() {
    				}.getType());
    		if(CollectionUtils.isNotEmpty(list)) {
    			list.forEach(one->{
    				Long tzId=one.getId();
    				if(tzId==null) {
    					one.setGxsj(new Date());
    					one.setFcbz(1);
    					one.setIntxmid(intxmid);
    					one.setIntztsgid(newId);
    					xmSgjdZtjgsgKzService.save(one);
    					}else {
    						one.setGxsj(new Date());
    						xmSgjdZtjgsgKzService.updateById(one);
    					}
    			});
    		}
    	}
    	
    }
    
    /**
     * 根据项目id和施工位置获取主体结构施工信息
     * @param xmid 项目id
     * @param sgwzd 施工位置
     * @param fwlx 访问类型 xz---新增 查询-cx
     * @param Long id 主键id
     * @return XmSgjdJcsgnewDO
     */
    @Override 
    public  XmSgjdZtjgsgDO getXmSgjdZtsgByXmidAndSgwzid(Long xmid,Long sgwzd,String fwlx,Long id) {
    	if(xmid==null) {
    		return null;
    	}
    	
    	if(sgwzd==null) {
    		return null;
    	}
    	
    	UserDO appUserDO = ShiroUtils.getAppUserDO();
    	List<XmSgjdZtjgsgDO> list=Lists.newArrayList();
    	XmSgjdZtjgsgDO xmSgjdZtjgsgDO=new XmSgjdZtjgsgDO();
    	if(id!=null) {
    		list.add(getById(id));
    	}else {
        	xmSgjdZtjgsgDO.setIntxmid(xmid);
        	xmSgjdZtjgsgDO.setIntsgwzd(sgwzd);
        	xmSgjdZtjgsgDO.setFcbz(1);
        	QueryWrapper<XmSgjdZtjgsgDO> queryWrapper=new QueryWrapper<XmSgjdZtjgsgDO>(xmSgjdZtjgsgDO).orderByDesc("id");
        	list=list(queryWrapper);
    	}
    	
    	if(CollectionUtils.isEmpty(list)) {
    		if(StringUtils.isNotEmpty(fwlx)) {
    			if("xz".equals(fwlx)) {
    				xmSgjdZtjgsgDO.setDtmbgrq(new Date());
            		xmSgjdZtjgsgDO.setChrbgrmc(appUserDO.getName());
            		xmSgjdZtjgsgDO.setIntbgrid(appUserDO.getId());
        		}else if("cx".equals(fwlx)) {
        			
        		}
        		//施工位置
        		if(sgwzd!=null) {
        			if(sgwzd.equals(-1L)) {
        				xmSgjdZtjgsgDO.setChrShowAddress("其他");
        			}else {
        				XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(sgwzd);
            			if(xmGqjdbjDO!=null) {
            				xmSgjdZtjgsgDO.setChrShowAddress(xmGqjdbjDO.getChrjdmc());
            			}
        			}
        		}
        		xmSgjdZtjgsgDO.setIntxh(0);
        		List<XmSgjdZtjgsgKzDO> ztjgKzList =null;
        		if(sgwzd.equals(-1L)) {
            		ztjgKzList = Lists.newArrayList();
        		}else {
        			//查询节点
            		Map<String,Object> map=Maps.newHashMap();
            		map.put("intxmid", xmid);
            		map.put("intztsgid", xmSgjdZtjgsgDO.getId());
            		ztjgKzList =xmSgjdZtjgsgKzService.getXmSgjdZtKzListByXmidAndZtsgid(map);
        		}
        		
        		if(CollectionUtils.isNotEmpty(ztjgKzList)) {
        			ztjgKzList.forEach(one->{
        				one.setId(null);
        			});
        			xmSgjdZtjgsgDO.setZtjgKzList(ztjgKzList);
        		}else {
        			xmSgjdZtjgsgDO.setZtjgKzList(Lists.newArrayList());
        		}
    		}
    		return xmSgjdZtjgsgDO;
    	}else {
    		XmSgjdZtjgsgDO newObj=list.get(0);
    		
    		//施工位置
    		Long intsgwzid = newObj.getIntsgwzd();
    		if(intsgwzid!=null) {
    			if(intsgwzid.equals(-1L)) {
    				newObj.setChrShowAddress("其他");
    			}else {
    				XmGqjdbjDO xmGqjdbjDO=xmGqjdbjService.getById(intsgwzid);
    				if(xmGqjdbjDO!=null) {
    					newObj.setChrShowAddress(xmGqjdbjDO.getChrjdmc());
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
    		
    		List<XmSgjdZtjgsgKzDO> ztjgKzList =null;
    		if(sgwzd.equals(-1L)) {
    			XmSgjdZtjgsgKzDO ztjgKzObj=new XmSgjdZtjgsgKzDO();
        		ztjgKzObj.setIntztsgid(newObj.getId());
        		ztjgKzObj.setFcbz(1);
        		QueryWrapper<XmSgjdZtjgsgKzDO> ztjgKzWrapper=new QueryWrapper<XmSgjdZtjgsgKzDO>(ztjgKzObj).orderByAsc("id");
        		ztjgKzList = xmSgjdZtjgsgKzService.list(ztjgKzWrapper);
    		}else {
    			//查询节点
        		Map<String,Object> map=Maps.newHashMap();
        		map.put("intxmid", xmid);
        		map.put("intztsgid", newObj.getId());
        		ztjgKzList =xmSgjdZtjgsgKzService.getXmSgjdZtKzListByXmidAndZtsgid(map);
    		}
    		
    		if(CollectionUtils.isNotEmpty(ztjgKzList)) {
    			ztjgKzList.forEach(one->{
    				if(!sgwzd.equals(-1L)) {
    					one.setId(null);
    				}
    			});
    			newObj.setZtjgKzList(ztjgKzList);
    		}else {
    			newObj.setZtjgKzList(Lists.newArrayList());
    		}
    		return newObj;
    	}
    	
      }
           
           
}
