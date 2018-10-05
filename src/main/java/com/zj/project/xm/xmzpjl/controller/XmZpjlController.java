package com.zj.project.xm.xmzpjl.controller;


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

import com.zj.project.xm.xmzpjl.domain.XmZpjlDO;
import com.zj.project.xm.xmzpjl.service.XmZpjlService;


/**
 * 
 * <pre>
 * 项目基本信息-照片记录
 * </pre>
 * <small> 2018-10-03 21:21:16 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmZpjl")
public class XmZpjlController extends AdminBaseController {
	@Autowired
	private XmZpjlService xmZpjlService;
	
	@GetMapping()
	@RequiresPermissions("project:xmZpjl:xmZpjl")
	String XmZpjl(){
	    return Constant.PC_PREFIX+"project/xm/xmzpjl/xmZpjl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmZpjl:xmZpjl")
	public Result<IPage<XmZpjlDO>> list(XmZpjlDO xmZpjlDTO){
        QueryWrapper<XmZpjlDO> wrapper = new QueryWrapper<XmZpjlDO>(xmZpjlDTO);
        IPage<XmZpjlDO> page = xmZpjlService.page(getPage(XmZpjlDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmZpjl:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmzpjl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmZpjl:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmZpjlDO xmZpjl = xmZpjlService.getById(id);
		model.addAttribute("xmZpjl", xmZpjl);
	    return Constant.PC_PREFIX+"project/xm/xmzpjl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmZpjl:add")
	public Result<String> save( XmZpjlDO xmZpjl){
		xmZpjlService.save(xmZpjl);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmZpjl:edit")
	public Result<String>  update( XmZpjlDO xmZpjl){
		xmZpjlService.updateById(xmZpjl);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmZpjl:remove")
	public Result<String>  remove( Long id){
		xmZpjlService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmZpjl:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmZpjlService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
