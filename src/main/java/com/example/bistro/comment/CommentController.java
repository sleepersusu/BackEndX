package com.example.bistro.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class CommentController {

	
	@Autowired
	private CommentRepository commentRepo;
	
	
	@GetMapping("/Bistro/findAllComment")
	public String findAllComment(Model model) {
		List<Comment> comments = commentRepo.findAll();
		model.addAttribute("allComments", comments);
		return "showAllCommentView";
	}
	
}
