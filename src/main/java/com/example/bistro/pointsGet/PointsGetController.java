package com.example.bistro.pointsGet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PointsGetController {
	
	@Autowired
	private PointsGetService PGService;
	
	@GetMapping("/Bistro/PointGet/showAll")
	public String showAll(Model model) {
		List<Object[]> results = PGService.findAllPointsGet();
		model.addAttribute("allPointsGet", results);
	    for (Object[] row : results) {
	        System.out.println("PointGet ID: " + row[0] + ", Order ID: " + row[1] + ", Member ID: " + row[2] +
	                           ", Member Name: " + row[3] + ", Orders Sum Price: " + row[4] +
	                           ", Point Getted: " + row[5] + ", Created At: " + row[6]);
	    }
		return "pointPrizes/showPointGet";
	}
	
	
}
