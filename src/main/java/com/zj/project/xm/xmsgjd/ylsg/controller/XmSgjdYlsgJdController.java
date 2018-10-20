package com.zj.project.xm.xmsgjd.ylsg.controller;


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

import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgJdDO;
import com.zj.project.xm.xmsgjd.ylsg.service.XmSgjdYlsgJdService;


/**
 * 
 * <pre>
 * 施工进度-园林施工-进度
 * </pre>
 * <small> 2018-10-20 23:35:49 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdYlsgJd")
public class XmSgjdYlsgJdController extends AdminBaseController {
	@Autowired
	private XmSgjdYlsgJdService xmSgjdYlsgJdService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdYlsgJd:xmSgjdYlsgJd")
	String XmSgjdYlsgJd(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdylsgjd/xmSgjdYlsgJd";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdYlsgJd:xmSgjdYlsgJd")
	public Result<IPage<XmSgjdYlsgJdDO>> list(XmSgjdYlsgJdDO xmSgjdYlsgJdDTO){
        QueryWrapper<XmSgjdYlsgJdDO> wrapper = new QueryWrapper<XmSgjdYlsgJdDO>(xmSgjdYlsgJdDTO);
        IPage<XmSgjdYlsgJdDO> page = xmSgjdYlsgJdService.page(getPage(XmSgjdYlsgJdDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdYlsgJd:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdylsgjd/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdYlsgJd:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdYlsgJdDO xmSgjdYlsgJd = xmSgjdYlsgJdService.getById(id);
		model.addAttribute("xmSgjdYlsgJd", xmSgjdYlsgJd);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdylsgjd/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdYlsgJd:add")
	public Result<String> save( XmSgjdYlsgJdDO xmSgjdYlsgJd){
		xmSgjdYlsgJdService.save(xmSgjdYlsgJd);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdYlsgJd:edit")
	public Result<String>  update( XmSgjdYlsgJdDO xmSgjdYlsgJd){
		xmSgjdYlsgJdService.updateById(xmSgjdYlsgJd);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdYlsgJd:remove")
	public Result<String>  remove( Long id){
		xmSgjdYlsgJdService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdYlsgJd:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdYlsgJdService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
