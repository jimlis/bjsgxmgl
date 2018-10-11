package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.DateUtils;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmqyjwz.domain.XmQyjwzDO;
import com.zj.project.xm.xmxkz.domain.XmXkzDO;
import com.zj.project.xm.xmzfxcyzxys.domain.XmZfxcyzxysDO;
import com.zj.project.xm.xmzfxcyzxys.service.XmZfxcyzxysService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <pre>
 * 政府巡查及专项验收Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmzfxcyzxys/")
@Api("政府巡查及专项验收api")
public class ApiXmZfxcyzxysController extends ApiBaseController {

    @Autowired
    private XmZfxcyzxysService xmZfxcyzxysService;

    @Log("获取巡查信息")
    @PostMapping("getXmZfxcyzxys")
    @ApiOperation(value="获取巡查信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
            @ApiImplicitParam(name="xclb",paramType="form",dataType = "string",required=false,value = "巡查类别"),
            @ApiImplicitParam(name="xcbm",paramType="form",dataType = "string",required=false,value = "巡查部门")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmZfxcyzxysDO>> getXmZfxcyzxysByXmidAndXcbm(Long xmid,String xclb,String xcbm) {
        try {
            XmZfxcyzxysDO xmZfxcyzxysDO=new XmZfxcyzxysDO();
            xmZfxcyzxysDO.setFcbz(1);
            xmZfxcyzxysDO.setIntxmid(xmid);
            xmZfxcyzxysDO.setIntxcbm(xcbm);
            xmZfxcyzxysDO.setIntxclb(xclb);
            QueryWrapper<XmZfxcyzxysDO> queryWrapper=new QueryWrapper<XmZfxcyzxysDO>(xmZfxcyzxysDO).orderByDesc("dtmxcrq");
            return Result.ok(xmZfxcyzxysService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("保存政府巡查及专项验收")
    @PostMapping("save")
    @ApiOperation(value="保存政府巡查及专项验收",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmZfxcyzxysDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmZfxcyzxysDO.class)})
    @RequiresAuthentication
    public Result<XmZfxcyzxysDO> save(XmZfxcyzxysDO xmZfxcyzxysDO,String fileIds) {
        try {
            xmZfxcyzxysService.saveXmZfxcyzxysxx(xmZfxcyzxysDO,fileIds);
            return Result.ok(xmZfxcyzxysDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
