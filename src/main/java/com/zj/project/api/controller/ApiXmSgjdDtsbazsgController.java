package com.zj.project.api.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmsgjd.dtsbazsg.domain.XmSgjdDtsbazsgDO;
import com.zj.project.xm.xmsgjd.dtsbazsg.service.XmSgjdDtsbazsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 施工进度-电梯设备安装施工Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmsgjddtsbazsg/")
@Api("施工进度-电梯设备安装施工Api")
public class ApiXmSgjdDtsbazsgController extends ApiBaseController {

    @Autowired
    private XmSgjdDtsbazsgService xmSgjdDtsbazsgService;

    @Log("根据xmid获取电梯设备安装施工信息")
    @PostMapping("getXmSgjdDtsbazsgListByXmidAndSgwz")
    @ApiOperation(value="根据xmid获取电梯设备安装施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	@ApiImplicitParam(name="sgwz",paramType="form",dataType = "Long",required=false,value = "施工位置id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmSgjdDtsbazsgDO>> getXmSgjdDtsbazsgListByXmidAndSgwz(Long xmid,Long sgwz) {
        try {
        	if(xmid==null) {
       		 Result.ok(Lists.newArrayList());
        	}
        	XmSgjdDtsbazsgDO queryOne=new XmSgjdDtsbazsgDO();
        	queryOne.setFcbz(1);
        	queryOne.setIntxmid(xmid);
        	queryOne.setIntsgwz(sgwz);
        	QueryWrapper<XmSgjdDtsbazsgDO> queryWrapperOne=new QueryWrapper<XmSgjdDtsbazsgDO>(queryOne).select("max(id) as id")
        			.groupBy("chrdtbh","intsgwz");
        	
        	List<XmSgjdDtsbazsgDO> list = xmSgjdDtsbazsgService.list(queryWrapperOne);
        	if(CollectionUtils.isEmpty(list)) {
        		Result.ok(Lists.newArrayList());
        	}
        	
        	XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO=new XmSgjdDtsbazsgDO();
        	xmSgjdDtsbazsgDO.setFcbz(1);
        	xmSgjdDtsbazsgDO.setIntxmid(xmid);
        	xmSgjdDtsbazsgDO.setIntsgwz(sgwz);
        	QueryWrapper<XmSgjdDtsbazsgDO> queryWrapper=new QueryWrapper<XmSgjdDtsbazsgDO>(xmSgjdDtsbazsgDO)
        			.in("id", list.stream().map(one->one.getId()).collect(Collectors.toList())).orderByAsc("dtmgxrq");
            return Result.ok(xmSgjdDtsbazsgService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmSgjdDtsbazsgId获取电梯设备安装施工信息")
    @PostMapping("getXmSgjdDtsbazsgById")
    @ApiOperation(value="根据xmSgjdDtsbazsgId获取电梯设备安装施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmSgjdDtsbazsgId",paramType="form",dataType = "Long",required=true,value = "项目施工电梯设备安装信息id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdDtsbazsgDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmSgjdDtsbazsgDO.class)})
    @RequiresAuthentication
    public Result<XmSgjdDtsbazsgDO> getXmSgjdDtsbazsgById(Long xmSgjdDtsbazsgId) {
        try {
            return Result.ok(xmSgjdDtsbazsgService.getById(xmSgjdDtsbazsgId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("保存电梯设备安装施工信息")
    @PostMapping("save")
    @ApiOperation(value="保存电梯设备安装施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "文件ids，多个以逗号隔开")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result<XmSgjdDtsbazsgDO> save(XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO, String fileIds) {
        try {
        	xmSgjdDtsbazsgService.saveXmSgjdDtsbazsgXx(xmSgjdDtsbazsgDO,fileIds);
           return Result.ok(xmSgjdDtsbazsgDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }
    
    @Log("根据xmSgjdEcjgzxId获取电梯设置安装施工记录信息")
    @PostMapping("getXmSgjdDtsbazsgByParam")
    @ApiOperation(value="根据xmSgjdEcjgzxId获取电梯设置安装施工记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmSgjdDtsbazsgId",paramType="form",dataType = "Long",required=false,value = "电梯安装记录id"),
    	@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=false,value = "项目id"),
    	@ApiImplicitParam(name="sgwz",paramType="form",dataType = "Long",required=false,value = "施工位置"),
    	@ApiImplicitParam(name="dtbh",paramType="form",dataType = "String",required=false,value = "电梯编号"),
    	@ApiImplicitParam(name="fwlx",paramType="form",dataType = "Long",required=false,value = "发起类型 xz-新增  cx-查询")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdDtsbazsgDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmSgjdDtsbazsgDO.class)})
    @RequiresAuthentication
    public Result<XmSgjdDtsbazsgDO> getXmSgjdDtsbazsgByParam(Long xmSgjdDtsbazsgId,Long xmid,Long sgwz,String dtbh,String fwlx) {
        try {
            return Result.ok(xmSgjdDtsbazsgService.getXmSgjdDtsbazsgByParam(xmSgjdDtsbazsgId,xmid,sgwz,dtbh,fwlx));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

}
