package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.ReactionPoint;

@Mapper
public interface ReactionPointRepository {

	int getReactionPointCntByArticleId(int articleId, int type);

	int getReactionPointCntByMemberId(int id, int memberId);

	void insertReactionPoint(int articleId, int memberId, int type);

	void deleteReactionPoint(int articleId, int memberId);

	void updateReactionPoint(int articleId, int memberId, int type);

}
