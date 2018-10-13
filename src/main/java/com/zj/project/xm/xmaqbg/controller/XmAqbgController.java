package com.zj.project.xm.xmaqbg.controller;


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

import com.zj.project.xm.xmaqbg.domain.XmAqbgDO;
import com.zj.project.xm.xmaqbg.service.XmAqbgService;


/**
 * 
 * <pre>
 * 安全报告
 * </pre>
 * <small> 2018-10-13 17:00:14 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmAqbg")
public class XmAqbgController extends AdminBaseController {
	@Autowired
	private XmAqbgService xmAqbgService;
	
	@GetMapping()
	@RequiresPermissions("project:xmAqbg:xmAqbg")
	String XmAqbg(){
	    return Constant.PC_PREFIX+"project/xm/xmaqbg/xmAqbg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmAqbg:xmAqbg")
	public Result<IPage<XmAqbgDO>> list(XmAqbgDO xmAqbgDTO){
        QueryWrapper<XmAqbgDO> wrapper = new QueryWrapper<XmAqbgDO>(xmAqbgDTO);
        IPage<XmAqbgDO> page = xmAqbgService.page(getPage(XmAqbgDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmAqbg:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmaqbg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmAqbg:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmAqbgDO xmAqbg = xmAqbgService.getById(id);
		model.addAttribute("xmAqbg", xmAqbg);
	    return Constant.PC_PREFIX+"project/xm/xmaqbg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmAqbg:add")
	public Result<String> save( XmAqbgDO xmAqbg){
		xmAqbgService.save(xmAqbg);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmAqbg:edit")
	public Result<String>  update( XmAqbgDO xmAqbg){
		xmAqbgService.updateById(xmAqbg);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmAqbg:remove")
	public Result<String>  remove( Long id){
		xmAqbgService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmAqbg:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmAqbgService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
