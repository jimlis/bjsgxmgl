package com.zj.project.xm.xmsgjd.qqbj.service.impl;

import java.io.Serializable;
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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.platform.shiro.util.ShiroUtils;
import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;
import com.zj.project.xm.xmsgjd.qqbj.dao.XmSgjdQqbjDao;
import com.zj.project.xm.xmsgjd.qqbj.domain.XmSgjdQqbjDO;
import com.zj.project.xm.xmsgjd.qqbj.service.XmSgjdQqbjService;

/**
 * 
 * <pre>
 * 项目基本信息-施工进度-前期报建
 * </pre>
 * <small> 2018-11-19 19:53:41 | lijun</small>
 */
@Service
public class XmSgjdQqbjServiceImpl extends BaseServiceImpl<XmSgjdQqbjDao, XmSgjdQqbjDO> implements XmSgjdQqbjService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdQqbjDO.class);
    }
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private XmGqjdbjService xmGqjdbjService;
    
    @Override
    public XmSgjdQqbjDO getById(Serializable id) {
    	XmSgjdQqbjDO xmSgjdQqbjDO=super.getById(id);
    	if(xmSgjdQqbjDO!=null) {
    		
    		Long intgqjdid = xmSgjdQqbjDO.getIntgqjdid();
    		if(intgqjdid!=null) {
    			XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intgqjdid);
    			if(xmGqjdbjDO!=null) {
    				xmSgjdQqbjDO.setChrgqjdmc(xmGqjdbjDO.getChrjdmc());
    			}
    		}
    		
    		//查询附件
        	FileDO fileDO=new FileDO();
        	fileDO.setBusId(xmSgjdQqbjDO.getId());
        	fileDO.setBusType(tableInfo.getTableName());
        	QueryWrapper<FileDO> queryWrapper=new QueryWrapper<FileDO>(fileDO).orderByAsc("id");
        	List<FileDO> fileList=fileService.list(queryWrapper);
        	if(CollectionUtils.isNotEmpty(fileList)) {
        		xmSgjdQqbjDO.setFileIds(fileList.stream().map(one->Objects.toString(one.getId(), "")).collect(Collectors.joining(",")));
        	}
    		
    	}
    	return xmSgjdQqbjDO;
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdQqbjDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存信息
     * @param xmSgjdQqbjDO 前期报建信息
     * @param fileIds  附件ids
     */
    @Override
    public  void  saveXmSgjdQqbjXx(XmSgjdQqbjDO xmSgjdQqbjDO,String fileIds){
    	
    	Long intxmid = xmSgjdQqbjDO.getIntxmid();
    	if(intxmid==null) {
    		throw new MyApiException("xmid不能为空");
    	}
    	
        //保存基本信息
        Long id = xmSgjdQqbjDO.getId();
        
    	UserDO appUserDO = ShiroUtils.getAppUserDO();
        
        if(id==null){
        	xmSgjdQqbjDO.setFcbz(1);
        	xmSgjdQqbjDO.setGxsj(new Date());
            save(xmSgjdQqbjDO);
        }else{
        	xmSgjdQqbjDO.setGxsj(new Date());
            updateById(xmSgjdQqbjDO);
        }

        id=xmSgjdQqbjDO.getId();

        //保存附件
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
        
    }
    
    /**
     * 获取前期报建记录
     * @param xmid 项目id
     * @param gqjdid 工期节点id
     * @param fwlx "xz"-新增  "cx"-查询
     * @return
     */
    @Override
    public XmSgjdQqbjDO getXmSgjdQqbj(Long xmid,Long gqjdid,String fwlx) {
    	if(xmid==null) {
    		throw new MyApiException("xmid不能为空");
    	}
    	
    	if(gqjdid==null) {
    		throw new MyApiException("gqjdid不能为空");
    	}
    	UserDO appUserDO = ShiroUtils.getAppUserDO();
    	XmSgjdQqbjDO xmSgjdQqbjDO=new XmSgjdQqbjDO();
    	xmSgjdQqbjDO.setIntxmid(xmid);
    	xmSgjdQqbjDO.setFcbz(1);
    	xmSgjdQqbjDO.setIntgqjdid(gqjdid);
    	
    	QueryWrapper<XmSgjdQqbjDO> queryWrapper=new QueryWrapper<XmSgjdQqbjDO>(xmSgjdQqbjDO).orderByDesc("id");
    	List<XmSgjdQqbjDO> list = list(queryWrapper);
    	if(CollectionUtils.isNotEmpty(list)) {
    		XmSgjdQqbjDO qqbj = getById(list.get(0).getId());
    		if("xz".equals(fwlx)) {
    			qqbj.setDtmbgrq(new Date());
    		}
    		return qqbj;
    	}else {
        		
        		//
        		Long intgqjdid = xmSgjdQqbjDO.getIntgqjdid();
        		if(intgqjdid!=null) {
        			XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intgqjdid);
        			if(xmGqjdbjDO!=null) {
        				xmSgjdQqbjDO.setChrgqjdmc(xmGqjdbjDO.getChrjdmc());
        			}
        		}
        		
        		if("xz".equals(fwlx)) {
        			xmSgjdQqbjDO.setIntbgrid(appUserDO.getId());
        			xmSgjdQqbjDO.setChrbgrmc(appUserDO.getName());
        			xmSgjdQqbjDO.setDtmbgrq(new Date());
        		}
        		return xmSgjdQqbjDO;
    	}
    }



}
