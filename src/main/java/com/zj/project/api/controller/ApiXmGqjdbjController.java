package com.zj.project.api.controller;


import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
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
    public Result<List<XmGqjdbjDO> > getXmGqjdbjList(Long gqjdbjid,Long xmid,String jdlx) {
        try {
        	if(xmid==null) {
        		return Result.ok(Lists.newArrayList());
        	}
        	XmGqjdbjDO xmGqjdbjDO=new XmGqjdbjDO();
        	xmGqjdbjDO.setId(gqjdbjid);
        	xmGqjdbjDO.setFcbz(1);
        	xmGqjdbjDO.setChrjdlx(jdlx);
            QueryWrapper<XmGqjdbjDO> queryWrapper=new QueryWrapper<XmGqjdbjDO>(xmGqjdbjDO).eq("intxmid",xmid).orderByAsc("intxh");
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




}