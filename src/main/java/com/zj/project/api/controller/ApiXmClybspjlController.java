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
import com.zj.project.xm.xmclybspjl.domain.XmClybspjlDO;
import com.zj.project.xm.xmclybspjl.service.XmClybspjlService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 材料样板审批记录Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmclybspjl/")
@Api("材料样板审批记录api")
public class ApiXmClybspjlController extends ApiBaseController {

    @Autowired
    private XmClybspjlService xmClybspjlService;

   @Log("根据xmid和clyblx获取材料样板审批记录集合")
    @PostMapping("getXmClybspjlListByXmidAndClyblx")
    @ApiOperation(value="根据xmid和clyblx获取材料样板审批记录集合",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	@ApiImplicitParam(name="clyblx",paramType="form",dataType = "Integer",required=true,value = "材料样板类型：1（土建）2（机电）3（装修）4（园林）5（其他）")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmClybspjlDO>> getXmClybspjlListByXmidAndClyblx(Long xmid,Integer clyblx) {
        try {
        	XmClybspjlDO xmClybspjlDO=new XmClybspjlDO();
        	xmClybspjlDO.setFcbz(1);
        	xmClybspjlDO.setIntxmid(xmid);
        	xmClybspjlDO.setIntclyblx(clyblx);
        	QueryWrapper<XmClybspjlDO> queryWrapper=new QueryWrapper<XmClybspjlDO>(xmClybspjlDO).orderByAsc("dtmgxrq");
            return Result.ok(xmClybspjlService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmClybspjlId获取材料样板审批记录信息")
    @PostMapping("getXmClybspjlById")
    @ApiOperation(value="根据xmClybspjlId获取材料样板审批记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmClybspjlId",paramType="form",dataType = "Long",required=true,value = "材料样板审批记录id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmClybspjlDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmClybspjlDO.class)})
    @RequiresAuthentication
    public Result<XmClybspjlDO> getXmClybspjlById(Long xmClybspjlId) {
        try {
            return Result.ok(xmClybspjlService.getById(xmClybspjlId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }


    @Log("保存材料样板审批记录信息")
    @PostMapping("save")
    @ApiOperation(value="保存材料样板审批记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开"),
            @ApiImplicitParam(name="xmClybspjlJszlJson",paramType="form",dataType = "string",required=false,value = "品牌及技术对象json串"),
            @ApiImplicitParam(name="deleteJszlIds",paramType="form",dataType = "string",required=false,value = "删除品牌及技术对象ids")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result<XmClybspjlDO> save(XmClybspjlDO xmClybspjlDO, String fileIds,String xmClybspjlJszlJson,String deleteJszlIds) {
        try {
        	xmClybspjlService.saveXmZpjlxx(xmClybspjlDO,fileIds,xmClybspjlJszlJson,deleteJszlIds);
           return Result.ok(xmClybspjlDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
