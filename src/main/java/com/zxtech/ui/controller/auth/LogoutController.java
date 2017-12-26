package com.zxtech.ui.controller.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request){
		System.out.println(request.getRequestURI());
	}
}
