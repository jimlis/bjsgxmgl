package com.zj.platform.business.role.controller;


import com.zj.platform.business.role.domain.RoleDO;
import com.zj.platform.business.role.service.RoleService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
*角色控制器
 */
@RequestMapping("/sys/role")
@Controller
public class RoleController extends AdminBaseController {
    String prefix = "sys/role";
    @Autowired
    RoleService roleService;
    
    @Log("进入系统角色页面")
    @RequiresPermissions("sys:role:role")
    @GetMapping()
    String role() {
        return Constant.PC_PREFIX+prefix + "/role";
    }
    
    @Log("查询系统角色菜单")
    @RequiresPermissions("sys:role:role")
    @GetMapping("/list")
    @ResponseBody()
    List<RoleDO> list() {
        List<RoleDO> roles = roleService.findAll();
        return roles;
    }

    @Log("添加角色")
    @RequiresPermissions("sys:role:add")
    @GetMapping("/add")
    String add() {
        return Constant.PC_PREFIX+prefix + "/add";
    }

    @Log("编辑角色")
    @RequiresPermissions("sys:role:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id, Model model) {
        RoleDO roleDO = roleService.getById(id);
        model.addAttribute("role", roleDO);
        return Constant.PC_PREFIX+prefix + "/edit";
    }

    @Log("保存角色")
    @RequiresPermissions("sys:role:add")
    @PostMapping("/save")
    @ResponseBody()
    Result<String> save(RoleDO role) {
        roleService.save(role);
        return Result.ok();
    }

    @Log("更新角色")
    @RequiresPermissions("sys:role:edit")
    @PostMapping("/update")
    @ResponseBody()
    Result<String> update(RoleDO role) {
        roleService.updateById(role);
        return Result.ok();
    }
    
    @Log("删除角色")
    @RequiresPermissions("sys:role:remove")
    @PostMapping("/remove")
    @ResponseBody()
    Result<String> save(Long id) {
        roleService.removeById(id);
        return Result.ok();
    }
    
    @RequiresPermissions("sys:role:batchRemove")
    @Log("批量删除角色")
    @PostMapping("/batchRemove")
    @ResponseBody
    Result<String> batchRemove(@RequestParam("ids[]") Long[] ids) {
        roleService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }
}
