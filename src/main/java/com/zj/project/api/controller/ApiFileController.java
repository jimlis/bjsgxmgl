package com.zj.project.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <pre>
 * 文件Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/file/")
public class ApiFileController  extends ApiBaseController {
    @Autowired
    private FileService fileService;


    @Log("查询文档附件列表")
    @ResponseBody
    @PostMapping("/wdlist")
    @ApiOperation(value="根据文件类型获取文档附件列表",httpMethod="POST")
    @ApiImplicitParams(@ApiImplicitParam(name="type",paramType="string",required=false,value = "文件类型"))
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
            @ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<FileDO>> list(FileDO fileDO) {
        QueryWrapper<FileDO> queryWrapper=new QueryWrapper<FileDO>().eq("busType", "bj_wdb").eq("type", fileDO.getType());
        // 查询列表数据
        IPage<FileDO> page = fileService.page(getPage(FileDO.class),queryWrapper);
        return Result.ok(page.getRecords());
    }

    @Log("附件下载")
    @GetMapping("/down/{id}")
    @ApiOperation(value="根据附件id下载附件",httpMethod="GET")
    @ApiImplicitParams(@ApiImplicitParam(name="id",paramType="long",required=true,value = "附件id"))
    @RequiresAuthentication
    public void list(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
            fileService.downFile(id,request,response);
    }
}