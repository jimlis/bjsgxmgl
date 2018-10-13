package com.zj.project.xm.xmsgjd.ecjgzx.controller;


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

import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxDO;
import com.zj.project.xm.xmsgjd.ecjgzx.service.XmSgjdEcjgzxService;


/**
 * 
 * <pre>
 * 二次结构装修：施工进度-二次结构、装修等施工
 * </pre>
 * <small> 2018-10-13 20:45:56 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdEcjgzx")
public class XmSgjdEcjgzxController extends AdminBaseController {
	@Autowired
	private XmSgjdEcjgzxService xmSgjdEcjgzxService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdEcjgzx:xmSgjdEcjgzx")
	String XmSgjdEcjgzx(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdecjgzx/xmSgjdEcjgzx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdEcjgzx:xmSgjdEcjgzx")
	public Result<IPage<XmSgjdEcjgzxDO>> list(XmSgjdEcjgzxDO xmSgjdEcjgzxDTO){
        QueryWrapper<XmSgjdEcjgzxDO> wrapper = new QueryWrapper<XmSgjdEcjgzxDO>(xmSgjdEcjgzxDTO);
        IPage<XmSgjdEcjgzxDO> page = xmSgjdEcjgzxService.page(getPage(XmSgjdEcjgzxDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdEcjgzx:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdecjgzx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdEcjgzx:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdEcjgzxDO xmSgjdEcjgzx = xmSgjdEcjgzxService.getById(id);
		model.addAttribute("xmSgjdEcjgzx", xmSgjdEcjgzx);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdecjgzx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdEcjgzx:add")
	public Result<String> save( XmSgjdEcjgzxDO xmSgjdEcjgzx){
		xmSgjdEcjgzxService.save(xmSgjdEcjgzx);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdEcjgzx:edit")
	public Result<String>  update( XmSgjdEcjgzxDO xmSgjdEcjgzx){
		xmSgjdEcjgzxService.updateById(xmSgjdEcjgzx);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdEcjgzx:remove")
	public Result<String>  remove( Long id){
		xmSgjdEcjgzxService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdEcjgzx:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdEcjgzxService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
