package com.harbor.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.LoginBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.LoginDao;
import com.harbor.dto.LoginDto;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDao logindao;
	
	public String verifyUser(LoginDto logindto) {
		
		int count = 0;
		LoginBo loginbo = null;
		
		
		// copy dto to bo		
		
	
		
		loginbo = new LoginBo();
		BeanUtils.copyProperties(logindto, loginbo);
		
		// Use DAO
		
		count = logindao.loginUser(loginbo);
		String admin_id=loginbo.getAdmin_id();
		logindto.setAdmin_id(admin_id);
		logindto.setHid(loginbo.getHid());
		if(count == 0) {
			return "failed";
		}else {
			return "success";
		}
		
	}

}
