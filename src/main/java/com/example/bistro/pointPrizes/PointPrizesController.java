package com.example.bistro.pointPrizes;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PointPrizesController {

	@Autowired
	private PointPrizesService pointPrizesService;

	@GetMapping("/Bistro/PointPrizes/showAll")
	public String showAll(Model model) {
		List<PointPrizesBean> pointPrizesList = pointPrizesService.findAllPointPrizes();
		model.addAttribute("pointPrizesList", pointPrizesList);
		System.out.println("-----------------------------------------------------------------");
		if (pointPrizesList.isEmpty()) {
			System.out.println("Empty");
		}
		return "pointPrizes/showPointPrizes";
	}

	@PostMapping("/Bistro/PointPrizes/create")
	public String createPointPrize(@RequestParam String pointPrizesName, @RequestParam Integer pointPrizesPoints,
			@RequestParam String pointPrizesDescription, @RequestParam Integer pointPrizesCount,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date pointPrizesExpiration,
			@RequestParam MultipartFile pointPrizesImg, Model model) throws IOException {

		byte[] fileByte = pointPrizesImg.getBytes();

		PointPrizesBean pointPrizesbenBean = new PointPrizesBean();
		pointPrizesbenBean.setPointPrizesName(pointPrizesName);
		pointPrizesbenBean.setPointPrizesPoints(pointPrizesPoints);
		pointPrizesbenBean.setPointPrizesDescription(pointPrizesDescription);
		pointPrizesbenBean.setPointPrizesCount(pointPrizesCount);
		pointPrizesbenBean.setPointPrizesExpiration(pointPrizesExpiration);
		pointPrizesbenBean.setPointPrizesImg(fileByte);

		pointPrizesService.createPointPrizes(pointPrizesbenBean);

		model.addAttribute("message", "兌換商品已成功新增！");
		return "redirect:/Bistro/PointPrizes/showAll";
	}

	@PostMapping("/Bistro/PointPrizes/delete")
	public String deletePointPrize(@RequestParam Integer id) throws IOException {
		System.out.println("delete");
		System.out.println(id);
		pointPrizesService.deletePointPrizesById(id);
	
		return "redirect:/Bistro/PointPrizes/showAll";
	}
	
	@PostMapping("/Bistro/PointPrizes/update")
	public String updatePointPrize(
	        @RequestParam Integer id, 
	        @RequestParam String pointPrizesName,
	        @RequestParam Integer pointPrizesPoints,
	        @RequestParam String pointPrizesDescription,
	        @RequestParam Integer pointPrizesCount,
	        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date pointPrizesExpiration,
	        @RequestParam(required = false) MultipartFile pointPrizesImg, // 圖片可選
	        Model model) throws IOException {

	    // 獲取原有的獎品數據
	    PointPrizesBean existingBean = pointPrizesService.findById(id);
	    if (existingBean == null) {
	        model.addAttribute("errorMessage", "未找到對應的獎品記錄！");
	        return "errorPage.html"; // 返回錯誤頁面
	    }

	    // 更新字段
	    existingBean.setPointPrizesName(pointPrizesName);
	    existingBean.setPointPrizesPoints(pointPrizesPoints);
	    existingBean.setPointPrizesDescription(pointPrizesDescription);
	    existingBean.setPointPrizesCount(pointPrizesCount);
	    existingBean.setPointPrizesExpiration(pointPrizesExpiration);

	    // 如果上傳了新圖片，則更新；否則保留原圖片
	    if (pointPrizesImg != null && !pointPrizesImg.isEmpty()) {
	        byte[] fileBytes = pointPrizesImg.getBytes();
	        existingBean.setPointPrizesImg(fileBytes);
	    }

	    // 保存更新數據
	    pointPrizesService.updatePointPrizes(existingBean);

	    model.addAttribute("message", "兌換商品已成功更新！");
	    return "redirect:/Bistro/PointPrizes/showAll";
	}

	
	
}
