package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Board;

@Mapper
public interface ReplyRepository {

	public void writeReply(int loginedMemberId, int articleId, String replyBody);

}
