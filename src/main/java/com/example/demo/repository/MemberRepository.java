package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {
//	@Insert("INSERT INTO `member` SET regDate = NOW(), updateDate = NOW(), loginId = #{loginId}, loginPw = #{loginPw}, `name` = #{name}")
	public int doJoin(String loginId, String loginPw, String name);

//	@Select("SELECT * FROM `member` WHERE loginId = #{loginId}")
	public Member getMemberByLoginId(String loginId);

}
