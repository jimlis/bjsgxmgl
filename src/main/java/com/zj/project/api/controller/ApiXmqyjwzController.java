package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xmjb.domain.XmQyjwzDO;
import com.zj.project.xmjb.service.XmQyjwzService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 项目区域及位置Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmqyjwz/")
@Api("项目区域位置api")
public class ApiXmqyjwzController extends ApiBaseController {
    @Autowired
    private XmQyjwzService xmQyjwzService;


    @Log("根据项目id获取项目所在区域及位置信息")
    @PostMapping("getXmqyjwzByXmid")
    @ApiOperation(value="根据项目id获取项目所在区域及位置信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "int",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmQyjwzDO.class),
    	@ApiResponse(code=1,message="操作失败",response=XmQyjwzDO.class)})
    @RequiresAuthentication
    public Result<XmQyjwzDO> getXmqyjwzByXmid(Integer xmid) {
        QueryWrapper<XmQyjwzDO> queryWrapper=new QueryWrapper<XmQyjwzDO>().eq("fcbz",1).eq("intxmid",xmid);
        XmQyjwzDO xmQyjwzDO = xmQyjwzService.getOne(queryWrapper);
        return Result.ok(xmQyjwzService.getById(xmQyjwzDO.getId()));
    }

    @Log("保存项目区域及位置信息")
    @PostMapping("save")
    @ApiOperation(value="保存项目区域及位置信息",httpMethod="POST")
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmQyjwzDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmQyjwzDO.class)})
    @RequiresAuthentication
    public Result<XmQyjwzDO> save(XmQyjwzDO xmQyjwzDO) {
        xmQyjwzService.saveXmQyjwzxx(xmQyjwzDO);
        return Result.ok(xmQyjwzDO);
    }



}
