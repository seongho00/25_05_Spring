package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {
//	@Insert("INSERT INTO `member` SET regDate = NOW(), updateDate = NOW(), loginId = #{loginId}, loginPw = #{loginPw}, `name` = #{name}")
	public int doJoin(String loginId, String loginPw, String name, String email);

//	@Select("SELECT * FROM `member` WHERE loginId = #{loginId}")
	public Member getMemberByLoginId(String loginId);

	public Member getMemberByNameEmail(String name, String email);

}
