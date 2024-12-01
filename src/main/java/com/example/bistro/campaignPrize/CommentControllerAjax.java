package com.example.bistro.campaignPrize;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bistro.comment.CommentService;

@RestController
public class CommentControllerAjax {

	@Autowired
	private CommentService commentService;

	@PostMapping("/Bistro/deleteComment")
	public ResponseEntity<Void> deleteComment(@RequestBody Map<String, Object> requestData) {
		Integer ID = Integer.parseInt(requestData.get("ID").toString()); // 從請求中提取 ID

		boolean isDeleted = commentService.deleteComment(ID);

		if (isDeleted) {
			return ResponseEntity.ok().build(); // 成功刪除
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 資源找不到
		}
	}
}
