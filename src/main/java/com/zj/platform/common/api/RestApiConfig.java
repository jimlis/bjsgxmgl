package com.zj.platform.common.api;

import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;

import com.google.common.base.Predicate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 配饰spring fox Swagger2
 * @author zj
 */
@EnableSwagger2
public class RestApiConfig {
	
	/**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * @return
     */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(selector()).paths(PathSelectors.any()).build();
	}
	
	 /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
	private ApiInfo apiInfo() {
		Contact contact = new Contact("测试", "test", "");
		return new ApiInfoBuilder().title("Spring 中使用Swagger2构建RESTful APIs")
				.termsOfServiceUrl("http://127.0.0.1:8080/platform").contact(contact).version("1.0").build();
	}
	
	/**
	 * 自定义api文档扫描规则：先路径匹配，然后类是是否有@Api注解，最后方法上是否有@ApiOperation注解
	 * @return
	 */
	public Predicate<RequestHandler> selector(){
		  return new Predicate<RequestHandler>() {
		      @Override
		      public boolean apply(RequestHandler input) {
		    	  AntPathMatcher ant=new AntPathMatcher();
		        return  ant.match("com.zj.**.web.controller", input.declaringClass().getPackage().getName())&&
		        		input.declaringClass().isAnnotationPresent(Api.class)&&
		        		input.isAnnotatedWith(ApiOperation.class);
		      }
		    };
	}
}
