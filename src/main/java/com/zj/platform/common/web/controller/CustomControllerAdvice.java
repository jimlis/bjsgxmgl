package com.zj.platform.common.web.controller;


import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.util.conversion.CustomDateTimeEditor;
import com.zj.platform.common.web.exception.CommonException;


/**
 * 控制器增强处理
 * @author zj
 */
@ControllerAdvice
public class CustomControllerAdvice {
	private Logger log= LoggerFactory.getLogger(CustomControllerAdvice.class);

	/**
	 * 处理日期转换
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateTimeEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),true));
	}

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(CommonException.class)
	@ResponseBody
	public Result<String> handlerException(CommonException e) {
		log.info("handlerException");
		try {
			int code = Integer.parseInt(e.getMessage());
			return Result.build(code, EnumErrorCode.getMsgByCode(code));
		} catch (NumberFormatException e1) {
			log.info("错误码使用错误，异常内容请抛出EnumErrorCode类的枚举值");
			return Result.build(EnumErrorCode.unknowFail.getCode(), EnumErrorCode.unknowFail.getMsg());
		}
	}

	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseBody
	public Result<String> handleDuplicateKeyException(DuplicateKeyException e) {
		log.error(e.getMessage());
		return Result.build(EnumErrorCode.duplicateKeyExist.getCode(), EnumErrorCode.duplicateKeyExist.getMsg());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseBody
	public Result<String> noHandlerFoundException(NoHandlerFoundException e) {
		log.error(e.getMessage());
		return Result.build(EnumErrorCode.pageNotFound.getCode(), EnumErrorCode.pageNotFound.getMsg());
	}

	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public Result<String> handleAuthorizationException(ShiroException e) {
		log.error(e.getMessage());
		if(e instanceof IncorrectCredentialsException) {
			return Result.build(EnumErrorCode.apiAuthorizationFailed.getCode(), EnumErrorCode.apiAuthorizationFailed.getMsg());
		}else if(e instanceof ExpiredCredentialsException) {
			return Result.build(EnumErrorCode.apiAuthorizationExpired.getCode(), EnumErrorCode.apiAuthorizationExpired.getMsg());
		}
		return Result.build(EnumErrorCode.notAuthorization.getCode(), EnumErrorCode.notAuthorization.getMsg());
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
		String errorMsg = null;

		if(log.isErrorEnabled()) {
			log.error("程序发生异常,异常信息："+e.getMessage());
		}

		if(e instanceof CommonException){
			errorMsg=e.getMessage();
		}else{
			errorMsg="服务端异常，请联系管理员";
		}
		
		if(log.isErrorEnabled()) {
			log.error("程序发生异常,异常信息："+e.getMessage());
		}
	
		e.printStackTrace();


		if ((request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("aplication/json;charset=utf-8");
			Result result=Result.build(Result.CODE_FAIL,errorMsg);
			try {
				Gson gson= new GsonBuilder().serializeNulls().create();
						try(OutputStream out=response.getOutputStream()){
							String json = gson.toJson(result);
							byte[] bytes = json.getBytes();
							out.write(bytes);
							out.flush();
						}catch (Exception e1){
							e1.printStackTrace();
						}
						return null;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			request.setAttribute("errorMsg", errorMsg);
		}
		
		return Constant.PC_PREFIX+"sys/error/error";
	}
}
