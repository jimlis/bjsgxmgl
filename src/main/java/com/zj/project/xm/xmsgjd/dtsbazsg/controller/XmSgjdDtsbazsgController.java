package com.zj.project.xm.xmsgjd.dtsbazsg.controller;


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

import com.zj.project.xm.xmsgjd.dtsbazsg.domain.XmSgjdDtsbazsgDO;
import com.zj.project.xm.xmsgjd.dtsbazsg.service.XmSgjdDtsbazsgService;


/**
 * 
 * <pre>
 * 施工进度-电梯设备安装施工
 * </pre>
 * <small> 2018-10-13 21:17:22 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdDtsbazsg")
public class XmSgjdDtsbazsgController extends AdminBaseController {
	@Autowired
	private XmSgjdDtsbazsgService xmSgjdDtsbazsgService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdDtsbazsg:xmSgjdDtsbazsg")
	String XmSgjdDtsbazsg(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjddtsbazsg/xmSgjdDtsbazsg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdDtsbazsg:xmSgjdDtsbazsg")
	public Result<IPage<XmSgjdDtsbazsgDO>> list(XmSgjdDtsbazsgDO xmSgjdDtsbazsgDTO){
        QueryWrapper<XmSgjdDtsbazsgDO> wrapper = new QueryWrapper<XmSgjdDtsbazsgDO>(xmSgjdDtsbazsgDTO);
        IPage<XmSgjdDtsbazsgDO> page = xmSgjdDtsbazsgService.page(getPage(XmSgjdDtsbazsgDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdDtsbazsg:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjddtsbazsg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdDtsbazsg:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdDtsbazsgDO xmSgjdDtsbazsg = xmSgjdDtsbazsgService.getById(id);
		model.addAttribute("xmSgjdDtsbazsg", xmSgjdDtsbazsg);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjddtsbazsg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdDtsbazsg:add")
	public Result<String> save( XmSgjdDtsbazsgDO xmSgjdDtsbazsg){
		xmSgjdDtsbazsgService.save(xmSgjdDtsbazsg);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdDtsbazsg:edit")
	public Result<String>  update( XmSgjdDtsbazsgDO xmSgjdDtsbazsg){
		xmSgjdDtsbazsgService.updateById(xmSgjdDtsbazsg);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdDtsbazsg:remove")
	public Result<String>  remove( Long id){
		xmSgjdDtsbazsgService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdDtsbazsg:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdDtsbazsgService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
