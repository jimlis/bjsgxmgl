package com.zj.project.xm.xmzfxcyzxys.service.impl;

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
import com.zj.platform.business.dict.service.DictService;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.platform.shiro.util.ShiroUtils;
import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;
import com.zj.project.xm.xmzfxcyzxys.dao.XmZfxcyzxysDao;
import com.zj.project.xm.xmzfxcyzxys.domain.XmZfxcyzxysDO;
import com.zj.project.xm.xmzfxcyzxys.service.XmZfxcyzxysService;

/**
 * 
 * <pre>
 * 政府巡查与专项验收：政府部门巡查/专项验收记录
 * </pre>
 * <small> 2018-10-06 09:34:15 | lijun</small>
 */
@Service
public class XmZfxcyzxysServiceImpl extends BaseServiceImpl<XmZfxcyzxysDao, XmZfxcyzxysDO> implements XmZfxcyzxysService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmZfxcyzxysDO.class);
    }

    @Autowired
    private FileService fileService;
    
    @Autowired
    private DictService dictService;
    
    @Autowired
    private XmGqjdbjService xmGqjdbjService;
    
    @Override
    public XmZfxcyzxysDO getById(Serializable id) {
    	XmZfxcyzxysDO xmZfxcyzxysDO=super.getById(id);
    	if(xmZfxcyzxysDO!=null) {
    		String intxcbm = xmZfxcyzxysDO.getIntxcbm();
    		String intxclb = xmZfxcyzxysDO.getIntxclb();
    		
    		xmZfxcyzxysDO.setChrxcbm(dictService.getName("xcbm",intxcbm));
    		xmZfxcyzxysDO.setChrxclb(dictService.getName("xclb",intxclb));
    		
    		Long intgqjdid = xmZfxcyzxysDO.getIntgqjdid();
    		if(intgqjdid!=null) {
    			XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intgqjdid);
    			if(xmGqjdbjDO!=null) {
    				xmZfxcyzxysDO.setChrgqjdmc(xmGqjdbjDO.getChrjdmc());
    			}
    		}
    		
    		//查询附件
        	FileDO fileDO=new FileDO();
        	fileDO.setBusId(xmZfxcyzxysDO.getId());
        	fileDO.setBusType(tableInfo.getTableName());
        	QueryWrapper<FileDO> queryWrapper=new QueryWrapper<FileDO>(fileDO).orderByAsc("id");
        	List<FileDO> fileList=fileService.list(queryWrapper);
        	if(CollectionUtils.isNotEmpty(fileList)) {
        		xmZfxcyzxysDO.setFileIds(fileList.stream().map(one->Objects.toString(one.getId(), "")).collect(Collectors.joining(",")));
        	}
    		
    	}
    	return xmZfxcyzxysDO;
    }
    
    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmZfxcyzxysDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }


    /**
     * 保存信息
     * @param xmZfxcyzxysDO 政府验收和巡查记录信息
     * @param fileIds  图片ids
     */
    @Override
    public  void  saveXmZfxcyzxysxx(XmZfxcyzxysDO xmZfxcyzxysDO,String fileIds){

        //保存基本信息
        Long id = xmZfxcyzxysDO.getId();
        
    	UserDO appUserDO = ShiroUtils.getAppUserDO();
        
        if(id==null){
            xmZfxcyzxysDO.setFcbz(1);
            xmZfxcyzxysDO.setGxsj(new Date());
            save(xmZfxcyzxysDO);
        }else{
            xmZfxcyzxysDO.setGxsj(new Date());
            updateById(xmZfxcyzxysDO);
        }

        id=xmZfxcyzxysDO.getId();

        //保存图片
        if (StringUtils.isNotEmpty(fileIds)) {
			String[] fileArr = fileIds.trim().split(",");
			String intcxlb=xmZfxcyzxysDO.getIntxclb();
			for (String fileid : fileArr) {
				if (StringUtils.isNotEmpty(fileid.trim())) {
					
					if("zxys".equals(intcxlb)||"jgys".equals(intcxlb)) {
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
     * 获取专项竣工验收政府巡查记录
     * @param xmid 项目id
     * @param xclb 巡查类别
     * @param gqjdid 工期节点id
     * @param fwlx "xz"-新增  "cx"-查询
     * @return
     */
    @Override
    public XmZfxcyzxysDO getXmZxysZfxcyzxys(Long xmid,String xclb,Long gqjdid,String fwlx) {
    	if(xmid==null) {
    		throw new MyApiException("xmid不能为空");
    	}
    	
    	if(xclb==null) {
    		throw new MyApiException("cxlb不能为空");
    	}
    	
    	if(gqjdid==null) {
    		throw new MyApiException("gqjdid不能为空");
    	}
    	UserDO appUserDO = ShiroUtils.getAppUserDO();
    	XmZfxcyzxysDO xmZfxcyzxysDO=new XmZfxcyzxysDO();
    	xmZfxcyzxysDO.setIntxmid(xmid);
    	xmZfxcyzxysDO.setFcbz(1);
    	xmZfxcyzxysDO.setIntxclb(xclb);
    	xmZfxcyzxysDO.setIntgqjdid(gqjdid);
    	
    	QueryWrapper<XmZfxcyzxysDO> queryWrapper=new QueryWrapper<XmZfxcyzxysDO>(xmZfxcyzxysDO).orderByDesc("id");
    	List<XmZfxcyzxysDO> list = list(queryWrapper);
    	if(CollectionUtils.isNotEmpty(list)) {
    		XmZfxcyzxysDO zfxc = getById(list.get(0).getId());
    		if("xz".equals(fwlx)) {
    			xmZfxcyzxysDO.setDtmgxrq(new Date());
    		}
    		return zfxc;
    	}else {
        		String intxclb = xmZfxcyzxysDO.getIntxclb();
        		xmZfxcyzxysDO.setChrxclb(dictService.getName("xclb",intxclb));
        		
        		//
        		Long intgqjdid = xmZfxcyzxysDO.getIntgqjdid();
        		if(intgqjdid!=null) {
        			XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intgqjdid);
        			if(xmGqjdbjDO!=null) {
        				xmZfxcyzxysDO.setChrgqjdmc(xmGqjdbjDO.getChrjdmc());
        			}
        		}
        		
        		if("xz".equals(fwlx)) {
        			xmZfxcyzxysDO.setIntbgrid(appUserDO.getId());
        			xmZfxcyzxysDO.setChrbgrmc(appUserDO.getName());
        			xmZfxcyzxysDO.setDtmgxrq(new Date());
        		}
        		return xmZfxcyzxysDO;
    	}
    }


}
