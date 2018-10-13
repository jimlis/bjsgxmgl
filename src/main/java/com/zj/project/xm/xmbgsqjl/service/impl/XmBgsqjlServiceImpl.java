package com.zj.project.xm.xmbgsqjl.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.project.xm.xmbgsqjl.dao.XmBgsqjlDao;
import com.zj.project.xm.xmbgsqjl.domain.XmBgsqjlDO;
import com.zj.project.xm.xmbgsqjl.service.XmBgsqjlService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;

import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 
 * <pre>
 * 工程/顾问工作变更申请记录
 * </pre>
 * <small> 2018-10-13 16:22:31 | lijun</small>
 */
@Service
public class XmBgsqjlServiceImpl extends BaseServiceImpl<XmBgsqjlDao, XmBgsqjlDO> implements XmBgsqjlService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmBgsqjlDO.class);
    }
    
    @Autowired
    private FileService fileService;
    
    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmBgsqjlDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存工程/顾问变更记录
     * <p>Title: </p>  
     * <p>Description: </p> 
     * @param xmBgsqjlDO
     * @param fileIds
     */
	@Override
	public void saveXmBgsqjlXx(XmBgsqjlDO xmBgsqjlDO, String fileIds) {
		Long id = xmBgsqjlDO.getId();
        if(id==null){
        	xmBgsqjlDO.setGxsj(new Date());
        	xmBgsqjlDO.setFcbz(1);
            save(xmBgsqjlDO);
        }else{
        	xmBgsqjlDO.setGxsj(new Date());
            updateById(xmBgsqjlDO);
        }

        id=xmBgsqjlDO.getId();

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
