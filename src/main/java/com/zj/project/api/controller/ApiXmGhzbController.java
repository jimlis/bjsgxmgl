package com.zj.project.api.controller;


import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmghzb.domain.XmGhzbDO;
import com.zj.project.xm.xmghzb.service.XmGhzbService;
import com.zj.project.xm.xmxkz.domain.XmXkzDO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 项目规划指标Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmghzb/")
@Api("项目规划指标api")
public class ApiXmGhzbController extends ApiBaseController {
    @Autowired
    private XmGhzbService xmGhzbService;


    @Log("根据项目id获取规划指标信息")
    @PostMapping("getXmGhzbByXmid")
    @ApiOperation(value="根据项目id获取规划指标信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmXkzDO.class),
    	@ApiResponse(code=1,message="操作失败",response=XmXkzDO.class)})
    @RequiresAuthentication
    public Result<List<XmGhzbDO> > getXmGhzbByXmid(Long xmid) {
        try {
        	if(xmid==null) {
        		return Result.ok(Lists.newArrayList());
        	}
            QueryWrapper<XmGhzbDO> queryWrapper=new QueryWrapper<XmGhzbDO>().eq("fcbz",1).eq("intxmid",xmid).orderByAsc("intxh");
            List<XmGhzbDO> list = xmGhzbService.list(queryWrapper);
            return Result.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

    @Log("批量保存项目规划指标信息")
    @PostMapping("batchSave")
    @ApiOperation(value="批量保存项目规划指标信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
            @ApiImplicitParam(name="ghzbJson",paramType="form",dataType = "string",required=true,value = "项目规划指标对象数组json字符串"),
            @ApiImplicitParam(name="deleteGhzbIds",paramType="form",dataType = "string",required=false,value = "删除项目规划指标ids"),})
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result batchSave(Long xmid,String ghzbJson,String deleteGhzbIds) {
        try {
           xmGhzbService.saveBatchXmGhzbxx(xmid,ghzbJson,deleteGhzbIds);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }


    @Log("删除项目规划指标信息")
    @PostMapping("del/{id}")
    @ApiOperation(value="删除项目规划指标信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="id",paramType="path",dataType = "Long",required=true,value = "项目规划信息id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result del(@PathVariable("id") Long id) {
        try {
            XmGhzbDO xmGhzbDO=new XmGhzbDO();
            xmGhzbDO.setId(id);
            xmGhzbDO.setFcbz(0);
            xmGhzbDO.setGxsj(new Date());
            xmGhzbService.updateById(xmGhzbDO);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }




}
