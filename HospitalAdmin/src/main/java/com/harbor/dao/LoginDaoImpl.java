package com.harbor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harbor.bo.LoginBo;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	private static final String LOGINUSER = "SELECT COUNT(*) FROM hospital_admin WHERE username=? AND password=?";
	
	private static final String GETADMINID="SELECT admin_id FROM hospital_admin  WHERE username=? AND password=?";
	private static final String GETHID="SELECT hid FROM hospital_admin  WHERE username=? AND password=?";
	
	@Autowired
	JdbcTemplate jt;
	
	public int loginUser(LoginBo loginbo) {
		int count = 0;
		String adminid=null,hid=null;
		count = jt.queryForObject(LOGINUSER, Integer.class, loginbo.getUsername(), loginbo.getPassword());
		
		if(count == 1) {
			adminid=jt.queryForObject(GETADMINID,String.class,loginbo.getUsername(),loginbo.getPassword());		
			hid=jt.queryForObject(GETHID,String.class,loginbo.getUsername(),loginbo.getPassword());		
			loginbo.setAdmin_id(adminid);
			loginbo.setHid(hid);
			
		}
		
		System.out.println(loginbo.getAdmin_id());
		return count;
	}
	
	
}
