package com.zj.project.xm.xmsgjd.sgjdecjgzxqt.controller;


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

import com.zj.project.xm.xmsgjd.sgjdecjgzxqt.domain.XmSgjdEcjgzxQtDO;
import com.zj.project.xm.xmsgjd.sgjdecjgzxqt.service.XmSgjdEcjgzxQtService;


/**
 * 
 * <pre>
 * 施工进度-二次结构装修-其他
 * </pre>
 * <small> 2018-11-10 14:07:31 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmSgjdEcjgzxQt")
public class XmSgjdEcjgzxQtController extends AdminBaseController {
	@Autowired
	private XmSgjdEcjgzxQtService xmSgjdEcjgzxQtService;
	
	@GetMapping()
	@RequiresPermissions("project:xmSgjdEcjgzxQt:xmSgjdEcjgzxQt")
	String XmSgjdEcjgzxQt(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdecjgzxqt/xmSgjdEcjgzxQt";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmSgjdEcjgzxQt:xmSgjdEcjgzxQt")
	public Result<IPage<XmSgjdEcjgzxQtDO>> list(XmSgjdEcjgzxQtDO xmSgjdEcjgzxQtDTO){
        QueryWrapper<XmSgjdEcjgzxQtDO> wrapper = new QueryWrapper<XmSgjdEcjgzxQtDO>(xmSgjdEcjgzxQtDTO);
        IPage<XmSgjdEcjgzxQtDO> page = xmSgjdEcjgzxQtService.page(getPage(XmSgjdEcjgzxQtDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmSgjdEcjgzxQt:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdecjgzxqt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmSgjdEcjgzxQt:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmSgjdEcjgzxQtDO xmSgjdEcjgzxQt = xmSgjdEcjgzxQtService.getById(id);
		model.addAttribute("xmSgjdEcjgzxQt", xmSgjdEcjgzxQt);
	    return Constant.PC_PREFIX+"project/xm/xmsgjd/xmsgjdecjgzxqt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmSgjdEcjgzxQt:add")
	public Result<String> save( XmSgjdEcjgzxQtDO xmSgjdEcjgzxQt){
		xmSgjdEcjgzxQtService.save(xmSgjdEcjgzxQt);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmSgjdEcjgzxQt:edit")
	public Result<String>  update( XmSgjdEcjgzxQtDO xmSgjdEcjgzxQt){
		xmSgjdEcjgzxQtService.updateById(xmSgjdEcjgzxQt);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdEcjgzxQt:remove")
	public Result<String>  remove( Long id){
		xmSgjdEcjgzxQtService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmSgjdEcjgzxQt:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmSgjdEcjgzxQtService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
