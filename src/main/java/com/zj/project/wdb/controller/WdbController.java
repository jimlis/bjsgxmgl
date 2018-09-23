package com.zj.project.wdb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import com.zj.project.wdb.domain.WdbDO;
import com.zj.project.wdb.service.WdbService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2018-09-09 20:40:48 | lijun</small>
 */
@Controller
@RequestMapping("/project/wdb")
public class WdbController extends AdminBaseController {
	@Autowired
	private WdbService wdbService;
	
	@GetMapping()
	@RequiresPermissions("project:wdb:wdb")
	String Wdb(){
	    return Constant.PC_PREFIX+"project/wdb/wdb";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:wdb:wdb")
	public Result<IPage<WdbDO>> list(WdbDO wdbDTO){
        QueryWrapper<WdbDO> wrapper = new QueryWrapper<WdbDO>().eq("fcbz",1).
				eq(StringUtils.isNotEmpty(wdbDTO.getType()),"type",wdbDTO.getType()).
				like(StringUtils.isNotEmpty(wdbDTO.getXmmc()),"xmmc",wdbDTO.getXmmc()).
				like(StringUtils.isNotEmpty(wdbDTO.getFileName()),"fileName",wdbDTO.getFileName());
		IPage<WdbDO> page = wdbService.page(getPage(WdbDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:wdb:add")
	String add(){
	    return Constant.PC_PREFIX+"project/wdb/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:wdb:edit")
	String edit(@PathVariable("id") Long id,Model model){
		WdbDO wdb = wdbService.getById(id);
		model.addAttribute("wdb", wdb);
	    return Constant.PC_PREFIX+"project/wdb/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:wdb:add")
	public Result<String> save(HttpServletRequest request,WdbDO wdb){
		String fileIds= Objects.toString(request.getParameter("fileIds"));
		try {
			wdbService.saveWdbxx(wdb,fileIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:wdb:edit")
	public Result<String>  update( WdbDO wdb){
		wdbService.updateById(wdb);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:wdb:remove")
	public Result<String>  remove( Long id){
		wdbService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:wdb:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		wdbService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}

	@GetMapping("/openFileDialog")
	@RequiresPermissions("project:wdb:wdb")
	String openFileDialog(String busType, Long busId,Model model){
		model.addAttribute("busType",busType);
		model.addAttribute("busId",busId);
		return Constant.PC_PREFIX+"project/wdb/file";
	}
	
}
