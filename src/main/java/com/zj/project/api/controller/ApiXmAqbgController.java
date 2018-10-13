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
import com.zj.project.xm.xmaqbg.domain.XmAqbgDO;
import com.zj.project.xm.xmaqbg.service.XmAqbgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 安全报告api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmaqbg/")
@Api("安全报告api")
public class ApiXmAqbgController extends ApiBaseController {

    @Autowired
    private XmAqbgService xmAqbgService;

    @Log("根据xmid获取安全报告信息")
    @PostMapping("getXmAqbgListByXmid")
    @ApiOperation(value="根据xmid获取安全报告信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmAqbgDO>> getXmAqbgListByXmid(Long xmid) {
        try {
        	XmAqbgDO xmAqbgDO=new XmAqbgDO();
        	xmAqbgDO.setFcbz(1);
        	xmAqbgDO.setIntxmid(xmid);
            QueryWrapper<XmAqbgDO> queryWrapper=new QueryWrapper<XmAqbgDO>(xmAqbgDO).orderByAsc("dtmgxrq");
            return Result.ok(xmAqbgService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmAqbgId获取安全报告信息")
    @PostMapping("getXmAqbgById")
    @ApiOperation(value="根据xmAqbgId获取安全报告信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmAqbgId",paramType="form",dataType = "Long",required=true,value = "安全报告id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmAqbgDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmAqbgDO.class)})
    @RequiresAuthentication
    public Result<XmAqbgDO> getXmAqbgById(Long xmAqbgId ) {
        try {
            return Result.ok(xmAqbgService.getById(xmAqbgId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }


    @Log("保存安全报告信息")
    @PostMapping("save")
    @ApiOperation(value="保存安全报告信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result save(XmAqbgDO xmAqbgDO, String fileIds) {
        try {
        	xmAqbgService.saveXmAqbgDOXx(xmAqbgDO,fileIds);
           return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
