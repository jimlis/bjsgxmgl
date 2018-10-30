package com.zj.project.xm.splczt.controller;


import java.util.Arrays;
import java.util.Date;

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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import com.zj.project.xm.splczt.domain.SplcZtDO;
import com.zj.project.xm.splczt.service.SplcZtService;


/**
 * 
 * <pre>
 * 审批流程状态
 * </pre>
 * <small> 2018-10-30 22:57:34 | lijun</small>
 */
@Controller
@RequestMapping("/project/splcZt")
public class SplcZtController extends AdminBaseController {
	@Autowired
	private SplcZtService splcZtService;
	
	@GetMapping()
	@RequiresPermissions("project:splcZt:splcZt")
	String SplcZt(){
	    return Constant.PC_PREFIX+"project/xm/splczt/splcZt";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:splcZt:splcZt")
	public Result<IPage<SplcZtDO>> list(SplcZtDO splcZtDTO){
		splcZtDTO.setFcbz(1);
        QueryWrapper<SplcZtDO> wrapper = new QueryWrapper<SplcZtDO>(splcZtDTO).orderByAsc("intxh");
        IPage<SplcZtDO> page = splcZtService.page(getPage(SplcZtDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:splcZt:add")
	String add(){
	    return Constant.PC_PREFIX+"project/xm/splczt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:splcZt:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SplcZtDO splcZt = splcZtService.getById(id);
		model.addAttribute("splcZt", splcZt);
	    return Constant.PC_PREFIX+"project/xm/splczt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:splcZt:add")
	public Result<String> save( SplcZtDO splcZt){
		splcZt.setGxsj(new Date());
		splcZt.setFcbz(1);
		splcZtService.save(splcZt);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:splcZt:edit")
	public Result<String>  update( SplcZtDO splcZt){
		splcZt.setGxsj(new Date());
		splcZtService.updateById(splcZt);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:splcZt:remove")
	public Result<String>  remove( Long id){
		SplcZtDO splcZtDO=new  SplcZtDO();
		splcZtDO.setId(id);
		splcZtDO.setFcbz(0);
		splcZtDO.setGxsj(new Date());
		splcZtService.updateById(splcZtDO);
		//splcZtService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:splcZt:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		if(ids.length==0)Result.ok();
		Arrays.asList(ids).forEach(id->{
			SplcZtDO splcZtDO=new  SplcZtDO();
			splcZtDO.setId(id);
			splcZtDO.setFcbz(0);
			splcZtDO.setGxsj(new Date());
			splcZtService.updateById(splcZtDO);
		});
		return Result.ok();
	}
	
}
