package com.zj.project.xm.xmzlqxbg.controller;


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

import com.zj.project.xm.xmzlqxbg.domain.XmZlqxbgDO;
import com.zj.project.xm.xmzlqxbg.service.XmZlqxbgService;


/**
 * 
 * <pre>
 * 质量缺陷报告
 * </pre>
 * <small> 2018-10-13 10:46:20 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmZlqxbg")
public class XmZlqxbgController extends AdminBaseController {
	@Autowired
	private XmZlqxbgService xmZlqxbgService;
	
	@GetMapping()
	@RequiresPermissions("project:xmZlqxbg:xmZlqxbg")
	String XmZlqxbg(){
	    return Constant.PC_PREFIX+"project/xm/xmzlqxbg/xmZlqxbg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmZlqxbg:xmZlqxbg")
	public Result<IPage<XmZlqxbgDO>> list(XmZlqxbgDO xmZlqxbgDTO){
        QueryWrapper<XmZlqxbgDO> wrapper = new QueryWrapper<XmZlqxbgDO>(xmZlqxbgDTO);
        IPage<XmZlqxbgDO> page = xmZlqxbgService.page(getPage(XmZlqxbgDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmZlqxbg:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmzlqxbg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmZlqxbg:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmZlqxbgDO xmZlqxbg = xmZlqxbgService.getById(id);
		model.addAttribute("xmZlqxbg", xmZlqxbg);
	    return Constant.PC_PREFIX+"project/xm/xmzlqxbg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmZlqxbg:add")
	public Result<String> save( XmZlqxbgDO xmZlqxbg){
		xmZlqxbgService.save(xmZlqxbg);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmZlqxbg:edit")
	public Result<String>  update( XmZlqxbgDO xmZlqxbg){
		xmZlqxbgService.updateById(xmZlqxbg);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmZlqxbg:remove")
	public Result<String>  remove( Long id){
		xmZlqxbgService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmZlqxbg:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmZlqxbgService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
