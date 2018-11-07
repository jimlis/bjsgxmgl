package com.zj.project.xm.xmsgjd.dtsbazsg.service.impl;

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
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.platform.shiro.util.ShiroUtils;
import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;
import com.zj.project.xm.xmsgjd.dtsbazsg.dao.XmSgjdDtsbazsgDao;
import com.zj.project.xm.xmsgjd.dtsbazsg.domain.XmSgjdDtsbazsgDO;
import com.zj.project.xm.xmsgjd.dtsbazsg.service.XmSgjdDtsbazsgService;

/**
 * 
 * <pre>
 * 施工进度-电梯设备安装施工
 * </pre>
 * <small> 2018-10-13 21:17:22 | lijun</small>
 */
@Service
public class XmSgjdDtsbazsgServiceImpl extends BaseServiceImpl<XmSgjdDtsbazsgDao, XmSgjdDtsbazsgDO> implements XmSgjdDtsbazsgService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdDtsbazsgDO.class);
    }
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private  XmGqjdbjService xmGqjdbjService;
    
    @Override
    public XmSgjdDtsbazsgDO getById(Serializable id) {
    	XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO=super.getById(id);
    	if(xmSgjdDtsbazsgDO!=null) {
    		Long intsgwz = xmSgjdDtsbazsgDO.getIntsgwz();
    		XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intsgwz);
    		if(xmGqjdbjDO!=null) {
    			xmSgjdDtsbazsgDO.setChrsgwz(xmGqjdbjDO.getChrjdmc());
    		}
    	}
    	
    	//查询附件
    	FileDO fileDO=new FileDO();
    	fileDO.setBusId(xmSgjdDtsbazsgDO.getId());
    	fileDO.setBusType(tableInfo.getTableName());
    	QueryWrapper<FileDO> queryWrapper=new QueryWrapper<FileDO>(fileDO).orderByAsc("id");
    	List<FileDO> fileList=fileService.list(queryWrapper);
    	if(CollectionUtils.isNotEmpty(fileList)) {
    		xmSgjdDtsbazsgDO.setFileIds(fileList.stream().map(one->Objects.toString(one.getId(), "")).collect(Collectors.joining(",")));
    	}
		return xmSgjdDtsbazsgDO;
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdDtsbazsgDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存项目施工记录电梯设备安装信息
     * @param xmSgjdDtsbazsgDO
     * @param fileIds
     */
	@Override
	public void saveXmSgjdDtsbazsgXx(XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO, String fileIds) {
		Long xmid = xmSgjdDtsbazsgDO.getIntxmid();
		
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		UserDO appUserDO = ShiroUtils.getAppUserDO();
		Long id = xmSgjdDtsbazsgDO.getId();
		if (id == null) {
			xmSgjdDtsbazsgDO.setFcbz(1);
			xmSgjdDtsbazsgDO.setGxsj(new Date());
			save(xmSgjdDtsbazsgDO);
		} else {
			xmSgjdDtsbazsgDO.setGxsj(new Date());
			updateById(xmSgjdDtsbazsgDO);
		}

		id = xmSgjdDtsbazsgDO.getId();

		// 更新附件信息
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
	 * 获取电梯设备安装信息
	 * @param xmSgjdDtsbazsgId 电梯安装记录
	 * @param xmid 项目id
	 * @param sgwz  施工位置
	 * @param fwlx "xz"-新增  "cx"-查询
	 * @return
	 */
	@Override
	public XmSgjdDtsbazsgDO getXmSgjdDtsbazsgByParam(Long xmSgjdDtsbazsgId, Long xmid, Long sgwz, String fwlx) {
		if(xmSgjdDtsbazsgId==null&&(xmid==null||sgwz==null)) {
			return null;
		}
	
		UserDO appUserDO = ShiroUtils.getAppUserDO();
		
		XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO=null;
		//主键存在 优先主键查询 否则查询最新记录
		if(xmSgjdDtsbazsgId!=null) {
			xmSgjdDtsbazsgDO=getById(xmSgjdDtsbazsgId);
		}else {
			xmSgjdDtsbazsgDO=new XmSgjdDtsbazsgDO();
			xmSgjdDtsbazsgDO.setFcbz(1);
			xmSgjdDtsbazsgDO.setIntxmid(xmid);
			xmSgjdDtsbazsgDO.setIntsgwz(sgwz);
			QueryWrapper<XmSgjdDtsbazsgDO> queryWrapper=new QueryWrapper<XmSgjdDtsbazsgDO>(xmSgjdDtsbazsgDO).orderByDesc("id");
			List<XmSgjdDtsbazsgDO> list=list(queryWrapper);
			if(CollectionUtils.isNotEmpty(list)) {
				xmSgjdDtsbazsgDO=list.get(0);
				
				//查询附件
		    	FileDO fileDO=new FileDO();
		    	fileDO.setBusId(xmSgjdDtsbazsgDO.getId());
		    	fileDO.setBusType(tableInfo.getTableName());
		    	QueryWrapper<FileDO> fileQueryWrapper=new QueryWrapper<FileDO>(fileDO).orderByAsc("id");
		    	List<FileDO> fileList=fileService.list(fileQueryWrapper);
		    	if(CollectionUtils.isNotEmpty(fileList)) {
		    		xmSgjdDtsbazsgDO.setFileIds(fileList.stream().map(one->Objects.toString(one.getId(), "")).collect(Collectors.joining(",")));
		    	}
				
			}else {//不存在 就构造一个新的
				
				if("xz".equals(fwlx)) {//新增
					xmSgjdDtsbazsgDO.setChrbgrmc(appUserDO.getName());
					xmSgjdDtsbazsgDO.setIntbgrid(appUserDO.getId());
					xmSgjdDtsbazsgDO.setDtmgxrq(new Date());
				}
				
				xmSgjdDtsbazsgDO.setIntxh(0);
				
			}
			
			//施工位置
			if(xmSgjdDtsbazsgDO!=null) {
	    		Long intsgwz = xmSgjdDtsbazsgDO.getIntsgwz();
	    		XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intsgwz);
	    		if(xmGqjdbjDO!=null) {
	    			xmSgjdDtsbazsgDO.setChrsgwz(xmGqjdbjDO.getChrjdmc());
	    		}
	    	}
		}
		return xmSgjdDtsbazsgDO;
	}


}
