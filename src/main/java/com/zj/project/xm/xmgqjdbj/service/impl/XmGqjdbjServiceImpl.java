package com.zj.project.xm.xmgqjdbj.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import com.zj.project.xm.xmgqjdbj.dao.XmGqjdbjDao;
import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;

/**
 * 
 * <pre>
 * 项目基本信息-主要工期节点比较
 * </pre>
 * <small> 2018-10-24 21:18:21 | lijun</small>
 */
@Service
public class XmGqjdbjServiceImpl extends BaseServiceImpl<XmGqjdbjDao, XmGqjdbjDO> implements XmGqjdbjService {

    public static TableInfo tableInfo = null;

    static {
        tableInfo=TableInfoHelper.getTableInfo( XmGqjdbjDO.class);
    }

    @Override
    public boolean removeByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        Assert.notEmpty(parmMap,"过滤后条件参数为空：请检查传入参数是否和实体属性名一致");
        return super.removeByMap(parmMap);
    }

    @Override
    public Collection<XmGqjdbjDO> listByParmMap(Map<String, Object> parmMap) {
        parmMap=parmToColumnMap(tableInfo, parmMap);
        return listByMap(parmMap);
    }
    
    /**
     * <p>Title:批量保存项目工期节点信息 </p>  
     * <p>Description: </p> 
     * @param xmid 项目id
     * @param jdlx 节点类型
     * @param gqjdbjJson 工期对象json、
     * @param deleteGqjdbjIds 删除工期对象ids
     * @date:2018年10月24日 下午10:20:17
     */
    @Override
    public void saveBatchXmGqjdbjXx(Long xmid,String jdlx,String gqjdbjJson,String  deleteGqjdbjIds) {
    	
    	  if(xmid==null){
              throw  new MyApiException("44005");
          }
    	  
    	  if(StringUtils.isEmpty(jdlx)){
              throw  new MyApiException("节点类型不能空");
          }
    	  if(StringUtils.isNotEmpty(gqjdbjJson)&&!"[]".equals(gqjdbjJson)) {
    		  Gson gson=new Gson();
              List<XmGqjdbjDO> list = gson.fromJson(gqjdbjJson, new TypeToken<List<XmGqjdbjDO>>() {
              }.getType());
              
              //保存
              if(CollectionUtils.isNotEmpty(list)){
                  list.forEach(xmGqjdbjDO->{
                      Long id=xmGqjdbjDO.getId();
                      String chrjdmc=xmGqjdbjDO.getChrjdmc();
                      if(StringUtils.isNotEmpty(chrjdmc)) {
                      	 if(id==null){
                      		   xmGqjdbjDO.setFcbz(1);
                      		   xmGqjdbjDO.setGxsj(new Date());
                      		   xmGqjdbjDO.setIntxmid(xmid);
                      		   xmGqjdbjDO.setChrjdlx(jdlx);
                               save(xmGqjdbjDO);
                           }else{
                        	   xmGqjdbjDO.setGxsj(new Date());
                               updateById(xmGqjdbjDO);
                           }
                      	   Long parentId= xmGqjdbjDO.getId();
                      	 List<XmGqjdbjDO> childList = xmGqjdbjDO.getChildList();
                      	 if(CollectionUtils.isNotEmpty(childList)) {
                      		childList.forEach(one->{
                      			 String nowchrjdmc=one.getChrjdmc();
                      			 if(StringUtils.isNotEmpty(nowchrjdmc)) {
                      				 Long nowId=one.getId();
                      				if(nowId==null){
                      					one.setFcbz(1);
                      					one.setGxsj(new Date());
                      					one.setIntxmid(xmid);
                      					one.setChrjdlx(jdlx);
                      					one.setIntfjdid(parentId);
                                        save(one);
                                    }else{
                                    	one.setGxsj(new Date());
                                        updateById(one);
                                    }
                      			 }
                      		});
                      	 }
                      }
                  });
              }
    	  }
          
          //删除
          if(StringUtils.isNotEmpty(deleteGqjdbjIds)) {
          	Arrays.stream(deleteGqjdbjIds.trim().split(",")).forEach(gqjdbjId->{
          		if(StringUtils.isNotEmpty(gqjdbjId)) {
          			 deleteGqjdbjById(Long.parseLong(gqjdbjId));
          		}
          	});
          }
    }
    
    /**
     * <p>Title: 删除节点类型</p>  
     * <p>Description: </p> 
     * @param gqjdbjId
     * @author zhujujun
     * @date:2018年10月25日 下午9:09:22
     */
    @Override
    public void  deleteGqjdbjById(Long gqjdbjId) {
    	 if(gqjdbjId==null) {
    		 return;
    	 }
    	 XmGqjdbjDO xmGqjdbjDO=new XmGqjdbjDO();
			xmGqjdbjDO.setId(gqjdbjId);
			xmGqjdbjDO.setFcbz(0);
			xmGqjdbjDO.setGxsj(new Date());
			updateById(xmGqjdbjDO);
			//删除子节点的
			
			XmGqjdbjDO updateWhere=new XmGqjdbjDO();
			updateWhere.setFcbz(1);
			updateWhere.setIntfjdid(gqjdbjId);
			UpdateWrapper<XmGqjdbjDO> updateWrapper=new UpdateWrapper<XmGqjdbjDO>(updateWhere);
			
			XmGqjdbjDO updateDo=new XmGqjdbjDO();
			updateDo.setFcbz(0);
			updateDo.setGxsj(new Date());
			update(updateDo, updateWrapper);
    }
    
    /**
     * <p>Title:获取主体施工信息 </p>  
     * <p>Description: </p> 
     * @param gqjdbjid
     * @param xmid
     * @param jdlx
     * @return
     * @author zhujujun
     * @date:2018年11月6日 上午1:07:50
     */
    @Override
    public List<XmGqjdbjDO> getXmGqjdbjZtList(Long gqjdbjid,Long xmid,String jdlx) {

  	    if(xmid==null){
            throw  new MyApiException("44005");
        }
  	    
  	    XmGqjdbjDO jcXmGqjdbjDO=new XmGqjdbjDO();
  	    			jcXmGqjdbjDO.setIntxmid(xmid);
  	    			jcXmGqjdbjDO.setFcbz(1);
  	    			jcXmGqjdbjDO.setChrjdlx("jc");
  	    			if(gqjdbjid!=null) {
  	    				jcXmGqjdbjDO.setId(gqjdbjid);
  	    			}
  	    QueryWrapper<XmGqjdbjDO> jcQueryWrapper=new QueryWrapper<XmGqjdbjDO>(jcXmGqjdbjDO).orderByAsc("intxh");
  	    List<XmGqjdbjDO> jcList=list(jcQueryWrapper);
  	    
  	    if(CollectionUtils.isNotEmpty(jcList)) {
  	        XmGqjdbjDO ztXmGqjdbjDO=new XmGqjdbjDO();
  	        ztXmGqjdbjDO.setIntxmid(xmid);
	  	    ztXmGqjdbjDO.setFcbz(1);
	  	    ztXmGqjdbjDO.setChrjdlx("zt");
			QueryWrapper<XmGqjdbjDO> ztQueryWrapper=new QueryWrapper<XmGqjdbjDO>(ztXmGqjdbjDO).select("id","intxmid","chrjdlx","intxh","intfjdid","chrjdmc",
        			"dtmjhwcsj","intsjbj"," ( SELECT a.wcsj FROM " + 
        					"(select k.dtmwcsj  wcsj,k.intxmid xmid,k.intgqjdbjid intgqjdbjid from (select max(id) id,intxmid,intsgwzd from bj_xm_sgjd_ztjgsg where  fcbz=1 and intsgwzd!=-1 and intsfwc=1   group by intxmid,intsgwzd) " + 
        					" z inner join bj_xm_sgjd_ztjgsg_kz k on z.id =k.intztsgid )  a WHERE  a.intgqjdbjid = id and a.xmid=intxmid ) dtmsjwcsj").orderByAsc("intxh");
			List<XmGqjdbjDO> ztList=list(ztQueryWrapper);
			jcList.forEach(one->{
				one.setChildList(ztList.stream().filter(two->two.getIntfjdid()!=null&&two.getIntfjdid().equals(one.getId())).collect(Collectors.toList()));
			});
			return jcList;
  	    }else {
  	    	return Lists.newArrayList();
  	    }
    }
    

}
