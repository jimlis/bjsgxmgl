package com.zj.project.xm.xmclybspjl.service.impl;

import java.io.Serializable;
import java.util.Arrays;
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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.splczt.domain.SplcZtDO;
import com.zj.project.xm.splczt.service.SplcZtService;
import com.zj.project.xm.xmclybspjl.dao.XmClybspjlDao;
import com.zj.project.xm.xmclybspjl.domain.XmClybspjlDO;
import com.zj.project.xm.xmclybspjl.domain.XmClybspjlJszlDO;
import com.zj.project.xm.xmclybspjl.service.XmClybspjlJszlService;
import com.zj.project.xm.xmclybspjl.service.XmClybspjlService;
import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmdwmd.service.XmDwmdService;

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
	
	@Autowired
	private XmDwmdService xmDwmdService;
	
	@Autowired
	private SplcZtService splcZtService;
	
	
	@Override
    public boolean updateById(XmClybspjlDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmClybspjlDO> updateWrapper=new UpdateWrapper<XmClybspjlDO>().eq("id", entity.getId());
    	fieldList.forEach(filed->{
    		if(filed.isCharSequence()&&FieldStrategy.NOT_EMPTY==filed.getFieldStrategy()) {
    			String value = (String)ReflectionKit.getMethodValue(entity, filed.getProperty());
        		updateWrapper.set(MyStringUtils.isEmptyString(value),filed.getColumn(),value);
    		}
    	});
    	return update(entity, updateWrapper);
    }
	
	@Override
	public XmClybspjlDO getById(Serializable id) {
		XmClybspjlDO xmClybspjlDO = super.getById(id);
		if(xmClybspjlDO!=null) {
			// 查询品牌技术信息
			XmClybspjlJszlDO xmClybspjlJszlDO = new XmClybspjlJszlDO();
			xmClybspjlJszlDO.setFcbz(1);
			xmClybspjlJszlDO.setIntclybspjlid(xmClybspjlDO.getId());
			QueryWrapper<XmClybspjlJszlDO> queryWrapper = new QueryWrapper<XmClybspjlJszlDO>(xmClybspjlJszlDO)
					.orderByAsc("intxh");
			List<XmClybspjlJszlDO> list = xmClybspjlJszlService.list(queryWrapper);
			xmClybspjlDO.setXmClybspjlJszlList(list);
			
			//施工名称
			Long intsgdw = xmClybspjlDO.getIntsgdw();
			if(intsgdw!=null) {
				XmDwmdDO xmDwmdDO = xmDwmdService.getById(intsgdw);
				if(xmDwmdDO!=null) {
					xmClybspjlDO.setChrsgdw(xmDwmdDO.getChrdwmc());
				}
			}
			
			Integer intclyblx = xmClybspjlDO.getIntclyblx();
			String chrclyblx ="";
			if(intclyblx!=null) {
				if(intclyblx.equals(1)) {
					chrclyblx="土建";
				}else if(intclyblx.equals(2)) {
					chrclyblx="机电";
				}else if(intclyblx.equals(3)) {
					chrclyblx="装修";
				}else if(intclyblx.equals(4)) {
					chrclyblx="园林";
				}else if(intclyblx.equals(5)) {
					chrclyblx="其他";
				}
				xmClybspjlDO.setChrclyblx(chrclyblx);
			}
			
			Integer intsfdtp = xmClybspjlDO.getIntsfdtp();
			xmClybspjlDO.setChrsfdtp((intsfdtp!=null&&intsfdtp.equals(1))?"是":"否");
			
			String intsplczt = xmClybspjlDO.getIntsplczt();
    		SplcZtDO splcZtDO = splcZtService.getById(intsplczt);
    		if(splcZtDO!=null) {
    			xmClybspjlDO.setChrsplczt(splcZtDO.getChrsprmc());
    		}
    		
    		String chrspzt = xmClybspjlDO.getChrspzt();
    		if(StringUtils.isNotEmpty(chrspzt)) {
    			String chrspztmc="";
    			if("wwc".equals(chrspzt)) {
    				chrspztmc="未完成";
    			}else if("wtg".equals(chrspzt)) {
    				chrspztmc="未通过";
    			}else if("tg".equals(chrspzt)) {
    				chrspztmc="通过";
    			}
    			xmClybspjlDO.setChrspztmc(chrspztmc);
    		}
		}
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
	 * @param deleteJszlIds 删除的品牌技术资料ids
	 */
	@Override
	public void saveXmClybspjlxx(XmClybspjlDO xmClybspjlDO, String fileIds, String xmClybspjlJszlJson,String deleteJszlIds) {
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
		
		//删除技术资料
		if(StringUtils.isNotEmpty(deleteJszlIds)) {
			Arrays.stream(deleteJszlIds.trim().split(",")).forEach(jszlId->{
				if(StringUtils.isNotEmpty(jszlId)) {
					XmClybspjlJszlDO xmClybspjlJszl=new XmClybspjlJszlDO();
					xmClybspjlJszl.setId(Long.parseLong(jszlId));
					xmClybspjlJszl.setFcbz(0);
					xmClybspjlJszl.setGxsj(new Date());
					xmClybspjlJszlService.updateById(xmClybspjlJszl);
				}
			});
		}
	}
	
	/**
	 * <p>Title:获取审批人审批数量 </p>  
	 * <p>Description: </p> 
	 * @param xmid
	 * @return
	 * @author zhujujun
	 * @date:2019年1月6日 下午4:00:17
	 */
	@Override
	public List<Map<String,Object>> getSprSpsl(Long xmid){
		return baseMapper.getSprSpsl(xmid);
	}

}
