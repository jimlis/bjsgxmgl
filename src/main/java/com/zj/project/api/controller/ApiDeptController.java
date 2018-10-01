package com.zj.project.api.controller;

import com.zj.platform.business.dept.service.DeptService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 部门Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/dept/")
@Api("部门api")
public class ApiDeptController extends ApiBaseController {
    @Autowired
    private DeptService deptService;

    @Log("根据部门id获取下一级部门、人员信息")
    @PostMapping("getNextDeptAndUser")
    @ApiOperation(value="根据部门id获取下一级部门、人员信息",httpMethod="POST")
    @ApiImplicitParams(@ApiImplicitParam(name="deptId",paramType="form",dataType = "string",required=false,defaultValue="0",value = "部门id"))
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<Map<String,Object>>> getNextDeptAndUser(@RequestParam(name="deptId",defaultValue="0")  Long deptId) {
        return Result.ok(deptService.getNextDeptAndUser(deptId));
    }
}
