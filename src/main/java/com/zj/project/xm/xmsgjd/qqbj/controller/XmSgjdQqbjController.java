package com.zj.project.xm.xmsgjd.qqbj.controller;


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

import com.zj.project.xm.xmsgjd.qqbj.domain.XmSgjdQqbjDO;
import com.zj.project.xm.xmsgjd.qqbj.service.XmSgjdQqbjService;


/**
 * 
 * <pre>
 * 项目基本信息-施工进度-前期报建
 * </pre>
 * <small> 2018-11-19 19:53:41 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdQqbj")
public class XmSgjdQqbjController extends AdminBaseController {
	@Autowired
	private XmSgjdQqbjService xmSgjdQqbjService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdQqbj:xmSgjdQqbj")
	String XmSgjdQqbj(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdqqbj/xmSgjdQqbj";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdQqbj:xmSgjdQqbj")
	public Result<IPage<XmSgjdQqbjDO>> list(XmSgjdQqbjDO xmSgjdQqbjDTO){
        QueryWrapper<XmSgjdQqbjDO> wrapper = new QueryWrapper<XmSgjdQqbjDO>(xmSgjdQqbjDTO);
        IPage<XmSgjdQqbjDO> page = xmSgjdQqbjService.page(getPage(XmSgjdQqbjDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdQqbj:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdqqbj/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdQqbj:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdQqbjDO xmSgjdQqbj = xmSgjdQqbjService.getById(id);
		model.addAttribute("xmSgjdQqbj", xmSgjdQqbj);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdqqbj/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdQqbj:add")
	public Result<String> save( XmSgjdQqbjDO xmSgjdQqbj){
		xmSgjdQqbjService.save(xmSgjdQqbj);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdQqbj:edit")
	public Result<String>  update( XmSgjdQqbjDO xmSgjdQqbj){
		xmSgjdQqbjService.updateById(xmSgjdQqbj);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdQqbj:remove")
	public Result<String>  remove( Long id){
		xmSgjdQqbjService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdQqbj:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdQqbjService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
