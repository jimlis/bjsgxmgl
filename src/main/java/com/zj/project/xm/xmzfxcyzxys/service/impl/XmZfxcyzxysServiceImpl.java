package com.zj.project.xm.xmzfxcyzxys.service.impl;

import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.project.xm.xmzfxcyzxys.dao.XmZfxcyzxysDao;
import com.zj.project.xm.xmzfxcyzxys.domain.XmZfxcyzxysDO;
import com.zj.project.xm.xmzfxcyzxys.service.XmZfxcyzxysService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.springframework.util.Assert;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

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
