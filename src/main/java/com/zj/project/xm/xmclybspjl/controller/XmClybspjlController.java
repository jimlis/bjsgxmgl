package com.zj.project.xm.xmclybspjl.controller;


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

import com.zj.project.xm.xmclybspjl.domain.XmClybspjlDO;
import com.zj.project.xm.xmclybspjl.service.XmClybspjlService;


/**
 * 
 * <pre>
 * 材料样板审批记录
 * </pre>
 * <small> 2018-10-13 18:35:59 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmClybspjl")
public class XmClybspjlController extends AdminBaseController {
	@Autowired
	private XmClybspjlService xmClybspjlService;
	
	@GetMapping()
	@RequiresPermissions("project:xmClybspjl:xmClybspjl")
	String XmClybspjl(){
	    return Constant.PC_PREFIX+"project/xm/xmclybspjl/xmClybspjl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmClybspjl:xmClybspjl")
	public Result<IPage<XmClybspjlDO>> list(XmClybspjlDO xmClybspjlDTO){
        QueryWrapper<XmClybspjlDO> wrapper = new QueryWrapper<XmClybspjlDO>(xmClybspjlDTO);
        IPage<XmClybspjlDO> page = xmClybspjlService.page(getPage(XmClybspjlDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmClybspjl:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmclybspjl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmClybspjl:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmClybspjlDO xmClybspjl = xmClybspjlService.getById(id);
		model.addAttribute("xmClybspjl", xmClybspjl);
	    return Constant.PC_PREFIX+"project/xm/xmclybspjl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmClybspjl:add")
	public Result<String> save( XmClybspjlDO xmClybspjl){
		xmClybspjlService.save(xmClybspjl);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmClybspjl:edit")
	public Result<String>  update( XmClybspjlDO xmClybspjl){
		xmClybspjlService.updateById(xmClybspjl);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmClybspjl:remove")
	public Result<String>  remove( Long id){
		xmClybspjlService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmClybspjl:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmClybspjlService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
