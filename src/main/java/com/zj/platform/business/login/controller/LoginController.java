package com.zj.platform.business.login.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.menu.domain.MenuDO;
import com.zj.platform.business.menu.service.MenuService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.HexUtils;
import com.zj.platform.common.util.MD5Utils;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import com.zj.platform.shiro.util.ShiroUtils;
import org.apache.poi.ss.formula.functions.T;
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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.io.ByteStreams.toByteArray;

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
        String corpid = environment.getProperty("corpid");
        String corpsecret = environment.getProperty("corpsecret");
        String getTokenPath = "https://oapi.dingtalk.com/gettoken?corpid="+corpid+"&corpsecret="+corpsecret;
        try {
            URL url = new URL(getTokenPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            InputStream inStream = conn.getInputStream();
            byte[] data = toByteArray(inStream);
            String result = new String(data, "UTF-8");
            java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {}.getType();
            Map<String, String> map = new Gson().fromJson(result, type);
            System.out.println(map.get("access_token"));
            //获取jsapi_ticket
            String getTicketPath ="https://oapi.dingtalk.com/get_jsapi_ticket?access_token="+map.get("access_token");
            url = new URL(getTicketPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            inStream = conn.getInputStream();
            data = toByteArray(inStream);
            result = new String(data, "UTF-8");
            type = new TypeToken<HashMap<String, String>>() {}.getType();
            map = new Gson().fromJson(result, type);
            System.out.println(map.get("ticket"));
            //返回前端鉴权-计算签名信息
            long timeStamp = System.currentTimeMillis();
            String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
            String signature = sign(map.get("ticket"),timeStamp+"",timeStamp,baseUrl);
            System.out.println(signature);
            //组装数据返回前端
            resultConfig.put("url",baseUrl);
            resultConfig.put("nonceStr",timeStamp+"");
            resultConfig.put("agentId",environment.getProperty("agentId"));
            resultConfig.put("timeStamp",timeStamp+"");
            resultConfig.put("corpId",environment.getProperty("corpId"));
            resultConfig.put("signature",signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(resultConfig);
    }
    public String sign(String ticket, String nonceStr, long timeStamp, String url) {
        String plain = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + String.valueOf(timeStamp)
                + "&url=" + url;
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            sha1.reset();
            sha1.update(plain.getBytes("UTF-8"));
            return HexUtils.bytesToHexString(sha1.digest());
        } catch (Exception e) {
            e.getMessage();
        }
        return "";
    }


}
