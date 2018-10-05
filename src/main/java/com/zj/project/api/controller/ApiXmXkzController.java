package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmqyjwz.domain.XmQyjwzDO;
import com.zj.project.xm.xmxkz.domain.XmXkzDO;
import com.zj.project.xm.xmxkz.service.XmXkzService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 项目许可证Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmxkz/")
@Api("项目许可证api")
public class ApiXmXkzController extends ApiBaseController {
    @Autowired
    private XmXkzService xmXkzService;


    @Log("根据项目id获取项目和许可证类型获取许可证信息")
    @PostMapping("getXmXkzByXmidAndLx")
    @ApiOperation(value="根据项目id获取项目和许可证类型获取许可证信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
            @ApiImplicitParam(name="xkzlx",paramType="form",dataType = "Integer",required=true,value = "许可证类型")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmXkzDO.class),
    	@ApiResponse(code=1,message="操作失败",response=XmXkzDO.class)})
    @RequiresAuthentication
    public Result<XmXkzDO> getXmXkzByXmidAndLx(Long xmid,Integer xkzlx) {
        try {
            QueryWrapper<XmXkzDO> queryWrapper=new QueryWrapper<XmXkzDO>().eq("fcbz",1).eq("intxmid",xmid).eq("intxkzlx",xkzlx);
            XmXkzDO xmXkzDO = xmXkzService.getOne(queryWrapper);
            xmXkzDO=xmXkzDO==null?new XmXkzDO():xmXkzDO;
            return Result.ok(xmXkzDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

    @Log("保存项目许可证")
    @PostMapping("save")
    @ApiOperation(value="保存项目许可证",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=false,value = "关联的附件id串，多个以逗号格卡")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmQyjwzDO.class),
            @ApiResponse(code=1,message="操作失败",response=XmQyjwzDO.class)})
    @RequiresAuthentication
    public Result<XmXkzDO> save(XmXkzDO xmXkzDO,String fileIds) {
        try {
            xmXkzService.saveXmXkzxx(xmXkzDO,fileIds);
            return Result.ok(xmXkzDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }



}
