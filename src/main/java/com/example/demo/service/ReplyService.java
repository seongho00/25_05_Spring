package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReplyRepository;
import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultData;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;

	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public ResultData writeReply(int loginedMemberId, String relTypeCode, int relId, String body) {

		replyRepository.writeReply(loginedMemberId, relTypeCode, relId, body);

		return ResultData.from("S-1", "댓글 작성 성공");

	}

	public List<Reply> getReplysById(String relTypeCode, int relId) {

		return replyRepository.getReplysById(relTypeCode, relId);
	}

}
