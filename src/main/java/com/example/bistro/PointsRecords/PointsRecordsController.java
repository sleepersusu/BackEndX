package com.example.bistro.PointsRecords;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PointsRecordsController {

	@Autowired
	private PointsRecordsService PRService;
	
	@GetMapping("/Bistro/PointsRecords/showAll")
	public String showAll(Model model) {
		List<Object[]> results = PRService.findAllPointsRecords();
		model.addAttribute("allPointsRecords", results);
		return "pointPrizes/showPointRecoed";
	}
	
	
}
