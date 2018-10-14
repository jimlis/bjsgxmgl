package com.zj.project.xm.xmybsgjl.service.impl;

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
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmybsgjl.dao.XmYbsgjlDao;
import com.zj.project.xm.xmybsgjl.domain.XmYbsgjlDO;
import com.zj.project.xm.xmybsgjl.service.XmYbsgjlService;

/**
 * 
 * <pre>
 * 样板施工记录
 * </pre>
 * <small> 2018-10-13 00:54:47 | lijun</small>
 */
@Service
public class XmYbsgjlServiceImpl extends BaseServiceImpl<XmYbsgjlDao, XmYbsgjlDO> implements XmYbsgjlService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmYbsgjlDO.class);
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
    public Collection<XmYbsgjlDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }

    /**
     * 保存项样板施工记录
     * @param xmYbsgjlDO
     * @param fileIds 图片ids 多个以逗号隔开
     */
    @Override
    public void saveXmYbsgjlXx(XmYbsgjlDO xmYbsgjlDO, String fileIds) {
       
        Long intxmid = xmYbsgjlDO.getIntxmid();
        if(intxmid==null){
            throw  new MyApiException("44005");
        }
        Long id = xmYbsgjlDO.getId();
        
        if(id==null){
            xmYbsgjlDO.setGxsj(new Date());
            xmYbsgjlDO.setFcbz(1);
            save(xmYbsgjlDO);
        }else{
            xmYbsgjlDO.setGxsj(new Date());
            updateById(xmYbsgjlDO);
        }

        id=xmYbsgjlDO.getId();

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
