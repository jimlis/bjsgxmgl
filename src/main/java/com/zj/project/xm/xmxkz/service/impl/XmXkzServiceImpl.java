package com.zj.project.xm.xmxkz.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmxkz.dao.XmXkzDao;
import com.zj.project.xm.xmxkz.domain.XmXkzDO;
import com.zj.project.xm.xmxkz.service.XmXkzService;

/**
 * 
 * <pre>
 * 项目基本信息-许可证
 * </pre>
 * <small> 2018-10-04 18:10:12 | lijun</small>
 */
@Service
public class XmXkzServiceImpl extends BaseServiceImpl<XmXkzDao, XmXkzDO> implements XmXkzService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmXkzDO.class);
    }

    @Autowired
    private FileService fileService;
    
    @Override
    public boolean updateById(XmXkzDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmXkzDO> updateWrapper=new UpdateWrapper<XmXkzDO>().eq("id", entity.getId());
    	fieldList.forEach(filed->{
    		if(filed.isCharSequence()&&FieldStrategy.NOT_EMPTY==filed.getFieldStrategy()) {
    			String value = (String)ReflectionKit.getMethodValue(entity, filed.getProperty());
        		updateWrapper.set(MyStringUtils.isEmptyString(value),filed.getColumn(),value);
    		}
    	});
    	return update(entity, updateWrapper);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmXkzDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }

    /*
     *保存项目许可证信息
     * @param XmXkzDO 许可证信息
     * @param fileIds 相关附件信息
     */
    @Override
    public void saveXmXkzxx(XmXkzDO xmXkzDO, String fileIds) {
        Long intxmid = xmXkzDO.getIntxmid();
        String intxkzlx = xmXkzDO.getIntxkzlx();
        if(intxmid==null){
            throw  new MyApiException("44005");
        }
        
        XmXkzDO xmXkzDOOne=new XmXkzDO();
        xmXkzDOOne.setFcbz(1);
        xmXkzDOOne.setIntxkzlx(intxkzlx);
        xmXkzDOOne.setIntxmid(intxmid);
        QueryWrapper<XmXkzDO>  queryWapper=new QueryWrapper<XmXkzDO>(xmXkzDOOne);
        XmXkzDO one = getOne(queryWapper);
        
       
        if(one!=null) {
        	xmXkzDO.setId(one.getId());
        }

        Long id=xmXkzDO.getId();
        if(id==null){
            xmXkzDO.setFcbz(1);
            xmXkzDO.setGxsj(new Date());
            save(xmXkzDO);
        }else{
            updateById(xmXkzDO);
        }

        if(StringUtils.isNotEmpty(fileIds)){
            String[] fileArr=fileIds.trim().split(",");
            for (String fileid : fileArr){
                if(StringUtils.isNotEmpty(fileid.trim())){
                    FileDO fileDO=new FileDO();
                    fileDO.setId(Long.parseLong(fileid.trim()));
                    fileDO.setBusType(tableInfo.getTableName());
                    fileDO.setBusId(xmXkzDO.getId());
                    fileService.updateById(fileDO);
                }
            }
        }
    }
}
