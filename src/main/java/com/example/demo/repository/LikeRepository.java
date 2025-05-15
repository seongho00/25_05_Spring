package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeRepository {

	public int getLikeCountByArticleId(int articleId);

	public void setLike(int articleId, int memberId);
}
