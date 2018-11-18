package com.zj.project.api.controller;


import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 工期节点比较Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/gqjdbj/")
@Api("工期节点比较api")
public class ApiXmGqjdbjController extends ApiBaseController {
    @Autowired
    private XmGqjdbjService xmGqjdbjService;


    @Log("根据项目id和节点类型获取工期节点比较信息")
    @PostMapping("getXmGqjdbjList")
    @ApiOperation(value="根据项目id和节点类型获取工期节点比较信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="gqjdbjid",paramType="form",dataType = "Long",required=false,value = "工期节点比较id"),
    	@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	@ApiImplicitParam(name="jdlx",paramType="form",dataType = "string",required=true,value = "节点类型")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmGqjdbjDO>> getXmGqjdbjList(Long gqjdbjid,Long xmid,String jdlx) {
        try {
        	if(xmid==null) {
        		return Result.ok(Lists.newArrayList());
        	}
        	XmGqjdbjDO xmGqjdbjDO=new XmGqjdbjDO();
        	xmGqjdbjDO.setId(gqjdbjid);
        	xmGqjdbjDO.setFcbz(1);
        	xmGqjdbjDO.setChrjdlx(jdlx);
            QueryWrapper<XmGqjdbjDO> queryWrapper=new QueryWrapper<XmGqjdbjDO>(xmGqjdbjDO).eq("intxmid",xmid).orderByAsc("intxh");
            if("jc".equals(jdlx)) {
            	queryWrapper=queryWrapper.select("id","intxmid","chrjdlx","intxh","intfjdid","chrjdmc",
            			"dtmjhwcsj","intsjbj"," ( select a.wcsj  from ( 		select j.intsgwzid, j.intxmid as xmid,j.dtmwcrq as wcsj from (select intsgwzid, intxmid as xmid, max(id) as id from bj_xm_sgjd_jcsgnew " + 
            					"			where intsgwzid != -1	and intsfwc = 1	and fcbz = 1 group by intsgwzid, intxmid ) jj inner join bj_xm_sgjd_jcsgnew j on jj.id=j.id		 ) a  " + 
            			"		WHERE  a.intsgwzid = id and a.xmid=intxmid ) dtmsjwcsj ");
            }else if("zt".equals(jdlx)) {
            	queryWrapper=queryWrapper.select("id","intxmid","chrjdlx","intxh","intfjdid","chrjdmc",
            			"dtmjhwcsj","intsjbj"," ( SELECT a.wcsj FROM (SELECT	intsgwzd,intxmid as xmid,max(dtmwcrq) AS wcsj " + 
            					"		FROM	bj_xm_sgjd_ztjgsg WHERE intsgwzd !=- 1 AND intsfwc = 1 AND fcbz = 1 GROUP BY intsgwzd,	intxmid ) a WHERE  a.intsgwzd = id and a.xmid=intxmid ) dtmsjwcsj");
            }
            List<XmGqjdbjDO> list = xmGqjdbjService.list(queryWrapper);
            return Result.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("批量保存项目工期节点信息")
    @PostMapping("batchSave")
    @ApiOperation(value="批量保存项目工期节点信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	   @ApiImplicitParam(name="jdlx",paramType="form",dataType = "string",required=true,value = "节点类型"),
            @ApiImplicitParam(name="gqjdbjJson",paramType="form",dataType = "string",required=true,value = "项目工期节点比较对象数组json字符串"),
            @ApiImplicitParam(name="deleteGqjdbjIds",paramType="form",dataType = "string",required=false,value = "删除项目工期节点比较ids"),})
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result batchSave(Long xmid,String jdlx,String gqjdbjJson,String deleteGqjdbjIds) {
        try {
        	xmGqjdbjService.saveBatchXmGqjdbjXx(xmid,jdlx,gqjdbjJson,deleteGqjdbjIds);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }


    @Log("删除项目工期节点信息")
    @PostMapping("del/{id}")
    @ApiOperation(value="删除项目工期节点信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="id",paramType="path",dataType = "Long",required=true,value = "项目规划信息id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result del(@PathVariable("id") Long id) {
        try {
        	xmGqjdbjService.deleteGqjdbjById(id);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    
    @Log("根据项目id和节点类型等条件获取工期节点比较信息")
    @PostMapping("getXmGqjdbjListByParam")
    @ApiOperation(value="根据项目id和节点类型等条件获取工期节点比较信息",httpMethod="POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	@ApiImplicitParam(name="jdlx",paramType="form",dataType = "string",required=true,value = "节点类型"),
    	@ApiImplicitParam(name="isParent",paramType="form",dataType = "string",required=true,value = "jdlx为'jc','zt'时查询是否是父节点的数据 1-是 0-不是"),
    	@ApiImplicitParam(name="parentId",paramType="form",dataType = "Long",required=true,value = "父节点id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmGqjdbjDO> > getXmGqjdbjListByParam(Long xmid,String jdlx,String isParent,Long parentId) {
        try {
        	if(xmid==null) {
        		return Result.ok(Lists.newArrayList());
        	}
        	XmGqjdbjDO xmGqjdbjDO=new XmGqjdbjDO();
        	xmGqjdbjDO.setFcbz(1);
        	xmGqjdbjDO.setChrjdlx(jdlx);
        	QueryWrapper<XmGqjdbjDO> queryWrapper=new QueryWrapper<XmGqjdbjDO>(xmGqjdbjDO).eq("intxmid",xmid).orderByAsc("intxh");
        	if(("jc".equals(jdlx)||"zt".equals(jdlx))) {
        		if("0".equals(isParent)&&parentId!=null) {
        			xmGqjdbjDO.setIntfjdid(parentId);
        		}else if("1".equals(isParent)) {
        			queryWrapper=queryWrapper.isNull("intfjdid");
        		}else {
        			return Result.ok(Lists.newArrayList());
        		}
        	}
            List<XmGqjdbjDO> list = xmGqjdbjService.list(queryWrapper);
            return Result.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("根据项目id获取工期主体节点比较信息")
    @PostMapping("getXmGqjdbjZtList")
    @ApiOperation(value="根据项目id获取工期主体节点比较信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="gqjdbjid",paramType="form",dataType = "Long",required=false,value = "工期节点比较id"),
    	@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmGqjdbjDO>> getXmGqjdbjZtList(Long gqjdbjid,Long xmid) {
        try {
        	if(xmid==null) {
        		return Result.ok(Lists.newArrayList());
        	}
            List<XmGqjdbjDO> list = xmGqjdbjService.getXmGqjdbjZtList(gqjdbjid, xmid);
            return Result.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    
    @Log("根据项目id获取工期专项竣工节点比较信息")
    @PostMapping("getXmGqjdbjZxjgMapListByXmid")
    @ApiOperation(value="根据项目id获取工期专项竣工节点比较信息",httpMethod="POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<Map<String, List<XmGqjdbjDO>>> getXmGqjdbjZxjgMapListByXmid(Long xmid) {
        try {
        	if(xmid==null) {
        		return Result.ok(Maps.newHashMap());
        	}
              Map<String, List<XmGqjdbjDO>> maps = xmGqjdbjService.getXmGqjdbjZxjgMapListByXmid(xmid);
            return Result.ok(maps);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("批量保存项目专项竣工工期节点信息")
    @PostMapping("batchSaveXmZxjgGqjdbjXx")
    @ApiOperation(value="批量保存项目工期节点信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
            @ApiImplicitParam(name="gqjdbjJson",paramType="form",dataType = "string",required=true,value = "项目工期节点比较对象数组json字符串"),
            @ApiImplicitParam(name="deleteGqjdbjIds",paramType="form",dataType = "string",required=false,value = "删除项目工期节点比较ids"),})
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result batchSaveXmZxjgGqjdbjXx(Long xmid,String gqjdbjJson,String deleteGqjdbjIds) {
        try {
        	xmGqjdbjService.batchSaveXmZxjgGqjdbjXx(xmid,gqjdbjJson,deleteGqjdbjIds);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }




}
