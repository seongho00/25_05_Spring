package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentRepository {

	void writeComment(int articleId, int memberId, String body);

}
