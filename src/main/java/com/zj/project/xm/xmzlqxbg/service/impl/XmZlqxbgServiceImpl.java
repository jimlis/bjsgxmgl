package com.zj.project.xm.xmzlqxbg.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmdwmd.service.XmDwmdService;
import com.zj.project.xm.xmzlqxbg.dao.XmZlqxbgDao;
import com.zj.project.xm.xmzlqxbg.domain.XmZlqxbgDO;
import com.zj.project.xm.xmzlqxbg.service.XmZlqxbgService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;

import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 
 * <pre>
 * 质量缺陷报告
 * </pre>
 * <small> 2018-10-13 10:46:20 | lijun</small>
 */
@Service
public class XmZlqxbgServiceImpl extends BaseServiceImpl<XmZlqxbgDao, XmZlqxbgDO> implements XmZlqxbgService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmZlqxbgDO.class);
    }
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private XmDwmdService xmDwmdService;
    
    @Override
    public XmZlqxbgDO getById(Serializable id) {
    	XmZlqxbgDO xmZlqxbgDO=super.getById(id);
    	Long intsgdw = xmZlqxbgDO.getIntsgdw();
    	if(intsgdw!=null) {
    		XmDwmdDO xmDwmdDO = xmDwmdService.getById(intsgdw);
    		if(xmDwmdDO!=null) {
    			xmZlqxbgDO.setChrsgdw(xmDwmdDO.getChrdwmc());
    		}
    	}
    	return xmZlqxbgDO;
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmZlqxbgDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存项目质量缺陷报告信息
     * @param xmZlqxbgDO
     * @param fileIds 图片id，多个以逗号隔开
     */
	@Override
	public void saveXmZlqxbgXx(XmZlqxbgDO xmZlqxbgDO, String fileIds) {
		Long id = xmZlqxbgDO.getId();
        if(id==null){
        	xmZlqxbgDO.setGxsj(new Date());
        	xmZlqxbgDO.setFcbz(1);
            save(xmZlqxbgDO);
        }else{
        	xmZlqxbgDO.setGxsj(new Date());
            updateById(xmZlqxbgDO);
        }

        id=xmZlqxbgDO.getId();

        if(StringUtils.isNotEmpty(fileIds)){
            String[] fileArr=fileIds.trim().split(",");
            for (String fileid : fileArr){
                if(StringUtils.isNotEmpty(fileid.trim())){
                    FileDO fileDO=new FileDO();
                    fileDO.setId(Long.parseLong(fileid.trim()));
                    fileDO.setBusType(tableInfo.getTableName());
                    fileDO.setBusId(id);
                    fileService.updateById(fileDO);
                }
            }
        }
	}


}
