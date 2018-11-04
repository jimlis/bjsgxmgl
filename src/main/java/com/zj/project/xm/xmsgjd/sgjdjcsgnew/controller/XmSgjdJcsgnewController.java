package com.zj.project.xm.xmsgjd.sgjdjcsgnew.controller;


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

import com.zj.project.xm.xmsgjd.sgjdjcsgnew.domain.XmSgjdJcsgnewDO;
import com.zj.project.xm.xmsgjd.sgjdjcsgnew.service.XmSgjdJcsgnewService;


/**
 * 
 * <pre>
 * 施工进度-基础施工新
 * </pre>
 * <small> 2018-11-04 20:58:53 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdJcsgnew")
public class XmSgjdJcsgnewController extends AdminBaseController {
	@Autowired
	private XmSgjdJcsgnewService xmSgjdJcsgnewService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdJcsgnew:xmSgjdJcsgnew")
	String XmSgjdJcsgnew(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdjcsgnew/xmSgjdJcsgnew";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdJcsgnew:xmSgjdJcsgnew")
	public Result<IPage<XmSgjdJcsgnewDO>> list(XmSgjdJcsgnewDO xmSgjdJcsgnewDTO){
        QueryWrapper<XmSgjdJcsgnewDO> wrapper = new QueryWrapper<XmSgjdJcsgnewDO>(xmSgjdJcsgnewDTO);
        IPage<XmSgjdJcsgnewDO> page = xmSgjdJcsgnewService.page(getPage(XmSgjdJcsgnewDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdJcsgnew:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdjcsgnew/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdJcsgnew:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdJcsgnewDO xmSgjdJcsgnew = xmSgjdJcsgnewService.getById(id);
		model.addAttribute("xmSgjdJcsgnew", xmSgjdJcsgnew);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdjcsgnew/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdJcsgnew:add")
	public Result<String> save( XmSgjdJcsgnewDO xmSgjdJcsgnew){
		xmSgjdJcsgnewService.save(xmSgjdJcsgnew);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdJcsgnew:edit")
	public Result<String>  update( XmSgjdJcsgnewDO xmSgjdJcsgnew){
		xmSgjdJcsgnewService.updateById(xmSgjdJcsgnew);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdJcsgnew:remove")
	public Result<String>  remove( Long id){
		xmSgjdJcsgnewService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdJcsgnew:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdJcsgnewService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
