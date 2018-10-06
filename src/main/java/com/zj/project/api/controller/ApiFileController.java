package com.zj.project.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * <pre>
 * 文件Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/file/")
@Api("文件api")
public class ApiFileController  extends ApiBaseController {
    @Autowired
    private FileService fileService;


    @Log("根据文件类型获取文档附件列表")
    @ResponseBody
    @PostMapping("/wdlist")
    @ApiOperation(value="根据文件类型获取文档附件列表",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="type",paramType="form",dataType = "string",required=false,value = "文件类型"),
            @ApiImplicitParam(name="pageNumber",paramType="form",dataType = "int",required=true,value = "页面",defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",paramType="form",dataType = "int",required=true,value = "分页大小",defaultValue = "10")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
            @ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<FileDO>> wdlist(String  type) {
        QueryWrapper<FileDO> queryWrapper=new QueryWrapper<FileDO>().eq("busType", "bj_wdb").eq("type", type);
        // 查询列表数据
        IPage<FileDO> page = fileService.page(getPage(FileDO.class),queryWrapper);
        return Result.ok(page.getRecords());
    }

    @Log("根据附件id下载附件")
    @GetMapping("/down/{id}")
    @ApiOperation(value="根据附件id下载附件",httpMethod="GET")
    @ApiImplicitParams(@ApiImplicitParam(name="id",paramType="path",dataType = "long",required=true,value = "附件id"))
    @RequiresAuthentication
    public void down(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
            fileService.downFile(id,request,response);
    }

    @Log("上传文件")
    @PostMapping("/upload")
    @ApiOperation(value="上传文件",httpMethod="GET")
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=FileDO.class),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    Result<FileDO> upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
        FileDO fileDO =null;
        try {
            String busType= Objects.toString(request.getParameter("busType"),"");
            String type=Objects.toString(request.getParameter("type"),"");
            fileDO = fileService.uploadFile(file,busType,type,"2");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.build(EnumErrorCode.FileUploadGetBytesError.getCode(),
                    EnumErrorCode.FileUploadGetBytesError.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            return Result.build(EnumErrorCode.unknowFail.getCode(),
                    EnumErrorCode.unknowFail.getMsg());
        }
        return Result.ok(fileDO);
    }

    @Log("根据附件id删除附件信息")
    @PostMapping("/del/{id}")
    @ApiOperation(value="根据附件id删除附件信息",httpMethod="POST")
    @ApiImplicitParams(@ApiImplicitParam(name="id",paramType="path",dataType = "long",required=true,value = "附件id"))
    @RequiresAuthentication
    public Result del(@PathVariable("id") Long id) {
       try {
           fileService.removeById(id);
           return  Result.ok();
       }catch (Exception e){
           e.printStackTrace();
           return  Result.fail();
       }
    }

}