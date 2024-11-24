package com.example.bistro.pointsGet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointsGetService {
	
	@Autowired
	private PointsGetRepository pointsGetRepo;
	
	public List<Object[]> findAllPointsGet(){
		return pointsGetRepo.findMembersAllPointGet();
	}
	
}
