package com.zj.project.xm.xmsgjd.sgjdztjgsgkz.controller;


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

import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.domain.XmSgjdZtjgsgKzDO;
import com.zj.project.xm.xmsgjd.sgjdztjgsgkz.service.XmSgjdZtjgsgKzService;


/**
 * 
 * <pre>
 * 施工进度-主体施工扩展
 * </pre>
 * <small> 2018-11-05 19:27:20 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdZtjgsgKz")
public class XmSgjdZtjgsgKzController extends AdminBaseController {
	@Autowired
	private XmSgjdZtjgsgKzService xmSgjdZtjgsgKzService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdZtjgsgKz:xmSgjdZtjgsgKz")
	String XmSgjdZtjgsgKz(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdztjgsgkz/xmSgjdZtjgsgKz";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdZtjgsgKz:xmSgjdZtjgsgKz")
	public Result<IPage<XmSgjdZtjgsgKzDO>> list(XmSgjdZtjgsgKzDO xmSgjdZtjgsgKzDTO){
        QueryWrapper<XmSgjdZtjgsgKzDO> wrapper = new QueryWrapper<XmSgjdZtjgsgKzDO>(xmSgjdZtjgsgKzDTO);
        IPage<XmSgjdZtjgsgKzDO> page = xmSgjdZtjgsgKzService.page(getPage(XmSgjdZtjgsgKzDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdZtjgsgKz:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdztjgsgkz/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdZtjgsgKz:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdZtjgsgKzDO xmSgjdZtjgsgKz = xmSgjdZtjgsgKzService.getById(id);
		model.addAttribute("xmSgjdZtjgsgKz", xmSgjdZtjgsgKz);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdztjgsgkz/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdZtjgsgKz:add")
	public Result<String> save( XmSgjdZtjgsgKzDO xmSgjdZtjgsgKz){
		xmSgjdZtjgsgKzService.save(xmSgjdZtjgsgKz);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdZtjgsgKz:edit")
	public Result<String>  update( XmSgjdZtjgsgKzDO xmSgjdZtjgsgKz){
		xmSgjdZtjgsgKzService.updateById(xmSgjdZtjgsgKz);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdZtjgsgKz:remove")
	public Result<String>  remove( Long id){
		xmSgjdZtjgsgKzService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdZtjgsgKz:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdZtjgsgKzService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
