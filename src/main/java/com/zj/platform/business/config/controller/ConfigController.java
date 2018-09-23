package com.zj.platform.business.config.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.business.config.domain.ConfigDO;
import com.zj.platform.business.config.service.ConfigService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 配置文件控制器
 */
@Controller
@RequestMapping("/sys/config")
public class ConfigController extends AdminBaseController {
    @Autowired
    private ConfigService configService;
    
    @Log("进入系统配置页面")
    @GetMapping()
    @RequiresPermissions("sys:config:config")
    public String Config() {
        return Constant.PC_PREFIX+"sys/config/config";
    }
    
    @Log("查询系统配置列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:config:config")
    public Result<IPage<ConfigDO>> list(ConfigDO configDTO) {
        QueryWrapper<ConfigDO> queryWrapper=new QueryWrapper<ConfigDO>().
                eq(StringUtils.isNotEmpty( configDTO.getK()),"k", configDTO.getK());
        // 查询列表数据
       IPage<ConfigDO> page = configService.page(getPage(ConfigDO.class), queryWrapper);
        return Result.ok(page);
    }
    
    @Log("进入系统配置添加页面")
    @GetMapping("/add")
    @RequiresPermissions("sys:config:add")
    String add() {
        return Constant.PC_PREFIX+"sys/config/add";
    }
    
    @Log("进入配置编辑页面")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("sys:config:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        ConfigDO config = configService.getById(id);
        model.addAttribute("config", config);
        return Constant.PC_PREFIX+"sys/config/edit";
    }

    /**
     * 保存
     */
    @Log("添加系统配置")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("sys:config:add")
    public Result<String> save(ConfigDO config) {
        if (configService.save(config)) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 修改
     */
    @Log("更新系统配置")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:config:edit")
    public Result<String> update(ConfigDO config) {
        configService.updateById(config);
        return Result.ok();
    }

    /**
     * 删除
     */
    @Log("删除系统配置")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("sys:config:remove")
    public Result<String> remove(Long id) {
        configService.removeById(id);
        return Result.ok();
    }

    /**
     * 删除
     */
    @Log("批量删除系统配置")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("sys:config:batchRemove")
    public Result<String> remove(@RequestParam("ids[]") Long[] ids) {
        configService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
