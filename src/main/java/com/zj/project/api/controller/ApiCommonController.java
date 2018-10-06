package com.zj.project.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.DateUtils;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmqyjwz.domain.XmQyjwzDO;
import com.zj.project.xm.xmxkz.domain.XmXkzDO;
import com.zj.project.xm.xmxkz.service.XmXkzService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <pre>
 * 通用Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/common/")
@Api("通用api")
public class ApiCommonController extends ApiBaseController {

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

}
