package com.zj.project.api.controller;


import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zj.platform.business.dict.domain.DictDO;
import com.zj.platform.business.dict.service.DictService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 数据字典Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/dict/")
@Api("数据字典api")
public class ApiDictController extends ApiBaseController {
    @Autowired
    private DictService dictService;


    @Log("根据types获取数据集合map")
    @PostMapping("getDictMapByTypes")
    @ApiOperation(value="根据types获取数据集合map",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="types",paramType="form",dataType = "string",required=true,value = "类型，多个以逗号隔开",example = "xclb,xcbm")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=Map.class),
    	@ApiResponse(code=1,message="操作失败",response=Map.class)})
    @RequiresAuthentication
    public Result<Map<String,List<DictDO>>> getDictMapByTypes(String types) {
        try {
            Map<String, List<DictDO>> map = dictService.dictMap(types);
            return Result.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
