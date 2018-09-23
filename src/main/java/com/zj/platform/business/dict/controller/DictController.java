package com.zj.platform.business.dict.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.business.dict.service.DictService;
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
import java.util.List;

/**
 * 数据字典
 */
@Controller
@RequestMapping("/sys/sysDict")
public class DictController extends AdminBaseController {
    @Autowired
    private DictService sysDictService;
    
    @Log("进入数据字典列表页面")
    @GetMapping()
    @RequiresPermissions("sys:sysDict:sysDict")
    String sysDict() {
        return Constant.PC_PREFIX+"sys/sysDict/sysDict";
    }
    
    @Log("查询数据字典列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:sysDict:sysDict")
    public Result<IPage<DictDO>> list(DictDO dictDTO) {
        QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().
                eq(StringUtils.isNotEmpty(dictDTO.getName()),"name", dictDTO.getName()).
                eq(StringUtils.isNotEmpty(dictDTO.getType()), "type", dictDTO.getType());
        // 查询列表数据
        IPage<DictDO> page = sysDictService.page(getPage(DictDO.class),queryWrapper);
        return Result.ok(page);
    }
    
    @Log("进入数据字典添加页面")
    @GetMapping("/add")
    @RequiresPermissions("sys:sysDict:add")
    String add() {
        return  Constant.PC_PREFIX+"sys/sysDict/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("sys:sysDict:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        DictDO sysDict = sysDictService.getById(id);
        model.addAttribute("sysDict", sysDict);
        return  Constant.PC_PREFIX+"sys/sysDict/edit";
    }

    /**
     * 保存
     */
    @Log("添加数据字典")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("sys:sysDict:add")
    public Result<String> save(DictDO sysDict) {
        sysDictService.save(sysDict);
        return Result.ok();
    }

    /**
     * 修改
     */
    @Log("更新数据字典")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:sysDict:edit")
    public Result<String> update(DictDO sysDict) {
        sysDictService.updateById(sysDict);
        return Result.ok();
    }

    /**
     * 删除
     */
    @Log("删除数据字典")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("sys:sysDict:remove")
    public Result<String> remove(Long id) {
        sysDictService.removeById(id);
        return Result.ok();
    }

    /**
     * 删除
     */
    @Log("删除数据字典")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("sys:sysDict:batchRemove")
    public Result<String> remove(@RequestParam("ids[]") Long[] ids) {
        sysDictService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }
    
    @Log("查询数据字典key列表")
    @GetMapping("/type")
    @ResponseBody
    public List<DictDO> listType() {
        return sysDictService.listType();
    };

    // 类别已经指定增加
    @Log("进入数据字典添加页面")
    @GetMapping("/add/{type}/{description}")
    @RequiresPermissions("sys:sysDict:add")
    String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description) {
        model.addAttribute("type", type);
        model.addAttribute("description", description);
        return  Constant.PC_PREFIX+"sys/sysDict/add";
    }
    
    @Log("根据key查询数据字典信息")
    @ResponseBody
    @RequestMapping("/list/{type}")
    public List<DictDO> listByType(@PathVariable("type") String type) {
        QueryWrapper<DictDO> queryWrapper=new QueryWrapper<DictDO>().
                eq("type", type).
                eq("delFlag", "0");
        List<DictDO> dictList = sysDictService.list(queryWrapper);
        return dictList;
    }
}
