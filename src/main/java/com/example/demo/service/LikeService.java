package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LikeRepository;
import com.example.demo.vo.Like;

@Service
public class LikeService {
	@Autowired
	private LikeRepository likeRepository;

	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}

	public int getLikeCountByArticleId(int articleId) {

		return likeRepository.getLikeCountByArticleId(articleId);
	}

	public void setLike(int articleId, int memberId) {

		likeRepository.setLike(articleId, memberId);
	}

}
