package com.zj.project.xm.xmbgsqjl.controller;


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

import com.zj.project.xm.xmbgsqjl.domain.XmBgsqjlDO;
import com.zj.project.xm.xmbgsqjl.service.XmBgsqjlService;


/**
 * 
 * <pre>
 * 工程/顾问工作变更申请记录
 * </pre>
 * <small> 2018-10-13 16:22:31 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmBgsqjl")
public class XmBgsqjlController extends AdminBaseController {
	@Autowired
	private XmBgsqjlService xmBgsqjlService;
	
	@GetMapping()
	@RequiresPermissions("project:xmBgsqjl:xmBgsqjl")
	String XmBgsqjl(){
	    return Constant.PC_PREFIX+"project/xm/xmbgsqjl/xmBgsqjl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmBgsqjl:xmBgsqjl")
	public Result<IPage<XmBgsqjlDO>> list(XmBgsqjlDO xmBgsqjlDTO){
        QueryWrapper<XmBgsqjlDO> wrapper = new QueryWrapper<XmBgsqjlDO>(xmBgsqjlDTO);
        IPage<XmBgsqjlDO> page = xmBgsqjlService.page(getPage(XmBgsqjlDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmBgsqjl:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmbgsqjl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmBgsqjl:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmBgsqjlDO xmBgsqjl = xmBgsqjlService.getById(id);
		model.addAttribute("xmBgsqjl", xmBgsqjl);
	    return Constant.PC_PREFIX+"project/xm/xmbgsqjl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmBgsqjl:add")
	public Result<String> save( XmBgsqjlDO xmBgsqjl){
		xmBgsqjlService.save(xmBgsqjl);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmBgsqjl:edit")
	public Result<String>  update( XmBgsqjlDO xmBgsqjl){
		xmBgsqjlService.updateById(xmBgsqjl);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmBgsqjl:remove")
	public Result<String>  remove( Long id){
		xmBgsqjlService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmBgsqjl:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmBgsqjlService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
