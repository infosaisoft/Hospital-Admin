package com.harbor.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String homePage() {
		
		return "home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String showPage(Map<String, Object>map,HttpServletRequest req) {
		ServletContext sc=null;
		sc=req.getServletContext();
		String uid=(String) sc.getAttribute("uid");
		String hid=(String) sc.getAttribute("hid");
		map.put("uid", uid);
		map.put("hid", hid);
		return "home";
	}
	
}
