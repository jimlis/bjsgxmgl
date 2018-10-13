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
import com.zj.project.xm.xmybsgjl.domain.XmYbsgjlDO;
import com.zj.project.xm.xmybsgjl.service.XmYbsgjlService;
import com.zj.project.xm.xmzlqxbg.domain.XmZlqxbgDO;
import com.zj.project.xm.xmzlqxbg.service.XmZlqxbgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 项目质量缺陷报告api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmzlqxbg/")
@Api("项目质量缺陷报告api")
public class ApiXmZlqxbgController extends ApiBaseController {

    @Autowired
    private XmZlqxbgService xmZlqxbgService;

    @Log("根据xmid和qxlx获取质量缺陷报告信息")
    @PostMapping("getXmZlqxbgListByXmidAndQxlx")
    @ApiOperation(value="根据xmid和qxlx获取质量缺陷报告信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
            @ApiImplicitParam(name="qxlx",paramType="form",dataType = "string",required=true,value = "缺陷类型")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmZlqxbgDO>> getXmZlqxbgListByXmidAndQxlx(Long xmid,String qxlx ) {
        try {
        	XmZlqxbgDO xmZlqxbgDO=new XmZlqxbgDO();
        	xmZlqxbgDO.setFcbz(1);
        	xmZlqxbgDO.setIntxmid(xmid);
        	xmZlqxbgDO.setIntqxlx(qxlx);
            QueryWrapper<XmZlqxbgDO> queryWrapper=new QueryWrapper<XmZlqxbgDO>(xmZlqxbgDO).orderByAsc("dtmgxrq");
            return Result.ok(xmZlqxbgService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmZlqxbgId获取项目质量缺陷报告信息")
    @PostMapping("getXmZlqxbgById")
    @ApiOperation(value="根据xmZlqxbgId获取项目质量缺陷报告信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmZlqxbgId",paramType="form",dataType = "Long",required=true,value = "项目质量缺陷报告id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmZlqxbgDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmZlqxbgDO.class)})
    @RequiresAuthentication
    public Result<XmZlqxbgDO> getXmZlqxbgById(Long xmZlqxbgId ) {
        try {
            return Result.ok(xmZlqxbgService.getById(xmZlqxbgId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }


    @Log("保存项目质量缺陷报告信息")
    @PostMapping("save")
    @ApiOperation(value="保存项目质量缺陷报告信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result save(XmZlqxbgDO xmZlqxbgDO, String fileIds) {
        try {
        	xmZlqxbgService.saveXmZlqxbgXx(xmZlqxbgDO,fileIds);
           return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
