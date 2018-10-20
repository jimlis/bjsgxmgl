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

import com.zj.project.xm.xmsgjd.swgwsg.domain.XmSgjdSwgwsgJdDO;
import com.zj.project.xm.xmsgjd.swgwsg.service.XmSgjdSwgwsgJdService;


/**
 * 
 * <pre>
 * 施工进度-室外管网施工-进度，通过更新日期与类型生成多条进度内容。
 * </pre>
 * <small> 2018-10-20 20:40:33 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdSwgwsgJd")
public class XmSgjdSwgwsgJdController extends AdminBaseController {
	@Autowired
	private XmSgjdSwgwsgJdService xmSgjdSwgwsgJdService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdSwgwsgJd:xmSgjdSwgwsgJd")
	String XmSgjdSwgwsgJd(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdswgwsgjd/xmSgjdSwgwsgJd";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdSwgwsgJd:xmSgjdSwgwsgJd")
	public Result<IPage<XmSgjdSwgwsgJdDO>> list(XmSgjdSwgwsgJdDO xmSgjdSwgwsgJdDTO){
        QueryWrapper<XmSgjdSwgwsgJdDO> wrapper = new QueryWrapper<XmSgjdSwgwsgJdDO>(xmSgjdSwgwsgJdDTO);
        IPage<XmSgjdSwgwsgJdDO> page = xmSgjdSwgwsgJdService.page(getPage(XmSgjdSwgwsgJdDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdSwgwsgJd:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdswgwsgjd/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdSwgwsgJd:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdSwgwsgJdDO xmSgjdSwgwsgJd = xmSgjdSwgwsgJdService.getById(id);
		model.addAttribute("xmSgjdSwgwsgJd", xmSgjdSwgwsgJd);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdswgwsgjd/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdSwgwsgJd:add")
	public Result<String> save( XmSgjdSwgwsgJdDO xmSgjdSwgwsgJd){
		xmSgjdSwgwsgJdService.save(xmSgjdSwgwsgJd);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdSwgwsgJd:edit")
	public Result<String>  update( XmSgjdSwgwsgJdDO xmSgjdSwgwsgJd){
		xmSgjdSwgwsgJdService.updateById(xmSgjdSwgwsgJd);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdSwgwsgJd:remove")
	public Result<String>  remove( Long id){
		xmSgjdSwgwsgJdService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdSwgwsgJd:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdSwgwsgJdService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
