package com.zj.project.xm.xmsgjd.swgwsg.controller;


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

import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwlxDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwlxService;


/**
 * 
 * <pre>
 * 施工进度-室外管网类型，没个项目有多个室外管网类型。
 * </pre>
 * <small> 2018-10-20 20:40:32 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdSwgwlx")
public class XmSgjdSwgwlxController extends AdminBaseController {
	@Autowired
	private XmSgjdSwgwlxService xmSgjdSwgwlxService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdSwgwlx:xmSgjdSwgwlx")
	String XmSgjdSwgwlx(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdswgwlx/xmSgjdSwgwlx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdSwgwlx:xmSgjdSwgwlx")
	public Result<IPage<XmSgjdSwgwlxDO>> list(XmSgjdSwgwlxDO xmSgjdSwgwlxDTO){
        QueryWrapper<XmSgjdSwgwlxDO> wrapper = new QueryWrapper<XmSgjdSwgwlxDO>(xmSgjdSwgwlxDTO);
        IPage<XmSgjdSwgwlxDO> page = xmSgjdSwgwlxService.page(getPage(XmSgjdSwgwlxDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdSwgwlx:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdswgwlx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdSwgwlx:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdSwgwlxDO xmSgjdSwgwlx = xmSgjdSwgwlxService.getById(id);
		model.addAttribute("xmSgjdSwgwlx", xmSgjdSwgwlx);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdswgwlx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdSwgwlx:add")
	public Result<String> save( XmSgjdSwgwlxDO xmSgjdSwgwlx){
		xmSgjdSwgwlxService.save(xmSgjdSwgwlx);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdSwgwlx:edit")
	public Result<String>  update( XmSgjdSwgwlxDO xmSgjdSwgwlx){
		xmSgjdSwgwlxService.updateById(xmSgjdSwgwlx);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdSwgwlx:remove")
	public Result<String>  remove( Long id){
		xmSgjdSwgwlxService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdSwgwlx:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdSwgwlxService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
