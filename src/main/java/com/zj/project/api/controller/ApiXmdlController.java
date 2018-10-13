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
import com.zj.project.xm.xmdl.domain.XmDlDO;
import com.zj.project.xm.xmdl.service.XmDlService;

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

    @Log("根据项目id获取项目栋楼信息")
    @PostMapping("getXmdlByXmid")
    @ApiOperation(value="根据项目id获取项目栋楼信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmDlDO>> getXmdlByXmid(Long xmid) {
        try {
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


}
