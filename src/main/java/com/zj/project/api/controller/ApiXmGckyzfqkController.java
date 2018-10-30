package com.zj.project.api.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmgckyzfqk.domain.XmGckyzfqkDO;
import com.zj.project.xm.xmgckyzfqk.service.XmGckyzfqkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 * 工程款与支付情况:工程款申请/支付情况Api控制器
 * </pre>
 */
@RestController
@RequestMapping("/api/xmgckyzfqk/")
@Api("工程款与支付情况:工程款申请/支付情况Api")
public class ApiXmGckyzfqkController extends ApiBaseController {

    @Autowired
    private XmGckyzfqkService xmGckyzfqkService;

    @Log("根据xmid和dwlx获取工程款与支付情况信息")
    @PostMapping("getXmGckyzfqkListByXmidAndDwlx")
    @ApiOperation(value="根据xmid和dwlx获取工程款与支付情况信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	@ApiImplicitParam(name="dwlx",paramType="form",dataType = "Integer",required=true,value = "单位类型id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<List<XmGckyzfqkDO>> getXmGckyzfqkListByXmidAndDwlx(Long xmid,Integer dwlx) {
        try {
        	XmGckyzfqkDO xmGckyzfqkDO=new XmGckyzfqkDO();
        	xmGckyzfqkDO.setFcbz(1);
        	xmGckyzfqkDO.setIntxmid(xmid);
        	xmGckyzfqkDO.setIntdwlx(dwlx);
        	QueryWrapper<XmGckyzfqkDO> queryWrapper=new QueryWrapper<XmGckyzfqkDO>(xmGckyzfqkDO).select("id","dtmgxrq","intdwmcid","intdwlx","(select chrdwmc from bj_xm_dwmd where id=intdwmcid) as chrdwmc").orderByAsc("dtmgxrq");
            return Result.ok(xmGckyzfqkService.list(queryWrapper));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("根据xmGckyzfqkId获取工程款与支付情况信息")
    @PostMapping("getXmGckyzfqkById")
    @ApiOperation(value="根据xmGckyzfqkId获取工程款与支付情况信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmGckyzfqkId",paramType="form",dataType = "Long",required=true,value = "工程款与支付情况id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=XmGckyzfqkDO.class),
    	@ApiResponse(code=1,message="操作失败",response=XmGckyzfqkDO.class)})
    @RequiresAuthentication
    public Result<XmGckyzfqkDO> getXmGckyzfqkById(Long xmGckyzfqkId) {
        try {
            return Result.ok(xmGckyzfqkService.getById(xmGckyzfqkId));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Log("保存工程款与支付情况信息")
    @PostMapping("save")
    @ApiOperation(value="保存工程款与支付情况信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="fileIds",paramType="form",dataType = "string",required=true,value = "文件ids，多个以逗号隔开")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result<XmGckyzfqkDO> save(XmGckyzfqkDO xmGckyzfqkDO, String fileIds) {
        try {
        	xmGckyzfqkService.saveXmGckyzfqkXx(xmGckyzfqkDO,fileIds);
           return Result.ok(xmGckyzfqkDO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("获取累计已发放期数和已发放金额")
    @PostMapping("getXmGcyfqsAndJe")
    @ApiOperation(value="保存工程款与支付情况信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id"),
    	@ApiImplicitParam(name="dwlx",paramType="form",dataType = "Integer",required=true,value = "单位类型"),
    	@ApiImplicitParam(name="dwmcid",paramType="form",dataType = "Long",required=true,value = "单位id"),
    	@ApiImplicitParam(name="xmgczfid",paramType="form",dataType = "Long",required=true,value = "工程支付id")
    })
    @ApiResponses({@ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=1,message="操作失败")})
    @RequiresAuthentication
    public Result<Map<String,Object>> getXmGcyfqsAndJe(Long xmid,Integer dwlx, Long dwmcid,Long xmgczfid) {
        try {
        	Map<String,Object> map=Maps.newHashMap();
        	map.put("yfqs", 0);
        	map.put("yfje", 0);
        	if(dwlx==null||dwmcid==null||xmid==null)return Result.ok(map);
        	
        	
        	//查询期数
        	XmGckyzfqkDO xmGckyzfqkDO=new XmGckyzfqkDO();
        	xmGckyzfqkDO.setFcbz(1);
        	xmGckyzfqkDO.setIntxmid(xmid);
        	xmGckyzfqkDO.setIntdwlx(dwlx);
        	xmGckyzfqkDO.setIntdwmcid(dwmcid);
        	QueryWrapper<XmGckyzfqkDO> queryWrapper=new QueryWrapper<XmGckyzfqkDO>(xmGckyzfqkDO);
        	if(xmgczfid!=null) {
        		queryWrapper.lt("id", xmgczfid);
        	}
        	List<XmGckyzfqkDO> list=xmGckyzfqkService.list(queryWrapper);
        	if(CollectionUtils.isEmpty(list)) {
        		return Result.ok(map);
        	}
        	int yfqs=0;
        	BigDecimal yfje=BigDecimal.ZERO;
        	for (XmGckyzfqkDO one : list) {
        		yfqs+=1;
        		BigDecimal intbqhsffje=one.getIntbqhsffje()==null?BigDecimal.ZERO:one.getIntbqhsffje();
        		yfje=yfje.add(intbqhsffje);
			}
        	map.put("yfqs", yfqs);
        	map.put("yfje", yfje);
           return Result.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    

}
