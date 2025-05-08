package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Controller
public class UsrMemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String checkPw, String name, String email) {
		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-6", Ut.f("아이디를 입력해주세요"), loginId);

		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-6", Ut.f("비밀번호를 입력해주세요"), loginPw);

		}
		if (Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-6", Ut.f("이름을 입력해주세요"), name);

		}
		if (Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-6", Ut.f("이메일을 입력해주세요"), email);

		}

		if (loginPw.equals(checkPw) == false) {
			return ResultData.from("F-7", Ut.f("두 비밀번호[(%s),(%s)]가 일치하지 않습니다.", loginPw, checkPw));

		}

		boolean isAvailableLoginId = memberService.isAvailableLogindId(loginId);

		if (isAvailableLoginId == false) {
			return ResultData.from("F-8", Ut.f("이미 가입된 ID(%s) 입니다.", loginId));

		}

		Member member = memberService.getMemberByNameEmail(name, email);
		if (member != null) {
			return ResultData.from("F-9", Ut.f("이미 가입된 이름(%s), email(%s) 입니다.", name, email));

		}

		memberService.doJoin(loginId, loginPw, name, email);

		return ResultData.from("S-6", Ut.f("%d번 회원가입되었습니다.", member.getId()));
	}

}
