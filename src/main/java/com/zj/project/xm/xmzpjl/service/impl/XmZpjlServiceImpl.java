package com.zj.project.xm.xmzpjl.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.util.MyStringUtils;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmdl.domain.XmDlDO;
import com.zj.project.xm.xmdl.service.XmDlService;
import com.zj.project.xm.xmzpjl.dao.XmZpjlDao;
import com.zj.project.xm.xmzpjl.domain.XmZpjlDO;
import com.zj.project.xm.xmzpjl.service.XmZpjlService;
import com.zj.project.xm.xmzpms.domain.XmZpmsDO;
import com.zj.project.xm.xmzpms.service.XmZpmsService;

/**
 * 
 * <pre>
 * 项目基本信息-照片记录
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
@Service
public class XmZpjlServiceImpl extends BaseServiceImpl<XmZpjlDao, XmZpjlDO> implements XmZpjlService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmZpjlDO.class);
    }

    @Autowired
    private FileService fileService;

    @Autowired
    private XmZpmsService xmZpmsService;
    
    @Autowired
    private XmDlService xmDlService;
    
    @Override
    public boolean updateById(XmZpjlDO entity) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
    	UpdateWrapper<XmZpjlDO> updateWrapper=new UpdateWrapper<XmZpjlDO>().eq("id", entity.getId());
    	fieldList.forEach(filed->{
    		if(filed.isCharSequence()&&FieldStrategy.NOT_EMPTY==filed.getFieldStrategy()) {
    			String value = (String)ReflectionKit.getMethodValue(entity, filed.getProperty());
        		updateWrapper.set(MyStringUtils.isEmptyString(value),filed.getColumn(),value);
    		}
    	});
    	return update(entity, updateWrapper);
    }
    
    @Override
    public XmZpjlDO getById(Serializable id) {
    	XmZpjlDO xmZpjlDO=super.getById(id);
    	if(xmZpjlDO!=null) {
    		
    		//设置报告类别名称
    		Integer intbglb = xmZpjlDO.getIntbglb();
    		String chrpswz = xmZpjlDO.getChrpswz();
    		String chrbglb="";
    		if(intbglb!=null) {
    			if(intbglb.equals(1)) {
    				chrbglb="整体形象进度";
    				xmZpjlDO.setChrpswzms(xmZpjlDO.getChrpswz());
    			}else if(intbglb.equals(2)) {
    				chrbglb="栋楼形象进度";
    				//设置拍摄地址描述
    				if(StringUtils.isNotEmpty(chrpswz)) {
    					XmDlDO xmDlDO = xmDlService.getById(Long.parseLong(chrpswz));
    					if(xmDlDO!=null) {
    						xmZpjlDO.setChrpswzms(xmDlDO.getChrdlmc());
    					}
    				}
    			}else if(intbglb.equals(3)) {
    				chrbglb="隐蔽工程形象进度";
    				String chrpswzms="";
    				if(StringUtils.isNotEmpty(chrpswz)) {
    					chrpswzms=getChrPswzms(chrpswz);
    					xmZpjlDO.setChrpswzms(chrpswzms);
    				}
    			}
    			xmZpjlDO.setChrbglb(chrbglb);
    		}
    		
    	}
    	return xmZpjlDO;
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmZpjlDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }

    /**
     * 保存项目照片记录信息
     * @param xmZpjlDO 照片记录信息
     * @param fileIds 图片ids 多个以逗号隔开
     * @param xmZpmsJson 照片对应描述对象json串
     */
    @Override
    public void saveXmZpjlxx(XmZpjlDO xmZpjlDO, String fileIds, String xmZpmsJson){
                Long xmid=xmZpjlDO.getIntxmid();
                if(xmid==null){
                    throw  new CommonException("xmid不能为空");
                }
                Long id=xmZpjlDO.getId();
                if(id==null){
                    xmZpjlDO.setFcbz(1);
                    xmZpjlDO.setGxsj(new Date());
                    save(xmZpjlDO);
                }else{
                    xmZpjlDO.setGxsj(new Date());
                    updateById(xmZpjlDO);
                }

                id=xmZpjlDO.getId();

            //更新图片信息
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

           //保存照片描述信息
        if (StringUtils.isNotEmpty(xmZpmsJson)&&!"[]".equals(xmZpmsJson)) {
            Gson gson=new Gson();
            List<XmZpmsDO> list=gson.fromJson(xmZpmsJson,new TypeToken<List<XmZpmsDO>>(){}.getType());
            if(CollectionUtils.isNotEmpty(list)){
                list.forEach(xmZpmsDO->{
                    Long xmZpmsId = xmZpmsDO.getId();
                    if(xmZpmsId==null){
                        xmZpmsDO.setFcbz(1);
                        xmZpmsDO.setGxsj(new Date());
                        xmZpmsDO.setIntxmid(xmid);
                        xmZpmsDO.setIntzpssid(xmZpjlDO.getId());
                        xmZpmsService.save(xmZpmsDO);
                    }else{
                    	xmZpmsDO.setGxsj(new Date());
                        xmZpmsService.updateById(xmZpmsDO);
                    }
                });
            }
        }

    }

    /**
     * 根据项目id 获取项目照片map集合
     * @param xmid 项目id
     * @return
     */
    @Override
    public Map<String,Object> getXmZpjlMapByXmid(Long xmid){
        if(xmid==null){
            throw  new CommonException("xmid不能为空");
        }
        XmDlDO xmDlDO=new XmDlDO();
        xmDlDO.setIntxmid(xmid);
        xmDlDO.setFcbz(1);
        QueryWrapper<XmDlDO>  xmDlQuery=new QueryWrapper<XmDlDO>(xmDlDO).orderByAsc("intxh","id");
        List<XmDlDO> list = xmDlService.list(xmDlQuery);
        Map<String, String> xmDlMap=Maps.newLinkedHashMap();
        if(CollectionUtils.isNotEmpty(list)) {
        	Map<String,String> collect = list.stream().collect(Collectors.toMap(one->Objects.toString(one.getId(), ""),one->one.getChrdlmc()));
        	xmDlMap.putAll(collect);
        }
        
        Map<String,Object> map= Maps.newHashMap();
        //1（整体形象进度），
        XmZpjlDO xmZpjlDO=new XmZpjlDO();
        xmZpjlDO.setFcbz(1);
        xmZpjlDO.setIntxmid(xmid);
        xmZpjlDO.setIntbglb(1);

        QueryWrapper<XmZpjlDO> queryWrapper=new QueryWrapper<XmZpjlDO>(xmZpjlDO).orderByDesc("dtmbgrq");
        List<XmZpjlDO> list1=list(queryWrapper);
        if(CollectionUtils.isNotEmpty(list1)){
            list1.forEach(one->{
                Integer intbglb=one.getIntbglb();
                if(map.containsKey(Objects.toString(intbglb, ""))){
                    List<XmZpjlDO> add=(List<XmZpjlDO>)map.get(Objects.toString(intbglb, ""));
                    add.add(one);
                    map.put(Objects.toString(intbglb, ""),add);
                }else{
                    List<XmZpjlDO> add= Lists.newArrayList();
                    add.add(one);
                    map.put(Objects.toString(intbglb, ""),add);
                }
            });
        }
        // 2（栋楼形象进度）
        xmZpjlDO.setIntbglb(2);
        queryWrapper=new QueryWrapper<XmZpjlDO>(xmZpjlDO).orderByAsc("chrpswz","dtmbgrq");
        List<XmZpjlDO> list2=list(queryWrapper);
        if(CollectionUtils.isNotEmpty(list2)){
        	Map<String,List<XmZpjlDO>> dlMap=Maps.newLinkedHashMap();
            list2.forEach(one->{
            	String chrpswz=Objects.toString(one.getChrpswz(), "");
            	one.setChrpswzms(xmDlMap.get(chrpswz));
                if(dlMap.containsKey(chrpswz)){
                    List<XmZpjlDO> add= dlMap.get(chrpswz);
                    add.add(one);
                    dlMap.put(chrpswz,add);
                }else{
                    List<XmZpjlDO> add= Lists.newArrayList();
                    add.add(one);
                    dlMap.put(chrpswz,add);
                }
            });
            Set<String> keySet = dlMap.keySet();
            Map<String,List<XmZpjlDO>> newDlMap=Maps.newLinkedHashMap();
            keySet.forEach(key->{
            	newDlMap.put(xmDlMap.get(key), dlMap.get(key));
            });
            map.put("2", dlMap);
        }

        // 3（隐蔽工程形象进度）
        xmZpjlDO.setIntbglb(3);
        queryWrapper=new QueryWrapper<XmZpjlDO>(xmZpjlDO).orderByAsc("chrpswz","dtmbgrq");
        List<XmZpjlDO> list3=list(queryWrapper);
        if(CollectionUtils.isNotEmpty(list3)){
        	Map<String,List<XmZpjlDO>> ycgcMap=Maps.newLinkedHashMap();
            list3.forEach(one->{
            	String chrpswz = Objects.toString(one.getChrpswz(), "");
            	String chrpswzms="";
				if(StringUtils.isNotEmpty(chrpswz)) {
					chrpswzms=getChrPswzms(chrpswz);
					one.setChrpswzms(chrpswzms);
				}
                if(ycgcMap.containsKey(chrpswzms)){
                    List<XmZpjlDO> add= ycgcMap.get(chrpswzms);
                    add.add(one);
                    ycgcMap.put(chrpswzms,add);
                }else{
                    List<XmZpjlDO> add= Lists.newArrayList();
                    add.add(one);
                    ycgcMap.put(chrpswzms,add);
                }
            });
            map.put("3", ycgcMap);
        }

        return  map;
    }
    
    private String getChrPswzms(String chrpswz) {
    	String chrpswzms="";
    	if("1".equals(chrpswz)) {
			chrpswzms="土建";
		}else if("2".equals(chrpswz)) {
			chrpswzms="机电";
		}else if("3".equals(chrpswz)) {
			chrpswzms="装饰";
		}else if("4".equals(chrpswz)) {
			chrpswzms="装修";
		}else if("5".equals(chrpswz)) {
			chrpswzms="园林";
		}else if("6".equals(chrpswz)) {
			chrpswzms="其他";
		}
    	return chrpswzms;
    }


}
