package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String checkPw, String name, String email) {
		if (Ut.isEmptyOrNull(loginId)) {
			return "아이디를 입력해주세요";
		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return "비밀번호를 입력해주세요";
		}
		if (Ut.isEmptyOrNull(name)) {
			return "이름을 입력해주세요";
		}
		if (Ut.isEmptyOrNull(email)) {
			return "이메일을 입력해주세요";
		}

		if (loginPw.equals(checkPw) == false) {
			return "비밀번호가 일치하지 않습니다.";
		}

		boolean isAvailableLoginId = memberService.isAvailableLogindId(loginId);

		if (isAvailableLoginId == false) {
			return "이미 가입된 ID 입니다.";
		}

		Member member = memberService.getMemberByNameEmail(name, email);
		if (member != null) {
			return "이미 가입된 이름, email 입니다.";
		}

		memberService.doJoin(loginId, loginPw, name, email);

		return name + "님 회원가입되었습니다.";
	}

}
