package com.zj.project.xm.xmsgjd.ztjgsg.service.impl;

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
import com.zj.project.xm.xmsgjd.ztjgsg.dao.XmSgjdZtjgsgDao;
import com.zj.project.xm.xmsgjd.ztjgsg.domain.XmSgjdZtjgsgDO;
import com.zj.project.xm.xmsgjd.ztjgsg.service.XmSgjdZtjgsgService;
import com.zj.project.xm.xmzpms.domain.XmZpmsDO;
import com.zj.project.xm.xmzpms.service.XmZpmsService;

/**
 * 
 * <pre>
 * 施工进度 - 主体结构施工
 * </pre>
 * 
 * <small> 2018-10-13 20:03:27 | lijun</small>
 */
@Service
public class XmSgjdZtjgsgServiceImpl extends BaseServiceImpl<XmSgjdZtjgsgDao, XmSgjdZtjgsgDO>
		implements XmSgjdZtjgsgService {

	public static TableInfo tableInfo = null;

	static {
		tableInfo = TableInfoHelper.getTableInfo(XmSgjdZtjgsgDO.class);
	}
	
	@Autowired
	private FileService fileService;

	@Autowired
	private XmZpmsService xmZpmsService;
	
	@Autowired
	private XmGqjdbjService xmGqjdbjService;
	
	
	
	@Override
	public XmSgjdZtjgsgDO getById(Serializable id) {
		XmSgjdZtjgsgDO xmSgjdZtjgsgDO=super.getById(id);
		if(xmSgjdZtjgsgDO!=null) {
			Long intsgwzd = xmSgjdZtjgsgDO.getIntsgwzd();
			if(intsgwzd!=null) {
				XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intsgwzd);
					if(xmGqjdbjDO!=null) {
						xmSgjdZtjgsgDO.setChrShowAddress(xmGqjdbjDO.getChrjdmc());
					}
			}
			
			Long intsgwzc = xmSgjdZtjgsgDO.getIntsgwzc();
			if(intsgwzc!=null) {
				XmGqjdbjDO xmGqjdbjDO = xmGqjdbjService.getById(intsgwzc);
					if(xmGqjdbjDO!=null) {
						xmSgjdZtjgsgDO.setChrsgwzc(xmGqjdbjDO.getChrjdmc());
					}
			}
			
		}
		return xmSgjdZtjgsgDO;
	}
	
	@Override
	public boolean removeByParmMap(Map<String, Object> parmMap) {
		parmMap = parmToColumnMap(tableInfo, parmMap);
		Assert.notEmpty(parmMap, "过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
		return super.removeByMap(parmMap);
	}

	@Override
	public Collection<XmSgjdZtjgsgDO> listByParmMap(Map<String, Object> parmMap) {
		parmMap = parmToColumnMap(tableInfo, parmMap);
		return listByMap(parmMap);
	}

	/**
	 * 保存主体施工信息
	 * 
	 * @param xmSgjdZtjgsgDO
	 * @param fileIds
	 *            图片IDS
	 * @param xmZpmsJson
	 *            图片描述对象json串
	 */
	@Override
	public void saveXmSgjdZtjgsgXx(XmSgjdZtjgsgDO xmSgjdZtjgsgDO, String fileIds, String xmZpmsJson) {
		Long xmid = xmSgjdZtjgsgDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdZtjgsgDO.getId();
		if (id == null) {
			xmSgjdZtjgsgDO.setFcbz(1);
			xmSgjdZtjgsgDO.setGxsj(new Date());
			save(xmSgjdZtjgsgDO);
		} else {
			xmSgjdZtjgsgDO.setGxsj(new Date());
			updateById(xmSgjdZtjgsgDO);
		}

		id = xmSgjdZtjgsgDO.getId();

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
						xmZpmsDO.setIntzpssid(xmSgjdZtjgsgDO.getId());
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
