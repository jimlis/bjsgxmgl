package com.zj.project.xm.xmgckyzfqk.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.project.xm.xmgckyzfqk.dao.XmGckyzfqkDao;
import com.zj.project.xm.xmgckyzfqk.domain.XmGckyzfqkDO;
import com.zj.project.xm.xmgckyzfqk.service.XmGckyzfqkService;
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
 * 工程款与支付情况:工程款申请/支付情况
 * </pre>
 * <small> 2018-10-14 10:40:37 | lijun</small>
 */
@Service
public class XmGckyzfqkServiceImpl extends BaseServiceImpl<XmGckyzfqkDao, XmGckyzfqkDO> implements XmGckyzfqkService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmGckyzfqkDO.class);
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
    public Collection<XmGckyzfqkDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存工程款与支付情况信息
     * @param xmGckyzfqkDO
     * @param fileIds 文件ids
     */
	@Override
	public void saveXmGckyzfqkXx(XmGckyzfqkDO xmGckyzfqkDO, String fileIds) {
		Long id = xmGckyzfqkDO.getId();
        if(id==null){
        	xmGckyzfqkDO.setGxsj(new Date());
        	xmGckyzfqkDO.setFcbz(1);
            save(xmGckyzfqkDO);
        }else{
        	xmGckyzfqkDO.setGxsj(new Date());
            updateById(xmGckyzfqkDO);
        }

        id=xmGckyzfqkDO.getId();

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
