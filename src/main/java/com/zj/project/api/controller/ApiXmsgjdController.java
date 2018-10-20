package com.zj.project.api.controller;

import java.util.List;
import java.util.Map;

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
import com.zj.project.xm.xmsgjd.dtsbazsg.domain.XmSgjdDtsbazsgDO;
import com.zj.project.xm.xmsgjd.dtsbazsg.service.XmSgjdDtsbazsgService;
import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxDO;
import com.zj.project.xm.xmsgjd.ecjgzx.service.XmSgjdEcjgzxService;
import com.zj.project.xm.xmsgjd.jcsg.domain.XmSgjdJcsgDO;
import com.zj.project.xm.xmsgjd.jcsg.service.XmSgjdJcsgService;
import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwsgService;
import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgDO;
import com.zj.project.xm.xmsgjd.ylsg.service.XmSgjdYlsgService;
import com.zj.project.xm.xmsgjd.ztjgsg.domain.XmSgjdZtjgsgDO;
import com.zj.project.xm.xmsgjd.ztjgsg.service.XmSgjdZtjgsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * 项目施工进度api
 */
@RestController
@RequestMapping("/api/xmsgjd/")
@Api("项目施工进度api")
public class ApiXmsgjdController extends ApiBaseController{
	 @Autowired
	 private XmSgjdJcsgService  xmSgjdJcsgService;
	 
	 @Autowired
	 private XmSgjdZtjgsgService xmSgjdZtjgsgService;
	 
	 @Autowired
	 private XmSgjdEcjgzxService xmSgjdEcjgzxService;
	 
	 @Autowired
	 private XmSgjdDtsbazsgService xmSgjdDtsbazsgService;
	 
	 @Autowired
	 private XmSgjdSwgwsgService  xmSgjdSwgwsgService;
	 
	 @Autowired
	 private XmSgjdYlsgService xmSgjdYlsgService;
	 
	 
	@Log("根据xmid获取施工进度信息")
    @PostMapping("getXmSgjdListByXmid")
    @ApiOperation(value="根据xmid获取施工进度信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=List.class),
    	@ApiResponse(code=1,message="操作失败",response=List.class)})
    @RequiresAuthentication
    public Result<Map<String,List>> getXmSgjdListByXmid(Long xmid) {
        try {
        	Map<String,List> map=Maps.newHashMap();
        	
        	if(xmid==null) {
        		return Result.ok(map);
        	}
        	
        	XmSgjdJcsgDO xmSgjdJcsgDO=new XmSgjdJcsgDO();
        	xmSgjdJcsgDO.setFcbz(1);
        	xmSgjdJcsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdJcsgDO> queryWrapper1=new QueryWrapper<XmSgjdJcsgDO>(xmSgjdJcsgDO).
        			select("id","dtmbgrq","(select chrdlmc  from  bj_xm_dl where id=intsgwzid ) as chrsgwzmc").orderByAsc("dtmbgrq");
        	map.put("jc", xmSgjdJcsgService.list(queryWrapper1));
        	
        	XmSgjdZtjgsgDO xmSgjdZtjgsgDO=new XmSgjdZtjgsgDO();
        	xmSgjdZtjgsgDO.setFcbz(1);
        	xmSgjdZtjgsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdZtjgsgDO> queryWrapper2=new QueryWrapper<XmSgjdZtjgsgDO>(xmSgjdZtjgsgDO).
        			select("id","dtmbgrq","concat((select chrdlmc  from  bj_xm_dl where id=intsgwzd ),'-',intsgwzc,'层') as chrShowAddress").orderByAsc("dtmbgrq");
        	map.put("zt", xmSgjdZtjgsgService.list(queryWrapper2));

        	XmSgjdEcjgzxDO xmSgjdEcjgzxDO=new XmSgjdEcjgzxDO();
        	xmSgjdEcjgzxDO.setFcbz(1);
        	xmSgjdEcjgzxDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdEcjgzxDO> queryWrapper3=new QueryWrapper<XmSgjdEcjgzxDO>(xmSgjdEcjgzxDO).
        			select("id","dtmgxrq","(select chrdlmc  from  bj_xm_dl where id=intdid ) as chrdmc").orderByAsc("dtmgxrq");
        	map.put("ec", xmSgjdEcjgzxService.list(queryWrapper3));
        	
        	
        	XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO=new XmSgjdDtsbazsgDO();
        	xmSgjdDtsbazsgDO.setFcbz(1);
        	xmSgjdDtsbazsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdDtsbazsgDO> queryWrapper4=new QueryWrapper<XmSgjdDtsbazsgDO>(xmSgjdDtsbazsgDO).
        			select("id","dtmgxrq","chrdtbh","(select chrdlmc  from  bj_xm_dl where id=intsgwz ) as chrsgwz").orderByAsc("dtmgxrq");
        	map.put("dt", xmSgjdDtsbazsgService.list(queryWrapper4));
        	
        	
        	XmSgjdSwgwsgDO xmSgjdSwgwsgDO=new XmSgjdSwgwsgDO();
        	xmSgjdSwgwsgDO.setFcbz(1);
        	xmSgjdSwgwsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdSwgwsgDO> queryWrapper5=new QueryWrapper<XmSgjdSwgwsgDO>(xmSgjdSwgwsgDO).select(" distinct intxmid"," dtmbgrq ") .orderByAsc("dtmbgrq");
        	map.put("sw", xmSgjdSwgwsgService.list(queryWrapper5));
        	
        	XmSgjdYlsgDO xmSgjdYlsgDO=new XmSgjdYlsgDO();
        	xmSgjdYlsgDO.setFcbz(1);
        	xmSgjdYlsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdYlsgDO> queryWrapper6=new QueryWrapper<XmSgjdYlsgDO>(xmSgjdYlsgDO).select(" distinct intxmid"," dtmgxrq ") .orderByAsc("dtmgxrq");
        	map.put("yl", xmSgjdYlsgService.list(queryWrapper6));
            
            return Result.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
}
