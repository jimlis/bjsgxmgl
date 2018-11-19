package com.zj.project.api.controller;


import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmsgjd.qqbj.domain.XmSgjdQqbjDO;
import com.zj.project.xm.xmsgjd.qqbj.service.XmSgjdQqbjService;
import com.zj.project.xm.xmzfxcyzxys.domain.XmZfxcyzxysDO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 项目施工进度前期报建Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmsgjdqqbj/")
@Api("项目施工进度前期报建api")
public class ApiXmSgjdQqbjController extends ApiBaseController {

    @Autowired
    private XmSgjdQqbjService xmSgjdQqbjService;

    @Log("保存项目施工进度前期报建")
    @PostMapping("save")
    @ApiOperation(value="保存项目施工进度前期报建",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdQqbjDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmSgjdQqbjDO.class)})
    @RequiresAuthentication
    public Result<XmSgjdQqbjDO> save(XmSgjdQqbjDO xmSgjdQqbjDO,String fileIds) {
        try {
        	xmSgjdQqbjService.saveXmSgjdQqbjXx(xmSgjdQqbjDO,fileIds);
            return Result.ok(xmSgjdQqbjDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }
    
    @Log("获取项目施工进度前期报建")
    @PostMapping("getXmSgjdQqbj")
    @ApiOperation(value="获取项目施工进度前期报建",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	@ApiImplicitParam(name="gqjdid",paramType="form",dataType = "Long",required=true,value = "工期节点比较id"),
    	@ApiImplicitParam(name="fwlx",paramType="form",dataType = "String",required=true,value = "访问类型 xz-新增 cx-查询")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmSgjdQqbjDO.class),
    	@ApiResponse(code=1,message="操作失败",response=XmSgjdQqbjDO.class)})
    @RequiresAuthentication
    public Result<XmSgjdQqbjDO> getXmSgjdQqbj(Long xmid,Long gqjdid,String fwlx) {
        try {
            return Result.ok(xmSgjdQqbjService.getXmSgjdQqbj(xmid, gqjdid, fwlx));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

}
