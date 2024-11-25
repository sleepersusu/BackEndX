package com.example.bistro.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bistro.members.Members;
import com.example.bistro.menu.Menu;
import com.example.bistro.menu.MenuService;


@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private MenuService menuService;

	@GetMapping("/Bistro/findAllComment")
	public String findAllComment(Model model) {

		List<Comment> comments = commentService.findAllComment();

		model.addAttribute("allComments", comments);
		return "comment/showAllCommentView";
	}



	@GetMapping("/api/categories")
	public ResponseEntity<List<String>> getCategories() {
		List<String> categories = menuService.findAllCategories();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/api/menus/{category}")
	public ResponseEntity<List<Menu>> getMenusByCategory(@PathVariable String category) {
		List<Menu> menus = menuService.findMenusByCategory(category);
		return ResponseEntity.ok(menus);
	}

	@PostMapping("/Bistro/postComment")
	public ResponseEntity<Map<String, Object>> addCommentToDatabase(@RequestBody Map<String, Object> requestData) {

		Integer memberId = Integer.parseInt(requestData.get("memberId").toString());
		Integer menuSelect = Integer.parseInt(requestData.get("menuSelect").toString());
		Integer commentRating = Integer.parseInt(requestData.get("commentRating").toString());
		String commentMessage = requestData.get("commentMessage").toString();
		
		
		Comment newComment = new Comment();
		Members newMember = new Members();
		newMember.setId(memberId);

		Menu newMenu = new Menu();
		newMenu.setID(menuSelect);

		Menu menuById = menuService.findMenuById(menuSelect);
		String commentProduct = menuById.getProductName();

		newComment.setMembers(newMember);
		newComment.setMenu(newMenu);
		newComment.setCommentProduct(commentProduct);
		newComment.setCommentRating(commentRating);
		newComment.setCommentMessage(commentMessage);

		// 創建評論
		commentService.createComment(newComment);

		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("comment", newComment); // 可以返回剛創建的評論
		return ResponseEntity.ok(response);

	}

	@GetMapping("Bistro/findAllCommentByMember/{memberId}")
	public String findAllCommentByMember(@PathVariable Integer memberId, Model model) {

		List<Comment> comments = commentService.findCommentByMembers(memberId);
		model.addAttribute("allComments", comments);
		return "comment/showAllCommentByMemberView";
	}

	

}
