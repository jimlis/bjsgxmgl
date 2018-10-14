package com.zj.project.api.controller;


import java.util.Date;
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
import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgDO;
import com.zj.project.xm.xmsgjd.ylsg.service.XmSgjdYlsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 施工进度-园林施工Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmsgjdylsg/")
@Api("施工进度-园林施工Api")
public class ApiXmSgjdYlsgController extends ApiBaseController {

    @Autowired
    private XmSgjdYlsgService xmSgjdYlsgService;

    @Log("根据xmid获取园林施工日期信息")
    @PostMapping("getXmSgjdYlsgDateListByXmid")
    @ApiOperation(value="根据xmid获取园林施工日期信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmSgjdYlsgDO>> getXmSgjdYlsgDateListByXmid(Long xmid) {
        try {
        	XmSgjdYlsgDO xmSgjdYlsgDO=new XmSgjdYlsgDO();
        	xmSgjdYlsgDO.setFcbz(1);
        	xmSgjdYlsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdYlsgDO> queryWrapper=new QueryWrapper<XmSgjdYlsgDO>(xmSgjdYlsgDO).select(" distinct intxmid"," dtmgxrq ") .orderByAsc("dtmgxrq");
            return Result.ok(xmSgjdYlsgService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("根据xmid和gxrq获取园林施工信息")
    @PostMapping("getXmSgjdYlsgListByXmidAndGxrq")
    @ApiOperation(value="根据xmid和gxrq获取园林施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	@ApiImplicitParam(name="gxrq",paramType="form",dataType = "string",required=true,value = "更新日期",example="2018-10-12")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmSgjdYlsgDO>> getXmSgjdYlsgListByXmidAndGxrq(Long xmid,Date gxrq) {
        try {
            return Result.ok(xmSgjdYlsgService.getXmSgjdYlsgListByXmidAndGxrq(xmid,gxrq));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("保存园林施工信息")
    @PostMapping("save")
    @ApiOperation(value="保存园林施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "文件ids，多个以逗号隔开")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result save(XmSgjdYlsgDO xmSgjdYlsgDO, String fileIds) {
        try {
        	xmSgjdYlsgService.saveXmSgjdYlsgXx(xmSgjdYlsgDO,fileIds);
           return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
