package com.zj.project.api.controller;


import java.util.List;

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
import com.zj.project.xm.xmsgjd.jcsg.domain.XmSgjdJcsgDO;
import com.zj.project.xm.xmsgjd.jcsg.service.XmSgjdJcsgService;
import com.zj.project.xm.xmsgjd.sgjdjcsgnew.domain.XmSgjdJcsgnewDO;
import com.zj.project.xm.xmsgjd.sgjdjcsgnew.service.XmSgjdJcsgnewService;

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
    
    @Autowired
    private XmSgjdJcsgnewService xmSgjdJcsgnewService;

   @Log("根据xmid获取基础施工信息")
    @PostMapping("getXmSgjdJcsgListByXmid")
    @ApiOperation(value="根据xmid获取基础施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmSgjdJcsgDO>> getXmSgjdJcsgListByXmid(Long xmid) {
        try {
        	if(xmid==null) {
        		return Result.ok(Lists.newArrayList());
        	}
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
   
   @Log("根据xmid获取其他基础施工信息")
   @PostMapping("getXmSgjdJcsgQtListByXmid")
   @ApiOperation(value="根据xmid获取基础施工信息",httpMethod="POST")
   @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
   @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
   	@ApiResponse(code=1,message="操作失败",response=List.class)})
   @RequiresAuthentication
   public Result<List<XmSgjdJcsgnewDO>> getXmSgjdJcsgQtListByXmid(Long xmid) {
       try {
    	if(xmid==null) {
       		return Result.ok(Lists.newArrayList());
       	}
    	XmSgjdJcsgnewDO xmSgjdJcsgnewDO=new XmSgjdJcsgnewDO();
    	xmSgjdJcsgnewDO.setFcbz(1);
    	xmSgjdJcsgnewDO.setIntxmid(xmid);
    	xmSgjdJcsgnewDO.setIntsgwzid(-1L);
       	QueryWrapper<XmSgjdJcsgnewDO> queryWrapper=new QueryWrapper<XmSgjdJcsgnewDO>(xmSgjdJcsgnewDO).orderByAsc("dtmbgrq");
           return Result.ok(xmSgjdJcsgnewService.list(queryWrapper));
       }catch (Exception e){
           e.printStackTrace();
           return Result.fail();
       }
   }
   
   @Log("根据xmid和施sgwzid获取基础施工信息")
   @PostMapping("getXmSgjdJcsgListByXmidAndSgwzid")
   @ApiOperation(value="根据xmid和施sgwzid获取基础施工信息",httpMethod="POST")
   @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
	   @ApiImplicitParam(name="sgwzid",paramType="form",dataType = "Long",required=true,value = "施工位置id"),
 	   @ApiImplicitParam(name="fwlx",paramType="form",dataType = "Long",required=false,value = "访问类型 xz---新增 查询-cx"),
 	   @ApiImplicitParam(name="id",paramType="form",dataType = "Long",required=false,value = "主键id")})
   @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdJcsgnewDO.class),
   	@ApiResponse(code=1,message="操作失败",response=XmSgjdJcsgnewDO.class)})
   @RequiresAuthentication
   public Result<XmSgjdJcsgnewDO> getXmSgjdJcsgListByXmid(Long xmid,Long sgwzid,String fwlx,Long id) {
       try {
           return Result.ok(xmSgjdJcsgnewService.getXmSgjdJcsgnewByXmidAndSgwzid(xmid, sgwzid,fwlx,id));
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
    public Result<XmSgjdJcsgDO> save(XmSgjdJcsgDO xmSgjdJcsgDO, String fileIds,String xmZpmsJson) {
        try {
        	xmSgjdJcsgService.saveXmSgjdJcsgXx(xmSgjdJcsgDO,fileIds,xmZpmsJson);
           return Result.ok(xmSgjdJcsgDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }
    
    @Log("保存新的基础施工信息")
    @PostMapping("newsave")
    @ApiOperation(value="保存新的基础施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开"),
    	@ApiImplicitParam(name="dljcsJson",paramType="form",dataType = "string",required=false,value = " 独立基础json"),
            @ApiImplicitParam(name="zjcsJson",paramType="form",dataType = "string",required=false,value = "独立基础json")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdJcsgnewDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmSgjdJcsgnewDO.class)})
    @RequiresAuthentication
    public Result<XmSgjdJcsgnewDO> newsave(XmSgjdJcsgnewDO xmSgjdJcsgnewDO,String fileIds, String dljcsJson,String zjcsJson) {
        try {
        	xmSgjdJcsgnewService.saveXmSgjdJcsgnewXx(xmSgjdJcsgnewDO,fileIds, dljcsJson, zjcsJson);
           return Result.ok(xmSgjdJcsgnewDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
