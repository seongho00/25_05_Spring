package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ReactionPointRepository;
import com.example.demo.vo.ReactionPoint;

@Service
public class ReactionPointService {
	private ReactionPointRepository reactionPointRepository;

	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}

	public int getReactionPointCntByArticleId(int id, int type) {

		return reactionPointRepository.getReactionPointCntByArticleId(id, type);

	}

	public int getReactionPointCntByMemberId(int id, int loginedMemberId) {
		return reactionPointRepository.getReactionPointCntByMemberId(id, loginedMemberId);
	}

	public void insertReactionPoint(int articleId, int memberId, int type) {
		reactionPointRepository.insertReactionPoint(articleId, memberId, type);
		reactionPointRepository.updateArticleReactionPoint();

	}

	public void deleteReactionPoint(int articleId, int memberId) {
		reactionPointRepository.deleteReactionPoint(articleId, memberId);
		reactionPointRepository.updateArticleReactionPoint();
	}

	public void updateReactionPoint(int articleId, int memberId, int type) {
		reactionPointRepository.updateReactionPoint(articleId, memberId, type);
		reactionPointRepository.updateArticleReactionPoint();
	}

}
