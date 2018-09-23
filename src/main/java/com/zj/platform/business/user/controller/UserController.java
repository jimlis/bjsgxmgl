package com.zj.platform.business.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.dept.domain.DeptDO;
import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.business.dict.service.DictService;
import com.zj.platform.business.role.domain.RoleDO;
import com.zj.platform.business.role.service.RoleService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.business.user.service.UserService;
import com.zj.platform.business.user.vo.UserVO;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.MD5Utils;
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
import java.util.Map;
import java.util.Objects;


/**
*用户controller
 */
@RequestMapping("/sys/user")
@Controller
public class UserController extends AdminBaseController {
    private String prefix = "sys/user";
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    DictService dictService;
    
    @Log("进入系统用户列表页面")
    @RequiresPermissions("sys:user:user")
    @GetMapping("")
    String user(Model model) {
        return Constant.PC_PREFIX+prefix + "/user";
    }
    
    @Log("查询系统用户列表")
    @GetMapping("/list")
    @ResponseBody
    public Result<IPage<UserDO>> list(UserDO userDTO) {
        QueryWrapper<UserDO> queryWrapper=new QueryWrapper<UserDO>().
                eq(StringUtils.isNotEmpty(userDTO.getName()),"name",userDTO.getName()).
                eq(Objects.nonNull(userDTO.getDeptId()), "deptId", userDTO.getDeptId());
        // 查询列表数据
        IPage<UserDO> page = userService.page(getPage(UserDO.class), queryWrapper);
        return Result.ok(page);
    }

    
    @RequiresPermissions("sys:user:add")
    @Log("添加用户")
    @GetMapping("/add")
    String add(Model model) {
        List<RoleDO> roles = roleService.list(null);
        model.addAttribute("roles", roles);
        return Constant.PC_PREFIX+prefix + "/add";
    }

    @RequiresPermissions("sys:user:edit")
    @Log("编辑用户")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Long id) {
        UserDO userDO = userService.getById(id);
        model.addAttribute("user", userDO);
        List<RoleDO> roles = roleService.findListByUserId(id);
        model.addAttribute("roles", roles);
        return Constant.PC_PREFIX+prefix + "/edit";
    }

    @RequiresPermissions("sys:user:add")
    @Log("保存用户")
    @PostMapping("/save")
    @ResponseBody
    Result<String> save(UserDO user) {
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        userService.save(user);
        return Result.ok();
    }

    @RequiresPermissions("sys:user:edit")
    @Log("更新用户")
    @PostMapping("/update")
    @ResponseBody
    Result<String> update(UserDO user) {
        userService.updateById(user);
        return Result.ok();
    }

    @RequiresPermissions("sys:user:edit")
    @Log("更新用户")
    @PostMapping("/updatePeronal")
    @ResponseBody
    Result<String> updatePeronal(UserDO user) {
        userService.updatePersonal(user);
        return Result.ok();
    }

    @RequiresPermissions("sys:user:remove")
    @Log("删除用户")
    @PostMapping("/remove")
    @ResponseBody
    Result<String> remove(Long id) {
        userService.removeById(id);
        return Result.ok();
    }

    @RequiresPermissions("sys:user:batchRemove")
    @Log("批量删除用户")
    @PostMapping("/batchRemove")
    @ResponseBody
    Result<String> batchRemove(@RequestParam("ids[]") Long[] userIds) {
        userService.removeByIds(Arrays.asList(userIds));
        return Result.ok();
    }
    
    @Log("退出")
    @PostMapping("/exit")
    @ResponseBody
    boolean exit(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false
        return !userService.exit(params);
    }

    @RequiresPermissions("sys:user:resetPwd")
    @Log("请求更改用户密码")
    @GetMapping("/resetPwd/{id}")
    String resetPwd(@PathVariable("id") Long userId, Model model) {

        UserDO userDO = new UserDO();
        userDO.setId(userId);
        model.addAttribute("user", userDO);
        return Constant.PC_PREFIX+prefix + "/reset_pwd";
    }

    @Log("提交更改用户密码")
    @PostMapping("/resetPwd")
    @ResponseBody
    Result<String> resetPwd(UserVO userVO) {
        userService.resetPwd(userVO, getUser());
        return Result.ok();
    }

    @RequiresPermissions("sys:user:resetPwd")
    @Log("admin提交更改用户密码")
    @PostMapping("/adminResetPwd")
    @ResponseBody
    Result<String> adminResetPwd(UserVO userVO) {
        userService.adminResetPwd(userVO);
        return Result.ok();

    }
    
    @Log("查询系统用户属性树形数据")
    @GetMapping("/tree")
    @ResponseBody
    public Tree<DeptDO> tree() {
        Tree<DeptDO> tree = new Tree<DeptDO>();
        tree = userService.getTree();
        return tree;
    }
    
    @Log("进入系统用户树形显示页面")
    @GetMapping("/treeView")
    String treeView() {
        return Constant.PC_PREFIX+prefix + "/userTree";
    }
    
    @Log("进入个人中心")
    @GetMapping("/personal")
    String personal(Model model) {
        UserDO userDO = userService.getById(getUserId());
        model.addAttribute("user", userDO);
        List<DictDO> hobbyList = dictService.getHobbyList(userDO);
        model.addAttribute("hobbyList", hobbyList);
        List<DictDO> sexList = dictService.getSexList();
        model.addAttribute("sexList", sexList);
        return Constant.PC_PREFIX+prefix + "/personal";
    }

}
