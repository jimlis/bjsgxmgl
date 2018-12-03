package com.zj.project.xm.xmsgjd.sgjdecjgzxqt.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmsgjd.sgjdecjgzxqt.dao.XmSgjdEcjgzxQtDao;
import com.zj.project.xm.xmsgjd.sgjdecjgzxqt.domain.XmSgjdEcjgzxQtDO;
import com.zj.project.xm.xmsgjd.sgjdecjgzxqt.service.XmSgjdEcjgzxQtService;

/**
 * 
 * <pre>
 * 施工进度-二次结构装修-其他
 * </pre>
 * <small> 2018-11-10 14:07:31 | lijun</small>
 */
@Service
public class XmSgjdEcjgzxQtServiceImpl extends BaseServiceImpl<XmSgjdEcjgzxQtDao, XmSgjdEcjgzxQtDO> implements XmSgjdEcjgzxQtService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdEcjgzxQtDO.class);
    }
    
    @Autowired
    private FileService fileService;
    
    @Override
    public boolean updateById(XmSgjdEcjgzxQtDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmSgjdEcjgzxQtDO> updateWrapper=new UpdateWrapper<XmSgjdEcjgzxQtDO>().eq("id", entity.getId());
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
    public Collection<XmSgjdEcjgzxQtDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存施工进度-二次结构装修-其他 
     * @param xmSgjdEcjgzxQtDO
     * @param fileIds 文件ids
     */
	@Override
	public void saveXmSgjdEcjgzxQtxx(XmSgjdEcjgzxQtDO xmSgjdEcjgzxQtDO, String fileIds) {
		Long xmid = xmSgjdEcjgzxQtDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdEcjgzxQtDO.getId();
		if (id == null) {
			xmSgjdEcjgzxQtDO.setFcbz(1);
			xmSgjdEcjgzxQtDO.setGxsj(new Date());
			save(xmSgjdEcjgzxQtDO);
		} else {
			xmSgjdEcjgzxQtDO.setGxsj(new Date());
			updateById(xmSgjdEcjgzxQtDO);
		}
		
		// 更新附件信息
		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fileArr = fileIds.trim().split(",");
			for (String fileid : fileArr) {
				if (StringUtils.isNotEmpty(fileid.trim())) {
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
