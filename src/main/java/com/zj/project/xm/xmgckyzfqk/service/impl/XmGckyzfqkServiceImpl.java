package com.zj.project.xm.xmgckyzfqk.service.impl;

import java.io.Serializable;
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
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.splczt.domain.SplcZtDO;
import com.zj.project.xm.splczt.service.SplcZtService;
import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmdwmd.service.XmDwmdService;
import com.zj.project.xm.xmgckyzfqk.dao.XmGckyzfqkDao;
import com.zj.project.xm.xmgckyzfqk.domain.XmGckyzfqkDO;
import com.zj.project.xm.xmgckyzfqk.service.XmGckyzfqkService;

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
    private SplcZtService splcZtService;
    
    @Override
    public boolean updateById(XmGckyzfqkDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmGckyzfqkDO> updateWrapper=new UpdateWrapper<XmGckyzfqkDO>().eq("id", entity.getId());
    	fieldList.forEach(filed->{
    		if(filed.isCharSequence()&&FieldStrategy.NOT_EMPTY==filed.getFieldStrategy()) {
    			String value = (String)ReflectionKit.getMethodValue(entity, filed.getProperty());
        		updateWrapper.set(MyStringUtils.isEmptyString(value),filed.getColumn(),value);
    		}
    	});
    	return update(entity, updateWrapper);
    }
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private XmDwmdService xmDwmdService;
    
    @Override
    public XmGckyzfqkDO getById(Serializable id) {
    	XmGckyzfqkDO xmGckyzfqkDO=super.getById(id);
    	if(xmGckyzfqkDO!=null) {
    		Integer intdwlx = xmGckyzfqkDO.getIntdwlx();
    		if(intdwlx!=null) {
    			String chrdwlx="";
    			if(intdwlx.equals(1)) {
    				chrdwlx="顾问单位";
    			}else if(intdwlx.equals(2)) {
    				chrdwlx="施工单位";
    			}if(intdwlx.equals(3)) {
    				chrdwlx="其他单位";
    			}
    			xmGckyzfqkDO.setChrdwlx(chrdwlx);
    		}
    		
    		Long intdwmcid = xmGckyzfqkDO.getIntdwmcid();
    		if(intdwmcid!=null) {
    			XmDwmdDO xmDwmdDO = xmDwmdService.getById(intdwmcid);
    			if(xmDwmdDO!=null) {
    				xmGckyzfqkDO.setChrdwmc(xmDwmdDO.getChrdwmc());
    			}
    		}
    		
    		String intsplczt = xmGckyzfqkDO.getIntsplcztid();
    		if(StringUtils.isNotEmpty(intsplczt)) {
    			SplcZtDO splcZtDO = splcZtService.getById(intsplczt);
    			if(splcZtDO!=null) {
    				xmGckyzfqkDO.setChrsplczt(splcZtDO.getChrsprmc());
    			}
    		}
    		
    	}
    	return xmGckyzfqkDO;
    }

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
