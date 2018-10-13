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
import com.zj.project.xm.xmbgsqjl.domain.XmBgsqjlDO;
import com.zj.project.xm.xmbgsqjl.service.XmBgsqjlService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 工程/顾问工作变更申请记录api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmbgsqjl/")
@Api("工程/顾问工作变更申请记录api")
public class ApiXmBgsqjlController extends ApiBaseController {

    @Autowired
    private XmBgsqjlService xmBgsqjlService;

    @Log("根据xmid和bgsqlx获取工程/顾问工作变更申请记录信息")
    @PostMapping("getXmBgsqjlListByXmidAndBgsqlx")
    @ApiOperation(value="根据xmid和bgsqlx获取工程/顾问工作变更申请记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
            @ApiImplicitParam(name="bgsqlx",paramType="form",dataType = "Integer",required=true,value = "变更申请类型")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmBgsqjlDO>> getXmBgsqjlListByXmidAndBgsqlx(Long xmid,Integer bgsqlx) {
        try {
        	XmBgsqjlDO xmBgsqjlDO=new XmBgsqjlDO();
        	xmBgsqjlDO.setFcbz(1);
        	xmBgsqjlDO.setIntxmid(xmid);
        	xmBgsqjlDO.setIntbgsqlx(bgsqlx);
            QueryWrapper<XmBgsqjlDO> queryWrapper=new QueryWrapper<XmBgsqjlDO>(xmBgsqjlDO).orderByAsc("dtmgxrq");
            return Result.ok(xmBgsqjlService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmBgsqjlId获取工程/顾问工作变更申请记录信息")
    @PostMapping("getXmBgsqjlById")
    @ApiOperation(value="根据xmBgsqjlId获取工程/顾问工作变更申请记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmBgsqjlId",paramType="form",dataType = "Long",required=true,value = "工程/顾问工作变更申请记录id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmBgsqjlDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmBgsqjlDO.class)})
    @RequiresAuthentication
    public Result<XmBgsqjlDO> getXmBgsqjlById(Long xmBgsqjlId ) {
        try {
            return Result.ok(xmBgsqjlService.getById(xmBgsqjlId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }


    @Log("保存工程/顾问工作变更申请记录信息")
    @PostMapping("save")
    @ApiOperation(value="保存工程/顾问工作变更申请记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result save(XmBgsqjlDO xmBgsqjlDO, String fileIds) {
        try {
        	xmBgsqjlService.saveXmBgsqjlXx(xmBgsqjlDO,fileIds);
           return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}