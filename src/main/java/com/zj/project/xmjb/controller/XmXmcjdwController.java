package com.zj.project.xmjb.controller;


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

import com.zj.project.xmjb.domain.XmXmcjdwDO;
import com.zj.project.xmjb.service.XmXmcjdwService;


/**
 * 
 * <pre>
 * 项目承建各方名称
 * </pre>
 * <small> 2018-09-25 22:20:27 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmXmcjdw")
public class XmXmcjdwController extends AdminBaseController {
	@Autowired
	private XmXmcjdwService xmXmcjdwService;
	
	@GetMapping()
	@RequiresPermissions("project:xmXmcjdw:xmXmcjdw")
	String XmXmcjdw(){
	    return Constant.PC_PREFIX+"project/xmxmcjdw/xmXmcjdw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmXmcjdw:xmXmcjdw")
	public Result<IPage<XmXmcjdwDO>> list(XmXmcjdwDO xmXmcjdwDTO){
        QueryWrapper<XmXmcjdwDO> wrapper = new QueryWrapper<XmXmcjdwDO>(xmXmcjdwDTO);
        IPage<XmXmcjdwDO> page = xmXmcjdwService.page(getPage(XmXmcjdwDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmXmcjdw:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xmxmcjdw/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmXmcjdw:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmXmcjdwDO xmXmcjdw = xmXmcjdwService.getById(id);
		model.addAttribute("xmXmcjdw", xmXmcjdw);
	    return Constant.PC_PREFIX+"project/xmxmcjdw/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmXmcjdw:add")
	public Result<String> save( XmXmcjdwDO xmXmcjdw){
		xmXmcjdwService.save(xmXmcjdw);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmXmcjdw:edit")
	public Result<String>  update( XmXmcjdwDO xmXmcjdw){
		xmXmcjdwService.updateById(xmXmcjdw);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmXmcjdw:remove")
	public Result<String>  remove( Long id){
		xmXmcjdwService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmXmcjdw:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmXmcjdwService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
