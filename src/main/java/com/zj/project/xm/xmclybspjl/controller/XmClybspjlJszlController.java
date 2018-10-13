package com.zj.project.xm.xmclybspjl.controller;


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

import com.zj.project.xm.xmclybspjl.domain.XmClybspjlJszlDO;
import com.zj.project.xm.xmclybspjl.service.XmClybspjlJszlService;


/**
 * 
 * <pre>
 * 材料样板审批记录-品牌及技术资料
 * </pre>
 * <small> 2018-10-13 18:36:00 | lijun</small>
 */
@Controller
@RequestMapping("/project/xmClybspjlJszl")
public class XmClybspjlJszlController extends AdminBaseController {
	@Autowired
	private XmClybspjlJszlService xmClybspjlJszlService;
	
	@GetMapping()
	@RequiresPermissions("project:xmClybspjlJszl:xmClybspjlJszl")
	String XmClybspjlJszl(){
	    return Constant.PC_PREFIX+"project/xm/xmclybspjljszl/xmClybspjlJszl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:xmClybspjlJszl:xmClybspjlJszl")
	public Result<IPage<XmClybspjlJszlDO>> list(XmClybspjlJszlDO xmClybspjlJszlDTO){
        QueryWrapper<XmClybspjlJszlDO> wrapper = new QueryWrapper<XmClybspjlJszlDO>(xmClybspjlJszlDTO);
        IPage<XmClybspjlJszlDO> page = xmClybspjlJszlService.page(getPage(XmClybspjlJszlDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:xmClybspjlJszl:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/xmclybspjljszl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:xmClybspjlJszl:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XmClybspjlJszlDO xmClybspjlJszl = xmClybspjlJszlService.getById(id);
		model.addAttribute("xmClybspjlJszl", xmClybspjlJszl);
	    return Constant.PC_PREFIX+"project/xm/xmclybspjljszl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:xmClybspjlJszl:add")
	public Result<String> save( XmClybspjlJszlDO xmClybspjlJszl){
		xmClybspjlJszlService.save(xmClybspjlJszl);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:xmClybspjlJszl:edit")
	public Result<String>  update( XmClybspjlJszlDO xmClybspjlJszl){
		xmClybspjlJszlService.updateById(xmClybspjlJszl);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:xmClybspjlJszl:remove")
	public Result<String>  remove( Long id){
		xmClybspjlJszlService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:xmClybspjlJszl:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		xmClybspjlJszlService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
