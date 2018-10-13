package com.zj.project.xm.xmsgjd.jcsg.controller;


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

import com.zj.project.xm.xmsgjd.jcsg.domain.XmSgjdJcsgDO;
import com.zj.project.xm.xmsgjd.jcsg.service.XmSgjdJcsgService;


/**
 * 
 * <pre>
 * 项目基本信息-施工进度-基础施工
 * </pre>
 * <small> 2018-10-13 19:32:53 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdJcsg")
public class XmSgjdJcsgController extends AdminBaseController {
	@Autowired
	private XmSgjdJcsgService xmSgjdJcsgService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdJcsg:xmSgjdJcsg")
	String XmSgjdJcsg(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdjcsg/xmSgjdJcsg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdJcsg:xmSgjdJcsg")
	public Result<IPage<XmSgjdJcsgDO>> list(XmSgjdJcsgDO xmSgjdJcsgDTO){
        QueryWrapper<XmSgjdJcsgDO> wrapper = new QueryWrapper<XmSgjdJcsgDO>(xmSgjdJcsgDTO);
        IPage<XmSgjdJcsgDO> page = xmSgjdJcsgService.page(getPage(XmSgjdJcsgDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdJcsg:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdjcsg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdJcsg:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdJcsgDO xmSgjdJcsg = xmSgjdJcsgService.getById(id);
		model.addAttribute("xmSgjdJcsg", xmSgjdJcsg);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdjcsg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdJcsg:add")
	public Result<String> save( XmSgjdJcsgDO xmSgjdJcsg){
		xmSgjdJcsgService.save(xmSgjdJcsg);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdJcsg:edit")
	public Result<String>  update( XmSgjdJcsgDO xmSgjdJcsg){
		xmSgjdJcsgService.updateById(xmSgjdJcsg);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdJcsg:remove")
	public Result<String>  remove( Long id){
		xmSgjdJcsgService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdJcsg:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdJcsgService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
