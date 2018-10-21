package com.zj.project.xm.xmsgjd.ylsg.service.impl;

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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmsgjd.ylsg.dao.XmSgjdYlsgDao;
import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgDO;
import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgJdDO;
import com.zj.project.xm.xmsgjd.ylsg.service.XmSgjdYlsgJdService;
import com.zj.project.xm.xmsgjd.ylsg.service.XmSgjdYlsgService;

/**
 * 
 * <pre>
 * 施工进度-园林施工
 * </pre>
 * <small> 2018-10-13 22:22:17 | lijun</small>
 */
@Service
public class XmSgjdYlsgServiceImpl extends BaseServiceImpl<XmSgjdYlsgDao, XmSgjdYlsgDO> implements XmSgjdYlsgService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmSgjdYlsgDO.class);
    }
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private XmSgjdYlsgJdService xmSgjdYlsgJdService;
    
    @Override
    public XmSgjdYlsgDO getById(Serializable id){
    	XmSgjdYlsgDO xmSgjdYlsgDO=super.getById(id);
    	if(xmSgjdYlsgDO!=null) {
    		XmSgjdYlsgJdDO xmSgjdYlsgJdDO=new XmSgjdYlsgJdDO();
    		xmSgjdYlsgJdDO.setFcbz(1);
    		xmSgjdYlsgJdDO.setIntylsgid(xmSgjdYlsgDO.getId());
    		QueryWrapper<XmSgjdYlsgJdDO> queryWrapper=new QueryWrapper<XmSgjdYlsgJdDO>(xmSgjdYlsgJdDO).orderByAsc("id");
    		List<XmSgjdYlsgJdDO> list = xmSgjdYlsgJdService.list(queryWrapper);
    		xmSgjdYlsgDO.setXmSgjdYlsgJdList(list);
    	}
    	return xmSgjdYlsgDO;
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmSgjdYlsgDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * 获取施工进度-园林施工信息
     * @param xmid 项目id
     * @param gxrq 更新日期
     */
    @Override
    public List<XmSgjdYlsgDO> getXmSgjdYlsgListByXmidAndGxrq(Long xmid,Date gxrq){
    	if(xmid==null) {
    		throw new CommonException("项目id不能为空");
    	}
    	
    	if(gxrq==null) {
    		throw new CommonException("更新日期不能为空");
    	}
    	
    	XmSgjdYlsgDO xmSgjdYlsgDO=new XmSgjdYlsgDO();
    	xmSgjdYlsgDO.setFcbz(1);
    	xmSgjdYlsgDO.setIntxmid(xmid);
    	xmSgjdYlsgDO.setDtmgxrq(gxrq);
    	QueryWrapper<XmSgjdYlsgDO> queryWrapper=new QueryWrapper<XmSgjdYlsgDO>(xmSgjdYlsgDO).orderByDesc("gxsj");
    	List<XmSgjdYlsgDO> list=list(queryWrapper);
    	
		return list;
    }
    
    /**
	 * 保存施工进度-园林施工信息
	 * @param xmSgjdYlsgDO
	 * @param fileIds 文件ids
	 * @param ylsgJdJson 施工进度json 
	 * @param deleteYlsgjdIds 删除施工进度ids
	 */
	@Override
	public void saveXmSgjdYlsgXx(XmSgjdYlsgDO xmSgjdYlsgDO, String fileIds,String ylsgJdJson,String deleteYlsgjdIds) {
		Long xmid = xmSgjdYlsgDO.getIntxmid();
		if (xmid == null) {
			throw new CommonException("xmid不能为空");
		}
		Long id = xmSgjdYlsgDO.getId();
		if (id == null) {
			xmSgjdYlsgDO.setFcbz(1);
			xmSgjdYlsgDO.setGxsj(new Date());
			save(xmSgjdYlsgDO);
		} else {
			xmSgjdYlsgDO.setGxsj(new Date());
			updateById(xmSgjdYlsgDO);
		}

		id = xmSgjdYlsgDO.getId();

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
		
		//新增施工进度
		if(StringUtils.isNotEmpty(ylsgJdJson)&&!"[]".equals(ylsgJdJson)) {
			Gson gson=new Gson();
			List<XmSgjdYlsgJdDO> xmSgjdYlsgJdList=gson.fromJson(ylsgJdJson, new TypeToken<List<XmSgjdYlsgJdDO>>() {
			}.getType());
			if(CollectionUtils.isNotEmpty(xmSgjdYlsgJdList)) {
				xmSgjdYlsgJdList.forEach(xmSgjdYlsgJdDO->{
					//保存类型
					String chrlxmc = xmSgjdYlsgJdDO.getChrlxmc();
					if(StringUtils.isNotEmpty(chrlxmc)) {
						Long xmYlsgId=xmSgjdYlsgJdDO.getId();
						String wcqkFileIds=xmSgjdYlsgJdDO.getFileIds();
						if(xmYlsgId==null) {
							xmSgjdYlsgJdDO.setFcbz(1);
							xmSgjdYlsgJdDO.setGxsj(new Date());
							xmSgjdYlsgJdDO.setIntylsgid(xmSgjdYlsgDO.getId());
							xmSgjdYlsgJdService.save(xmSgjdYlsgJdDO);
						}else {
							xmSgjdYlsgJdDO.setGxsj(new Date());
							xmSgjdYlsgJdService.updateById(xmSgjdYlsgJdDO);
						}
						
						//更新附件信息
						// 更新文件信息
						if (StringUtils.isNotEmpty(wcqkFileIds)) {
							String[] fileArr = wcqkFileIds.trim().split(",");
							for (String fileid : fileArr) {
								if (StringUtils.isNotEmpty(fileid.trim())) {
									FileDO fileDO = new FileDO();
									fileDO.setId(Long.parseLong(fileid.trim()));
									fileDO.setBusType(XmSgjdYlsgJdServiceImpl.tableInfo.getTableName());
									fileDO.setBusId(xmSgjdYlsgJdDO.getId());
									fileService.updateById(fileDO);
								}
							}
						}
					}
				});
			}
		}
		
		//删除施工进度
		if(StringUtils.isNotEmpty(deleteYlsgjdIds)) {
			Arrays.stream(deleteYlsgjdIds.trim().split(",")).forEach(sgjdId->{
				XmSgjdYlsgJdDO xmSgjdYlsgJdDO=new XmSgjdYlsgJdDO();
				xmSgjdYlsgJdDO.setId(Long.parseLong(sgjdId));
				xmSgjdYlsgJdDO.setFcbz(0);
				xmSgjdYlsgJdDO.setGxsj(new Date());
				xmSgjdYlsgJdService.updateById(xmSgjdYlsgJdDO);
			});
		}
	}
	
}
