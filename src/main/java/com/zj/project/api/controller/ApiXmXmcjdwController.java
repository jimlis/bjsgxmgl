package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.platform.common.web.domain.BaseDomain;
import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmxmcjdw.domain.XmXmcjdwDO;
import com.zj.project.xm.xmxmcjdw.service.XmXmcjdwService;
import com.zj.project.xm.xmzfxcyzxys.domain.XmZfxcyzxysDO;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 项目承建单位Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmxmcjdw/")
@Api("项目承建单位api")
public class ApiXmXmcjdwController extends ApiBaseController {

    @Autowired
    private XmXmcjdwService xmXmcjdwService;

    @Log("获取项目承建单位信息")
    @PostMapping("getXmXmcjdwByXmid")
    @ApiOperation(value="获取项目承建单位信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmZfxcyzxysDO.class),
    	@ApiResponse(code=1,message="操作失败",response=XmZfxcyzxysDO.class)})
    @RequiresAuthentication
    public Result<XmXmcjdwDO> getXmXmcjdwByXmid(Long xmid) {
        try {
            XmXmcjdwDO xmXmcjdwDO=new XmXmcjdwDO();
            xmXmcjdwDO.setFcbz(1);
            xmXmcjdwDO.setIntxmid(xmid);
            QueryWrapper<XmXmcjdwDO> queryWrapper=new QueryWrapper<XmXmcjdwDO>(xmXmcjdwDO);
            return Result.ok(xmXmcjdwService.getOne(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

   @Log("保存项目承建单位信息")
    @PostMapping("save")
    @ApiOperation(value="保存项目承建单位信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=Map.class),
            @ApiResponse(code=1,message="操作失败",response=Map.class)})
    @RequiresAuthentication
    public Result<Map<String,BaseDomain>> save(XmXmcjdwDO xmXmcjdwDO, XmDwmdDO xmDwmdDO) {
        try {
            xmXmcjdwService.saveXmXmcjdwAndXmDwmd(xmXmcjdwDO,xmDwmdDO);
            Map<String,BaseDomain> map=new HashMap<String,BaseDomain>();
            map.put("xmXmcjdwDO",xmXmcjdwDO);
            map.put("xmDwmdDO",xmDwmdDO);
            return Result.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
