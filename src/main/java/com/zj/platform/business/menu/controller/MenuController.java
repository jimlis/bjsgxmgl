package com.zj.platform.business.menu.controller;

import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.menu.domain.MenuDO;
import com.zj.platform.business.menu.service.MenuService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*菜单控制器
 */
@RequestMapping("/sys/menu")
@Controller
public class MenuController extends AdminBaseController {
    String prefix = "sys/menu";
    @Autowired
    MenuService menuService;
    
    @Log("进入系统菜单页面")
    @RequiresPermissions("sys:menu:menu")
    @GetMapping()
    String menu(Model model) {
        return Constant.PC_PREFIX+prefix + "/menu";
    }
    
    @Log("查询菜单列表")
    @RequiresPermissions("sys:menu:menu")
    @RequestMapping("/list")
    @ResponseBody
    List<MenuDO> list() {
        List<MenuDO> menus = menuService.list(null);
        return menus;
    }

    @Log("添加菜单")
    @RequiresPermissions("sys:menu:add")
    @GetMapping("/add/{pId}")
    String add(Model model, @PathVariable("pId") Long pId) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.getById(pId).getName());
        }
        return Constant.PC_PREFIX+prefix + "/add";
    }

    @Log("编辑菜单")
    @RequiresPermissions("sys:menu:edit")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Long id) {
        MenuDO mdo = menuService.getById(id);
        Long pId = mdo.getParentId();
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.getById(pId).getName());
        }
        model.addAttribute("menu", mdo);
        return Constant.PC_PREFIX+prefix + "/edit";
    }

    @GetMapping("/fontIcoList")
    String fontIcoList(Model model) {
        return Constant.PC_PREFIX+prefix + "/FontIcoList";
    }

    @Log("保存菜单")
    @RequiresPermissions("sys:menu:add")
    @PostMapping("/save")
    @ResponseBody
    Result<String> save(MenuDO menu) {
        menuService.save(menu);
        return Result.ok();
    }

    @Log("更新菜单")
    @RequiresPermissions("sys:menu:edit")
    @PostMapping("/update")
    @ResponseBody
    Result<String> update(MenuDO menu) {
        menuService.updateById(menu);
        return Result.ok();
    }

    @Log("删除菜单")
    @RequiresPermissions("sys:menu:remove")
    @PostMapping("/remove")
    @ResponseBody
    Result<String> remove(Long id) {
        menuService.removeById(id);
        return Result.ok();
    }
    
    @Log("查询菜单树形数据")
    @GetMapping("/tree")
    @ResponseBody
    Tree<MenuDO> tree() {
        Tree<MenuDO> tree = new Tree<MenuDO>();
        tree = menuService.getTree();
        return tree;
    }
    
    @Log("根据角色ID查询菜单树形数据")
    @GetMapping("/tree/{roleId}")
    @ResponseBody
    Tree<MenuDO> tree(@PathVariable("roleId") Long roleId) {
        Tree<MenuDO> tree = new Tree<MenuDO>();
        tree = menuService.getTree(roleId);
        return tree;
    }
}
