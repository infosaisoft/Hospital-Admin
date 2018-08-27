package com.harbor.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.harbor.bo.QueueBO;
@Repository
public class QueueDaoImpl implements QueueDAO  {
  
	private static final String ViewQueue = "SELECT departments.dpt_name,departments.dpt_location,hospital_admin.fname,hospital_admin.lname FROM departments INNER JOIN hospital_admin ON departments.hid =	hospital_admin.hid	 WHERE hospital_admin.role='doctor' AND departments.dpt_name=hospital_admin.dpt_name";
	
	private static final String ShowQueue = "SELECT dpt_name,dpt_location,room_no,fname,lname FROM queue_mng";
	
	private static final String InsertQueue = "insert into queue_mng (dpt_name,dpt_location,room_no,fname,lname) values (?,?,?,?,?)";
	@Autowired
	private JdbcTemplate JT;
	
	public int AddQueue(QueueBO QueueBO) {
		int insertCount = 0;
		insertCount = JT.update(InsertQueue, QueueBO.getDpt_name(),QueueBO.getDpt_location(),QueueBO.getRoom_no(),QueueBO.getFname(),QueueBO.getLname());
		return insertCount;
	}

	public List<QueueBO> getAll() {
		
		List<QueueBO>listbo=null;
		
		listbo=JT.query(ViewQueue, new ResultSetExtractor<List<QueueBO>>() {

			public List<QueueBO> extractData(ResultSet rs) throws SQLException, DataAccessException {
				 QueueBO bo=null;
				List<QueueBO>listbo=null;
				listbo=new ArrayList<>();
			
				
				while(rs.next()) {
					bo=new QueueBO();
					bo.setDpt_name(rs.getString(1));
				   System.out.println(bo.getDpt_name());
					bo.setDpt_location(rs.getString(2));
					System.out.println(bo.getDpt_location());
					bo.setFname(rs.getString(3));
					bo.setLname(rs.getString(4));			
					listbo.add(bo);
				}
				
				return listbo;
			}
			
			
		});
		        System.out.println(listbo);
			return listbo;
	}

public List<QueueBO> QueueAll() {
		
		List<QueueBO>listqbo=null;
		
		listqbo=JT.query(ShowQueue, new ResultSetExtractor<List<QueueBO>>() {

			public List<QueueBO> extractData(ResultSet rss) throws SQLException, DataAccessException {
				 QueueBO qbo=null;
				List<QueueBO>listqbo=null;
				listqbo=new ArrayList<>();
				qbo=new QueueBO();
				
				while(rss.next()) {
					qbo.setDpt_name(rss.getString(1));
				    System.out.println(qbo.getDpt_name());
					qbo.setDpt_location(rss.getString(2));
					qbo.setRoom_no(rss.getInt(3));
					qbo.setFname(rss.getString(4));
					qbo.setLname(rss.getString(5));
					
					
					
					
					
					
					listqbo.add(qbo);
				}
				
				return listqbo;
			}
			
			
		});
		return	listqbo;
	}

	
	
	
}
