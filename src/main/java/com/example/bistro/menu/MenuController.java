package com.example.bistro.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bistro.config.ImageService;

import jakarta.servlet.annotation.MultipartConfig;

@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuRepository menuRepo;
	
	
	@Autowired
	private ImageService imageService;
	

	@PostMapping("/Bistro/postMenu")
	public String postMenu(@RequestParam String productCategory, @RequestParam String productName,
			@RequestParam MultipartFile productImage, @RequestParam Integer productPrice,
			@RequestParam String productDescribe, @RequestParam Integer productCount,
			@RequestParam Integer minproductCount, @RequestParam(defaultValue = "0.0") Double avgScore,
			@RequestParam String menuStatus) throws IOException {
		
		
		String type = "menu";

		byte[] fileBytes = productImage.getBytes();
		String originalFilename = productImage.getOriginalFilename();

		Menu newMenu = new Menu();
		newMenu.setProductCategory(productCategory);
		newMenu.setProductName(productName);
//		newMenu.setProductImg(fileBytes);
		
		
		
		newMenu.setProductPrice(productPrice);
		newMenu.setProductDescribe(productDescribe);
		newMenu.setProductImgUrl(originalFilename);
		newMenu.setProductCount(productCount);
		newMenu.setMinproductCount(minproductCount);

		newMenu.setMenuStatus(menuStatus);

		if (avgScore == null) {
			newMenu.setAvgScore(0.0);
		} else {
			newMenu.setAvgScore(avgScore);
		}

		newMenu.setMenuStatus(menuStatus);
		

		menuService.createMenu(newMenu);
		
		imageService.imageUpload(type,newMenu.getID(),fileBytes);
		
		
		return "redirect:/Bistro/findAllMenu";

	}

//	@GetMapping("/Bistro/download/0")
//	public ResponseEntity<byte[]> downloadMenu(@RequestParam Integer ID) {
//		Optional<Menu> op = menuRepo.findById(ID);
//
//		if (op.isPresent()) {
//
//			Menu menu = op.get();
//			byte[] menubyte = menu.getProductImg();
//			String originalFilename = menu.getProductImgUrl();
//
//			String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//
//			MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM; // 默認為通用二進位流
//			if (fileExtension.equalsIgnoreCase("png")) {
//				mediaType = MediaType.IMAGE_PNG;
//			} else if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg")) {
//				mediaType = MediaType.IMAGE_JPEG;
//			}
//
//			HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.setContentType(mediaType);
//
//			return new ResponseEntity<byte[]>(menubyte, httpHeaders, HttpStatus.OK);
//
//		}
//
//		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
//	}

	@GetMapping("/Bistro/findAllMenu")
	public String findAllMenu(Model model) {

		// 查詢所有菜單
		List<Menu> menuList = menuService.findAllMenu();

		List<String> lowStockItems = new ArrayList<>();

		// 只檢查上架庫存是否不足 已下架庫存不理他
		for (Menu menu : menuList) {
			if ((menu.getProductCount() < menu.getMinproductCount()) && menu.getMenuStatus().equals("上架")) {
				lowStockItems.add(menu.getProductName());
			}
		}

		// 將庫存不足的商品名稱傳遞給前端
		model.addAttribute("lowStockItems", lowStockItems);

		// 查詢所有菜品的平均分數
		List<Object[]> avgScores = menuService.countAvgScores();

		// 創建一個 Map 用來存儲菜品名稱和平均分數
		Map<String, Double> avgScoreMap = new HashMap<>();
		for (Object[] avgScore : avgScores) {
			String productName = (String) avgScore[0]; // 取得菜品名稱
			Double score = (Double) avgScore[1]; // 取得平均分數
			avgScoreMap.put(productName, score);
		}

		// 為每個菜品設置平均分數
		for (Menu menu : menuList) {
			Double avgScore = avgScoreMap.get(menu.getProductName());
			if (avgScore != null) {
				double roundedAvgScore = Math.round(avgScore * 10.0) / 10.0;
				menu.setAvgScore(roundedAvgScore);// 設置平均分數

			} else {
				menu.setAvgScore(0.0); // 如果沒有評論則設置為 0
			}

		}

		menuRepo.saveAll(menuList);


		model.addAttribute("allMenu", menuList);
		model.addAttribute("lowStockItems", lowStockItems);
		model.addAttribute("menuList", menuList);

		return "menu/showAllMenuView";
	}

	@GetMapping("/Menu/updateMenu")
	public String updateMenu(@RequestParam Integer ID, Model model) {

		Menu updateMenu = menuService.findMenuById(ID);
		model.addAttribute("updateMenu", updateMenu);

		if ("已下架".equals(updateMenu.getMenuStatus())) {
			model.addAttribute("outOfStock", true);
		} else {
			model.addAttribute("outOfStock", false);
		}

		return "redirect:/Bistro/findAllMenu";

	}
	
	
	

	@PostMapping("/Bistro/updateMenuPost")
	@Transactional
	public String updateMenuPost(@ModelAttribute Menu menu, @RequestParam("productImage") MultipartFile file
			) {

		try {
			if (file.isEmpty()) {
				Menu findmenu = menuService.findMenuById(menu.getID());
				if (findmenu != null) {
					byte[] productImg = findmenu.getProductImg();
					String productImgUrl = findmenu.getProductImgUrl();

					menu.setProductImg(productImg);
					menu.setProductImgUrl(productImgUrl);
				}
			} else {
				byte[] fileBytes = file.getBytes();
				String filename = file.getOriginalFilename();
				menu.setProductImg(fileBytes);
				menu.setProductImgUrl(filename);
			}

			Menu updateMenu = menuService.updateMenu(menu);

			

			return "redirect:/Bistro/findAllMenu";

		} catch (IOException e) {

			e.printStackTrace();
		}

		return "Menu/errorPage";

	}

	@PostMapping("/Bistro/deleteMenu") // 下架商品
	public String deleteMenu(@RequestParam Integer ID) {

		menuService.deleteMenu(ID);

		return "redirect:/Bistro/findAllMenu";

	}

	@PostMapping("/Bistro/recoverMenu") // 下架商品恢復成上架
	public String recoverMenu(@RequestParam Integer ID) {

		menuService.recoverMenu(ID);

		return "redirect:/Bistro/findAllMenu";

	}




}
