package com.zj.project.xm.xmzpjl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmzpjl.dao.XmZpjlDao;
import com.zj.project.xm.xmzpjl.domain.XmZpjlDO;
import com.zj.project.xm.xmzpjl.service.XmZpjlService;
import com.zj.project.xm.xmzpms.domain.XmZpmsDO;
import com.zj.project.xm.xmzpms.service.XmZpmsService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
                        xmZpmsService.save(xmZpmsDO);
                    }else{
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
    public Map<String,List<XmZpjlDO>> getXmZpjlMapByXmid(Long xmid){
        if(xmid==null){
            throw  new CommonException("xmid不能为空");
        }
        Map<String,List<XmZpjlDO>> map= Maps.newHashMap();
        //1（整体形象进度），
        XmZpjlDO xmZpjlDO=new XmZpjlDO();
        xmZpjlDO.setFcbz(1);
        xmZpjlDO.setIntxmid(xmid);
        xmZpjlDO.setIntbglb("1");

        QueryWrapper<XmZpjlDO> queryWrapper=new QueryWrapper<XmZpjlDO>(xmZpjlDO).orderByAsc("dtmbgrq");
        List<XmZpjlDO> list1=list(queryWrapper);
        if(CollectionUtils.isNotEmpty(list1)){
            list1.forEach(one->{
                String intbglb=one.getIntbglb();
                if(map.containsKey(intbglb)){
                    List<XmZpjlDO> add= map.get(intbglb);
                    add.add(one);
                    map.put(intbglb,add);
                }else{
                    List<XmZpjlDO> add= Lists.newArrayList();
                    add.add(one);
                    map.put(intbglb,add);
                }
            });
        }
        // 2（栋楼形象进度）
        xmZpjlDO.setIntbglb("2");
        queryWrapper=new QueryWrapper<XmZpjlDO>(xmZpjlDO).orderByAsc("chrpswz","dtmbgrq");
        List<XmZpjlDO> list2=list(queryWrapper);
        if(CollectionUtils.isNotEmpty(list2)){
            list2.forEach(one->{
                String intbglb=one.getIntbglb();
                if(map.containsKey(intbglb)){
                    List<XmZpjlDO> add= map.get(intbglb);
                    add.add(one);
                    map.put(intbglb,add);
                }else{
                    List<XmZpjlDO> add= Lists.newArrayList();
                    add.add(one);
                    map.put(intbglb,add);
                }
            });
        }

        // 3（隐蔽工程形象进度）
        xmZpjlDO.setIntbglb("3");
        queryWrapper=new QueryWrapper<XmZpjlDO>(xmZpjlDO).orderByAsc("chrpswz","dtmbgrq");
        List<XmZpjlDO> list3=list(queryWrapper);
        if(CollectionUtils.isNotEmpty(list3)){
            list3.forEach(one->{
                String intbglb=one.getIntbglb();
                if(map.containsKey(intbglb)){
                    List<XmZpjlDO> add= map.get(intbglb);
                    add.add(one);
                    map.put(intbglb,add);
                }else{
                    List<XmZpjlDO> add= Lists.newArrayList();
                    add.add(one);
                    map.put(intbglb,add);
                }
            });
        }

        return  map;
    }


}
