package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmjb.domain.XmjbDO;
import com.zj.project.xm.xmjb.service.XmjbService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <pre>
 * 项目Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmjb/")
@Api("项目基本信息api")
public class ApiXmjbController extends ApiBaseController {
    @Autowired
    private XmjbService xmjbService;

    @Autowired
    private FileService fileService;

    @Log("根据项目类型分页查询项目信息集合")
    @PostMapping("getListByXmlx")
    @ApiOperation(value="根据项目类型分页查询项目信息集合",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmlx",paramType="form",dataType = "int",required=true,value = "项目类型"),
            @ApiImplicitParam(name="pageNumber",paramType="form",dataType = "int",required=true,value = "页面",defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",paramType="form",dataType = "int",required=true,value = "分页大小",defaultValue = "10")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmjbDO>> getListByXmlx(Integer xmlx) {
        try {
            QueryWrapper<XmjbDO> queryWrapper=new QueryWrapper<XmjbDO>().eq("fcbz",1).eq("intxmlx",xmlx).orderByAsc("intxh");
            // 查询列表数据
            IPage<XmjbDO> page = xmjbService.page(getPage(XmjbDO.class), queryWrapper);
            return Result.ok(page.getRecords());
        }catch (Exception e){
            e.printStackTrace();
            return  Result.fail();
        }

    }



}
