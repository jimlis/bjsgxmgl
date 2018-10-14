package com.zj.project.xm.xmclybspjl.service.impl;

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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmclybspjl.dao.XmClybspjlDao;
import com.zj.project.xm.xmclybspjl.domain.XmClybspjlDO;
import com.zj.project.xm.xmclybspjl.domain.XmClybspjlJszlDO;
import com.zj.project.xm.xmclybspjl.service.XmClybspjlJszlService;
import com.zj.project.xm.xmclybspjl.service.XmClybspjlService;

/**
 * 
 * <pre>
 * 材料样板审批记录
 * </pre>
 * 
 * <small> 2018-10-13 18:35:59 | lijun</small>
 */
@Service
public class XmClybspjlServiceImpl extends BaseServiceImpl<XmClybspjlDao, XmClybspjlDO> implements XmClybspjlService {

	public static TableInfo tableInfo = null;

	static {
		tableInfo = TableInfoHelper.getTableInfo(XmClybspjlDO.class);
	}

	@Autowired
	private XmClybspjlJszlService xmClybspjlJszlService;
	
	@Autowired
	private FileService fileService;
	
	@Override
	public XmClybspjlDO getById(Serializable id) {
		XmClybspjlDO xmClybspjlDO = super.getById(id);

		// 查询品牌技术信息
		XmClybspjlJszlDO xmClybspjlJszlDO = new XmClybspjlJszlDO();
		xmClybspjlJszlDO.setFcbz(1);
		xmClybspjlJszlDO.setIntclybspjlid(xmClybspjlDO.getId());
		QueryWrapper<XmClybspjlJszlDO> queryWrapper = new QueryWrapper<XmClybspjlJszlDO>(xmClybspjlJszlDO)
				.orderByAsc("intxh");
		List<XmClybspjlJszlDO> list = xmClybspjlJszlService.list(queryWrapper);
		xmClybspjlDO.setXmClybspjlJszlList(list);

		return xmClybspjlDO;
	}

	@Override
	public boolean removeByParmMap(Map<String, Object> parmMap) {
		parmMap = parmToColumnMap(tableInfo, parmMap);
		Assert.notEmpty(parmMap, "过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
		return super.removeByMap(parmMap);
	}

	@Override
	public Collection<XmClybspjlDO> listByParmMap(Map<String, Object> parmMap) {
		parmMap = parmToColumnMap(tableInfo, parmMap);
		return listByMap(parmMap);
	}

	/**
	 * 保存材料样板审批记录信息
	 * 
	 * @param xmClybspjlDO
	 * @param fileIds
	 *            文件ids
	 * @param xmClybspjlJszlJson
	 *            品牌和技术json串
	 */
	@Override
	public void saveXmZpjlxx(XmClybspjlDO xmClybspjlDO, String fileIds, String xmClybspjlJszlJson) {
		Long xmid = xmClybspjlDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmClybspjlDO.getId();
		if (id == null) {
			xmClybspjlDO.setFcbz(1);
			xmClybspjlDO.setGxsj(new Date());
			save(xmClybspjlDO);
		} else {
			xmClybspjlDO.setGxsj(new Date());
			updateById(xmClybspjlDO);
		}

		id = xmClybspjlDO.getId();

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

		// 保存品牌和技术信息
		if (StringUtils.isNotEmpty(xmClybspjlJszlJson) && !"[]".equals(xmClybspjlJszlJson)) {
			Gson gson = new Gson();
			List<XmClybspjlJszlDO> list = gson.fromJson(xmClybspjlJszlJson, new TypeToken<List<XmClybspjlJszlDO>>() {
			}.getType());
			if (CollectionUtils.isNotEmpty(list)) {
				list.forEach(xmClybspjlJszlDO -> {
					Long xmClybspjlJszlId = xmClybspjlJszlDO.getId();
					if (xmClybspjlJszlId == null) {
						xmClybspjlJszlDO.setFcbz(1);
						xmClybspjlJszlDO.setGxsj(new Date());
						xmClybspjlJszlDO.setIntclybspjlid(xmClybspjlDO.getId());
						xmClybspjlJszlService.save(xmClybspjlJszlDO);
					} else {
						xmClybspjlJszlDO.setGxsj(new Date());
						xmClybspjlJszlService.updateById(xmClybspjlJszlDO);
					}
				});
			}
		}
	}

}
