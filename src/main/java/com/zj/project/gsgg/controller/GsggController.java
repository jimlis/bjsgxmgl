package com.zj.project.gsgg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.AdminBaseController;
import com.zj.project.gsgg.domain.GsggDO;
import com.zj.project.gsgg.domain.GsggNrDO;
import com.zj.project.gsgg.service.GsggNrService;
import com.zj.project.gsgg.service.GsggService;
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
 * 公司公告
 * </pre>
 * <small> 2018-09-13 20:25:05 | lijun</small>
 */
@Controller
@RequestMapping("/project/gsgg")
public class GsggController extends AdminBaseController {
	@Autowired
	private GsggService gsggService;

	@Autowired
	private GsggNrService gsggNrService;
	
	@GetMapping()
	@RequiresPermissions("project:gsgg:gsgg")
	String Gsgg(){
	    return Constant.PC_PREFIX+"project/gsgg/gsgg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:gsgg:gsgg")
	public Result<IPage<GsggDO>> list(GsggDO gsggDTO){
        QueryWrapper<GsggDO> wrapper = new QueryWrapper<GsggDO>().eq(StringUtils.isNotEmpty(gsggDTO.getChrlx()),"chrlx",gsggDTO.getChrlx()).
				like(StringUtils.isNotEmpty(gsggDTO.getChrbt()),"chrbt",gsggDTO.getChrbt()).eq("fcbz","1");
        IPage<GsggDO> page = gsggService.page(getPage(GsggDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:gsgg:add")
	String add(){
	    return Constant.PC_PREFIX+"project/gsgg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("project:gsgg:edit")
	String edit(@PathVariable("id") Long id,Model model){
		GsggDO gsgg = gsggService.getById(id);
		model.addAttribute("gsgg", gsgg);

		GsggNrDO gsggNrDO=new GsggNrDO();
		gsggNrDO.setIntggid(id);
		gsggNrDO.setFcbz(1);
		QueryWrapper<GsggNrDO> wrapper = new QueryWrapper<GsggNrDO>(gsggNrDO);
		gsggNrDO = gsggNrService.getOne(wrapper);
		model.addAttribute("gsggnr", gsggNrDO);
	    return Constant.PC_PREFIX+"project/gsgg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:gsgg:add")
	public Result<String> save(HttpServletRequest request,GsggDO gsgg){
		String fileIds= Objects.toString(request.getParameter("fileIds"));
		String chrggnr= Objects.toString(request.getParameter("chrggnr"));
		try {
			gsggService.saveGsggxx(gsgg,chrggnr,fileIds);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail();
		}
		return Result.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:gsgg:edit")
	public Result<String>  update( GsggDO gsgg){
		gsggService.updateById(gsgg);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:gsgg:remove")
	public Result<String>  remove( Long id){
		gsggService.removeById(id);
        return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:gsgg:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		gsggService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
