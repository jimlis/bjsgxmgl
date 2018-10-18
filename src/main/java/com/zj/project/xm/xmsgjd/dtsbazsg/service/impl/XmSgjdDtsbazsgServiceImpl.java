package com.zj.project.xm.xmsgjd.dtsbazsg.service.impl;

import java.io.Serializable;
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
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmdl.domain.XmDlDO;
import com.zj.project.xm.xmdl.service.XmDlService;
import com.zj.project.xm.xmsgjd.dtsbazsg.dao.XmSgjdDtsbazsgDao;
import com.zj.project.xm.xmsgjd.dtsbazsg.domain.XmSgjdDtsbazsgDO;
import com.zj.project.xm.xmsgjd.dtsbazsg.service.XmSgjdDtsbazsgService;

/**
 * 
 * <pre>
 * 施工进度-电梯设备安装施工
 * </pre>
 * <small> 2018-10-13 21:17:22 | lijun</small>
 */
@Service
public class XmSgjdDtsbazsgServiceImpl extends BaseServiceImpl<XmSgjdDtsbazsgDao, XmSgjdDtsbazsgDO> implements XmSgjdDtsbazsgService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdDtsbazsgDO.class);
    }
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private  XmDlService xmDlService;
    
    @Override
    public XmSgjdDtsbazsgDO getById(Serializable id) {
    	XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO=super.getById(id);
    	if(xmSgjdDtsbazsgDO!=null) {
    		Long intsgwz = xmSgjdDtsbazsgDO.getIntsgwz();
    		XmDlDO xmDlDO = xmDlService.getById(intsgwz);
    		if(xmDlDO!=null) {
    			xmSgjdDtsbazsgDO.setChrsgwz(xmDlDO.getChrdlmc());
    		}
    	}
		return xmSgjdDtsbazsgDO;
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdDtsbazsgDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 保存项目施工记录电梯设备安装信息
     * @param xmSgjdDtsbazsgDO
     * @param fileIds
     */
	@Override
	public void saveXmSgjdDtsbazsgXx(XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO, String fileIds) {
		Long xmid = xmSgjdDtsbazsgDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdDtsbazsgDO.getId();
		if (id == null) {
			xmSgjdDtsbazsgDO.setFcbz(1);
			xmSgjdDtsbazsgDO.setGxsj(new Date());
			save(xmSgjdDtsbazsgDO);
		} else {
			xmSgjdDtsbazsgDO.setGxsj(new Date());
			updateById(xmSgjdDtsbazsgDO);
		}

		id = xmSgjdDtsbazsgDO.getId();

		// 更新附件信息
		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fileArr = fileIds.trim().split(",");
			for (String fileid : fileArr) {
				if (StringUtils.isNotEmpty(fileid.trim())) {
					FileDO fileDO = new FileDO();
					fileDO.setId(Long.parseLong(fileid.trim()));
					fileDO.setBusType(tableInfo.getTableName());
					fileDO.setBusId(id);
					fileService.updateById(fileDO);
				}
			}
		}

	}


}
