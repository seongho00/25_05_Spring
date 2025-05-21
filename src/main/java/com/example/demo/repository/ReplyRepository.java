package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyRepository {

	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String body);

	public List<Reply> getReplysById(String relTypeCode, int relId);

}
