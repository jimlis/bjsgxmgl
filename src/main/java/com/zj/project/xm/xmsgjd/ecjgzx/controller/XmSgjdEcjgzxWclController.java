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

import com.zj.project.xm.xmsgjd.ecjgzx.domain.XmSgjdEcjgzxWclDO;
import com.zj.project.xm.xmsgjd.ecjgzx.service.XmSgjdEcjgzxWclService;


/**
 * 
 * <pre>
 * 施工进度-二次结构、装修等施工-完成量
 * </pre>
 * <small> 2018-10-13 20:45:57 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdEcjgzxWcl")
public class XmSgjdEcjgzxWclController extends AdminBaseController {
	@Autowired
	private XmSgjdEcjgzxWclService xmSgjdEcjgzxWclService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdEcjgzxWcl:xmSgjdEcjgzxWcl")
	String XmSgjdEcjgzxWcl(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdecjgzxwcl/xmSgjdEcjgzxWcl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdEcjgzxWcl:xmSgjdEcjgzxWcl")
	public Result<IPage<XmSgjdEcjgzxWclDO>> list(XmSgjdEcjgzxWclDO xmSgjdEcjgzxWclDTO){
        QueryWrapper<XmSgjdEcjgzxWclDO> wrapper = new QueryWrapper<XmSgjdEcjgzxWclDO>(xmSgjdEcjgzxWclDTO);
        IPage<XmSgjdEcjgzxWclDO> page = xmSgjdEcjgzxWclService.page(getPage(XmSgjdEcjgzxWclDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdEcjgzxWcl:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdecjgzxwcl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdEcjgzxWcl:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdEcjgzxWclDO xmSgjdEcjgzxWcl = xmSgjdEcjgzxWclService.getById(id);
		model.addAttribute("xmSgjdEcjgzxWcl", xmSgjdEcjgzxWcl);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdecjgzxwcl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdEcjgzxWcl:add")
	public Result<String> save( XmSgjdEcjgzxWclDO xmSgjdEcjgzxWcl){
		xmSgjdEcjgzxWclService.save(xmSgjdEcjgzxWcl);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdEcjgzxWcl:edit")
	public Result<String>  update( XmSgjdEcjgzxWclDO xmSgjdEcjgzxWcl){
		xmSgjdEcjgzxWclService.updateById(xmSgjdEcjgzxWcl);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdEcjgzxWcl:remove")
	public Result<String>  remove( Long id){
		xmSgjdEcjgzxWclService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdEcjgzxWcl:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdEcjgzxWclService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
