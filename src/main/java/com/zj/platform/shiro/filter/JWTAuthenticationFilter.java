package com.zj.platform.shiro.filter;


import com.zj.platform.common.util.Result;
import com.zj.platform.common.util.SpringContextHolder;
import com.zj.platform.common.util.json.JSONUtils;
import com.zj.platform.common.web.exception.MyApiException;
import com.zj.project.api.service.ApiUserService;
import com.zj.platform.shiro.util.JWTAuthenticationTokenToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
*jwt身份验证Filter
 */
public class JWTAuthenticationFilter extends BasicHttpAuthenticationFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 判断是否为登录请求
     */
    @Override
    protected boolean isLoginAttempt(String authzHeader) {
    	return StringUtils.isNotBlank(authzHeader);
    }
    
    @Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response){
		return false;//已经在isAccessAllowed登录过了，不执行父类的登录操作（token不同）
	}

	/**
     * 这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 注解 @RequiresAuthentication
     * 缺点：不能够对GET,POST等请求进行分别过滤鉴权
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    	ShiroHttpServletRequest req=(ShiroHttpServletRequest)request;
    	String servletPath = req.getServletPath();
    	if(servletPath.equals("/api/user/login")||servletPath.startsWith("/api/file/")||servletPath.startsWith("/api/user/logonFree")) {
       	 	return true;
       }
        if (isLoginAttempt(request, response)) {
        	String error = "请重新登录";
            try {
            	HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                String authorization = httpServletRequest.getHeader("Authorization");
                if(!SpringContextHolder.getBean(ApiUserService.class).verifyToken(authorization, false)) {
                	getSubject(request, response).logout();
                }else {
	                JWTAuthenticationTokenToken token = new JWTAuthenticationTokenToken(authorization);
	                Subject subject = getSubject(request, response);
                    if(!subject.isAuthenticated()) {
	                	subject.login(token);
	                }
	            	return true;
                }
            } catch (MyApiException | IncorrectCredentialsException | ExpiredCredentialsException e) {
            	error = e.getMessage();
            } catch (Exception e) {
            	log.info(e.getMessage());
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try(PrintWriter writer = response.getWriter()){
                Result result = Result.build(2,error);
            	writer.write(JSONUtils.beanToJson(result));
            }catch (Exception ex) {
            	log.warn(ex.getMessage());
            }
            return false;
        }
        	return false;
       
    }

	/**
     * 跨域处理
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

}
