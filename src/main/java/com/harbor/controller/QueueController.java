package com.harbor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harbor.command.QueueCommand;
import com.harbor.dto.QueueDTO;
import com.harbor.service.QueueService;

@Controller
public class QueueController {

	@Autowired
	QueueService ser;
	private boolean add;
	

	@RequestMapping(value="/insertqueue",method=RequestMethod.GET)
	public String showAdminpage(@ModelAttribute("addQueue") QueueCommand AddQueue) {
		return "addqueue";

	}
	



	@RequestMapping(value="/insertqueue",method=RequestMethod.POST)
	public String processQpage(Map<String,Object>map,@ModelAttribute("addQueue") QueueCommand AddQueue) throws Exception {
		QueueDTO dto=null;
		//Sring  result = null;
		int result=0;
		//copy cmd to dto
		dto=new QueueDTO();
		BeanUtils.copyProperties(AddQueue, dto);
		//use service
		result=ser.InsertQueue(dto);
		map.put("result", result);
		return "addqueue";

	}
	@RequestMapping	(value="viewqueue",method=RequestMethod.GET)
	public String QAll(Map<String,Object>map) {	
		List<QueueDTO> listdtoo = null;
		listdtoo = ser.AllQueue();
		map.put("listdtoo",listdtoo);
		return "queueall";
	}	

	
	
	@RequestMapping	(value="addqueue",method=RequestMethod.GET)
	public String getAll(Map<String,Object>map, @ModelAttribute("addQueue") QueueCommand addQueue) {	
		
		return "addqueue";
	}	

	
	
	@ModelAttribute("select_Dptt")
	private List<String> getdept() {
Map<String,Object>map=new HashMap<>();
		  List<String>select_Dptt=new ArrayList<>(); 
		  List<QueueDTO> docListt = ser.fetchQueue();
	       for (QueueDTO doct : docListt) {
	    	   //select_Dptt.add( doct.getDpt_name());
	    	   select_Dptt.add(doct.getDpt_name());
	       }
	       map.put("select_Dptt",select_Dptt);
		return select_Dptt;
	}
    
	@ModelAttribute("dpt_location")
	private List<String> getLocation(Map<String,Object>map) {

		  List<String>dpt_location=new ArrayList<>(); 
		  List<QueueDTO> docListt = ser.fetchQueue();
	       for (QueueDTO doct : docListt) {
	    	  dpt_location.add(doct.getDpt_location().toString());
	       }
	   	map.put("dpt_location", dpt_location);
		return dpt_location;
	
	}

@ModelAttribute("firstname")
private List<String> firstname(){
	
	List<String> firstname = new ArrayList<>();
	List<QueueDTO> fdoc = ser.fetchQueue();
	 for(QueueDTO doq: fdoc ) {
		 
		 firstname.add(doq.getFname());
	 }
	
	return firstname;
	
}
	
@ModelAttribute("lname")
private List<String> lastname(){
	
	List<String> lname=  new ArrayList<>();
	List<QueueDTO> lnam = ser.fetchQueue();
	for(QueueDTO ldoc:lnam ) {
		
		lname.add(ldoc.getLname());
		
	}
	return lname;
}
	
	
	
		

}
