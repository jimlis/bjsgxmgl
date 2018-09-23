package com.zj.project.gsgg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import com.zj.project.gsgg.domain.GsggNrDO;
import com.zj.project.gsgg.service.GsggNrService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 
 * <pre>
 * 公司公告-内容
 * </pre>
 * <small> 2018-09-13 20:26:41 | lijun</small>
 */
@Controller
@RequestMapping("/project/gsggNr")
public class GsggNrController extends AdminBaseController {
	@Autowired
	private GsggNrService gsggNrService;

	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:gsggNr:gsggNr")
	public Result<IPage<GsggNrDO>> list(GsggNrDO gsggNrDTO){
        QueryWrapper<GsggNrDO> wrapper = new QueryWrapper<GsggNrDO>(gsggNrDTO);
		IPage<GsggNrDO> page = gsggNrService.page(getPage(GsggNrDO.class), wrapper);
        return Result.ok(page);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:gsggNr:add")
	public Result<String> save( GsggNrDO gsggNr){
		gsggNrService.save(gsggNr);
        return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:gsggNr:edit")
	public Result<String>  update( GsggNrDO gsggNr){
		gsggNrService.updateById(gsggNr);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:gsggNr:remove")
	public Result<String>  remove( Long id){
		gsggNrService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:gsggNr:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		gsggNrService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
