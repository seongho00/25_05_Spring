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

}
