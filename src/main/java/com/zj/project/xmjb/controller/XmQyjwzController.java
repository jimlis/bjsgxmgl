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

import com.zj.project.xmjb.domain.XmQyjwzDO;
import com.zj.project.xmjb.service.XmQyjwzService;


/**
 * 
 * <pre>
 * 所在区域及位置
 * </pre>
 * <small> 2018-10-01 22:46:44 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmQyjwz")
public class XmQyjwzController extends AdminBaseController {
	@Autowired
	private XmQyjwzService xmQyjwzService;
	
	@GetMapping()
	@RequiresPermissions("project:xmQyjwz:xmQyjwz")
	String XmQyjwz(){
	    return Constant.PC_PREFIX+"project/xmqyjwz/xmQyjwz";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmQyjwz:xmQyjwz")
	public Result<IPage<XmQyjwzDO>> list(XmQyjwzDO xmQyjwzDTO){
        QueryWrapper<XmQyjwzDO> wrapper = new QueryWrapper<XmQyjwzDO>(xmQyjwzDTO);
        IPage<XmQyjwzDO> page = xmQyjwzService.page(getPage(XmQyjwzDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmQyjwz:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xmqyjwz/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmQyjwz:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmQyjwzDO xmQyjwz = xmQyjwzService.getById(id);
		model.addAttribute("xmQyjwz", xmQyjwz);
	    return Constant.PC_PREFIX+"project/xmqyjwz/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmQyjwz:add")
	public Result<String> save( XmQyjwzDO xmQyjwz){
		xmQyjwzService.save(xmQyjwz);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmQyjwz:edit")
	public Result<String>  update( XmQyjwzDO xmQyjwz){
		xmQyjwzService.updateById(xmQyjwz);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmQyjwz:remove")
	public Result<String>  remove( Long id){
		xmQyjwzService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmQyjwz:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmQyjwzService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
