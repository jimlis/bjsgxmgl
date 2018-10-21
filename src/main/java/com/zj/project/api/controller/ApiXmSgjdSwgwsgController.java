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
import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 施工进度-室外管网施工Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmsgjdswgwsg/")
@Api("施工进度-室外管网施工Api")
public class ApiXmSgjdSwgwsgController extends ApiBaseController {

    @Autowired
    private XmSgjdSwgwsgService  xmSgjdSwgwsgService;

    @Log("根据xmid获取室外管网施工日期信息")
    @PostMapping("getXmSgjdSwgwsgDateListByXmid")
    @ApiOperation(value="根据xmid获取室外管网施工日期信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmSgjdSwgwsgDO>> getXmSgjdSwgwsgDateListByXmid(Long xmid) {
        try {
        	XmSgjdSwgwsgDO xmSgjdSwgwsgDO=new XmSgjdSwgwsgDO();
        	xmSgjdSwgwsgDO.setFcbz(1);
        	xmSgjdSwgwsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdSwgwsgDO> queryWrapper=new QueryWrapper<XmSgjdSwgwsgDO>(xmSgjdSwgwsgDO).select(" distinct intxmid"," dtmgxrq ") .orderByAsc("dtmgxrq");
            return Result.ok(xmSgjdSwgwsgService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("根据xmid和gxrq获取室外管网施工信息")
    @PostMapping("getXmSgjdSwgwsgListByXmidAndGxrq")
    @ApiOperation(value="根据xmid和gxrq获取室外管网施工信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	@ApiImplicitParam(name="gxrq",paramType="form",dataType = "string",required=true,value = "更新日期",example="2018-10-12")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmSgjdSwgwsgDO>> getXmSgjdSwgwsgListByXmidAndGxrq(Long xmid,Date gxrq) {
        try {
            return Result.ok(xmSgjdSwgwsgService.getXmSgjdYlsgListByXmidAndGxrq(xmid,gxrq));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("保存室外管网施工")
    @PostMapping("save")
    @ApiOperation(value="保存室外管网施工",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=false,value = "文件ids，多个以逗号隔开"),
    	@ApiImplicitParam(name="sglxAndJdJson",paramType="form",dataType = "string",required=false,value = "类型区域json串"),
	@ApiImplicitParam(name="deleteSwgwlxIds",paramType="form",dataType = "string",required=false,value = "删除的项目室外管网施工类型ids"),
	@ApiImplicitParam(name="deleteSwgwjdIds",paramType="form",dataType = "string",required=false,value = "删除的项目室外管网施工进度ids")
    	})
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result<XmSgjdSwgwsgDO> save(XmSgjdSwgwsgDO xmSgjdSwgwsgDO, String fileIds,String sglxAndJdJson,String deleteSwgwlxIds,String deleteSwgwjdIds) {
        try {
        	xmSgjdSwgwsgService.saveXmSgjdSwgwsgXx(xmSgjdSwgwsgDO,fileIds,sglxAndJdJson,deleteSwgwlxIds,deleteSwgwjdIds);
           return Result.ok(xmSgjdSwgwsgDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
