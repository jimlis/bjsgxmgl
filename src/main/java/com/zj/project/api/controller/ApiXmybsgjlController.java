package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmybsgjl.domain.XmYbsgjlDO;
import com.zj.project.xm.xmybsgjl.service.XmYbsgjlService;
import com.zj.project.xm.xmzpjl.domain.XmZpjlDO;
import com.zj.project.xm.xmzpjl.service.XmZpjlService;
import com.zj.project.xm.xmzpms.domain.XmZpmsDO;
import com.zj.project.xm.xmzpms.service.XmZpmsService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 样式样板施工api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmybsgjl/")
@Api("样式样板施工api")
public class ApiXmybsgjlController extends ApiBaseController {

    @Autowired
    private XmYbsgjlService xmYbsgjlService;

   @Log("根据xmid和yblx获取样板施工记录信息")
    @PostMapping("getXmYbsgjlListByXmidAndYblx")
    @ApiOperation(value="根据xmid和yblx获取样板施工记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
            @ApiImplicitParam(name="yblx",paramType="form",dataType = "string",required=true,value = "样板类型")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmYbsgjlDO>> getXmYbsgjlListByXmidAndYblx(Long xmid,String yblx ) {
        try {
            XmYbsgjlDO xmYbsgjlDO=new XmYbsgjlDO();
            xmYbsgjlDO.setFcbz(1);
            xmYbsgjlDO.setIntxmid(xmid);
            xmYbsgjlDO.setIntyblx(yblx);
            QueryWrapper<XmYbsgjlDO> queryWrapper=new QueryWrapper<XmYbsgjlDO>(xmYbsgjlDO).orderByAsc("dtmgxrq");
            return Result.ok(xmYbsgjlService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmYbsgjlId获取样板施工记录信息")
    @PostMapping("getXmYbsgjlById")
    @ApiOperation(value="根据xmYbsgjlId获取样板施工记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmYbsgjlId",paramType="form",dataType = "Long",required=true,value = "样板施工记录id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmYbsgjlDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmYbsgjlDO.class)})
    @RequiresAuthentication
    public Result<XmYbsgjlDO> getXmYbsgjlById(Long xmYbsgjlId ) {
        try {
            return Result.ok(xmYbsgjlService.getById(xmYbsgjlId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }


    @Log("保存样板施工记录信息")
    @PostMapping("save")
    @ApiOperation(value="保存样板施工记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result save(XmYbsgjlDO xmYbsgjlDO, String fileIds) {
        try {
            xmYbsgjlService.saveXmYbsgjlXx(xmYbsgjlDO,fileIds);
           return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
