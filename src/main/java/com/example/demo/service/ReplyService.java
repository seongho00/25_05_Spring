package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReplyRepository;
import com.example.demo.vo.ResultData;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;

	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public ResultData writeReply(int loginedMemberId, int articleId, String replyBody) {
		
		replyRepository.writeReply(loginedMemberId, articleId, replyBody);
		
		return ResultData.from("S-1", "댓글 작성 성공");
		
	}

}
