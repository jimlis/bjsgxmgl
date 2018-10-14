package com.zj.platform.business.generator.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zj.platform.business.config.domain.ConfigDO;
import com.zj.platform.business.config.service.ConfigService;
import com.zj.platform.business.generator.service.GeneratorService;
import com.zj.platform.business.generator.type.EnumGen;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;

/**
 * 代码生成
 */
@RequestMapping("/sys/generator")
@Controller
public class GeneratorController {
    String prefix = "sys/generator";
    @Autowired
    GeneratorService generatorService;
    @Autowired
    ConfigService configService;
    
    
    @Log("进入代码生成页面")
    @GetMapping()
    String generator() {
        return Constant.PC_PREFIX+prefix + "/list";
    }
    
    @Log("查询数据表列表")
    @ResponseBody
    @GetMapping("/list")
    List<Map<String, Object>> list() {
        List<Map<String, Object>> list = generatorService.list();
        return list;
    };
    
    @Log("根据数据表生成代码")
    @RequestMapping("/code/{tableName}")
    @ResponseBody
    public Result code(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("tableName") String tableName) throws IOException {
        /*String[] tableNames = new String[] { tableName };
        byte[] data = generatorService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());*/
        try {
            String moduleCode= Objects.toString(request.getParameter("moduleCode"),"");
            String[] tableNames = new String[] { tableName };
            generatorService.generatorCode(moduleCode,tableNames);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("根据数据表批量生成代码")
    @RequestMapping("/batchCode")
    @ResponseBody
    public Result batchCode(HttpServletRequest request, HttpServletResponse response, String tables) throws Exception {
        try {
            String[] tableNames = new String[] {};
            String moduleCode= Objects.toString(request.getParameter("moduleCode"),"");
            tableNames = JSON.parseArray(tables).toArray(tableNames);
            generatorService.generatorCode(moduleCode,tableNames);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @Log("进入代码生成配置编辑页面")
    @GetMapping("/edit")
    public String edit(Model model) {
        List<ConfigDO> list = configService.findListByKvType(EnumGen.KvType.mapping.getValue());
        List<ConfigDO> list2 = configService.findListByKvType(EnumGen.KvType.base.getValue());
        HashMap<String, String> map = new HashMap<>();
        for(ConfigDO config : list2) {
            map.put(config.getK(), config.getV());
        }
        
        model.addAttribute("list", list);
        model.addAttribute("property", map);
        return Constant.PC_PREFIX+prefix + "/edit";
    }
    
    @Log("更新代码生成配置")
    @ResponseBody
    @PostMapping("/update")
    Result<String> update(@RequestParam Map<String, String> map) {
        if(!map.containsKey("autoRemovePre")) {
            map.put("autoRemovePre", "false");
        }else {
            map.put("autoRemovePre", "true");
        }
        configService.updateKV(map);
        return Result.ok();
    }
}
