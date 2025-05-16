package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Comment;

@Mapper
public interface CommentRepository {

	public void writeComment(int articleId, int memberId, String body);

	public List<Comment> getCommentByArticleId(int articleId);

}
