package com.zj.project.xm.xmzfxcyzxys.controller;


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

import com.zj.project.xm.xmzfxcyzxys.domain.XmZfxcyzxysDO;
import com.zj.project.xm.xmzfxcyzxys.service.XmZfxcyzxysService;


/**
 * 
 * <pre>
 * 政府巡查与专项验收：政府部门巡查/专项验收记录
 * </pre>
 * <small> 2018-10-06 09:34:15 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmZfxcyzxys")
public class XmZfxcyzxysController extends AdminBaseController {
	@Autowired
	private XmZfxcyzxysService xmZfxcyzxysService;
	
	@GetMapping()
	@RequiresPermissions("project:xmZfxcyzxys:xmZfxcyzxys")
	String XmZfxcyzxys(){
	    return Constant.PC_PREFIX+"project/xm/xmzfxcyzxys/xmZfxcyzxys";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmZfxcyzxys:xmZfxcyzxys")
	public Result<IPage<XmZfxcyzxysDO>> list(XmZfxcyzxysDO xmZfxcyzxysDTO){
        QueryWrapper<XmZfxcyzxysDO> wrapper = new QueryWrapper<XmZfxcyzxysDO>(xmZfxcyzxysDTO);
        IPage<XmZfxcyzxysDO> page = xmZfxcyzxysService.page(getPage(XmZfxcyzxysDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmZfxcyzxys:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmzfxcyzxys/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmZfxcyzxys:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmZfxcyzxysDO xmZfxcyzxys = xmZfxcyzxysService.getById(id);
		model.addAttribute("xmZfxcyzxys", xmZfxcyzxys);
	    return Constant.PC_PREFIX+"project/xm/xmzfxcyzxys/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmZfxcyzxys:add")
	public Result<String> save( XmZfxcyzxysDO xmZfxcyzxys){
		xmZfxcyzxysService.save(xmZfxcyzxys);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmZfxcyzxys:edit")
	public Result<String>  update( XmZfxcyzxysDO xmZfxcyzxys){
		xmZfxcyzxysService.updateById(xmZfxcyzxys);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmZfxcyzxys:remove")
	public Result<String>  remove( Long id){
		xmZfxcyzxysService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmZfxcyzxys:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmZfxcyzxysService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
