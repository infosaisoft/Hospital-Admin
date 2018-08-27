package com.harbor.service;

import java.util.List;

import com.harbor.dto.QueueDTO;

public interface QueueService {

	public List<QueueDTO> fetchQueue();
	public List<QueueDTO> AllQueue();
	public int InsertQueue (QueueDTO  QueueDTO);

}
