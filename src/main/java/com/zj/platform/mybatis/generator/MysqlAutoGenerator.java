package com.zj.platform.mybatis.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.zj.platform.mybatis.generator.config.converts.CustomOracleTypeConvert;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MysqlAutoGenerator {

	private AutoGenerator autoGenerator=new AutoGenerator();

	private Properties pros=new Properties();

	private GlobalConfig globalConfig=new GlobalConfig();

	private DataSourceConfig dataSourceConfig=new DataSourceConfig();

	private StrategyConfig strategyConfig=new StrategyConfig();

	private PackageConfig packageConfig=new PackageConfig();

	private InjectionConfig injectionConfig=null;

	private TemplateConfig template = new TemplateConfig();

	private String outputDir=System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"java";

	private String author="zj";

	private String[] tables=new String[]{};

	/**自定义实体，公共字段 父类中字段 代码生成时候忽略  代码判断时候区分大小写*/
	private String[] superEntityColumns=new String[]{};

	/**父包路径*/
	private String parentPackage="com.zj.project";

	/**模块名*/
	private String moduleName=null;

	/**自定义vm中属性*/
	private Map<String, Object> attr=new HashMap<String, Object>();

	/**添加自定义的模版输出*/
	List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

	/**只生成一次 不可以覆盖**/
	private boolean fileOverride=false;

	public MysqlAutoGenerator() {

	}

	public MysqlAutoGenerator(String author) {
		this.author=author;
	}

	public MysqlAutoGenerator(String author, String outputDir, String moduleName, String[] tables) {
		this.author=author;
		this.outputDir=outputDir;
		this.moduleName=moduleName;
		this.tables=tables;
	}
	
	public void initProperties() {
		try {
			pros.load(MysqlAutoGenerator.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 全局配置
	public void initGlobalConfig(){
		globalConfig.setOutputDir(outputDir);
		globalConfig.setFileOverride(fileOverride);//只生成一次 不可以覆盖
		globalConfig.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
		globalConfig.setEnableCache(false);// XML 二级缓存
		globalConfig.setBaseResultMap(false);// XML ResultMap
		globalConfig.setBaseColumnList(false);//生成xml公共的字段sql片段
		globalConfig.setDateType(DateType.ONLY_DATE);//时间对应
	    // .setKotlin(true) 是否生成 kotlin 代码
		globalConfig.setAuthor(author);
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		globalConfig.setMapperName("%sDao");
		globalConfig.setXmlName("%sMapper");
		globalConfig.setServiceName("%sService");
		globalConfig.setServiceImplName("%sServiceImpl");
		globalConfig.setControllerName("%sController");
		globalConfig.setEntityName("%sDO");
	}
	
	// 数据源配置
	public void initDataSourceConfig(){
		dataSourceConfig.setDbType(DbType.MYSQL);
		dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
		dataSourceConfig.setDriverName(pros.getProperty("jdbc.driver"));
		dataSourceConfig.setUsername(pros.getProperty("jdbc.username"));
		dataSourceConfig.setPassword(pros.getProperty("jdbc.password"));
		dataSourceConfig.setUrl(pros.getProperty("jdbc.url"));
	}
	
	// 策略配置
	public void initStrategyConfig(){
		 	strategyConfig.setCapitalMode(false);// 全局大写命名 ORACLE 注意
		 	strategyConfig.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		   // strategyConfig.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
		 	strategyConfig.setColumnNaming(NamingStrategy.nochange);//表字段
		 	strategyConfig.setInclude(tables); // 需要生成的表
		    // strategyConfig.setExclude(new String[]{"test"}); // 排除生成的表
		    // 自定义实体父类
		 	strategyConfig.setSuperEntityClass("com.zj.platform.common.web.domain.BaseDomain");
		    // 自定义实体，公共字段
		 	strategyConfig.setSuperEntityColumns(superEntityColumns);
		    // 自定义 mapper 父类
		 	strategyConfig.setSuperMapperClass("com.zj.platform.common.web.dao.Dao");
		    // 自定义 service 父类
		 	strategyConfig.setSuperServiceClass("com.zj.platform.common.web.service.BaseService");
		    // 自定义 service 实现类父类
		 	strategyConfig.setSuperServiceImplClass("com.zj.platform.common.web.service.impl.BaseServiceImpl");
		    // 自定义 controller 父类
		 	strategyConfig.setSuperControllerClass("com.zj.platform.common.web.controller.AdminBaseController");
		    // 【实体】是否生成字段常量（默认 false）
		    // public static final String ID = "test_id";
		    // strategy.setEntityColumnConstant(true);
		    // 【实体】是否为构建者模型（默认 false）
		    // public User setName(String name) {this.name = name; return this;}
		 	strategyConfig.setEntityBuilderModel(false);
	}
    
	// 包配置
	public void initPackageConfig() {
		packageConfig.setParent(parentPackage);
	    //父包模块名。
	    //pc.setModuleName("syscode");
	    if(Objects.nonNull(moduleName)) {
	    	packageConfig.setModuleName(moduleName);
	    }
	    packageConfig.setEntity("domain");
	    packageConfig.setMapper("dao");
	    packageConfig.setController("controller");
	    packageConfig.setXml("mapper");
	}
	
	// 注入自定义配置，可以在 VM 中使用 
	public void initInjectionConfig(Map<String, Object> map) {
		injectionConfig=new InjectionConfig() {
	        @Override
	        public void initMap() {
	            this.setMap(map);
	        }
	    };
	    injectionConfig.initMap();
	    injectionConfig.setFileOutConfigList(focList);
	}
	
	public AutoGenerator beforeExecute(){
		initProperties();
		initGlobalConfig();
		initDataSourceConfig();
		initStrategyConfig();
		initPackageConfig();
		initInjectionConfig(attr);
		
	   return autoGenerator.setGlobalConfig(globalConfig).setDataSource(dataSourceConfig).
		setStrategy(strategyConfig).setPackageInfo(packageConfig).setCfg(injectionConfig).setTemplate(template);
	}
	

	public void execute() {
		autoGenerator.execute();
	}
	
	public AutoGenerator getAutoGenerator() {
		return autoGenerator;
	}

	public void setAutoGenerator(AutoGenerator autoGenerator) {
		this.autoGenerator = autoGenerator;
	}

	public Properties getPros() {
		return pros;
	}

	public void setPros(Properties pros) {
		this.pros = pros;
	}

	public GlobalConfig getGlobalConfig() {
		return globalConfig;
	}

	public void setGlobalConfig(GlobalConfig globalConfig) {
		this.globalConfig = globalConfig;
	}

	public DataSourceConfig getDataSourceConfig() {
		return dataSourceConfig;
	}

	public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
		this.dataSourceConfig = dataSourceConfig;
	}

	public StrategyConfig getStrategyConfig() {
		return strategyConfig;
	}

	public void setStrategyConfig(StrategyConfig strategyConfig) {
		this.strategyConfig = strategyConfig;
	}

	public PackageConfig getPackageConfig() {
		return packageConfig;
	}

	public void setPackageConfig(PackageConfig packageConfig) {
		this.packageConfig = packageConfig;
	}

	public InjectionConfig getInjectionConfig() {
		return injectionConfig;
	}

	public void setInjectionConfig(InjectionConfig injectionConfig) {
		this.injectionConfig = injectionConfig;
	}

	public TemplateConfig getTemplate() {
		return template;
	}

	public void setTemplate(TemplateConfig template) {
		this.template = template;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String[] getTables() {
		return tables;
	}

	public void setTables(String[] tables) {
		this.tables = tables;
	}

	public String[] getSuperEntityColumns() {
		return superEntityColumns;
	}

	public void setSuperEntityColumns(String[] superEntityColumns) {
		this.superEntityColumns = superEntityColumns;
	}

	public String getParentPackage() {
		return parentPackage;
	}

	public void setParentPackage(String parentPackage) {
		this.parentPackage = parentPackage;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Map<String, Object> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}

	public List<FileOutConfig> getFocList() {
		return focList;
	}

	public void setFocList(List<FileOutConfig> focList) {
		this.focList = focList;
	}
	
	
	public boolean isFileOverride() {
		return fileOverride;
	}

	public void setFileOverride(boolean fileOverride) {
		this.fileOverride = fileOverride;
	}

	/**
	 * @param tables 生成的表
	 * 注意 生成的文件已存在 不会在生成新的覆盖原来的 需要覆盖 设置fileOverride=true
	 */
	public void generatorTables(String[] tables){
		this.setTables(tables);
		this.beforeExecute().execute();
	}

	public static void main(String[] args) throws Exception {
		MysqlAutoGenerator mysqlAutoGenerator=
				new MysqlAutoGenerator("zj");

		String[] tables=new String[] {"SysUser"};
		for (int i = 0; i < tables.length; i++) {
			tables[i]=StringUtils.camelToUnderline(tables[i]).toUpperCase();
		}

		mysqlAutoGenerator.generatorTables(tables);
	}

}
