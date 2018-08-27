package com.harbor.dao;

import java.util.List;

import com.harbor.bo.QueueBO;

public interface QueueDAO {
	
	public List<QueueBO> getAll();
	
	public List<QueueBO> QueueAll();
	
	public int AddQueue(QueueBO QueueBO);

}
