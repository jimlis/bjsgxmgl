package com.zj.platform.business.log.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.business.log.domain.LogDO;
import com.zj.platform.business.log.service.LogService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 日志
 */
@RequestMapping("/sys/log")
@Controller
public class LogController extends AdminBaseController {
    @Autowired
    LogService logService;
    String prefix = "sys/log";
    
    @Log("进入系统日志列表页面")
    @GetMapping()
    String log() {
        return Constant.PC_PREFIX+prefix + "/log";
    }
    
    @Log("查询系统日志列表")
    @ResponseBody
    @GetMapping("/list")
    public Result<IPage<LogDO>> list(LogDO logDTO) {
        QueryWrapper<LogDO> queryWrapper=new QueryWrapper<LogDO>().
                eq(StringUtils.isNotEmpty(logDTO.getUsername()),"username", logDTO.getUsername()).
                eq(StringUtils.isNotEmpty(logDTO.getOperation()), "operation", logDTO.getOperation());
        // 查询列表数据
        IPage<LogDO> page = logService.page(getPage(LogDO.class),queryWrapper);
        return Result.ok(page);
    }
    
    @Log("删除系统日志")
    @ResponseBody
    @PostMapping("/remove")
    Result<String> remove(Long id) {
        logService.removeById(id);
        return Result.ok();
    }
    
    @Log("批量删除系统日志")
    @ResponseBody
    @PostMapping("/batchRemove")
    Result<String> batchRemove(@RequestParam("ids[]") Long[] ids) {
        logService.removeByIds(Arrays.asList(ids));
        return Result.fail();
    }
}
