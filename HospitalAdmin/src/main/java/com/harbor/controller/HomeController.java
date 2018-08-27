package com.harbor.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.harbor.command.Departmentcommand;
import com.harbor.dto.DepartmentDto;
import com.harbor.dto.HospitalDto;
import com.harbor.service.DepartmentService;
import com.harbor.service.HospitalService;

@Controller
public class HomeController {

	@Autowired
	HospitalService hservice = null;

	@Autowired
	DepartmentService dptService;
	
	ServletContext sc =null;
	
	
	    
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(Map<String, Object> map, @ModelAttribute("departmentcmd") Departmentcommand departmentcmd,
			HttpServletRequest req, HttpServletResponse res) {
		sc = req.getServletContext();
		String uid = (String) sc.getAttribute("uid");
		String hid = (String) sc.getAttribute("hid");
		HospitalDto dto = null;
		List<DepartmentDto> listdto = null;
		System.out.println("hoem:::"+uid);
		if(uid==null) {
			return "redirect:/login";
		}

		// copy cmd to dto
		dto = new HospitalDto();
		// use service
		dto = hservice.featchHospitalInfo(hid);
		listdto = dptService.featchAllDepartment();
		map.put("listdto", listdto);
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("dto", dto);
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String showPage(Map<String, Object> map, @ModelAttribute("departmentcmd") Departmentcommand departmentcmd,
			HttpServletRequest req) {

		DepartmentDto dptdto = null;
		ServletContext sc = null;
		String result = null;
		List<DepartmentDto> listdto = null;
		HospitalDto dto = null;
		sc = req.getServletContext();
		String uid = (String) sc.getAttribute("uid");
		String hid = (String) sc.getAttribute("hid");

		// copy cmd to dto
		dptdto = new DepartmentDto();
		dto = new HospitalDto();

		BeanUtils.copyProperties(departmentcmd, dptdto);

		dptdto.setHid(hid);
		// use service

		dto = hservice.featchHospitalInfo(hid);
		result = dptService.registrationDepartment(dptdto);
		listdto = dptService.featchAllDepartment();
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("result", result);
		map.put("dto", dto);
		map.put("listdto", listdto);
		return "home";
	}
	
	
	@RequestMapping(value = "/delete_dpt", method = RequestMethod.GET)
	public String deleteDepartment(Map<String, Object> map, @ModelAttribute("departmentcmd") Departmentcommand departmentcmd,
			HttpServletRequest req) {
		String dpt_id=req.getParameter("dpt_id");
		String delete=null;
		DepartmentDto dptdto = null;
		List<DepartmentDto> listdto = null;
       System.out.println("delete controller");
		String uid = (String) sc.getAttribute("uid");
		String hid = (String) sc.getAttribute("hid");

		// copy cmd to dto
		HospitalDto dto;
		dto = new HospitalDto();
		dptdto = new DepartmentDto();
		dptdto.setHid(hid);	
		BeanUtils.copyProperties(departmentcmd, dptdto);
		//use service
		dto = hservice.featchHospitalInfo(hid);
		delete=dptService.removeDept(dpt_id);
		listdto = dptService.featchAllDepartment();
	
		System.out.println("delete");
		map.put("uid", uid);
		map.put("delete", delete);
		return "redirect:/home";
	}

}
