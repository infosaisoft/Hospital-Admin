package com.harbor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.harbor.command.UserCommand;
import com.harbor.dto.UserDto;
import com.harbor.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	UserService userser;
	ServletContext sc = null;
	@RequestMapping(value="/manage-user", method=RequestMethod.GET)
	public String manageUser(HttpServletRequest req, Map<String, Object> map, @ModelAttribute("insert_user") UserCommand insert_user) {
		
		
		
		sc = req.getServletContext();
		String hid = (String) sc.getAttribute("hid");
		System.out.println(hid);
		map.put("hid", hid);
		
		List<UserDto> userdto = null;
		
		userdto = userser.getUser(hid);
		
		map.put("userDto", userdto);
		
		return "manage-user";
	}
	
	@ModelAttribute("rolelist")
	private Map<String, String> getRoles(){
		Map<String, String> rolelist=new HashMap<>();
		rolelist.put("doctor", "Doctor");
		rolelist.put("asst_doc", "Assitant Doctor");
		rolelist.put("nurse", "Nurse");
		rolelist.put("reception", "Reception");
		rolelist.put("billing", "Billing");
		rolelist.put("admin", "Admin");
		return rolelist;
	}
	
	@RequestMapping(value="/manage-user", method=RequestMethod.POST)
	public String insertUser(HttpServletRequest req, Map<String, Object> map, @ModelAttribute("insert_user") UserCommand insert_user) {
		
		UserDto user = new UserDto();
		sc = req.getServletContext();
		
		
		String hid = (String) sc.getAttribute("hid");
		MultipartFile userPhoto = null;
		InputStream is=null;
		OutputStream os = null;
		String filename = null;
		
		// Get Name of file
		userPhoto = insert_user.getPhoto();
		filename = userPhoto.getOriginalFilename();
		try {
			String imgPath = null;
			imgPath = req.getContextPath();
			System.out.println(imgPath);
			os = new FileOutputStream(imgPath+"\\src\\main\\webapp\\assets\\images\\admin\\"+filename);
			is = userPhoto.getInputStream();
			
			// perform file copy operation
			IOUtils.copy(is, os);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// close streams
			try {
				if(os != null) {
					os.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				if(is != null) {
					is.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		
		map.put("hid", hid);
		
		List<UserDto> userdto = null;
		
		user.setHid(hid);
		
		// copy cmd to dto
		BeanUtils.copyProperties(insert_user, user);
		
		user.setPhoto(filename);
		
		// use Service
		String userResult = userser.insertUser(user);
		
		map.put("userDto", userdto);
		map.put("userResult", userResult);
		
		return "manage-user";
	}
	
}
