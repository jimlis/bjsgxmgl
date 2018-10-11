package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
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
 * 照片记录Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/zpjl/")
@Api("照片记录api")
public class ApiXmZpjlController extends ApiBaseController {

    @Autowired
    private XmZpjlService xmZpjlService;

    @Autowired
    private XmZpmsService xmZpmsService;

   @Log("根据xmid获取照片记录map集合")
    @PostMapping("getXmZpjlMapByXmid")
    @ApiOperation(value="根据xmid获取照片记录map集合",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=Map.class),
    	@ApiResponse(code=1,message="操作失败",response=Map.class)})
    @RequiresAuthentication
    public Result<Map<String,List<XmZpjlDO>>> getXmZpjlMapByXmid(Long xmid) {
        try {
            return Result.ok(xmZpjlService.getXmZpjlMapByXmid(xmid));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmzpjlid获取照片记录信息")
    @PostMapping("getXmZpjlById")
    @ApiOperation(value="根据xmzpjlid获取照片记录信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmZpjlId",paramType="form",dataType = "Long",required=true,value = "项目照片记录id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
            @ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<XmZpjlDO> getXmZpjlById(Long xmZpjlId) {
        try {
            return Result.ok(xmZpjlService.getById(xmZpjlId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("根据xmzpjlid获取照片记录描述集合")
    @PostMapping("getXmZpmsListByXmZpjlid")
    @ApiOperation(value="根据xmid获取照片记录map集合",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmZpjlId",paramType="form",dataType = "Long",required=true,value = "项目照片记录id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
            @ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmZpmsDO>> getXmZpmsListByXmZpjlid(Long xmZpjlId) {
        try {
            XmZpmsDO xmZpmsDO=new XmZpmsDO();
            xmZpmsDO.setFcbz(1);
            xmZpmsDO.setIntsslx("1");
            xmZpmsDO.setIntxmid(xmZpjlId);
            QueryWrapper<XmZpmsDO> queryWrapper=new QueryWrapper<XmZpmsDO>(xmZpmsDO);
            return Result.ok(xmZpmsService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("保存照片记录")
    @PostMapping("save")
    @ApiOperation(value="保存照片记录",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "图片ids，多个以逗号隔开"),
            @ApiImplicitParam(name="xmZpmsJson",paramType="form",dataType = "string",required=false,value = "照片对应描述对象json串")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result save(XmZpjlDO XmZpjlDO, String fileIds,String xmZpmsJson) {
        try {
            xmZpjlService.saveXmZpjlxx(XmZpjlDO,fileIds,xmZpmsJson);
           return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
