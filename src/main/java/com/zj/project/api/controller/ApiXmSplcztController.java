package com.zj.project.api.controller;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.zj.project.xm.splczt.domain.SplcZtDO;
import com.zj.project.xm.splczt.service.SplcZtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 项目审批流程状态Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/splczt/")
@Api("项目审批流程状态api")
public class ApiXmSplcztController extends ApiBaseController {

    @Autowired
    private SplcZtService splcZtService;


    @Log("根据审批流程类型获取项目审批流程状态集合")
    @PostMapping("getSplcZtBySplclx")
    @ApiOperation(value="根据审批流程类型获取项目审批流程状态集合",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="splclx",paramType="form",dataType = "String",required=true,value = "审批类型")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<SplcZtDO>> getSplcZtBySplclx(String splclx) {
        try {
        	if(StringUtils.isEmpty(splclx)) {
        		return Result.ok(Lists.newArrayList());
        	}
        	SplcZtDO splcZtDO=new SplcZtDO();
        	splcZtDO.setFcbz(1);
        	splcZtDO.setChrsplclx(splclx);
        	QueryWrapper<SplcZtDO> queryWrapper=new QueryWrapper<SplcZtDO>(splcZtDO).orderByAsc("intxh");
            return Result.ok(splcZtService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

}
