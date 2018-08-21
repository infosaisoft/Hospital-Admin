package com.harbor.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.harbor.command.LoginCommand;
import com.harbor.dto.LoginDto;
import com.harbor.service.LoginService;

@Controller
@EnableWebMvc
public class LoginController {
	
	@Autowired
	LoginService loginservice;
	
	@RequestMapping(value="/login.html", method=RequestMethod.GET)
	public String loginPage(@ModelAttribute("loginPage") LoginCommand loginPage) {
		return "login";
	}
	
	@RequestMapping(value="/login.html", method=RequestMethod.POST)
	public  String loginVerify(Map<String, Object> map, @ModelAttribute("loginPage") LoginCommand loginPage,HttpServletRequest req,HttpServletResponse res)throws Exception {
		ServletContext sc=null;
		String result = null;
		PrintWriter pw=null;
		LoginDto logindto = null;
		RequestDispatcher rd=null;
		
		// copy command to dto
		logindto = new LoginDto();
		pw=	res.getWriter();
		BeanUtils.copyProperties(loginPage, logindto);
		sc=req.getServletContext();
		sc.setAttribute("uid", logindto.getUsername());
		
		// Use Service
		result = loginservice.verifyUser(logindto);
		System.out.println(result);
		if(result.equalsIgnoreCase("success")) {
			return "redirect:home.html";
		}
		map.put("result", result);			
		return "login";
	}
	
}
