package com.zj.project.xm.xmgqjdbj.controller;


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

import com.zj.project.xm.xmgqjdbj.domain.XmGqjdbjDO;
import com.zj.project.xm.xmgqjdbj.service.XmGqjdbjService;


/**
 * 
 * <pre>
 * 项目基本信息-主要工期节点比较
 * </pre>
 * <small> 2018-10-24 21:18:21 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmGqjdbj")
public class XmGqjdbjController extends AdminBaseController {
	@Autowired
	private XmGqjdbjService xmGqjdbjService;
	
	@GetMapping()
	@RequiresPermissions("project:xmGqjdbj:xmGqjdbj")
	String XmGqjdbj(){
	    return Constant.PC_PREFIX+"project/xm/xmgqjdbj/xmGqjdbj";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmGqjdbj:xmGqjdbj")
	public Result<IPage<XmGqjdbjDO>> list(XmGqjdbjDO xmGqjdbjDTO){
        QueryWrapper<XmGqjdbjDO> wrapper = new QueryWrapper<XmGqjdbjDO>(xmGqjdbjDTO);
        IPage<XmGqjdbjDO> page = xmGqjdbjService.page(getPage(XmGqjdbjDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmGqjdbj:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmgqjdbj/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmGqjdbj:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmGqjdbjDO xmGqjdbj = xmGqjdbjService.getById(id);
		model.addAttribute("xmGqjdbj", xmGqjdbj);
	    return Constant.PC_PREFIX+"project/xm/xmgqjdbj/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmGqjdbj:add")
	public Result<String> save( XmGqjdbjDO xmGqjdbj){
		xmGqjdbjService.save(xmGqjdbj);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmGqjdbj:edit")
	public Result<String>  update( XmGqjdbjDO xmGqjdbj){
		xmGqjdbjService.updateById(xmGqjdbj);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmGqjdbj:remove")
	public Result<String>  remove( Long id){
		xmGqjdbjService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmGqjdbj:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmGqjdbjService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
