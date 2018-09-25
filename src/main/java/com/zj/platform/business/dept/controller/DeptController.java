package com.zj.platform.business.dept.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.dept.domain.DeptDO;
import com.zj.platform.business.dept.service.DeptService;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 部门管理控制器
 */
@Controller
@RequestMapping("/sys/dept")
public class DeptController extends AdminBaseController {
    private String prefix = "sys/dept";
    @Autowired
    private DeptService sysDeptService;

    @GetMapping()
    @Log("进入部分页面")
    @RequiresPermissions("system:sysDept:sysDept")
    String dept() {
        return Constant.PC_PREFIX+prefix + "/dept";
    }

    @ApiOperation(value = "获取部门列表", notes = "")
    @ResponseBody
    @GetMapping("/list")
    @Log("获取部门列表")
    @RequiresPermissions("system:sysDept:sysDept")
    public List<DeptDO> list(DeptDO deptDTO) {
        QueryWrapper<DeptDO> queryWrapper=new QueryWrapper<DeptDO>().like(StringUtils.isNotEmpty(deptDTO.getName()),"name",deptDTO.getName()).orderByAsc("orderNum");
        return sysDeptService.list(queryWrapper);
    }

    @GetMapping("/add/{pId}")
    @Log("进入添加部门页面")
    @RequiresPermissions("system:sysDept:add")
    String add(@PathVariable("pId") Long pId, Model model) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "无");
        } else {
            model.addAttribute("pName", sysDeptService.getById(pId).getName());
        }
        return Constant.PC_PREFIX+prefix + "/add";
    }

    @GetMapping("/edit/{deptId}")
    @RequiresPermissions("system:sysDept:edit")
    @Log("编辑部门")
    String edit(@PathVariable("deptId") Long deptId, Model model) {
        DeptDO sysDept = sysDeptService.getById(deptId);
        model.addAttribute("sysDept", sysDept);
        if (Constant.Sys.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
            model.addAttribute("parentDeptName", "无");
        } else {
            DeptDO parDept = sysDeptService.getById(sysDept.getParentId());
            model.addAttribute("parentDeptName", parDept.getName());
        }
        return Constant.PC_PREFIX+prefix + "/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:sysDept:add")
    @Log("保存部门")
    public Result<String> save(DeptDO sysDept) {
        sysDeptService.save(sysDept);
        return Result.ok();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:sysDept:edit")
    public Result<String> update(DeptDO sysDept) {
        sysDeptService.updateById(sysDept);
        return Result.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:sysDept:remove")
    @Log("删除部门")
    public Result<String> remove(Long id) {
        QueryWrapper<DeptDO> queryWrapper=new QueryWrapper<DeptDO>().eq("parentId",id);
        int size = sysDeptService.list(queryWrapper).size();
        if (size > 0) {
            return Result.build(EnumErrorCode.deptUpdateErrorExistChilds.getCode(),
                    EnumErrorCode.deptUpdateErrorExistChilds.getMsg());
        }
        if (sysDeptService.checkDeptHasUser(id)) {
            sysDeptService.removeById(id);
            return Result.ok();
        } else {
            return Result.build(EnumErrorCode.deptDeleteErrorExistUsers.getCode(),
                    EnumErrorCode.deptDeleteErrorExistUsers.getMsg());
        }
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:sysDept:batchRemove")
    @Log("删除部门")
    public Result<String> remove(@RequestParam("ids[]") Long[] deptIds) {
        sysDeptService.removeByIds(Arrays.asList(deptIds));
        return Result.ok();
    }

    @GetMapping("/tree")
    @ResponseBody
    @Log("查询部门树形数据")
    public Tree<DeptDO> tree() {
        Tree<DeptDO> tree = new Tree<DeptDO>();
        tree = sysDeptService.getTree();
        return tree;
    }

    @GetMapping("/treeView")
    @Log("进入部门树形显示页面")
    String treeView() {
        return Constant.PC_PREFIX+prefix + "/deptTree";
    }

}
