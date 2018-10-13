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
import com.zj.project.xm.xmsgjd.jcsg.domain.XmSgjdJcsgDO;
import com.zj.project.xm.xmsgjd.jcsg.service.XmSgjdJcsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 施工进度-基础施工Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmsgjdjcsg/")
@Api("施工进度-基础施工api")
public class ApiXmSgjdJcsgController extends ApiBaseController {

    @Autowired
    private XmSgjdJcsgService  xmSgjdJcsgService;

   @Log("根据xmid获取基础施工信息")
    @PostMapping("getXmSgjdJcsgListByXmid")
    @ApiOperation(value="根据xmid获取基础施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmSgjdJcsgDO>> getXmSgjdJcsgListByXmid(Long xmid) {
        try {
        	XmSgjdJcsgDO xmSgjdJcsgDO=new XmSgjdJcsgDO();
        	xmSgjdJcsgDO.setFcbz(1);
        	xmSgjdJcsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdJcsgDO> queryWrapper=new QueryWrapper<XmSgjdJcsgDO>(xmSgjdJcsgDO).orderByAsc("dtmbgrq");
            return Result.ok(xmSgjdJcsgService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmSgjdJcsgId获取基础施工信息")
    @PostMapping("getXmSgjdJcsgById")
    @ApiOperation(value="根据xmSgjdJcsgId获取基础施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmSgjdJcsgId",paramType="form",dataType = "Long",required=true,value = "基础施工id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdJcsgDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmSgjdJcsgDO.class)})
    @RequiresAuthentication
    public Result<XmSgjdJcsgDO> getXmSgjdJcsgById(Long xmSgjdJcsgId) {
        try {
            return Result.ok(xmSgjdJcsgService.getById(xmSgjdJcsgId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("保存基础施工信息")
    @PostMapping("save")
    @ApiOperation(value="保存基础施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开"),
            @ApiImplicitParam(name="xmZpmsJson",paramType="form",dataType = "string",required=false,value = "照片对应描述对象json串")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result save(XmSgjdJcsgDO xmSgjdJcsgDO, String fileIds,String xmZpmsJson) {
        try {
        	xmSgjdJcsgService.saveXmSgjdJcsgXx(xmSgjdJcsgDO,fileIds,xmZpmsJson);
           return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
