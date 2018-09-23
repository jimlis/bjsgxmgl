package com.zj.platform.common.web.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.util.conversion.CustomDateTimeEditor;
import com.zj.platform.common.web.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 控制器增强处理
 * @author zj
 */
@ControllerAdvice
public class CustomControllerAdvice {
	private Logger logger= LoggerFactory.getLogger(CustomControllerAdvice.class);

	/**
	 * 处理日期转换
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateTimeEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),true));
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
		String errorMsg = null;

		if(logger.isErrorEnabled()) {
			logger.error("程序发生异常,异常信息："+e.getMessage());
		}

		if(e instanceof CommonException){
			errorMsg=e.getMessage();
		}else{
			errorMsg="服务端异常，请联系管理员";
		}

		if ((request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("aplication/json;charset=utf-8");
			Result result=Result.build(Result.CODE_FAIL,errorMsg);
			try {
				Gson gson= new GsonBuilder().serializeNulls().create();
						try(PrintWriter pw=response.getWriter()){
							pw.write(gson.toJson(result));
							pw.flush();
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

		if(logger.isErrorEnabled()) {
			logger.error("程序发生异常,异常信息："+e.getMessage());
		}
	
		e.printStackTrace();

		return Constant.PC_PREFIX+"sys/error/error";
	}
}
