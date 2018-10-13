package com.zj.project.xm.xmsgjd.ztjgsg.controller;


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

import com.zj.project.xm.xmsgjd.ztjgsg.domain.XmSgjdZtjgsgDO;
import com.zj.project.xm.xmsgjd.ztjgsg.service.XmSgjdZtjgsgService;


/**
 * 
 * <pre>
 * 施工进度-主体结构施工
 * </pre>
 * <small> 2018-10-13 20:03:27 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdZtjgsg")
public class XmSgjdZtjgsgController extends AdminBaseController {
	@Autowired
	private XmSgjdZtjgsgService xmSgjdZtjgsgService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdZtjgsg:xmSgjdZtjgsg")
	String XmSgjdZtjgsg(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdztjgsg/xmSgjdZtjgsg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdZtjgsg:xmSgjdZtjgsg")
	public Result<IPage<XmSgjdZtjgsgDO>> list(XmSgjdZtjgsgDO xmSgjdZtjgsgDTO){
        QueryWrapper<XmSgjdZtjgsgDO> wrapper = new QueryWrapper<XmSgjdZtjgsgDO>(xmSgjdZtjgsgDTO);
        IPage<XmSgjdZtjgsgDO> page = xmSgjdZtjgsgService.page(getPage(XmSgjdZtjgsgDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdZtjgsg:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdztjgsg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdZtjgsg:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdZtjgsgDO xmSgjdZtjgsg = xmSgjdZtjgsgService.getById(id);
		model.addAttribute("xmSgjdZtjgsg", xmSgjdZtjgsg);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdztjgsg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdZtjgsg:add")
	public Result<String> save( XmSgjdZtjgsgDO xmSgjdZtjgsg){
		xmSgjdZtjgsgService.save(xmSgjdZtjgsg);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdZtjgsg:edit")
	public Result<String>  update( XmSgjdZtjgsgDO xmSgjdZtjgsg){
		xmSgjdZtjgsgService.updateById(xmSgjdZtjgsg);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdZtjgsg:remove")
	public Result<String>  remove( Long id){
		xmSgjdZtjgsgService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdZtjgsg:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdZtjgsgService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
