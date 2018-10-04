package com.zj.project.xm.xmxkz.controller;


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

import com.zj.project.xm.xmxkz.domain.XmXkzDO;
import com.zj.project.xm.xmxkz.service.XmXkzService;


/**
 * 
 * <pre>
 * 项目基本信息-许可证
 * </pre>
 * <small> 2018-10-04 18:10:12 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmXkz")
public class XmXkzController extends AdminBaseController {
	@Autowired
	private XmXkzService xmXkzService;
	
	@GetMapping()
	@RequiresPermissions("project:xmXkz:xmXkz")
	String XmXkz(){
	    return Constant.PC_PREFIX+"project/xm/xmxkz/xmXkz";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmXkz:xmXkz")
	public Result<IPage<XmXkzDO>> list(XmXkzDO xmXkzDTO){
        QueryWrapper<XmXkzDO> wrapper = new QueryWrapper<XmXkzDO>(xmXkzDTO);
        IPage<XmXkzDO> page = xmXkzService.page(getPage(XmXkzDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmXkz:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmxkz/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmXkz:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmXkzDO xmXkz = xmXkzService.getById(id);
		model.addAttribute("xmXkz", xmXkz);
	    return Constant.PC_PREFIX+"project/xm/xmxkz/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmXkz:add")
	public Result<String> save( XmXkzDO xmXkz){
		xmXkzService.save(xmXkz);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmXkz:edit")
	public Result<String>  update( XmXkzDO xmXkz){
		xmXkzService.updateById(xmXkz);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmXkz:remove")
	public Result<String>  remove( Long id){
		xmXkzService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmXkz:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmXkzService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
