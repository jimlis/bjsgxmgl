package com.zj.project.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.xm.xmaqbg.domain.XmAqbgDO;
import com.zj.project.xm.xmaqbg.service.XmAqbgService;
import com.zj.project.xm.xmbgsqjl.domain.XmBgsqjlDO;
import com.zj.project.xm.xmbgsqjl.service.XmBgsqjlService;
import com.zj.project.xm.xmclybspjl.domain.XmClybspjlDO;
import com.zj.project.xm.xmclybspjl.service.XmClybspjlService;
import com.zj.project.xm.xmgckyzfqk.domain.XmGckyzfqkDO;
import com.zj.project.xm.xmgckyzfqk.service.XmGckyzfqkService;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;
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
import com.zj.project.xm.xmybsgjl.domain.XmYbsgjlDO;
import com.zj.project.xm.xmybsgjl.service.XmYbsgjlService;
import com.zj.project.xm.xmzlqxbg.domain.XmZlqxbgDO;
import com.zj.project.xm.xmzlqxbg.service.XmZlqxbgService;

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
	 
	 @Autowired
	 private XmGqjdbjService xmGqjdbjService;
	 
	 @Autowired
	 private XmYbsgjlService xmYbsgjlService;
	 
	 @Autowired
	 private XmClybspjlService xmClybspjlService;
	 
	 @Autowired
	 private XmZlqxbgService xmZlqxbgService;
	 
	 @Autowired
	 private XmBgsqjlService xmBgsqjlService;
	 
	 @Autowired
	 private XmGckyzfqkService xmGckyzfqkService;
	 
	 @Autowired
	 private XmAqbgService xmAqbgService;
	 
	 
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
        			select("id","dtmbgrq","concat((select chrjdmc  from  bj_xm_gqjdbj where id=intsgwzid ),'-',(select chrjdmc  from  bj_xm_gqjdbj where id=intjclx )) as chrsgwzmc").orderByAsc("dtmbgrq");
        	map.put("jc", xmSgjdJcsgService.list(queryWrapper1));
        	
        	XmSgjdZtjgsgDO xmSgjdZtjgsgDO=new XmSgjdZtjgsgDO();
        	xmSgjdZtjgsgDO.setFcbz(1);
        	xmSgjdZtjgsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdZtjgsgDO> queryWrapper2=new QueryWrapper<XmSgjdZtjgsgDO>(xmSgjdZtjgsgDO).
        			select("id","dtmbgrq","concat((select chrjdmc  from  bj_xm_gqjdbj where id=intsgwzd ),'-',(select chrjdmc  from  bj_xm_gqjdbj where id=intsgwzc )) as chrShowAddress").orderByAsc("dtmbgrq");
        	map.put("zt", xmSgjdZtjgsgService.list(queryWrapper2));

        	XmSgjdEcjgzxDO xmSgjdEcjgzxDO=new XmSgjdEcjgzxDO();
        	xmSgjdEcjgzxDO.setFcbz(1);
        	xmSgjdEcjgzxDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdEcjgzxDO> queryWrapper3=new QueryWrapper<XmSgjdEcjgzxDO>(xmSgjdEcjgzxDO).
        			select("id","dtmgxrq","(select chrjdmc  from  bj_xm_gqjdbj where id=intdid ) as chrdmc").orderByAsc("dtmgxrq");
        	map.put("ec", xmSgjdEcjgzxService.list(queryWrapper3));
        	
        	
        	XmSgjdDtsbazsgDO xmSgjdDtsbazsgDO=new XmSgjdDtsbazsgDO();
        	xmSgjdDtsbazsgDO.setFcbz(1);
        	xmSgjdDtsbazsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdDtsbazsgDO> queryWrapper4=new QueryWrapper<XmSgjdDtsbazsgDO>(xmSgjdDtsbazsgDO).
        			select("id","dtmgxrq","chrdtbh","(select chrjdmc  from  bj_xm_gqjdbj where id=intsgwz ) as chrsgwz").orderByAsc("dtmgxrq");
        	map.put("dt", xmSgjdDtsbazsgService.list(queryWrapper4));
        	
        	
        	XmSgjdSwgwsgDO xmSgjdSwgwsgDO=new XmSgjdSwgwsgDO();
        	xmSgjdSwgwsgDO.setFcbz(1);
        	xmSgjdSwgwsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdSwgwsgDO> queryWrapper5=new QueryWrapper<XmSgjdSwgwsgDO>(xmSgjdSwgwsgDO).select("id","intxmid","dtmgxrq") .orderByAsc("dtmgxrq");
        	map.put("sw", xmSgjdSwgwsgService.list(queryWrapper5));
        	
        	XmSgjdYlsgDO xmSgjdYlsgDO=new XmSgjdYlsgDO();
        	xmSgjdYlsgDO.setFcbz(1);
        	xmSgjdYlsgDO.setIntxmid(xmid);
        	QueryWrapper<XmSgjdYlsgDO> queryWrapper6=new QueryWrapper<XmSgjdYlsgDO>(xmSgjdYlsgDO).select("id","intxmid","dtmgxrq") .orderByAsc("dtmgxrq");
        	map.put("yl", xmSgjdYlsgService.list(queryWrapper6));
            
            return Result.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
	
	@Log("根据xmid获取施工进度汇总信息")
    @PostMapping("getXmSgjdHzxxByXmid")
    @ApiOperation(value="根据xmid获取施工进度汇总信息",httpMethod="POST")
    @ApiImplicitParams({@ApiImplicitParam(name="xmid",paramType="form",dataType = "Long",required=true,value = "项目id")})
    @ApiResponses({@ApiResponse(code=0,message="操作成功",response=Map.class),
    	@ApiResponse(code=1,message="操作失败",response=Map.class)})
    @RequiresAuthentication
    public Result<Map<String,Object>> getXmSgjdHzxxByXmid(Long xmid) {
		Map<String,Object> map=Maps.newHashMap();
		if(xmid==null) {
			return Result.ok(map);
		}
		//主要工期节点
		List<Map<String,Object>> jdWcslLits = xmGqjdbjService.getXmgqjdbjXx(xmid,"1");
		map.put("jdWcsl",getTotal(jdWcslLits));
		List<Map<String,Object>> jdWwcslLits = xmGqjdbjService.getXmgqjdbjXx(xmid,"2");
		map.put("jdWwcsl",getTotal(jdWwcslLits));
		
		//样板施工记录
		XmYbsgjlDO xmYbsgjlDO=new XmYbsgjlDO();
		xmYbsgjlDO.setIntxmid(xmid);
		xmYbsgjlDO.setFcbz(1);
		QueryWrapper<XmYbsgjlDO> xmYbsgjlQuery=new QueryWrapper<XmYbsgjlDO>(xmYbsgjlDO);
		QueryWrapper<XmYbsgjlDO> xmYbsgjlWcQuery=xmYbsgjlQuery.clone();
		QueryWrapper<XmYbsgjlDO> xmYbsgjlTgQuery =xmYbsgjlQuery.clone();
		map.put("ybWcsl", xmYbsgjlService.count(xmYbsgjlWcQuery.isNotNull("dtmwcrq")));
		map.put("ybTgsl", xmYbsgjlService.count(xmYbsgjlTgQuery.isNotNull("dtmsprq")));
		
		//材料样板审批记录
		XmClybspjlDO xmClybspjlDO=new XmClybspjlDO();
		xmClybspjlDO.setIntxmid(xmid);
		xmClybspjlDO.setFcbz(1);
		QueryWrapper<XmClybspjlDO> xmClybspjlQuery=new QueryWrapper<XmClybspjlDO>(xmClybspjlDO);
		QueryWrapper<XmClybspjlDO> xmClybspjlCountQuery=xmClybspjlQuery.clone();
		map.put("ybspZsl", xmClybspjlService.count(xmClybspjlCountQuery));
		QueryWrapper<XmClybspjlDO> xmClybspjlWcspQuery=xmClybspjlQuery.clone();
		QueryWrapper<XmClybspjlDO> xmClybspjlTgspQuery=xmClybspjlQuery.clone();
		/*int ybspWcsl=xmClybspjlService.count(xmClybspjlWcspQuery.inSql("intsplczt", " ( select id  from bj_splc_zt where (chrsplclx,intxh) in " + 
				"(select chrsplclx,max(intxh) maxxh  from bj_splc_zt where fcbz=1 and chrsplclx in ('clybspjls','clybspjlf') GROUP BY chrsplclx) " + 
				" )  "));*/
		List<String> wcWhereList=Lists.newArrayList();
		wcWhereList.add("wtg");
		wcWhereList.add("tg");
		int ybspWcsl=xmClybspjlService.count(xmClybspjlWcspQuery.in("chrspzt", wcWhereList));
		map.put("ybspWcsl", ybspWcsl);
		int ybspTgsl=xmClybspjlService.count(xmClybspjlTgspQuery.eq("chrspzt", "tg"));
		map.put("ybspTgsl", ybspTgsl);
		map.put("ybspList", xmClybspjlService.getSprSpsl(xmid));
		
		//质量缺陷报告
		XmZlqxbgDO xmZlqxbgDO=new XmZlqxbgDO();
		xmZlqxbgDO.setIntxmid(xmid);
		xmZlqxbgDO.setFcbz(1);
		QueryWrapper<XmZlqxbgDO> xmZlqxbgQuery=new QueryWrapper<XmZlqxbgDO>(xmZlqxbgDO);
		QueryWrapper<XmZlqxbgDO> xmZlqxbgCountQuery=xmZlqxbgQuery.clone();
		QueryWrapper<XmZlqxbgDO> xmZlqxbgYzgQuery=xmZlqxbgQuery.clone();
		map.put("qxWcsl", xmZlqxbgService.count(xmZlqxbgCountQuery));
		map.put("qxZgsl", xmZlqxbgService.count(xmZlqxbgYzgQuery.isNotNull("dtmzgwcrq")));
		
		//工程顾问变更记录
		XmBgsqjlDO xmBgsqjlDO=new XmBgsqjlDO();
		xmBgsqjlDO.setIntxmid(xmid);
		xmBgsqjlDO.setFcbz(1);
		QueryWrapper<XmBgsqjlDO> xmBgsqjlQuery=new QueryWrapper<XmBgsqjlDO>(xmBgsqjlDO);
		QueryWrapper<XmBgsqjlDO> xmBgsqjlCountQuery=xmBgsqjlQuery.clone();
		map.put("bgZsl", xmBgsqjlService.count(xmBgsqjlCountQuery));
		QueryWrapper<XmBgsqjlDO> xmBgsqjlWcspQuery=xmBgsqjlQuery.clone();
		QueryWrapper<XmBgsqjlDO> xmBgsqjlTgspQuery=xmBgsqjlQuery.clone();
		/*int bgjlWcsl=xmBgsqjlService.count(xmBgsqjlWcspQuery.inSql("intsplczt", " ( select id  from bj_splc_zt where (chrsplclx,intxh) in " + 
				"(select chrsplclx,max(intxh) maxxh  from bj_splc_zt where fcbz=1 and chrsplclx in ('bgjls','bgjlf') GROUP BY chrsplclx) " + 
				" )  "));*/
		int bgjlWcsl=xmBgsqjlService.count(xmBgsqjlWcspQuery.in("chrspzt",wcWhereList));
		map.put("bgWcsl", bgjlWcsl);
		int bgjlTgsl=xmBgsqjlService.count(xmBgsqjlTgspQuery.eq("chrspzt","tg"));
		map.put("bgTgsl", bgjlTgsl);
		map.put("bgList", xmBgsqjlService.getSprSpsl(xmid));
		
		//工程款申请/支付记录
		XmGckyzfqkDO xmGckyzfqkDO=new XmGckyzfqkDO();
		xmGckyzfqkDO.setIntxmid(xmid);
		xmGckyzfqkDO.setFcbz(1);
		QueryWrapper<XmGckyzfqkDO> xmGckyzfqkQuery=new QueryWrapper<XmGckyzfqkDO>(xmGckyzfqkDO);
		QueryWrapper<XmGckyzfqkDO> xmGckyzfqkCountQuery=xmGckyzfqkQuery.clone();
		map.put("zfZsl", xmGckyzfqkService.count(xmGckyzfqkCountQuery));
		QueryWrapper<XmGckyzfqkDO> xmGckyzfqkWcspQuery=xmGckyzfqkQuery.clone();
		QueryWrapper<XmGckyzfqkDO> xmGckyzfqkTgspQuery=xmGckyzfqkQuery.clone();
		/*int zfWcsl=xmGckyzfqkService.count(xmGckyzfqkWcspQuery.inSql("intsplcztid", " ( select id  from bj_splc_zt where (chrsplclx,intxh) in " + 
				"(select chrsplclx,max(intxh) maxxh  from bj_splc_zt where fcbz=1 and chrsplclx in ('zfqk') GROUP BY chrsplclx) " + 
				" )  "));*/
		int zfWcsl=xmGckyzfqkService.count(xmGckyzfqkWcspQuery.in("chrspzt", wcWhereList));
		map.put("zfWcsl", zfWcsl);
		int zfTgsl=xmGckyzfqkService.count(xmGckyzfqkTgspQuery.eq("chrspzt", "tg"));
		map.put("zfTgsl", zfTgsl);
		map.put("zfList", xmGckyzfqkService.getSprSpsl(xmid));
		
		//安全报告
		XmAqbgDO xmAqbgDO=new XmAqbgDO();
		xmAqbgDO.setIntxmid(xmid);
		xmAqbgDO.setFcbz(1);
		QueryWrapper<XmAqbgDO> xmAqbgQuery=new QueryWrapper<XmAqbgDO>(xmAqbgDO);
		QueryWrapper<XmAqbgDO> xmAqbgCountQuery=xmAqbgQuery.clone();
		map.put("aqZsl", xmAqbgService.count(xmAqbgCountQuery));
		QueryWrapper<XmAqbgDO> xmAqbgYzgQuery=xmAqbgQuery.clone();
		map.put("aqYzgsl", xmAqbgService.count(xmAqbgYzgQuery.isNotNull("dtmwczgrq")));
		return Result.ok(map);
	}
	
	private int getTotal(List<?> list) {
		return CollectionUtils.isEmpty(list)?0:list.size();
	}
}
