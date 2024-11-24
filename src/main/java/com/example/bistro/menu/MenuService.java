package com.example.bistro.menu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class MenuService {


	@Autowired
	private MenuRepository menuRepo;

	public Menu createMenu(Menu menu) {

		return menuRepo.save(menu);

	}

	public Menu findMenuById(Integer id) {
		Optional<Menu> op = menuRepo.findById(id);
		if (op.isPresent()) {

			return op.get();
		}

		return null;
	}

	public void deleteMenu(Integer id) {
		Optional<Menu> op = menuRepo.findById(id);
		if (op.isPresent()) {

			Menu menu = op.get();
			menu.setMenuStatus("已下架");
			menuRepo.save(menu);
			return;
		}
	}

	public void recoverMenu(Integer id) {
		Optional<Menu> op = menuRepo.findById(id);
		if (op.isPresent()) {

			Menu menu = op.get();
			menu.setMenuStatus("上架");
			menuRepo.save(menu);
			return;
		}

	}

	public Menu updateMenu(Menu menu) {
		return menuRepo.save(menu);
	}

	public List<Menu> findAllMenu() {
		return menuRepo.findAll();
	}

	public List<Menu> findMenuByNameLike(String productname) {
		List<Menu> menuByNameLike = menuRepo.findMenuByNameLike("%" + productname + "%");

		return menuByNameLike;

	}

	public Menu findMenuByProductName(String productName) {
		Menu findMenuByProductName = menuRepo.findByProductName(productName);
		return findMenuByProductName;
	}

	// 查詢所有菜品的平均分數
	public List<Object[]> countAvgScores() {
		return menuRepo.countAvgScores();
	}

	// 上架商品
	public List<Menu> findMenuByStatusIsSold() {
		return menuRepo.findMenuByStatusIsSold();
	}

	public List<String> findAllCategories() {
		return menuRepo.findAllCategories();
	}

	public List<Menu> findMenusByCategory(String category) {
		return menuRepo.findMenusByCategory(category);
	}

	

}
