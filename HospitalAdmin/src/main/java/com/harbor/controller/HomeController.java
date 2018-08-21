package com.harbor.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.harbor.command.HospitalCommand;
import com.harbor.dto.HospitalDto;
import com.harbor.service.HospitalService;

@Controller
public class HomeController {
	
	@Autowired
	HospitalService hservice=null;
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String homePage() {
		
		return "home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String showPage(Map<String, Object>map,@ModelAttribute("hospitalcmd") HospitalCommand hospitalcmd,HttpServletRequest req) {
		
		ServletContext sc=null;
		HospitalDto dto=null;
		sc=req.getServletContext();
		String uid=(String) sc.getAttribute("uid");
		String hid=(String) sc.getAttribute("hid");
		
		//copy cmd to dto
		dto=new HospitalDto();
		BeanUtils.copyProperties(hospitalcmd, dto);
		//use service
		dto=hservice.featchHospitalInfo(hid);
		System.out.println("controller service"+dto);
		
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("dto", dto);
		return "home";
	}
	
}
