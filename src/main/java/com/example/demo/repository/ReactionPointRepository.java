package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.ReactionPoint;

@Mapper
public interface ReactionPointRepository {

	int getReactionPointCntByArticleId(int articleId, int type);

}
