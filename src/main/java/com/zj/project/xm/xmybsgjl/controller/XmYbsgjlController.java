package com.zj.project.xm.xmybsgjl.controller;


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

import com.zj.project.xm.xmybsgjl.domain.XmYbsgjlDO;
import com.zj.project.xm.xmybsgjl.service.XmYbsgjlService;


/**
 * 
 * <pre>
 * 样板施工记录
 * </pre>
 * <small> 2018-10-13 00:54:47 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmYbsgjl")
public class XmYbsgjlController extends AdminBaseController {
	@Autowired
	private XmYbsgjlService xmYbsgjlService;
	
	@GetMapping()
	@RequiresPermissions("project:xmYbsgjl:xmYbsgjl")
	String XmYbsgjl(){
	    return Constant.PC_PREFIX+"project/xm/xmybsgjl/xmYbsgjl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmYbsgjl:xmYbsgjl")
	public Result<IPage<XmYbsgjlDO>> list(XmYbsgjlDO xmYbsgjlDTO){
        QueryWrapper<XmYbsgjlDO> wrapper = new QueryWrapper<XmYbsgjlDO>(xmYbsgjlDTO);
        IPage<XmYbsgjlDO> page = xmYbsgjlService.page(getPage(XmYbsgjlDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmYbsgjl:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmybsgjl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmYbsgjl:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmYbsgjlDO xmYbsgjl = xmYbsgjlService.getById(id);
		model.addAttribute("xmYbsgjl", xmYbsgjl);
	    return Constant.PC_PREFIX+"project/xm/xmybsgjl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmYbsgjl:add")
	public Result<String> save( XmYbsgjlDO xmYbsgjl){
		xmYbsgjlService.save(xmYbsgjl);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmYbsgjl:edit")
	public Result<String>  update( XmYbsgjlDO xmYbsgjl){
		xmYbsgjlService.updateById(xmYbsgjl);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmYbsgjl:remove")
	public Result<String>  remove( Long id){
		xmYbsgjlService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmYbsgjl:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmYbsgjlService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
