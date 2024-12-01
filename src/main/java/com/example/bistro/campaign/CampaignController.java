package com.example.bistro.campaign;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class CampaignController {

	@Autowired
	CampaignService campaignService;
	
	@GetMapping("/Bistro/campaign/findAll")
	public String getMethodName(Model model) {
		List<Campaign> allCampaign = campaignService.findAllCampaign();
		model.addAttribute("allCampaign", allCampaign);
		return "campaign/campaignView";
	}	
	
	@PostMapping("/Bistro/campaign/create")
	public String createPost(@RequestParam String campaignTitle, 
							@RequestParam String campaignDescription, 
							@RequestParam String campaignType, 
							@RequestParam Integer minOrderAmount,
							@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") @RequestParam Date startDate,
							@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") @RequestParam Date endDate,
							@RequestParam String note) {
		Campaign campaign = new Campaign();
		campaign.setCampaignTitle(campaignTitle);
		campaign.setCampaignDescription(campaignDescription);
		campaign.setCampaignType(campaignType);
		campaign.setMinOrderAmount(minOrderAmount);
		campaign.setStartDate(startDate);
		campaign.setEndDate(endDate);
		campaign.setNote(note);
		
		campaignService.insertCampaign(campaign);
		
		return "redirect:/Bistro/campaign/findAll";
	}
	
	
	@PostMapping("/Bistro/campaign/update")
	public String updatePost(HttpServletRequest request) {
	    try {
	        Campaign campaign = new Campaign();
	        campaign.setId(Integer.parseInt(request.getParameter("id")));
	        campaign.setCampaignTitle(request.getParameter("campaignTitle"));
	        campaign.setCampaignDescription(request.getParameter("campaignDescription"));
	        campaign.setCampaignType(request.getParameter("campaignType"));
	        campaign.setMinOrderAmount(Integer.parseInt(request.getParameter("minOrderAmount")));
	        campaign.setNote(request.getParameter("note"));
	        
	       
	        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	        
	        
	        Date startDate = inputFormat.parse(request.getParameter("startDate"));
	        campaign.setStartDate(outputFormat.parse(outputFormat.format(startDate)));
	        
	        Date endDate = inputFormat.parse(request.getParameter("endDate"));
	        campaign.setEndDate(outputFormat.parse(outputFormat.format(endDate)));	        
	        
	        Campaign existingCampaign = campaignService.findCampaignById(campaign.getId());
	        if (existingCampaign != null) {
	            campaign.setCreatedAt(existingCampaign.getCreatedAt());
	        }
	        
	        campaignService.updateCampaign(campaign);	        
	        return "redirect:/Bistro/campaign/findAll";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/Bistro/campaign/findAll";
	    }
	}
	
	@PostMapping("/Bistro/campaign/delete")
	public String deleteById(@RequestParam Integer id) {
		campaignService.deleteCampaignById(id);
		return "redirect:/Bistro/campaign/findAll";
	}
	
	
	
	
	
}
