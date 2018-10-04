package com.zj.project.xm.xmdl.controller;


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

import com.zj.project.xm.xmdl.domain.XmDlDO;
import com.zj.project.xm.xmdl.service.XmDlService;


/**
 * 
 * <pre>
 * 项目基本信息-栋楼,记录每栋楼名称及编号
 * </pre>
 * <small> 2018-09-28 22:39:21 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmDl")
public class XmDlController extends AdminBaseController {
	@Autowired
	private XmDlService xmDlService;
	
	@GetMapping()
	@RequiresPermissions("project:xmDl:xmDl")
	String XmDl(){
	    return Constant.PC_PREFIX+"project/xm/xmdl/xmDl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmDl:xmDl")
	public Result<IPage<XmDlDO>> list(XmDlDO xmDlDTO){
        QueryWrapper<XmDlDO> wrapper = new QueryWrapper<XmDlDO>(xmDlDTO);
        IPage<XmDlDO> page = xmDlService.page(getPage(XmDlDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmDl:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmdl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmDl:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		XmDlDO xmDl = xmDlService.getById(id);
		model.addAttribute("xmDl", xmDl);
	    return Constant.PC_PREFIX+"project/xm/xmdl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmDl:add")
	public Result<String> save( XmDlDO xmDl){
		xmDlService.save(xmDl);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmDl:edit")
	public Result<String>  update( XmDlDO xmDl){
		xmDlService.updateById(xmDl);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmDl:remove")
	public Result<String>  remove( Integer id){
		xmDlService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmDl:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Integer[] ids){
		xmDlService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
