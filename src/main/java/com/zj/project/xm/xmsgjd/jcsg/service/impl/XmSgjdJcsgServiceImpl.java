package com.zj.project.xm.xmsgjd.jcsg.service.impl;

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

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;
import com.zj.project.xm.xmsgjd.jcsg.dao.XmSgjdJcsgDao;
import com.zj.project.xm.xmsgjd.jcsg.domain.XmSgjdJcsgDO;
import com.zj.project.xm.xmsgjd.jcsg.service.XmSgjdJcsgService;
import com.zj.project.xm.xmzpms.domain.XmZpmsDO;
import com.zj.project.xm.xmzpms.service.XmZpmsService;

/**
 * 
 * <pre>
 * 项目基本信息 - 施工进度 - 基础施工
 * </pre>
 * 
 * <small> 2018-10-13 19:32:53 | lijun</small>
 */
@Service
public class XmSgjdJcsgServiceImpl extends BaseServiceImpl<XmSgjdJcsgDao, XmSgjdJcsgDO> implements XmSgjdJcsgService {

	public static TableInfo tableInfo = null;

	static {
		tableInfo = TableInfoHelper.getTableInfo(XmSgjdJcsgDO.class);
	}
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private XmZpmsService xmZpmsService;
	
	@Autowired
	private XmGqjdbjService xmGqjdbjService;
	
	@Override
	public XmSgjdJcsgDO getById(Serializable id) {
		XmSgjdJcsgDO xmSgjdJcsgDO=super.getById(id);
		if(xmSgjdJcsgDO!=null) {
			Long intsgwzid = xmSgjdJcsgDO.getIntsgwzid();
			if(intsgwzid!=null) {
				if(intsgwzid==-1) {
					xmSgjdJcsgDO.setChrsgwzmc("其他");
				}else{
					XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intsgwzid);
					if(xmGqjdbjDO!=null) {
						xmSgjdJcsgDO.setChrsgwzmc(xmGqjdbjDO.getChrjdmc());
					}
				}
			}
			
			Integer intjclx = xmSgjdJcsgDO.getIntjclx();
			if(intjclx!=null) {
					XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intjclx);
					if(xmGqjdbjDO!=null) {
						xmSgjdJcsgDO.setChrjclx(xmGqjdbjDO.getChrjdmc());
					}
			}
			
		}
		return xmSgjdJcsgDO;
	}
	
	@Override
	public boolean removeByParmMap(Map<String, Object> parmMap) {
		parmMap = parmToColumnMap(tableInfo, parmMap);
		Assert.notEmpty(parmMap, "过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
		return super.removeByMap(parmMap);
	}

	@Override
	public Collection<XmSgjdJcsgDO> listByParmMap(Map<String, Object> parmMap) {
		parmMap = parmToColumnMap(tableInfo, parmMap);
		return listByMap(parmMap);
	}

	/**
	 * 保存施工进度-基础施工
	 * 
	 * @param xmSgjdJcsgDO
	 * @param fileIds
	 *            图片ids
	 * @param xmZpmsJson
	 *            图片描述json
	 */
	@Override
	public void saveXmSgjdJcsgXx(XmSgjdJcsgDO xmSgjdJcsgDO, String fileIds, String xmZpmsJson) {
		Long xmid = xmSgjdJcsgDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdJcsgDO.getId();
		if (id == null) {
			xmSgjdJcsgDO.setFcbz(1);
			xmSgjdJcsgDO.setGxsj(new Date());
			save(xmSgjdJcsgDO);
		} else {
			xmSgjdJcsgDO.setGxsj(new Date());
			updateById(xmSgjdJcsgDO);
		}

		id = xmSgjdJcsgDO.getId();

		// 更新图片信息
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

		// 保存照片描述信息
		if (StringUtils.isNotEmpty(xmZpmsJson) && !"[]".equals(xmZpmsJson)) {
			Gson gson = new Gson();
			List<XmZpmsDO> list = gson.fromJson(xmZpmsJson, new TypeToken<List<XmZpmsDO>>() {
			}.getType());
			if (CollectionUtils.isNotEmpty(list)) {
				list.forEach(xmZpmsDO -> {
					Long xmZpmsId = xmZpmsDO.getId();
					if (xmZpmsId == null) {
						xmZpmsDO.setFcbz(1);
						xmZpmsDO.setGxsj(new Date());
						xmZpmsDO.setIntxmid(xmid);
						xmZpmsDO.setIntzpssid(xmSgjdJcsgDO.getId());
						xmZpmsService.save(xmZpmsDO);
					} else {
						xmZpmsDO.setGxsj(new Date());
						xmZpmsService.updateById(xmZpmsDO);
					}
				});
			}
		}
	}

}
