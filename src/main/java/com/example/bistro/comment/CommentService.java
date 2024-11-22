package com.example.bistro.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepo;

	public Comment createComment(Comment comment) {
		return commentRepo.save(comment);
	}
	
	public List<Comment> findAllComment() {
		return commentRepo.findAll();
	}

	public List<Comment> findCommentByMemberId(Integer memberId) {
		return commentRepo.findCommentByMember(memberId);

	}
	
	
	public List<Comment> findCommentByMenuId(Integer menuId) {
		return commentRepo.findCommentByMenuId(menuId);

	}
	
	
	public Comment findCommentById(Integer id) {
		Optional<Comment> op = commentRepo.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}
		return null;		
	}
	
	
	public void deleteComment(Integer id) {
		Optional<Comment> op = commentRepo.findById(id);
		if(op.isPresent()) {
			Comment comment = op.get();
			commentRepo.delete(comment);
		}
		return;
	}
	
	
	public Comment updateComment(Comment comment) {
		return commentRepo.save(comment);
	}
	
	
	
}
