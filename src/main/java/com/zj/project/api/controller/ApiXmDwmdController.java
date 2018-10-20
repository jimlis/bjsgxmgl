package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmdwmd.service.XmDwmdService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 项目单位名单Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmdwmd/")
@Api("项目单位名单api")
public class ApiXmDwmdController extends ApiBaseController {

    @Autowired
    private XmDwmdService xmDwmdService;

    @Log("获取项目单位名单信息")
    @PostMapping("getXmDwmdxxByXmid")
    @ApiOperation(value = "获取项目单位名单信息", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "xmid", paramType = "form", dataType = "Long", required = true, value = "项目id")})
    @ApiResponses({@ApiResponse(code = 0, message = "操作成功", response = Map.class),
            @ApiResponse(code = 1, message = "操作失败", response = Map.class)})
    @RequiresAuthentication
    public Result<Map<String, List<XmDwmdDO>>> getXmDwmdxxByXmid(Long xmid) {
        try {
            return Result.ok(xmDwmdService.getXmDwmdxxByXmid(xmid));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("根据xmid和lxmd获取项目单位名单信息")
    @PostMapping("getXmDwmdListByXmidAndLxmd")
    @ApiOperation(value = "获取项目单位名单信息", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "xmid", paramType = "form", dataType = "Long", required = true, value = "项目id"),
    	@ApiImplicitParam(name = "lxmd", paramType = "form", dataType = "string", required = true, value = "类型名单")})
    @ApiResponses({@ApiResponse(code = 0, message = "操作成功", response = List.class),
            @ApiResponse(code = 1, message = "操作失败", response = List.class)})
    @RequiresAuthentication
    public Result<List<XmDwmdDO>> getXmDwmdListByXmidAndLxmd(Long xmid,String lxmd) {
        try {
        	XmDwmdDO xmDwmdDO=new  XmDwmdDO();
        	xmDwmdDO.setFcbz(1);
        	xmDwmdDO.setIntxmid(xmid);
        	xmDwmdDO.setIntlxmd(lxmd);
        	QueryWrapper<XmDwmdDO> queryWrapper=new QueryWrapper<XmDwmdDO>(xmDwmdDO).orderByAsc("intxh");
            return Result.ok(xmDwmdService.list(queryWrapper));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("获取项目已建单位名单信息")
    @PostMapping("getXmYjDwmdByXmid")
    @ApiOperation(value = "获取项目已建单位名单信息", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "xmid", paramType = "form", dataType = "Long", required = true, value = "项目id")})
    @ApiResponses({@ApiResponse(code = 0, message = "操作成功", response = List.class),
            @ApiResponse(code = 1, message = "操作失败", response = List.class)})
    @RequiresAuthentication
    public Result<List<XmDwmdDO>> getXmYjDwmdByXmid(Long xmid) {
        try {
            return Result.ok(xmDwmdService.getXmYjDwmdByXmid(xmid));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("获取单位类型")
    @PostMapping("getDwlx")
    @ApiOperation(value = "获取项目单位名单信息", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "xmid", paramType = "form", dataType = "Long", required = true, value = "项目id"),
            @ApiImplicitParam(name = "lxmd", paramType = "form", dataType = "string", required = true, value = "单位名单")})
    @ApiResponses({@ApiResponse(code = 0, message = "操作成功", response = List.class),
            @ApiResponse(code = 1, message = "操作失败", response = List.class)})
    @RequiresAuthentication
    public Result<List<DictDO>> getDwlx(Long xmid, String lxmd) {
        try {
            return Result.ok(xmDwmdService.getDwlx(xmid, lxmd));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }



    @Log("删除项目单位名单信息")
    @PostMapping("del/{id}")
    @ApiOperation(value = "删除项目单位名单信息", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "path", dataType = "Long", required = true, value = "单位名单id")})
    @ApiResponses({@ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "操作失败")})
    @RequiresAuthentication
    public Result del(@PathVariable("id") Long id) {
        try {
            XmDwmdDO xmDwmdDO = new XmDwmdDO();
            xmDwmdDO.setId(id);
            xmDwmdDO.setFcbz(0);
            xmDwmdDO.setGxsj(new Date());
            xmDwmdService.updateById(xmDwmdDO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }

    }
}
