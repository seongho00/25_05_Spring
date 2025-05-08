package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

@Service
public class MemberService {
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void doJoin(String loginId, String loginPw, String name, String email) {
		memberRepository.doJoin(loginId, loginPw, name, email);

	}

	public Member getMemberByLoginId(String loginId) {

		return memberRepository.getMemberByLoginId(loginId);

	}

	public boolean isAvailableLogindId(String loginId) {
		Member member = memberRepository.getMemberByLoginId(loginId);
		if (member == null) {
			return true;
		}
		return false;
	}

	public Member getMemberByNameEmail(String name, String email) {

		return memberRepository.getMemberByNameEmail(name, email);

	}
}
