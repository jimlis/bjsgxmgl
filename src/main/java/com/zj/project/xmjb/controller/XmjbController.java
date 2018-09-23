package com.zj.project.xmjb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import com.zj.project.xmjb.domain.XmjbDO;
import com.zj.project.xmjb.service.XmjbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;



/**
 * 
 * <pre>
 * 项目基本，所有项目相关表的主表
 * </pre>
 * <small> 2018-09-12 23:09:04 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmjb")
public class XmjbController extends AdminBaseController {
	@Autowired
	private XmjbService xmjbService;
	
	@GetMapping()
	@RequiresPermissions("project:xmjb:xmjb")
	String Xmjb(){
	    return "project/xmjb/xmjb";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmjb:xmjb")
	public Result<IPage<XmjbDO>> list(XmjbDO xmjbDTO){
        QueryWrapper<XmjbDO> wrapper = new QueryWrapper<XmjbDO>(xmjbDTO);
		IPage<XmjbDO> page = xmjbService.page(getPage(XmjbDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmjb:add")
	String add(){
	    return "project/xmjb/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmjb:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		XmjbDO xmjb = xmjbService.getById(id);
		model.addAttribute("xmjb", xmjb);
	    return "project/xmjb/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmjb:add")
	public Result<String> save( XmjbDO xmjb){
		xmjbService.save(xmjb);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmjb:edit")
	public Result<String>  update( XmjbDO xmjb){
		xmjbService.updateById(xmjb);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmjb:remove")
	public Result<String>  remove( Integer id){
		xmjbService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmjb:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Integer[] ids){
		xmjbService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}

	@ResponseBody
	@RequestMapping("/listAll")
	public List<XmjbDO> listAll(XmjbDO xmjbDTO){
		QueryWrapper<XmjbDO> wrapper = new QueryWrapper<XmjbDO>(xmjbDTO).eq("fcbz","1");;
		List<XmjbDO> list = xmjbService.list(wrapper);
		return list;
	}
	
}
