package com.example.bistro.PointsTotal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PointsTotalService {

	@Autowired
	private PointsTotalRepository PTRepo;
	
	public List<Object[]> findAllPointsTotal(){
		return PTRepo.findPointsTotal();
	}
}
