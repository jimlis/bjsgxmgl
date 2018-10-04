package com.zj.platform.common.util;

import com.zj.platform.business.config.domain.ConfigDO;
import com.zj.platform.business.config.service.ConfigService;
import com.zj.platform.business.generator.domain.ColumnDO;
import com.zj.platform.business.generator.domain.TableDO;
import com.zj.platform.business.generator.type.EnumGen;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.web.exception.CommonException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器 工具类
 */
public class GenUtils {

    private static Logger log = LoggerFactory.getLogger(GenUtils.class);

    private  static  String genPath=SpringContextHolder.getBean(ConfigurableEnvironment.class).getProperty("genPath");

    private static String javaOutPutDir=genPath+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator;

    private static String htmlOutPutDir=genPath+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"view"+File.separator+
            "pc"+File.separator+"html"+File.separator;

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        String pathPrefix="temp";
        templates.add(pathPrefix+"/domain.java.vm");
        templates.add(pathPrefix+"/Dao.java.vm");
        templates.add(pathPrefix+"/Mapper.xml.vm");
        templates.add(pathPrefix+"/Service.java.vm");
        templates.add(pathPrefix+"/ServiceImpl.java.vm");
        templates.add(pathPrefix+"/Controller.java.vm");
        templates.add(pathPrefix+"/list.html.vm");
        templates.add(pathPrefix+"/add.html.vm");
        templates.add(pathPrefix+"/edit.html.vm");
        templates.add(pathPrefix+"/list.js.vm");
        templates.add(pathPrefix+"/add.js.vm");
        templates.add(pathPrefix+"/edit.js.vm");
        templates.add(pathPrefix+"/menu.sql.vm");
        return templates;
    }

    /**
     * 生成代码
     */

    public static void generatorCode(String moduleCode,Map<String, String> table, List<Map<String, String>> columns) throws IOException {
        // 配置信息
        Map<String, String> config = getConfig();
        // 表信息
        TableDO tableDO = new TableDO();
        tableDO.setTableName(table.get("tableName"));
        tableDO.setComments(table.get("tableComment"));
        // 表名转换成Java类名
        String className = tableToJava(tableDO.getTableName(), config.get("tablePrefix"), config.get("autoRemovePre"));
        tableDO.setClassName(className);
        tableDO.setClassname(StringUtils.uncapitalize(className));

        // 列信息
        List<ColumnDO> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnDO columnDO = new ColumnDO();
            columnDO.setColumnName(column.get("columnName"));
            columnDO.setDataType(column.get("dataType"));
            columnDO.setComments(column.get("columnComment"));
            columnDO.setExtra(column.get("extra"));

            // 列名转换成Java属性名
            String attrName = columnToJava(columnDO.getColumnName());
            columnDO.setAttrName(attrName);
            columnDO.setAttrname(StringUtils.uncapitalize(attrName));

            // 列的数据类型，转换成Java类型
            String attrType = config.get(columnDO.getDataType());
            columnDO.setAttrType(attrType);

            // 是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableDO.getPk() == null) {
                tableDO.setPk(columnDO);
            }

            columsList.add(columnDO);
        }
        tableDO.setColumns(columsList);

        // 没主键，则第一个字段为主键
        if (tableDO.getPk() == null) {
            tableDO.setPk(tableDO.getColumns().get(0));
        }

        // 设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        // 封装模板数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("tableName", tableDO.getTableName());
        map.put("comments", tableDO.getComments());
        map.put("pk", tableDO.getPk());
        map.put("className", tableDO.getClassName());
        map.put("classname", tableDO.getClassname());
        String pack = config.get("package");
        map.put("pathName", pack.substring(pack.lastIndexOf(".") + 1));
        map.put("columns", tableDO.getColumns());
        map.put("package", pack+"."+moduleCode);
        map.put("modulePath",moduleCode);
        String htmlPathPer=moduleCode.indexOf(".")>0?moduleCode.substring(0,moduleCode.lastIndexOf(".")+1).replace(".","/"):"";
        map.put("htmlPathPer",htmlPathPer);
        map.put("author", config.get("author"));
        map.put("email", config.get("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_19));
        VelocityContext context = new VelocityContext(map);

        // 获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            // 渲染模板

            FileOutputStream outputStream=null;
            OutputStreamWriter writer=null;
            BufferedWriter  bufferedWriter=null;
            try {
                Template tpl = Velocity.getTemplate(template, "UTF-8");

                String filePath = getFileName(template, tableDO.getClassname(), tableDO.getClassName(),Objects.toString(map.get("pathName")),
                        Objects.toString(map.get("package")),htmlPathPer);
                String packPath=filePath.substring(0,filePath.lastIndexOf("\\"));

                File packFolder = new File(packPath);
                if(!packFolder.exists()){
                    packFolder.mkdirs();
                }

                File file = new File(filePath);
                if(!file.exists()){
                    file.createNewFile();
                }

              //创建输出流
              outputStream = new FileOutputStream(file);
               writer = new OutputStreamWriter(outputStream,"utf-8");
               bufferedWriter = new BufferedWriter(writer);
               tpl.merge(context,bufferedWriter);
               bufferedWriter.flush();
          }catch (Exception e){
                        throw  e;
          }finally {
                if(outputStream!=null){
                    outputStream.close();
                }

                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            }
        }
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalize(columnName, new char[] { '_' }).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix, String autoRemovePre) {
        if (Constant.Generator.AUTO_REOMVE_PRE.equals(autoRemovePre)) {
            tableName = tableName.substring(tableName.indexOf("_") + 1);
        }
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }

        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Map<String, String> getConfig() {
        ConfigService configService = SpringContextHolder.getBean(ConfigService.class);
        List<ConfigDO> list = configService.findListByKvType(EnumGen.KvType.base.getValue());
        list.addAll(configService.findListByKvType(EnumGen.KvType.mapping.getValue()));
        Map<String, String> config = new HashMap<>();
        list.stream().forEach(kv -> config.put(kv.getK(), kv.getV()));
        return config;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String classname, String className,String pathName, String packageName,String htmlPathPer) {
        String packagePath =javaOutPutDir;
        String htmlPath=htmlOutPutDir+pathName+File.separator;
        if(StringUtils.isNotBlank(htmlPathPer)){
            htmlPath+=htmlPathPer.replace("/",File.separator);
        }
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if (template.contains("domain.java.vm")) {
            return packagePath + "domain" + File.separator + className + "DO.java";
        }

        if (template.contains("Dao.java.vm")) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Mapper.xml.vm")) {
            return packagePath + "mapper" + File.separator + className+"Mapper.xml";
        }

        if (template.contains("list.html.vm")) {
            return htmlPath + classname.toLowerCase() + File.separator + classname + ".html";
        }
        if (template.contains("add.html.vm")) {
            return htmlPath + classname.toLowerCase() + File.separator + "add.html";
        }
        if (template.contains("edit.html.vm")) {
            return htmlPath+ classname.toLowerCase() + File.separator + "edit.html";
        }

        if (template.contains("list.js.vm")) {
            return htmlPath+ classname.toLowerCase() + File.separator+ "js" + File.separator  + classname + ".js";
        }
        if (template.contains("add.js.vm")) {
            return htmlPath+ classname.toLowerCase() + File.separator+"js"+File.separator + "add.js";
        }
        if (template.contains("edit.js.vm")) {
            return htmlPath+ classname.toLowerCase() + File.separator+"js" + File.separator + "edit.js";
        }

        if (template.contains("menu.sql.vm")) {
            return htmlPath+ classname.toLowerCase() + File.separator+"sql"+File.separator + className.toLowerCase() + "_menu.sql";
        }

        return null;
    }
    
    public static void main(String[] args) {
		String typeName="typeName";
		String str=WordUtils.uncapitalize(typeName, new char[] {'_'});
		System.out.println(str);
	}
}
