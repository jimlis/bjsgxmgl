package com.zj.project.xm.xmsgjd.swgwsg.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmsgjd.swgwsg.dao.XmSgjdSwgwsgDao;
import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwsgService;

/**
 * 
 * <pre>
 * 施工进度-室外管网施工
 * </pre>
 * <small> 2018-10-14 10:20:56 | lijun</small>
 */
@Service
public class XmSgjdSwgwsgServiceImpl extends BaseServiceImpl<XmSgjdSwgwsgDao, XmSgjdSwgwsgDO> implements XmSgjdSwgwsgService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdSwgwsgDO.class);
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
    public Collection<XmSgjdSwgwsgDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }

	@Override
	public List<XmSgjdSwgwsgDO> getXmSgjdYlsgListByXmidAndGxrq(Long xmid, Date gxrq) {
		if(xmid==null) {
    		throw new CommonException("项目id不能为空");
    	}
    	
    	if(gxrq==null) {
    		throw new CommonException("更新日期不能为空");
    	}
    	
    	XmSgjdSwgwsgDO xmSgjdSwgwsgDO=new XmSgjdSwgwsgDO();
    	xmSgjdSwgwsgDO.setFcbz(1);
    	xmSgjdSwgwsgDO.setIntxmid(xmid);
    	xmSgjdSwgwsgDO.setDtmbgrq(gxrq);
    	QueryWrapper<XmSgjdSwgwsgDO> queryWrapper=new QueryWrapper<XmSgjdSwgwsgDO>(xmSgjdSwgwsgDO).orderByDesc("gxsj");
    	List<XmSgjdSwgwsgDO> list=list(queryWrapper);
    	if(CollectionUtils.isNotEmpty(list)) {
    		list.forEach(xmSgjdSwgwsgDOOne->{
    			FileDO fileDO=new FileDO();
    			fileDO.setBusId(xmSgjdSwgwsgDOOne.getId());
    			fileDO.setBusType(tableInfo.getTableName());
    			fileDO.setType("2");//完成情况
    			QueryWrapper<FileDO> fileQuery=new QueryWrapper<FileDO>(fileDO);
    			xmSgjdSwgwsgDOOne.setWcqkList(fileService.list(fileQuery));
    		});
    	}
    	
		return list;
	}
	
	/**
	 * 保存施工进度-室外管网施工信息
	 * @param xmSgjdSwgwsgDO
	 * @param fileIds 附件ids
	 */
	@Override
	public void saveXmSgjdSwgwsgXx(XmSgjdSwgwsgDO xmSgjdSwgwsgDO, String fileIds) {
		Long xmid = xmSgjdSwgwsgDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdSwgwsgDO.getId();
		if (id == null) {
			xmSgjdSwgwsgDO.setFcbz(1);
			xmSgjdSwgwsgDO.setGxsj(new Date());
			save(xmSgjdSwgwsgDO);
		} else {
			xmSgjdSwgwsgDO.setGxsj(new Date());
			updateById(xmSgjdSwgwsgDO);
		}

		id = xmSgjdSwgwsgDO.getId();

		// 更新文件信息
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
