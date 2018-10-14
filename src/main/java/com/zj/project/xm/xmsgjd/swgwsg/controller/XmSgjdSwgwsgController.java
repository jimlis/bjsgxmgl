package com.zj.project.xm.xmsgjd.swgwsg.controller;


import java.util.Arrays;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.web.controller.AdminBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zj.platform.common.util.Result;

import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwsgService;


/**
 * 
 * <pre>
 * 施工进度-室外管网施工
 * </pre>
 * <small> 2018-10-14 10:20:56 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdSwgwsg")
public class XmSgjdSwgwsgController extends AdminBaseController {
	@Autowired
	private XmSgjdSwgwsgService xmSgjdSwgwsgService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdSwgwsg:xmSgjdSwgwsg")
	String XmSgjdSwgwsg(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdswgwsg/xmSgjdSwgwsg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdSwgwsg:xmSgjdSwgwsg")
	public Result<IPage<XmSgjdSwgwsgDO>> list(XmSgjdSwgwsgDO xmSgjdSwgwsgDTO){
        QueryWrapper<XmSgjdSwgwsgDO> wrapper = new QueryWrapper<XmSgjdSwgwsgDO>(xmSgjdSwgwsgDTO);
        IPage<XmSgjdSwgwsgDO> page = xmSgjdSwgwsgService.page(getPage(XmSgjdSwgwsgDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdSwgwsg:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdswgwsg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdSwgwsg:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdSwgwsgDO xmSgjdSwgwsg = xmSgjdSwgwsgService.getById(id);
		model.addAttribute("xmSgjdSwgwsg", xmSgjdSwgwsg);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdswgwsg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdSwgwsg:add")
	public Result<String> save( XmSgjdSwgwsgDO xmSgjdSwgwsg){
		xmSgjdSwgwsgService.save(xmSgjdSwgwsg);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdSwgwsg:edit")
	public Result<String>  update( XmSgjdSwgwsgDO xmSgjdSwgwsg){
		xmSgjdSwgwsgService.updateById(xmSgjdSwgwsg);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdSwgwsg:remove")
	public Result<String>  remove( Long id){
		xmSgjdSwgwsgService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdSwgwsg:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdSwgwsgService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
