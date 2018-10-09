package com.zj.project.xm.xmdwmd.controller;


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

import com.zj.project.xm.xmdwmd.domain.XmDwmdDO;
import com.zj.project.xm.xmdwmd.service.XmDwmdService;


/**
 * 
 * <pre>
 * 项目基本信息-所有单位名单
 * </pre>
 * <small> 2018-10-09 21:32:05 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmDwmd")
public class XmDwmdController extends AdminBaseController {
	@Autowired
	private XmDwmdService xmDwmdService;
	
	@GetMapping()
	@RequiresPermissions("project:xmDwmd:xmDwmd")
	String XmDwmd(){
	    return Constant.PC_PREFIX+"project/xm/xmdwmd/xmDwmd";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmDwmd:xmDwmd")
	public Result<IPage<XmDwmdDO>> list(XmDwmdDO xmDwmdDTO){
        QueryWrapper<XmDwmdDO> wrapper = new QueryWrapper<XmDwmdDO>(xmDwmdDTO);
        IPage<XmDwmdDO> page = xmDwmdService.page(getPage(XmDwmdDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmDwmd:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmdwmd/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmDwmd:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmDwmdDO xmDwmd = xmDwmdService.getById(id);
		model.addAttribute("xmDwmd", xmDwmd);
	    return Constant.PC_PREFIX+"project/xm/xmdwmd/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmDwmd:add")
	public Result<String> save( XmDwmdDO xmDwmd){
		xmDwmdService.save(xmDwmd);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmDwmd:edit")
	public Result<String>  update( XmDwmdDO xmDwmd){
		xmDwmdService.updateById(xmDwmd);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmDwmd:remove")
	public Result<String>  remove( Long id){
		xmDwmdService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmDwmd:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmDwmdService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
