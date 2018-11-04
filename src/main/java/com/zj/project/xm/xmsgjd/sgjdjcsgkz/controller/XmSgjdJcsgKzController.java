package com.zj.project.xm.xmsgjd.sgjdjcsgkz.controller;


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

import com.zj.project.xm.xmsgjd.sgjdjcsgkz.domain.XmSgjdJcsgKzDO;
import com.zj.project.xm.xmsgjd.sgjdjcsgkz.service.XmSgjdJcsgKzService;


/**
 * 
 * <pre>
 * 施工进度-基础施工扩展
 * </pre>
 * <small> 2018-11-04 20:55:42 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdJcsgKz")
public class XmSgjdJcsgKzController extends AdminBaseController {
	@Autowired
	private XmSgjdJcsgKzService xmSgjdJcsgKzService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdJcsgKz:xmSgjdJcsgKz")
	String XmSgjdJcsgKz(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdjcsgkz/xmSgjdJcsgKz";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdJcsgKz:xmSgjdJcsgKz")
	public Result<IPage<XmSgjdJcsgKzDO>> list(XmSgjdJcsgKzDO xmSgjdJcsgKzDTO){
        QueryWrapper<XmSgjdJcsgKzDO> wrapper = new QueryWrapper<XmSgjdJcsgKzDO>(xmSgjdJcsgKzDTO);
        IPage<XmSgjdJcsgKzDO> page = xmSgjdJcsgKzService.page(getPage(XmSgjdJcsgKzDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdJcsgKz:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdjcsgkz/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdJcsgKz:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdJcsgKzDO xmSgjdJcsgKz = xmSgjdJcsgKzService.getById(id);
		model.addAttribute("xmSgjdJcsgKz", xmSgjdJcsgKz);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdjcsgkz/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdJcsgKz:add")
	public Result<String> save( XmSgjdJcsgKzDO xmSgjdJcsgKz){
		xmSgjdJcsgKzService.save(xmSgjdJcsgKz);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdJcsgKz:edit")
	public Result<String>  update( XmSgjdJcsgKzDO xmSgjdJcsgKz){
		xmSgjdJcsgKzService.updateById(xmSgjdJcsgKz);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdJcsgKz:remove")
	public Result<String>  remove( Long id){
		xmSgjdJcsgKzService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdJcsgKz:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdJcsgKzService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
