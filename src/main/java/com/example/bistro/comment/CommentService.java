package com.example.bistro.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return commentRepo.findCommentByMembers(memberId);

	}

	public Comment findCommentById(Integer id) {
		Optional<Comment> op = commentRepo.findById(id);

		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	public boolean deleteComment(Integer id) {
		Optional<Comment> op = commentRepo.findById(id);
		if (op.isPresent()) {
			Comment comment = op.get();
			commentRepo.delete(comment);
			return true;
		}
		return false;
		
	}

	public Comment updateComment(Comment comment) {
		return commentRepo.save(comment);
	}

	@Transactional
	public List<Comment> findCommentByMembers(Integer memberId) {

		return commentRepo.findCommentByMembers(memberId);

	}
	@Transactional
	public List<Comment> findCommentByMenuId(Integer menuId) {

		return commentRepo.findCommentByMenuId(menuId);

	}
}
