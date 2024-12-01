package com.example.bistro.campaignPrize;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bistro.campaign.Campaign;
import com.example.bistro.campaign.CampaignService;

@Controller
public class CampaignPrizesController {

	@Autowired
	CampaignPrizeService campaignPrizeService;

	@Autowired
	CampaignService campaignService;

	@GetMapping("/Bistro/campaign/prize/findAll")
	public String findAllPrize(Model model) {
		List<CampaignPrizes> prizeList = campaignPrizeService.findAllCampaignPrizes();
		List<Campaign> campaigns = campaignService.findAllCampaign();
		model.addAttribute("campaigns", campaigns);
		model.addAttribute("prizeList", prizeList);
		return "campaign/campaignPrizesView";
	}

	@PostMapping("/Bistro/campaign/prize/create")
	public String insertPrize(@RequestParam Integer campaignId,
								@RequestParam String prizeName,
								@RequestParam MultipartFile prizeImg, 
								@RequestParam Integer prizeQuantity,
								@RequestParam String prizeDescription,
								@RequestParam BigDecimal probability) throws IOException {

		byte[] imgByte = prizeImg.getBytes();
		Campaign campaign = campaignService.findCampaignById(campaignId);
		CampaignPrizes prizes = new CampaignPrizes();
		prizes.setPrizeName(prizeName);
		prizes.setPrizeImg(imgByte);
		prizes.setPrizeQuantity(prizeQuantity);
		prizes.setPrizeDescription(prizeDescription);
		prizes.setProbability(probability);
		prizes.setCampaign(campaign);
		campaignPrizeService.insertPrize(prizes);

		return "redirect:/Bistro/campaign/prize/findAll";
	}

	@GetMapping("/images/download")
	public ResponseEntity<byte[]> downloadPhotos(@RequestParam Integer id) {
		CampaignPrizes prize = campaignPrizeService.findPrizeById(id);

		byte[] prizeImg = prize.getPrizeImg();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(prizeImg, headers, HttpStatus.OK);

	}

	@PostMapping("/Bistro/campaign/prize/delete")
	public String deletePrize(@RequestParam Integer id) {
		campaignPrizeService.deletePrizeById(id);
		return "redirect:/Bistro/campaign/prize/findAll";
	}

	@PostMapping("/Bistro/campaign/prize/update")
	public String updatePrize(@RequestParam Integer id, 
								@RequestParam Integer campaignId,
								@RequestParam String prizeName, 
								@RequestParam(required = false) MultipartFile prizeImg,
								@RequestParam Integer prizeQuantity, 
								@RequestParam String prizeDescription,
								@RequestParam BigDecimal probability) throws IOException {

		CampaignPrizes prize = campaignPrizeService.findPrizeById(id);
		Campaign campaign = campaignService.findCampaignById(campaignId);

		prize.setPrizeName(prizeName);
		prize.setPrizeQuantity(prizeQuantity);
		prize.setPrizeDescription(prizeDescription);
		prize.setProbability(probability);
		prize.setCampaign(campaign);

		
		if (prizeImg != null && !prizeImg.isEmpty()) {
			prize.setPrizeImg(prizeImg.getBytes());
		}

		campaignPrizeService.updatePrize(prize);

		return "redirect:/Bistro/campaign/prize/findAll";
	}

}
