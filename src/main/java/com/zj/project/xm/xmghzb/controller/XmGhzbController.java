package com.zj.project.xm.xmghzb.controller;


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

import com.zj.project.xm.xmghzb.domain.XmGhzbDO;
import com.zj.project.xm.xmghzb.service.XmGhzbService;


/**
 * 
 * <pre>
 * 项目基本信息-规划指标数据
 * </pre>
 * <small> 2018-10-04 18:35:49 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmGhzb")
public class XmGhzbController extends AdminBaseController {
	@Autowired
	private XmGhzbService xmGhzbService;
	
	@GetMapping()
	@RequiresPermissions("project:xmGhzb:xmGhzb")
	String XmGhzb(){
	    return Constant.PC_PREFIX+"project/xm/xmghzb/xmGhzb";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmGhzb:xmGhzb")
	public Result<IPage<XmGhzbDO>> list(XmGhzbDO xmGhzbDTO){
        QueryWrapper<XmGhzbDO> wrapper = new QueryWrapper<XmGhzbDO>(xmGhzbDTO);
        IPage<XmGhzbDO> page = xmGhzbService.page(getPage(XmGhzbDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmGhzb:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmghzb/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmGhzb:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmGhzbDO xmGhzb = xmGhzbService.getById(id);
		model.addAttribute("xmGhzb", xmGhzb);
	    return Constant.PC_PREFIX+"project/xm/xmghzb/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmGhzb:add")
	public Result<String> save( XmGhzbDO xmGhzb){
		xmGhzbService.save(xmGhzb);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmGhzb:edit")
	public Result<String>  update( XmGhzbDO xmGhzb){
		xmGhzbService.updateById(xmGhzb);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmGhzb:remove")
	public Result<String>  remove( Long id){
		xmGhzbService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmGhzb:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmGhzbService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
