package com.zj.project.api.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.zj.platform.business.user.service.UserService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.common.dingding.SendMessage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.DateUtils;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * 通用Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/common/")
@Api("通用api")
public class ApiCommonController extends ApiBaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigurableEnvironment environment;

    @Log("获取服务端系统时间")
    @PostMapping("getSysDate")
    @ApiOperation(value="获取服务端系统时间",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="dfm",paramType="form",dataType = "string",required=false,value = "时间格式",defaultValue = DateUtils.DATE_TIME_PATTERN_19)})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=String.class),
    	@ApiResponse(code=1,message="操作失败",response=String.class)})
    @RequiresAuthentication
    public Result<String> getSysDate(@RequestParam(value = "dfm",defaultValue = "yyyy-MM-dd HH:mm:ss") String dfm) {
        try {
            LocalDateTime localDateTime=LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(dfm);
            return Result.ok(dateTimeFormatter.format(localDateTime));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

    @Log("钉钉发消息")
    @PostMapping("sendMessage")
    @ApiOperation(value="钉钉发消息",httpMethod="POST")
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=String.class),
            @ApiResponse(code=1,message="操作失败",response=String.class)})
    @RequiresAuthentication
    public Result<String> sendMessage(HttpServletRequest request,String contentText,String userId) {
        UserDO user = userService.getById(userId);
        String touser = user.getChrUserId();
        try {
          String result = SendMessage.sendMessage(environment.getProperty("corpid"),environment.getProperty("corpsecret"),environment.getProperty("agentId"),contentText,touser);
          return Result.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }


}
