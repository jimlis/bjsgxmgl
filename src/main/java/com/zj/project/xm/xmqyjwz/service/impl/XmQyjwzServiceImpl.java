package com.zj.project.xm.xmqyjwz.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmqyjwz.dao.XmQyjwzDao;
import com.zj.project.xm.xmqyjwz.domain.XmQyjwzDO;
import com.zj.project.xm.xmqyjwz.service.XmQyjwzService;

/**
 * 
 * <pre>
 * 所在区域及位置
 * </pre>
 * <small> 2018-10-01 22:46:44 | lijun</small>
 */
@Service
public class XmQyjwzServiceImpl extends BaseServiceImpl<XmQyjwzDao, XmQyjwzDO> implements XmQyjwzService {

    public static TableInfo tableInfo = null;
    @Autowired
    public FileService fileService;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmQyjwzDO.class);
    }
    
    @Override
    public boolean updateById(XmQyjwzDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmQyjwzDO> updateWrapper=new UpdateWrapper<XmQyjwzDO>().eq("id", entity.getId());
    	fieldList.forEach(filed->{
    		if(filed.isCharSequence()&&FieldStrategy.NOT_EMPTY==filed.getFieldStrategy()) {
    			String value = (String)ReflectionKit.getMethodValue(entity, filed.getProperty());
        		updateWrapper.set(MyStringUtils.isEmptyString(value),filed.getColumn(),value);
    		}
    	});
    	return update(entity, updateWrapper);
    }

    @Override
    public XmQyjwzDO getById(Serializable id){
        XmQyjwzDO xmQyjwzDO = super.getById(id);
        if(xmQyjwzDO!=null){
            List<FileDO> fileDOList = fileService.queryFileDOList(tableInfo.getTableName(), xmQyjwzDO.getId(), null);

            //设置相关文件id
            String fileIds="";
            if(CollectionUtils.isNotEmpty(fileDOList)){

                for (FileDO fileDo:fileDOList){
                    fileIds+=fileDo.getId()+",";
                }
                fileIds=fileIds.endsWith(",")?fileIds.substring(0,fileIds.length()-1):fileIds;

            }
            xmQyjwzDO.setFileIds(fileIds);

        }

        return xmQyjwzDO;


    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmQyjwzDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }

    /**
     * 保存项目区域位置信息
     * @param xmQyjwzDO  项目区域位置
     */
    @Override
    public void saveXmQyjwzxx(XmQyjwzDO xmQyjwzDO){
    	  Long id=xmQyjwzDO.getId();
    	  if(id==null) {
    		  xmQyjwzDO.setFcbz(1);
              xmQyjwzDO.setGxsj(new Date());
              save(xmQyjwzDO);
    	  }else {
    		  xmQyjwzDO.setGxsj(new Date());
              updateById(xmQyjwzDO);
    	  }
    	  
    	  id = xmQyjwzDO.getId();

        //关联附件对象
        String fileIds=xmQyjwzDO.getFileIds();
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
