package com.zj.project.xm.xmaqbg.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmaqbg.dao.XmAqbgDao;
import com.zj.project.xm.xmaqbg.domain.XmAqbgDO;
import com.zj.project.xm.xmaqbg.service.XmAqbgService;

/**
 * 
 * <pre>
 * 安全报告
 * </pre>
 * <small> 2018-10-13 17:00:14 | lijun</small>
 */
@Service
public class XmAqbgServiceImpl extends BaseServiceImpl<XmAqbgDao, XmAqbgDO> implements XmAqbgService {

    public static TableInfo tableInfo = null;
    
    static {
        tableInfo=TableInfoHelper.getTableInfo( XmAqbgDO.class);
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
    public Collection<XmAqbgDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存安全报告信息
     * @param xmAqbgDO
     * @param fileIds
     */
	@Override
	public void saveXmAqbgDOXx(XmAqbgDO xmAqbgDO, String fileIds) {
		Long id = xmAqbgDO.getId();
        if(id==null){
        	xmAqbgDO.setGxsj(new Date());
        	xmAqbgDO.setFcbz(1);
            save(xmAqbgDO);
        }else{
        	xmAqbgDO.setGxsj(new Date());
            updateById(xmAqbgDO);
        }

        id=xmAqbgDO.getId();

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
