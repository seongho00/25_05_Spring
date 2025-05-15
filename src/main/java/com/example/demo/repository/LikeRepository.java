package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Like;

@Mapper
public interface LikeRepository {

	public int getLikeCountByArticleId(int articleId);

	public void setLike(int articleId, int memberId);

	public Like getLikeByMemberId(int memberId);

	public void deleteLike(int memberId);
}
