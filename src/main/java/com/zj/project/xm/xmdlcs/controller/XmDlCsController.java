package com.zj.project.xm.xmdlcs.controller;


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

import com.zj.project.xm.xmdlcs.domain.XmDlCsDO;
import com.zj.project.xm.xmdlcs.service.XmDlCsService;


/**
 * 
 * <pre>
 * 项目基本信息-栋楼-层数,记录每层情况
 * </pre>
 * <small> 2018-10-13 20:26:30 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmDlCs")
public class XmDlCsController extends AdminBaseController {
	@Autowired
	private XmDlCsService xmDlCsService;
	
	@GetMapping()
	@RequiresPermissions("project:xmDlCs:xmDlCs")
	String XmDlCs(){
	    return Constant.PC_PREFIX+"project/xm/xmdlcs/xmDlCs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmDlCs:xmDlCs")
	public Result<IPage<XmDlCsDO>> list(XmDlCsDO xmDlCsDTO){
        QueryWrapper<XmDlCsDO> wrapper = new QueryWrapper<XmDlCsDO>(xmDlCsDTO);
        IPage<XmDlCsDO> page = xmDlCsService.page(getPage(XmDlCsDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmDlCs:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmdlcs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmDlCs:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmDlCsDO xmDlCs = xmDlCsService.getById(id);
		model.addAttribute("xmDlCs", xmDlCs);
	    return Constant.PC_PREFIX+"project/xm/xmdlcs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmDlCs:add")
	public Result<String> save( XmDlCsDO xmDlCs){
		xmDlCsService.save(xmDlCs);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmDlCs:edit")
	public Result<String>  update( XmDlCsDO xmDlCs){
		xmDlCsService.updateById(xmDlCs);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmDlCs:remove")
	public Result<String>  remove( Long id){
		xmDlCsService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmDlCs:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmDlCsService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
