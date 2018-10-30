package com.zj.platform.business.login.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.menu.domain.MenuDO;
import com.zj.platform.business.menu.service.MenuService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.util.CommonUtils;
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
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
*登录控制器
 */
@Controller
public class LoginController extends AdminBaseController {

    @Autowired
    MenuService menuService;
    @Autowired
    FileService fileService;
    @Autowired
    private ConfigurableEnvironment environment;

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

    @Log("得到钉钉token")
    @GetMapping("/getToken")
    @ResponseBody
    Result<Map<String ,String>> getToken(HttpServletRequest request,String code) {
        Map<String ,String> resultConfig =new HashMap<>();
        String corpid = environment.getProperty("corpId");
        String corpsecret = environment.getProperty("corpsecret");
        String getTokenPath = "https://oapi.dingtalk.com/gettoken?corpid="+corpid+"&corpsecret="+corpsecret;
        String accessToken = "";//token
        String ticket = "";//jsapi_ticket
        String signature = "";//前端鉴权-计算签名信息
        try {
            //得到token
            JSONObject tokenResult= CommonUtils.httpGet(getTokenPath);
            java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {}.getType();
            Map<String ,String> tokenMap = new Gson().fromJson(tokenResult.toJSONString(), type);
            accessToken = tokenMap.get("access_token");
            resultConfig.put("access_token",tokenMap.get("access_token"));
            //获取jsapi_ticket
            String getTicketPath ="https://oapi.dingtalk.com/get_jsapi_ticket?access_token="+accessToken;
            String ticketResult = CommonUtils.HttpURLConnectionGet(getTicketPath);
            type = new TypeToken<HashMap<String, String>>() {}.getType();
            Map<String ,String> ticketMap = new Gson().fromJson(ticketResult, type);
            ticket = ticketMap.get("ticket");
            //返回前端鉴权-计算签名信息
            String urlString = request.getRequestURL().toString();
            String queryString = request.getQueryString();
            String queryStringEncode = null;
            String baseUrl;
            if (queryString != null) {
                queryStringEncode = URLDecoder.decode(queryString);
                baseUrl = urlString + "?" + queryStringEncode;
            } else {
                baseUrl = urlString;
            }
            long timeStamp = System.currentTimeMillis();
            signature = CommonUtils.sign(ticket,timeStamp+"",timeStamp,baseUrl);
            //组装数据返回前端
            resultConfig.put("url",baseUrl);
            resultConfig.put("nonceStr","abcdefg");
            resultConfig.put("agentId",environment.getProperty("agentId"));
            resultConfig.put("timeStamp",System.currentTimeMillis() / 1000+"");
            resultConfig.put("corpId",environment.getProperty("corpId"));
            resultConfig.put("signature",signature);
            resultConfig.put("access_token",accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(resultConfig);
    }
    @Log("得到钉钉用户id")
    @GetMapping("/getDDUser")
    @ResponseBody
    Result<Map<String ,String>> getDDUser(HttpServletRequest request,String access_token,String code) {
        String url = "https://oapi.dingtalk.com/user/getuserinfo?access_token="+access_token+"&code="+code;
        String userResult = CommonUtils.HttpURLConnectionGet(url);
        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String ,String> userMap = new Gson().fromJson(userResult, type);
        return Result.ok(userMap);
    }
    @Log("得到钉钉用户信息")
    @GetMapping("/getDDUserInfo")
    @ResponseBody
    Result<String> getDDUserInfo(HttpServletRequest request,String access_token,String userid) {
        String url = "https://oapi.dingtalk.com/user/get?access_token="+access_token+"&userid="+userid;
        String userInfo = CommonUtils.HttpURLConnectionGet(url);
        return Result.ok(userInfo);
    }

}
