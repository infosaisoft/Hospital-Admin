package com.harbor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/home.html", method=RequestMethod.GET)
	public String homePage() {
		return "home";
	}
	
	@RequestMapping(value="/home.html", method=RequestMethod.POST)
	public String showPage() {
		return "home";
	}
	
}
