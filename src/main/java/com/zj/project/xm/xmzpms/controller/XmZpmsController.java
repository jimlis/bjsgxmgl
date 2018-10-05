package com.zj.project.xm.xmzpms.controller;


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

import com.zj.project.xm.xmzpms.domain.XmZpmsDO;
import com.zj.project.xm.xmzpms.service.XmZpmsService;


/**
 * 
 * <pre>
 * 项目基本信息-照片描述
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmZpms")
public class XmZpmsController extends AdminBaseController {
	@Autowired
	private XmZpmsService xmZpmsService;
	
	@GetMapping()
	@RequiresPermissions("project:xmZpms:xmZpms")
	String XmZpms(){
	    return Constant.PC_PREFIX+"project/xm/xmzpms/xmZpms";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmZpms:xmZpms")
	public Result<IPage<XmZpmsDO>> list(XmZpmsDO xmZpmsDTO){
        QueryWrapper<XmZpmsDO> wrapper = new QueryWrapper<XmZpmsDO>(xmZpmsDTO);
        IPage<XmZpmsDO> page = xmZpmsService.page(getPage(XmZpmsDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmZpms:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmzpms/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmZpms:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmZpmsDO xmZpms = xmZpmsService.getById(id);
		model.addAttribute("xmZpms", xmZpms);
	    return Constant.PC_PREFIX+"project/xm/xmzpms/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmZpms:add")
	public Result<String> save( XmZpmsDO xmZpms){
		xmZpmsService.save(xmZpms);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmZpms:edit")
	public Result<String>  update( XmZpmsDO xmZpms){
		xmZpmsService.updateById(xmZpms);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmZpms:remove")
	public Result<String>  remove( Long id){
		xmZpmsService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmZpms:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmZpmsService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
