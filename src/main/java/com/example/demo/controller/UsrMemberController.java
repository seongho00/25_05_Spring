package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(HttpSession session, String loginId, String loginPw, String checkPw, String name,
			String email) {

		if (session.getAttribute("loginedMember") != null) {
			return ResultData.from("F-A", Ut.f("이미 로그인 중"));
		}
		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", Ut.f("아이디를 입력해주세요"), loginId);

		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", Ut.f("비밀번호를 입력해주세요"), loginPw);

		}
		if (Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-3", Ut.f("이름을 입력해주세요"), name);

		}
		if (Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-4", Ut.f("이메일을 입력해주세요"), email);

		}

		if (loginPw.equals(checkPw) == false) {
			return ResultData.from("F-5", Ut.f("두 비밀번호[(%s),(%s)]가 일치하지 않습니다.", loginPw, checkPw));

		}

		boolean isExistLogindId = memberService.isExistLogindId(loginId);

		if (isExistLogindId == true) {
			return ResultData.from("F-6", Ut.f("이미 가입된 ID(%s) 입니다.", loginId));

		}

		Member member = memberService.getMemberByNameEmail(name, email);
		if (member != null) {
			return ResultData.from("F-7", Ut.f("이미 가입된 이름(%s), email(%s) 입니다.", name, email));

		}

		memberService.doJoin(loginId, loginPw, name, email);

		return ResultData.from("S-1", Ut.f("%d번 회원가입되었습니다.", member.getId()));
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession session, String loginId, String loginPw) {

		if (session.getAttribute("loginedMember") != null) {
			return ResultData.from("F-A", Ut.f("이미 로그인 중"));
		}

		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", Ut.f("아이디를 입력해주세요"), loginId);

		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", Ut.f("비밀번호를 입력해주세요"), loginPw);

		}

		boolean isExistLogindId = memberService.isExistLogindId(loginId);
		if (isExistLogindId == false) {
			return ResultData.from("F-3", Ut.f("%s는 가입되어 있지 않은 ID 입니다.", loginId));
		}

		Member loginedMember = memberService.getMemberByLoginId(loginId);

		if (loginedMember.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", Ut.f("비밀번호가 일치하지 않습니다."), loginPw);
		}

		session.setAttribute("loginedMember", loginedMember);
		session.setAttribute("loginedMemberId", loginedMember.getId());

		return ResultData.from("S-1", Ut.f("%s님 환영합니다.", loginedMember.getName()), loginedMember);

	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMember") == null) {
			return ResultData.from("F-A", Ut.f("이미 로그아웃 중"));
		}

		session.removeAttribute("loginedMember");

		return ResultData.from("S-1", Ut.f("로그아웃 되었습니다."));

	}

}
