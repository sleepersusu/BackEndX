package com.example.bistro.PointsRecords;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class PointsRecordsService {

	@Autowired
	private PointsRecordsRepository PRRepo;
	
	public List<Object[]> findAllPointsRecords(){
		return PRRepo.findMembersAllPointRecord();
	}
	
	
	
}
