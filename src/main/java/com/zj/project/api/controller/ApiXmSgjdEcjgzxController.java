package com.zj.project.api.controller;


import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxDO;
import com.zj.project.xm.xmsgjd.ecjgzx.service.XmSgjdEcjgzxService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 施工进度-二次结构、装修等施工记录Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmsgjdecjgzx/")
@Api("施工进度-二次结构、装修等施工记录Api")
public class ApiXmSgjdEcjgzxController extends ApiBaseController {

    @Autowired
    private XmSgjdEcjgzxService xmSgjdEcjgzxService;

   @Log("根据xmid获取施工进度-二次结构、装修等施工记录集合")
    @PostMapping("getXmSgjdEcjgzxListByXmid")
    @ApiOperation(value="根据xmid获取施工进度-二次结构、装修等施工记录集合",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmSgjdEcjgzxDO>> getXmSgjdEcjgzxListByXmid(Long xmid) {
        try {
        	XmSgjdEcjgzxDO xmSgjdEcjgzxDO=new XmSgjdEcjgzxDO();
        	xmSgjdEcjgzxDO.setFcbz(1);
        	xmSgjdEcjgzxDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdEcjgzxDO> queryWrapper=new QueryWrapper<XmSgjdEcjgzxDO>(xmSgjdEcjgzxDO).orderByAsc("dtmgxrq");
            return Result.ok(xmSgjdEcjgzxService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmSgjdEcjgzxId获取施工进度-二次结构、装修等施工记录信息")
    @PostMapping("getXmSgjdEcjgzxById")
    @ApiOperation(value="根据xmSgjdEcjgzxId获取施工进度-二次结构、装修等施工记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmSgjdEcjgzxId",paramType="form",dataType = "Long",required=true,value = "施工记录id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdEcjgzxDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmSgjdEcjgzxDO.class)})
    @RequiresAuthentication
    public Result<XmSgjdEcjgzxDO> getXmSgjdEcjgzxById(Long xmSgjdEcjgzxId) {
        try {
            return Result.ok(xmSgjdEcjgzxService.getById(xmSgjdEcjgzxId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }


    @Log("保存施工进度-二次结构、装修等施工记录信息")
    @PostMapping("save")
    @ApiOperation(value="保存施工进度-二次结构、装修等施工记录信息",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="xmSgjdEcjgzxWclJson",paramType="form",dataType = "string",required=false,value = "施工完成量对象json串"),
            @ApiImplicitParam(name="deleteWclIds",paramType="form",dataType = "string",required=false,value = "删除的完成量ids")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result<XmSgjdEcjgzxDO> save(XmSgjdEcjgzxDO xmSgjdEcjgzxDO, String fileIds,String xmSgjdEcjgzxWclJson,String deleteWclIds) {
        try {
        	xmSgjdEcjgzxService.saveXmSgjdEcjgzxxx(xmSgjdEcjgzxDO,xmSgjdEcjgzxWclJson,deleteWclIds);
           return Result.ok(xmSgjdEcjgzxDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }
    
    @Log("根据xmSgjdEcjgzxId获取施工进度-二次结构、装修等施工记录信息")
    @PostMapping("getXmSgjdEcjgzxByParam")
    @ApiOperation(value="根据xmSgjdEcjgzxId获取施工进度-二次结构、装修等施工记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmSgjdEcjgzxId",paramType="form",dataType = "Long",required=false,value = "施工记录id"),
    	@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=false,value = "项目id"),
    	@ApiImplicitParam(name="did",paramType="form",dataType = "Long",required=false,value = "栋id"),
    	@ApiImplicitParam(name="fwlx",paramType="form",dataType = "Long",required=false,value = "发起类型 xz-新增  cx-查询")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdEcjgzxDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmSgjdEcjgzxDO.class)})
    @RequiresAuthentication
    public Result<XmSgjdEcjgzxDO> getXmSgjdEcjgzxByParam(Long xmSgjdEcjgzxId,Long xmid,Long did,String fwlx) {
        try {
            return Result.ok(xmSgjdEcjgzxService.getXmSgjdEcjgzxByParam(xmSgjdEcjgzxId,xmid,did,fwlx));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

}
