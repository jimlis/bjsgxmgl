package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.api.pojo.vo.GsggDetailsVo;
import com.zj.project.gsgg.domain.GsggDO;
import com.zj.project.gsgg.service.GsggNrService;
import com.zj.project.gsgg.service.GsggService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <pre>
 * 公司公告Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/gsgg/")
@Api("公司公告api")
public class ApiGsggController extends ApiBaseController {
    @Autowired
    private GsggService gsggService;
    @Autowired
    private GsggNrService gsggNrService;
    @Autowired
    private FileService fileService;

    @Log("获取公司公告分页列表")
    @PostMapping("list")
    @ApiOperation(value="获取公司公告分页列表",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNumber",paramType="form",dataType = "int",required=true,value = "页面",defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",paramType="form",dataType = "int",required=true,value = "分页大小",defaultValue = "10")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<GsggDO>> list() {
        QueryWrapper<GsggDO> queryWrapper=new QueryWrapper<GsggDO>().eq("fcbz",1);
        // 查询列表数据
        IPage<GsggDO> page = gsggService.page(getPage(GsggDO.class), queryWrapper);
        return Result.ok(page.getRecords());
    }

    @Log("根据公告id获取公司公告详情信息")
    @PostMapping("details")
    @ApiOperation(value="根据公告id获取公司公告详情信息",httpMethod="POST")
    @ApiImplicitParams(@ApiImplicitParam(name="id",paramType="form",dataType = "long",required=true,value = "公司公告id"))
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=GsggDetailsVo.class),
            @ApiResponse(code=1,message="操作失败",response=GsggDetailsVo.class)})
    @RequiresAuthentication
    public Result<GsggDetailsVo> details(Long id) {
        if(null==id){
            return  Result.fail();
        }
        return Result.ok(gsggService.getGsggDetailsById(id));
    }
}
