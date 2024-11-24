package com.example.bistro.PointsTotal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PointsTotalController {
	
	@Autowired
	private PointsTotalService PTService;

	@GetMapping("/Bistro/PointsTotal/showAll")
	public String showAll(Model model) {
		List<Object[]> results = PTService.findAllPointsTotal();
		model.addAttribute("allPointsTotal", results);
		return "pointPrizes/showPointTotal";
	}
	
	
}
