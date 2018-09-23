package com.zj.platform.business.login.controller;


import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.menu.domain.MenuDO;
import com.zj.platform.business.menu.service.MenuService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.MD5Utils;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import com.zj.platform.shiro.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
*登录控制器
 */
@Controller
public class LoginController extends AdminBaseController {
	public LoginController() {
		System.out.println("LoginController:被初始胡.............");
	}
    @Autowired
    MenuService menuService;
    @Autowired
    FileService fileService;

    @GetMapping({ "/", "" })
    @Log("重定向到登录")
    String welcome(Model model) {
        return "redirect:/login";
    }

    @Log("请求访问主页")
    @GetMapping({ "/index" })
    String index(Model model) {
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getName());
        model.addAttribute("username", getUser().getUsername());
        FileDO fileDO = fileService.getById(getUser().getPicId());
        model.addAttribute("picUrl", fileDO == null ? "/img/photo_s.jpg" : fileDO.getUrl());
        return Constant.PC_PREFIX+"sys/main/index_v1";
    }

    @GetMapping("/login")
    String login() {
        return Constant.PC_PREFIX+"sys/login/login";
    }

    @Log("登录")
    @PostMapping("/login")
    @ResponseBody
    Result<String> ajaxLogin(String username, String password) {
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return Result.ok();
        } catch (AuthenticationException e) {
            return Result.build(EnumErrorCode.userLoginFail.getCode(), EnumErrorCode.userLoginFail.getMsg());
        }
    }

    @GetMapping("/logout")
    @Log("退出")
    String logout(HttpServletRequest request) {
        ShiroUtils.logout();
        return "redirect:"+request.getServletPath()+"/login";
    }
    
    @Log("主页")
    @GetMapping("/main")
    String main() {
        return Constant.PC_PREFIX+"sys/main/main";
    }

    @Log("错误403")
    @GetMapping("/403")
    String error403() {
        return Constant.PC_PREFIX+"sys/error/403";
    }

}
