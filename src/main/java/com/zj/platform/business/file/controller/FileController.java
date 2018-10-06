package com.zj.platform.business.file.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 文件上传
 */
@Controller
@RequestMapping("/sys/sysFile")
public class FileController extends AdminBaseController {
    
    @Autowired
    private FileService sysFileService;

    @Autowired
    private ConfigurableEnvironment environment;
    
    @Log("进入文件管理页面")
    @GetMapping()
    @RequiresPermissions("sys:file:file")
    String sysFile(Model model) {
        return "sys/file/file";
    }
    
    @Log("查询文件列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:file:list")
    public Result<IPage<FileDO>> list(Integer pageNumber, Integer pageSize, FileDO fileDTO) {
        // 查询列表数据
        IPage<FileDO> page = new Page<>(pageNumber, pageSize);
        
        QueryWrapper<FileDO> wrapper = new QueryWrapper<FileDO>(fileDTO);
        page = sysFileService.page(page, wrapper);
        return Result.ok(page);
    }
    
    @Log("进入添加文件页面")
    @GetMapping("/add")
    @RequiresPermissions("sys:file:add")
    String add() {
        return "sys/sysFile/add";
    }
    
    @Log("进入更新文件页面")
    @GetMapping("/edit")
    @RequiresPermissions("sys:file:update")
    String edit(Long id, Model model) {
        FileDO sysFile = sysFileService.getById(id);
        model.addAttribute("sysFile", sysFile);
        return "sys/sysFile/edit";
    }
    
    /**
     * 信息
     */
    @Log("根据id查询文件信息")
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:file:info")
    public Result<FileDO> info(@PathVariable("id") Long id) {
        FileDO sysFile = sysFileService.getById(id);
        return Result.ok(sysFile);
    }
    
    /**
     * 保存
     */
    @Log("添加文件")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("sys:file:add")
    public Result<String> save(FileDO sysFile) {
        sysFileService.save(sysFile);
        return Result.ok();
    }
    
    /**
     * 修改
     */
    @Log("更新文件")
    @RequestMapping("/update")
    @RequiresPermissions("sys:file:update")
    public Result<String> update(@RequestBody FileDO sysFile) {
        sysFileService.updateById(sysFile);
        
        return Result.ok();
    }
    
    /**
     * 删除
     */
    @Log("删除文件")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("sys:file:remove")
    public Result<String> remove(Long id) {
        sysFileService.removeById(id);
        return Result.ok();
    }
    
    /**
     * 删除
     */
    @Log("批量删除文件")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("sys:file:remove")
    public Result<String> remove(@RequestParam("ids[]") Long[] ids) {
        sysFileService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }
    
    @Log("上传文件")
    @ResponseBody
    @PostMapping("/upload")
    Result<FileDO> upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
        FileDO fileDO =null;
        try {
            String busType= Objects.toString(request.getParameter("busType"),"");
            String type= Objects.toString(request.getParameter("type"),"");
            fileDO = sysFileService.uploadFile(file,busType,type,"1");
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

    @Log("下载文件")
    @RequestMapping("/downFile/{id}")
   public void downFile(@PathVariable("id")Long id, HttpServletRequest request, HttpServletResponse response) {
        sysFileService.downFile(id,request,response);
    }

    @ResponseBody
    @RequestMapping("/listAll")
    public Result<List<FileDO>> listAll(FileDO fileDO){
        QueryWrapper<FileDO> wrapper = new QueryWrapper<FileDO>(fileDO);
        List<FileDO> list = sysFileService.list(wrapper);
        return Result.ok(list);
    }
    
}
