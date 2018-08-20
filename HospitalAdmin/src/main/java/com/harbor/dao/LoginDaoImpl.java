package com.harbor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harbor.bo.LoginBo;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	private static final String LOGINUSER = "SELECT COUNT(*) FROM hospital_admin WHERE username=? AND password=?";
	
	@Autowired
	JdbcTemplate jt;
	
	public int loginUser(LoginBo loginbo) {
		int count = 0;
		
		count = jt.queryForObject(LOGINUSER, Integer.class, loginbo.getUsername(), loginbo.getPassword());
		
		return count;
	}

}
