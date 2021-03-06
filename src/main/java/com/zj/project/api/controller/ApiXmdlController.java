package com.zj.project.api.controller;


import java.util.List;
import java.util.Map;

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
import com.zj.project.xm.xmdl.domain.XmDlDO;
import com.zj.project.xm.xmdl.service.XmDlService;
import com.zj.project.xm.xmdlcs.domain.XmDlCsDO;
import com.zj.project.xm.xmdlcs.service.XmDlCsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 项目栋楼Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmdl/")
@Api("项目栋楼api")
public class ApiXmdlController extends ApiBaseController {

    @Autowired
    private XmDlService xmDlService;
    
    @Autowired
    private XmDlCsService xmDlCsService;

    @Log("根据项目id获取项目栋楼信息")
    @PostMapping("getXmdlByXmid")
    @ApiOperation(value="根据项目id获取项目栋楼信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmDlDO>> getXmdlByXmid(Long xmid) {
        try {
        	if(xmid==null) {
        		Result.ok(Lists.newArrayList());
        	}
           XmDlDO xmDlDO=new XmDlDO();
            xmDlDO.setFcbz(1);
            xmDlDO.setIntxmid(xmid);
            QueryWrapper<XmDlDO> queryWrapper=new QueryWrapper<XmDlDO>(xmDlDO).orderByAsc("intxh");
            return Result.ok(xmDlService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("根据xmDlId获取项目栋楼层数信息")
    @PostMapping("getXmDlCsByXmDlId")
    @ApiOperation(value="根据xmDlId获取项目栋楼层数信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmDlId",paramType="form",dataType = "Long",required=true,value = "项目栋楼id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmDlCsDO>> getXmDlCsByXmDlId(Long xmDlId) {
        try {
        	if(xmDlId==null) {
        		Result.ok(Lists.newArrayList());
        	}
        	XmDlCsDO xmDlCsDO=new XmDlCsDO();
        	xmDlCsDO.setFcbz(1);
        	xmDlCsDO.setIntxmdlid(xmDlId);
            QueryWrapper<XmDlCsDO> queryWrapper=new QueryWrapper<XmDlCsDO>(xmDlCsDO).orderByAsc("intxh");
            return Result.ok(xmDlCsService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("根据xmid获取项目栋楼和层数信息")
    @PostMapping("getXmDlAndCsByXmid")
    @ApiOperation(value="根据xmid获取项目栋楼和层数信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<Map<String,Object>>> getXmDlAndCsByXmid(Long xmid) {
        try {
        	if(xmid==null) {
        		Result.ok(Lists.newArrayList());
        	}
            return Result.ok(xmDlService.getXmDlAndCsByXmid(xmid));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

}
