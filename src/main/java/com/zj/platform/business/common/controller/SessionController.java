package com.zj.platform.business.common.controller;

import com.zj.platform.business.common.domain.UserOnline;
import com.zj.platform.business.common.service.SessionService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Constant;
import com.zj.platform.common.util.Result;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;


@RequestMapping("/sys/online")
@Controller
public class SessionController {
	@Autowired
	SessionService sessionService;
	
	@Log("进入在线用户列表页面")
	@GetMapping()
	public String online() {
		return Constant.PC_PREFIX+"sys/online/online";
	}
	
	@Log("查询在线用户列表数据")
	@ResponseBody
	@RequestMapping("/list")
	public List<UserOnline> list() {
		return sessionService.list();
	}
	
	@Log("根据sessionId强踢用户下线")
	@ResponseBody
	@RequestMapping("/forceLogout/{sessionId}")
	public Result<String> forceLogout(@PathVariable("sessionId") String sessionId, RedirectAttributes redirectAttributes) {
		try {
			sessionService.forceLogout(sessionId);
			return Result.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail();
		}
	}
	
	@Log("查询在线用户数据列表")
	@ResponseBody
	@RequestMapping("/sessionList")
	public Collection<Session> sessionList() {
		return sessionService.sessionList();
	}
}
