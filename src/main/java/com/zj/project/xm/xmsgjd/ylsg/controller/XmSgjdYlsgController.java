package com.zj.project.xm.xmsgjd.ylsg.controller;


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

import com.zj.project.xm.xmsgjd.ylsg.domain.XmSgjdYlsgDO;
import com.zj.project.xm.xmsgjd.ylsg.service.XmSgjdYlsgService;


/**
 * 
 * <pre>
 * 施工进度-园林施工
 * </pre>
 * <small> 2018-10-13 22:22:17 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdYlsg")
public class XmSgjdYlsgController extends AdminBaseController {
	@Autowired
	private XmSgjdYlsgService xmSgjdYlsgService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdYlsg:xmSgjdYlsg")
	String XmSgjdYlsg(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdylsg/xmSgjdYlsg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdYlsg:xmSgjdYlsg")
	public Result<IPage<XmSgjdYlsgDO>> list(XmSgjdYlsgDO xmSgjdYlsgDTO){
        QueryWrapper<XmSgjdYlsgDO> wrapper = new QueryWrapper<XmSgjdYlsgDO>(xmSgjdYlsgDTO);
        IPage<XmSgjdYlsgDO> page = xmSgjdYlsgService.page(getPage(XmSgjdYlsgDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdYlsg:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdylsg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdYlsg:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdYlsgDO xmSgjdYlsg = xmSgjdYlsgService.getById(id);
		model.addAttribute("xmSgjdYlsg", xmSgjdYlsg);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdylsg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdYlsg:add")
	public Result<String> save( XmSgjdYlsgDO xmSgjdYlsg){
		xmSgjdYlsgService.save(xmSgjdYlsg);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdYlsg:edit")
	public Result<String>  update( XmSgjdYlsgDO xmSgjdYlsg){
		xmSgjdYlsgService.updateById(xmSgjdYlsg);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdYlsg:remove")
	public Result<String>  remove( Long id){
		xmSgjdYlsgService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdYlsg:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdYlsgService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
