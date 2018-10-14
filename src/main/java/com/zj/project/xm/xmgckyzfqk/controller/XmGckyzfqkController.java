package com.zj.project.xm.xmgckyzfqk.controller;


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

import com.zj.project.xm.xmgckyzfqk.domain.XmGckyzfqkDO;
import com.zj.project.xm.xmgckyzfqk.service.XmGckyzfqkService;


/**
 * 
 * <pre>
 * 工程款与支付情况:工程款申请/支付情况
 * </pre>
 * <small> 2018-10-14 10:40:37 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmGckyzfqk")
public class XmGckyzfqkController extends AdminBaseController {
	@Autowired
	private XmGckyzfqkService xmGckyzfqkService;
	
	@GetMapping()
	@RequiresPermissions("project:xmGckyzfqk:xmGckyzfqk")
	String XmGckyzfqk(){
	    return Constant.PC_PREFIX+"project/xm/xmgckyzfqk/xmGckyzfqk";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmGckyzfqk:xmGckyzfqk")
	public Result<IPage<XmGckyzfqkDO>> list(XmGckyzfqkDO xmGckyzfqkDTO){
        QueryWrapper<XmGckyzfqkDO> wrapper = new QueryWrapper<XmGckyzfqkDO>(xmGckyzfqkDTO);
        IPage<XmGckyzfqkDO> page = xmGckyzfqkService.page(getPage(XmGckyzfqkDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmGckyzfqk:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmgckyzfqk/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmGckyzfqk:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		XmGckyzfqkDO xmGckyzfqk = xmGckyzfqkService.getById(id);
		model.addAttribute("xmGckyzfqk", xmGckyzfqk);
	    return Constant.PC_PREFIX+"project/xm/xmgckyzfqk/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmGckyzfqk:add")
	public Result<String> save( XmGckyzfqkDO xmGckyzfqk){
		xmGckyzfqkService.save(xmGckyzfqk);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmGckyzfqk:edit")
	public Result<String>  update( XmGckyzfqkDO xmGckyzfqk){
		xmGckyzfqkService.updateById(xmGckyzfqk);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmGckyzfqk:remove")
	public Result<String>  remove( Integer id){
		xmGckyzfqkService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmGckyzfqk:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Integer[] ids){
		xmGckyzfqkService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
