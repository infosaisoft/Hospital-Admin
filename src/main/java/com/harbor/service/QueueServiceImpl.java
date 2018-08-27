package com.harbor.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import com.harbor.bo.QueueBO;
import com.harbor.dao.QueueDAO;
import com.harbor.dto.QueueDTO;
@Service
public class QueueServiceImpl  implements QueueService{

@Autowired
QueueDAO dao;
	
public List<QueueDTO> fetchQueue() {
		// TODO Auto-generated method stub
		
		List<QueueBO> listBO = null;
		List<QueueDTO> listDTO = new ArrayList();
		
		//use dAO getting all methods of dao in  listBO
				listBO=dao.getAll();
				//copy listBO to listDTO  using lamda  all  funcn in bo1
				
				listBO.forEach(bo->{
					
					QueueDTO dto1=new QueueDTO();
					System.out.println("servicebo:::"+bo.getDpt_location());
					BeanUtils.copyProperties(bo,dto1);
					System.out.println("service view::"+dto1.getDpt_location());
					listDTO.add(dto1);
				});
				
				return listDTO;
	
	}

public int InsertQueue (QueueDTO  QueueDTO) {
	
	int count1 = 0;
	String datainsert = null;
	QueueBO boq = new QueueBO();
	// convert DTO to BO
	BeanUtils.copyProperties(QueueDTO, boq);
    count1 = dao.AddQueue(boq);
	return count1;
	
}


public List<QueueDTO> AllQueue() {
	// TODO Auto-generated method stub
	
	List<QueueBO> listBO1 = null;
	List<QueueDTO> listDTO1 = new ArrayList();
	
	//use dAO getting all methods of dao in  listBO
			listBO1=dao.QueueAll();
			//copy listBO to listDTO  using lamda  all  funcn in bo1
			
			listBO1.forEach(bo2->{
				QueueDTO dto2=new QueueDTO();
				System.out.println("servicebo:::"+bo2.getDpt_location());
				BeanUtils.copyProperties(bo2,dto2);
				System.out.println("service view::"+dto2.getDpt_name());
				listDTO1.add(dto2);
			});
			
			return listDTO1;

}
	
	
}
