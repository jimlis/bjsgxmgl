package com.zj.project.xm.xmsgjd.ylsg.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.platform.shiro.util.ShiroUtils;
import com.zj.project.xm.xmsgjd.ylsg.dao.XmSgjdYlsgDao;
import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgDO;
import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgJdDO;
import com.zj.project.xm.xmsgjd.ylsg.service.XmSgjdYlsgJdService;
import com.zj.project.xm.xmsgjd.ylsg.service.XmSgjdYlsgService;

/**
 * 
 * <pre>
 * 施工进度-园林施工
 * </pre>
 * <small> 2018-10-13 22:22:17 | lijun</small>
 */
@Service
public class XmSgjdYlsgServiceImpl extends BaseServiceImpl<XmSgjdYlsgDao, XmSgjdYlsgDO> implements XmSgjdYlsgService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdYlsgDO.class);
    }
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private XmSgjdYlsgJdService xmSgjdYlsgJdService;
    
    @Override
    public boolean updateById(XmSgjdYlsgDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmSgjdYlsgDO> updateWrapper=new UpdateWrapper<XmSgjdYlsgDO>().eq("id", entity.getId());
    	fieldList.forEach(filed->{
    		if(filed.isCharSequence()&&FieldStrategy.NOT_EMPTY==filed.getFieldStrategy()) {
    			String value = (String)ReflectionKit.getMethodValue(entity, filed.getProperty());
        		updateWrapper.set(MyStringUtils.isEmptyString(value),filed.getColumn(),value);
    		}
    	});
    	return update(entity, updateWrapper);
    }
    
    @Override
    public XmSgjdYlsgDO getById(Serializable id){
    	XmSgjdYlsgDO xmSgjdYlsgDO=super.getById(id);
    	if(xmSgjdYlsgDO!=null) {
    		
    		//查询附件
        	FileDO fileDO=new FileDO();
        	fileDO.setBusId(xmSgjdYlsgDO.getId());
        	fileDO.setBusType(tableInfo.getTableName());
        	QueryWrapper<FileDO> fileQueryWrapper=new QueryWrapper<FileDO>(fileDO).orderByAsc("id");
        	List<FileDO> fileList=fileService.list(fileQueryWrapper);
        	if(CollectionUtils.isNotEmpty(fileList)) {
        		xmSgjdYlsgDO.setFileIds(fileList.stream().map(one->Objects.toString(one.getId(), "")).collect(Collectors.joining(",")));
        	}
    		
    		XmSgjdYlsgJdDO xmSgjdYlsgJdDO=new XmSgjdYlsgJdDO();
    		xmSgjdYlsgJdDO.setFcbz(1);
    		xmSgjdYlsgJdDO.setIntylsgid(xmSgjdYlsgDO.getId());
    		QueryWrapper<XmSgjdYlsgJdDO> queryWrapper=new QueryWrapper<XmSgjdYlsgJdDO>(xmSgjdYlsgJdDO).orderByAsc("id");
    		List<XmSgjdYlsgJdDO> list = xmSgjdYlsgJdService.list(queryWrapper);
    		list.forEach(one->{
    			//查询附件
	        	FileDO oneFileDO=new FileDO();
	        	oneFileDO.setBusId(one.getId());
	        	oneFileDO.setBusType(XmSgjdYlsgJdServiceImpl.tableInfo.getTableName());
	        	QueryWrapper<FileDO> oneFileQueryWrapper=new QueryWrapper<FileDO>(oneFileDO).orderByAsc("id");
	        	List<FileDO> oneFileList=fileService.list(oneFileQueryWrapper);
	        	if(CollectionUtils.isNotEmpty(oneFileList)) {
	        		one.setFileIds(oneFileList.stream().map(o->Objects.toString(o.getId(), "")).collect(Collectors.joining(",")));
	        	}
    		});
    		xmSgjdYlsgDO.setXmSgjdYlsgJdList(list);
    	}
    	return xmSgjdYlsgDO;
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdYlsgDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 获取施工进度-园林施工信息
     * @param xmid 项目id
     * @param gxrq 更新日期
     */
    @Override
    public List<XmSgjdYlsgDO> getXmSgjdYlsgListByXmidAndGxrq(Long xmid,Date gxrq){
    	if(xmid==null) {
    		throw new CommonException("项目id不能为空");
    	}
    	
    	if(gxrq==null) {
    		throw new CommonException("更新日期不能为空");
    	}
    	
    	XmSgjdYlsgDO xmSgjdYlsgDO=new XmSgjdYlsgDO();
    	xmSgjdYlsgDO.setFcbz(1);
    	xmSgjdYlsgDO.setIntxmid(xmid);
    	xmSgjdYlsgDO.setDtmgxrq(gxrq);
    	QueryWrapper<XmSgjdYlsgDO> queryWrapper=new QueryWrapper<XmSgjdYlsgDO>(xmSgjdYlsgDO).orderByDesc("gxsj");
    	List<XmSgjdYlsgDO> list=list(queryWrapper);
    	
		return list;
    }
    
    /**
	 * 保存施工进度-园林施工信息
	 * @param xmSgjdYlsgDO
	 * @param fileIds 文件ids
	 * @param ylsgJdJson 施工进度json 
	 * @param deleteYlsgjdIds 删除施工进度ids
	 */
	@Override
	public void saveXmSgjdYlsgXx(XmSgjdYlsgDO xmSgjdYlsgDO, String fileIds,String ylsgJdJson,String deleteYlsgjdIds) {
		Long xmid = xmSgjdYlsgDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdYlsgDO.getId();
		if (id == null) {
			xmSgjdYlsgDO.setFcbz(1);
			xmSgjdYlsgDO.setGxsj(new Date());
			save(xmSgjdYlsgDO);
		} else {
			xmSgjdYlsgDO.setGxsj(new Date());
			updateById(xmSgjdYlsgDO);
		}

		id = xmSgjdYlsgDO.getId();
		
		UserDO appUserDO = ShiroUtils.getAppUserDO();

		// 更新文件信息
		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fileArr = fileIds.trim().split(",");
			for (String fileid : fileArr) {
				if (StringUtils.isNotEmpty(fileid.trim())) {
					//查询附件是否已经关联业务数据 如果关联复制附件信息指向同一个文件
					FileDO fileDO = fileService.getById(Long.parseLong(fileid.trim()));
					if(fileDO!=null&&StringUtils.isNotEmpty(fileDO.getBusType())&&fileDO.getBusId()!=null) {
						fileDO.setId(null);
						fileDO.setBusType(tableInfo.getTableName());
						fileDO.setBusId(id);
					    fileDO.setCreateUserId(appUserDO.getId());
				        fileDO.setCreateUserName(appUserDO.getName());
				        fileDO.setCreateDeptId(appUserDO.getDeptId());
				        fileDO.setCreateDeptName(appUserDO.getDeptName());
				        fileDO.setCreateDate(new Date());
				        fileService.save(fileDO);
					}else {
						FileDO newFileDO =	new FileDO();
						newFileDO.setId(Long.parseLong(fileid.trim()));
						newFileDO.setBusType(tableInfo.getTableName());
						newFileDO.setBusId(id);
						fileService.updateById(newFileDO);
					}
					
				}
			}
		}
		
		//新增施工进度
		if(StringUtils.isNotEmpty(ylsgJdJson)&&!"[]".equals(ylsgJdJson)) {
			Gson gson=new Gson();
			List<XmSgjdYlsgJdDO> xmSgjdYlsgJdList=gson.fromJson(ylsgJdJson, new TypeToken<List<XmSgjdYlsgJdDO>>() {
			}.getType());
			if(CollectionUtils.isNotEmpty(xmSgjdYlsgJdList)) {
				xmSgjdYlsgJdList.forEach(xmSgjdYlsgJdDO->{
					//保存类型
					String chrlxmc = xmSgjdYlsgJdDO.getChrlxmc();
					if(StringUtils.isNotEmpty(chrlxmc)) {
						Long xmYlsgId=xmSgjdYlsgJdDO.getId();
						String wcqkFileIds=xmSgjdYlsgJdDO.getFileIds();
						if(xmYlsgId==null) {
							xmSgjdYlsgJdDO.setFcbz(1);
							xmSgjdYlsgJdDO.setGxsj(new Date());
							xmSgjdYlsgJdDO.setIntylsgid(xmSgjdYlsgDO.getId());
							xmSgjdYlsgJdService.save(xmSgjdYlsgJdDO);
						}else {
							xmSgjdYlsgJdDO.setGxsj(new Date());
							xmSgjdYlsgJdService.updateById(xmSgjdYlsgJdDO);
						}
						
						//更新附件信息
						if (StringUtils.isNotEmpty(wcqkFileIds)) {
							String[] fileArr = wcqkFileIds.trim().split(",");
							for (String fileid : fileArr) {
								if (StringUtils.isNotEmpty(fileid.trim())) {
									//查询附件是否已经关联业务数据 如果关联复制附件信息指向同一个文件
									FileDO fileDO = fileService.getById(Long.parseLong(fileid.trim()));
									if(fileDO!=null&&StringUtils.isNotEmpty(fileDO.getBusType())&&fileDO.getBusId()!=null) {
										fileDO.setId(null);
										fileDO.setBusType(XmSgjdYlsgJdServiceImpl.tableInfo.getTableName());
										fileDO.setBusId(xmSgjdYlsgJdDO.getId());
									    fileDO.setCreateUserId(appUserDO.getId());
								        fileDO.setCreateUserName(appUserDO.getName());
								        fileDO.setCreateDeptId(appUserDO.getDeptId());
								        fileDO.setCreateDeptName(appUserDO.getDeptName());
								        fileDO.setCreateDate(new Date());
								        fileService.save(fileDO);
									}else {
										FileDO newFileDO =	new FileDO();
										newFileDO.setId(Long.parseLong(fileid.trim()));
										newFileDO.setBusType(XmSgjdYlsgJdServiceImpl.tableInfo.getTableName());
										newFileDO.setBusId(xmSgjdYlsgJdDO.getId());
										fileService.updateById(newFileDO);
									}
									
								}
							}
						}
					}
				});
			}
		}
		
		//删除施工进度
		if(StringUtils.isNotEmpty(deleteYlsgjdIds)) {
			Arrays.stream(deleteYlsgjdIds.trim().split(",")).forEach(sgjdId->{
				XmSgjdYlsgJdDO xmSgjdYlsgJdDO=new XmSgjdYlsgJdDO();
				xmSgjdYlsgJdDO.setId(Long.parseLong(sgjdId));
				xmSgjdYlsgJdDO.setFcbz(0);
				xmSgjdYlsgJdDO.setGxsj(new Date());
				xmSgjdYlsgJdService.updateById(xmSgjdYlsgJdDO);
			});
		}
	}
	
	 /**
     * 根据项目id获取园林施工信息
     * @param xmid 项目id
     * @param fwlx 访问类型 xz---新增 查询-cx
     * @param Long id 主键id
     * @return XmSgjdSwgwsgDO
     */
    @Override 
    public  XmSgjdYlsgDO getXmSgjdYlsgByParam(Long xmid,String fwlx,Long id) {
    	if(xmid==null) {
    		return null;
    	}
    	
    	UserDO appUserDO = ShiroUtils.getAppUserDO();
    	
    	List<XmSgjdYlsgDO> list=Lists.newArrayList();
    	XmSgjdYlsgDO xmSgjdYlsgDO=new XmSgjdYlsgDO();
    	if(id!=null) {
    		return getById(id);
    	}else {
    		xmSgjdYlsgDO.setIntxmid(xmid);
    		xmSgjdYlsgDO.setFcbz(1);
        	QueryWrapper<XmSgjdYlsgDO> queryWrapper=new QueryWrapper<XmSgjdYlsgDO>(xmSgjdYlsgDO).orderByDesc("id");
        	list=list(queryWrapper);
    	}
    	
    	if(CollectionUtils.isEmpty(list)) {
    		if(StringUtils.isNotEmpty(fwlx)) {
    			if("xz".equals(fwlx)) {
    				xmSgjdYlsgDO.setDtmgxrq(new Date());
    				xmSgjdYlsgDO.setChrbgrmc(appUserDO.getName());
    				xmSgjdYlsgDO.setIntbgrid(appUserDO.getId());
        		}else if("cx".equals(fwlx)) {
        			
        		}
    			
    			xmSgjdYlsgDO.setIntxh(0);
        		
    			xmSgjdYlsgDO.setXmSgjdYlsgJdList(Lists.newArrayList());
    		}
    		return xmSgjdYlsgDO;
    	}else {
    		XmSgjdYlsgDO newObj=list.get(0);
    		return getById(newObj.getId());
    	}
    	
      }
	
}
