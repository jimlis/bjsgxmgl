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
import com.zj.project.xm.xmsgjd.ztjgsg.domain.XmSgjdZtjgsgDO;
import com.zj.project.xm.xmsgjd.ztjgsg.service.XmSgjdZtjgsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 施工进度-主体结构施工Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmsgjdztjgsg/")
@Api("施工进度-主体结构施工Api控制器")
public class ApiXmSgjdZtjgsgController extends ApiBaseController {

    @Autowired
    private XmSgjdZtjgsgService xmSgjdZtjgsgService;
    
   @Log("根据xmid获取主体结构施工信息")
    @PostMapping("getXmSgjdZtjgsgListByXmid")
    @ApiOperation(value="根据xmid获取主体结构施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmSgjdZtjgsgDO>> getXmSgjdZtjgsgListByXmid(Long xmid) {
        try {
        	XmSgjdZtjgsgDO xmSgjdZtjgsgDO=new XmSgjdZtjgsgDO();
        	xmSgjdZtjgsgDO.setFcbz(1);
        	xmSgjdZtjgsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdZtjgsgDO> queryWrapper=new QueryWrapper<XmSgjdZtjgsgDO>(xmSgjdZtjgsgDO).orderByAsc("dtmbgrq");
            return Result.ok(xmSgjdZtjgsgService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmSgjdZtjgsgId获取主体结构施工信息")
    @PostMapping("getXmSgjdJcsgById")
    @ApiOperation(value="根据xmSgjdZtjgsgId获取主体结构施工信息息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmSgjdZtjgsgId",paramType="form",dataType = "Long",required=true,value = "主体施工id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdZtjgsgDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmSgjdZtjgsgDO.class)})
    @RequiresAuthentication
    public Result<XmSgjdZtjgsgDO> getXmSgjdJcsgById(Long xmSgjdJcsgId) {
        try {
            return Result.ok(xmSgjdZtjgsgService.getById(xmSgjdJcsgId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("保存主体结构施工信息")
    @PostMapping("save")
    @ApiOperation(value="保存主体结构施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开"),
            @ApiImplicitParam(name="xmZpmsJson",paramType="form",dataType = "string",required=false,value = "照片对应描述对象json串")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result save(XmSgjdZtjgsgDO xmSgjdZtjgsgDO, String fileIds,String xmZpmsJson) {
        try {
        	xmSgjdZtjgsgService.saveXmSgjdZtjgsgXx(xmSgjdZtjgsgDO,fileIds,xmZpmsJson);
           return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
