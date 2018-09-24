package com.zj.platform.business.generator.service.impl;

import com.zj.platform.business.generator.dao.GeneratorMapper;
import com.zj.platform.business.generator.service.GeneratorService;
import com.zj.platform.common.util.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Autowired
    GeneratorMapper generatorMapper;

    @Override
    public List<Map<String, Object>> list() {
        List<Map<String, Object>> list = generatorMapper.list();
        return list;
    }

    @Override
    public void generatorCode(String moduleCode,String[] tableNames) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (String tableName : tableNames) {
            // 查询表信息
            Map<String, String> table = generatorMapper.get(tableName);
            // 查询列信息
            List<Map<String, String>> columns = generatorMapper.listColumns(tableName);
            // 生成代码
            GenUtils.generatorCode(moduleCode,table, columns);
        }
    }

}
