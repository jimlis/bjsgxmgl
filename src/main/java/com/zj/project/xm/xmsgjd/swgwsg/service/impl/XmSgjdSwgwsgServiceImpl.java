package com.zj.project.xm.xmsgjd.swgwsg.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmsgjd.swgwsg.dao.XmSgjdSwgwsgDao;
import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwlxDO;
import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgDO;
import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgJdDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwlxService;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwsgJdService;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwsgService;

/**
 * 
 * <pre>
 * 施工进度-室外管网施工
 * </pre>
 * <small> 2018-10-14 10:20:56 | lijun</small>
 */
@Service
public class XmSgjdSwgwsgServiceImpl extends BaseServiceImpl<XmSgjdSwgwsgDao, XmSgjdSwgwsgDO> implements XmSgjdSwgwsgService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdSwgwsgDO.class);
    }
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private XmSgjdSwgwsgJdService xmSgjdSwgwsgJdService;
    
    @Autowired
    private XmSgjdSwgwlxService xmSgjdSwgwlxService;
    
    @Override
    public XmSgjdSwgwsgDO getById(Serializable id){
    	XmSgjdSwgwsgDO xmSgjdSwgwsgDO=super.getById(id);
    	if(xmSgjdSwgwsgDO!=null) {
    		XmSgjdSwgwlxDO xmSgjdSwgwlxDO=new XmSgjdSwgwlxDO();
    		xmSgjdSwgwlxDO.setFcbz(1);
    		xmSgjdSwgwlxDO.setIntxmid(xmSgjdSwgwsgDO.getIntxmid());
    		QueryWrapper<XmSgjdSwgwlxDO> queryWrapper1=new QueryWrapper<XmSgjdSwgwlxDO>(xmSgjdSwgwlxDO).orderByAsc("id");
    		List<XmSgjdSwgwlxDO> list = xmSgjdSwgwlxService.list(queryWrapper1);
    		Map<Long,XmSgjdSwgwlxDO> lxMaps=Maps.newHashMap();
    		if(CollectionUtils.isNotEmpty(list)) {
    			lxMaps.putAll(list.stream().collect(Collectors.toMap(xmSgjdSwgwlx->xmSgjdSwgwlx.getId() ,xmSgjdSwgwlx->xmSgjdSwgwlx) ));
    		}
    		xmSgjdSwgwsgDO.setXmSgjdSwgwsgLxMap(lxMaps);
    		
    		//设置 施工进度
    		XmSgjdSwgwsgJdDO xmSgjdSwgwsgJdDO=new XmSgjdSwgwsgJdDO();
    		xmSgjdSwgwsgJdDO.setFcbz(1);
    		xmSgjdSwgwsgJdDO.setIntswgwsgid(xmSgjdSwgwsgDO.getId());
    		QueryWrapper<XmSgjdSwgwsgJdDO> queryWrapper=new QueryWrapper<XmSgjdSwgwsgJdDO>(xmSgjdSwgwsgJdDO).orderByAsc("id");
    		List<XmSgjdSwgwsgJdDO> xmSgjdSwgwsgJdList = xmSgjdSwgwsgJdService.list(queryWrapper);
    		if(CollectionUtils.isNotEmpty(xmSgjdSwgwsgJdList)) {
    			Map<Long,List<XmSgjdSwgwsgJdDO>> maps=Maps.newLinkedHashMap();
    			xmSgjdSwgwsgJdList.forEach(xmSgjdSwgwsgJd->{
    				Long intswgwlxid = xmSgjdSwgwsgJd.getIntswgwlxid();
    				if(intswgwlxid!=null) {
    					xmSgjdSwgwsgJd.setChrswgwlxid(lxMaps.get(intswgwlxid).getChrswgwlx());
        				if(maps.containsKey(intswgwlxid)) {
        					maps.get(intswgwlxid).add(xmSgjdSwgwsgJd);
        				}else {
        					List<XmSgjdSwgwsgJdDO> newList=Lists.newArrayList();
        					newList.add(xmSgjdSwgwsgJd);
        					maps.put(intswgwlxid, newList);
        				}
    				}
    			});
    			
    			if(!maps.isEmpty()) {
    				Map<String,List<XmSgjdSwgwsgJdDO>> newMaps=Maps.newLinkedHashMap();
    				Set<Long> keySet = maps.keySet();
    				for(Long key : keySet) {
    					newMaps.put(lxMaps.get(key).getChrswgwlx(), maps.get(key));
    				}
    				xmSgjdSwgwsgDO.setXmSgjdSwgwsgJdListMap(maps);
    			}
    		}
    	}
    	return xmSgjdSwgwsgDO;
    }
    
    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdSwgwsgDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }

	@Override
	public List<XmSgjdSwgwsgDO> getXmSgjdYlsgListByXmidAndGxrq(Long xmid, Date gxrq) {
		if(xmid==null) {
    		throw new CommonException("项目id不能为空");
    	}
    	
    	if(gxrq==null) {
    		throw new CommonException("更新日期不能为空");
    	}
    	
    	XmSgjdSwgwsgDO xmSgjdSwgwsgDO=new XmSgjdSwgwsgDO();
    	xmSgjdSwgwsgDO.setFcbz(1);
    	xmSgjdSwgwsgDO.setIntxmid(xmid);
    	xmSgjdSwgwsgDO.setDtmgxrq(gxrq);
    	QueryWrapper<XmSgjdSwgwsgDO> queryWrapper=new QueryWrapper<XmSgjdSwgwsgDO>(xmSgjdSwgwsgDO).orderByDesc("gxsj");
    	List<XmSgjdSwgwsgDO> list=list(queryWrapper);
    	
		return list;
	}
	
	/**
	 * 保存施工进度-室外管网施工信息
	 * @param xmSgjdSwgwsgDO
	 * @param fileIds 附件ids
	 */
	@Override
	public void saveXmSgjdSwgwsgXx(XmSgjdSwgwsgDO xmSgjdSwgwsgDO, String fileIds,String sglxAndJdJson,String deleteSwgwlxIds,String deleteSwgwjdIds) {
		Long xmid = xmSgjdSwgwsgDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdSwgwsgDO.getId();
		if (id == null) {
			xmSgjdSwgwsgDO.setFcbz(1);
			xmSgjdSwgwsgDO.setGxsj(new Date());
			save(xmSgjdSwgwsgDO);
		} else {
			xmSgjdSwgwsgDO.setGxsj(new Date());
			updateById(xmSgjdSwgwsgDO);
		}

		id = xmSgjdSwgwsgDO.getId();

		// 更新文件信息
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
		
		//新增项目施工管网类型和施工进度
		if(StringUtils.isNotEmpty(sglxAndJdJson)&&!"[]".equals(sglxAndJdJson)) {
			Gson gson=new Gson();
			List<XmSgjdSwgwlxDO> xmSgjdSwgwlxList=gson.fromJson(sglxAndJdJson, new TypeToken<List<XmSgjdSwgwlxDO>>() {
			}.getType());
			if(CollectionUtils.isNotEmpty(xmSgjdSwgwlxList)) {
				xmSgjdSwgwlxList.forEach(xmSgjdSwgwlxDO->{
					//保存类型
					String chrswgwlx = xmSgjdSwgwlxDO.getChrswgwlx();
					if(StringUtils.isNotEmpty(chrswgwlx)) {
						Long xmSgjdSwgwlxId=xmSgjdSwgwlxDO.getId();
						if(xmSgjdSwgwlxId==null) {
							xmSgjdSwgwlxDO.setFcbz(1);
							xmSgjdSwgwlxDO.setGxsj(new Date());
							xmSgjdSwgwlxDO.setIntxmid(xmid);
							xmSgjdSwgwlxService.save(xmSgjdSwgwlxDO);
						}else {
							xmSgjdSwgwlxDO.setGxsj(new Date());
							xmSgjdSwgwlxService.updateById(xmSgjdSwgwlxDO);
						}
						
						//保存施工进度
						Long newXmSgjdSwgwlxId=xmSgjdSwgwlxDO.getId();
						List<XmSgjdSwgwsgJdDO> xmSgjdSwgwsgJdList=xmSgjdSwgwlxDO.getXmSgjdSwgwsgJdList();
						if(CollectionUtils.isNotEmpty(xmSgjdSwgwsgJdList)) {
							xmSgjdSwgwsgJdList.forEach(xmSgjdSwgwsgJdDO->{
								String chrsgqy = xmSgjdSwgwsgJdDO.getChrsgqy();
								if(StringUtils.isNotEmpty(chrsgqy)) {
									Long xmSgjdSwgwsgJdId=xmSgjdSwgwsgJdDO.getId();
									String wcqkFileIds=xmSgjdSwgwsgJdDO.getFileIds();
									if(xmSgjdSwgwsgJdId==null) {
										xmSgjdSwgwsgJdDO.setFcbz(1);
										xmSgjdSwgwsgJdDO.setGxsj(new Date());
										xmSgjdSwgwsgJdDO.setIntswgwlxid(newXmSgjdSwgwlxId);
										xmSgjdSwgwsgJdDO.setIntswgwsgid(xmSgjdSwgwsgDO.getId());
										xmSgjdSwgwsgJdService.save(xmSgjdSwgwsgJdDO);
									}else {
										xmSgjdSwgwsgJdDO.setGxsj(new Date());
										xmSgjdSwgwsgJdService.updateById(xmSgjdSwgwsgJdDO);
									}
									
									//更新附件信息
									// 更新文件信息
									if (StringUtils.isNotEmpty(wcqkFileIds)) {
										String[] fileArr = wcqkFileIds.trim().split(",");
										for (String fileid : fileArr) {
											if (StringUtils.isNotEmpty(fileid.trim())) {
												FileDO fileDO = new FileDO();
												fileDO.setId(Long.parseLong(fileid.trim()));
												fileDO.setBusType(XmSgjdSwgwsgJdServiceImpl.tableInfo.getTableName());
												fileDO.setBusId(xmSgjdSwgwsgJdDO.getId());
												fileService.updateById(fileDO);
											}
										}
									}
								}
							});
						}
						
						
					}
				});
			}
		}
		
		//删除项目施工管网类型
		if(StringUtils.isNotEmpty(deleteSwgwlxIds)) {
			Arrays.stream(deleteSwgwlxIds.trim().split(",")).forEach(sgLxId->{
				XmSgjdSwgwlxDO xmSgjdSwgwlxDO=new XmSgjdSwgwlxDO();
				xmSgjdSwgwlxDO.setId(Long.parseLong(sgLxId));
				xmSgjdSwgwlxDO.setFcbz(0);
				xmSgjdSwgwlxDO.setGxsj(new Date());
				xmSgjdSwgwlxService.updateById(xmSgjdSwgwlxDO);
				
				//废除施工进度
				XmSgjdSwgwsgJdDO xmSgjdSwgwsgJdDO=new XmSgjdSwgwsgJdDO();
				xmSgjdSwgwsgJdDO.setFcbz(0);
				xmSgjdSwgwsgJdDO.setGxsj(new Date());
				
				XmSgjdSwgwsgJdDO where=new XmSgjdSwgwsgJdDO();
				where.setFcbz(1);
				where.setIntswgwlxid(Long.parseLong(sgLxId));
				where.setIntswgwsgid(xmSgjdSwgwsgDO.getId());
				UpdateWrapper<XmSgjdSwgwsgJdDO> updateWrapper=new UpdateWrapper<XmSgjdSwgwsgJdDO>(where);
				xmSgjdSwgwsgJdService.update(xmSgjdSwgwsgJdDO, updateWrapper);
			});
		}
		
		//删除施工进度
		if(StringUtils.isNotEmpty(deleteSwgwjdIds)) {
			Arrays.stream(deleteSwgwjdIds.trim().split(",")).forEach(sgjdId->{
				XmSgjdSwgwsgJdDO xmSgjdSwgwsgJdDO=new XmSgjdSwgwsgJdDO();
				xmSgjdSwgwsgJdDO.setId(Long.parseLong(sgjdId));
				xmSgjdSwgwsgJdDO.setFcbz(0);
				xmSgjdSwgwsgJdDO.setGxsj(new Date());
				xmSgjdSwgwsgJdService.updateById(xmSgjdSwgwsgJdDO);
			});
		}
	}


}
