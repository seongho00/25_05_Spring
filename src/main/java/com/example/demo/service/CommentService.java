package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.vo.Comment;

@Service
public class CommentService {

	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public void writeComment(int articleId, int memberId, String body) {
		commentRepository.writeComment(articleId, memberId, body);
	}

	public List<Comment> getCommentByArticleId(int articleId) {

		return commentRepository.getCommentByArticleId(articleId);

	}

}
