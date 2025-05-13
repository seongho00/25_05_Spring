package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	@Autowired
	private Rq rq;

	@Autowired
	MemberService memberService;

	private String nullCheck(String loginId, String loginPw, String name, String email) {

		nullCheck(loginId, loginPw);

		if (Ut.isEmptyOrNull(name)) {
			return Ut.jsHistoryBack("F-3", Ut.f("이름을 입력해주세요"));

		}
		if (Ut.isEmptyOrNull(email)) {
			return Ut.jsHistoryBack("F-4", Ut.f("이메일을 입력해주세요"));

		}
		return null;

	}

	private String nullCheck(String loginId, String loginPw) {
		if (Ut.isEmptyOrNull(loginId)) {
			return Ut.jsHistoryBack("F-1", Ut.f("아이디를 입력해주세요"));

		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return Ut.jsHistoryBack("F-2", Ut.f("비밀번호를 입력해주세요"));

		}
		return null;

	}

	@RequestMapping("/usr/member/joinPage")
	public String joinPage() {
		return "usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String checkPw, String name, String email) {

		if (nullCheck(loginId, loginPw, name, email) != null) {
			return nullCheck(loginId, loginPw, name, email);
		}

		if (loginPw.equals(checkPw) == false) {
			return Ut.jsHistoryBack("F-5", Ut.f("두 비밀번호[(%s),(%s)]가 일치하지 않습니다.", loginPw, checkPw));

		}

		boolean isExistLogindId = memberService.isExistLogindId(loginId);

		if (isExistLogindId == true) {
			return Ut.jsHistoryBack("F-6", Ut.f("이미 가입된 ID(%s) 입니다.", loginId));

		}

		Member member = memberService.getMemberByNameEmail(name, email);
		if (member != null) {
			return Ut.jsHistoryBack("F-7", Ut.f("이미 가입된 이름(%s), email(%s) 입니다.", name, email));

		}
		memberService.doJoin(loginId, loginPw, name, email);
		Member registerMember = memberService.getMemberByLoginId(loginId);

		return Ut.jsReplace("S-1", Ut.f("%d번 회원가입되었습니다.", registerMember.getId()), "../member/loginPage");
	}

	@RequestMapping("/usr/member/loginPage")
	public String loginPage(HttpSession session, String loginId, String loginPw) {
		return "/usr/member/login";

	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

		if (nullCheck(loginId, loginPw) != null) {
			return nullCheck(loginId, loginPw);
		}

		boolean isExistLogindId = memberService.isExistLogindId(loginId);
		if (isExistLogindId == false) {
			return Ut.jsHistoryBack("F-3", Ut.f("%s는 가입되어 있지 않은 ID 입니다.", loginId));
		}

		Member loginedMember = memberService.getMemberByLoginId(loginId);

		if (loginedMember.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-4", Ut.f("비밀번호가 일치하지 않습니다."));
		}

		rq.login(loginedMember);

		return Ut.jsReplace("S-1", Ut.f("%s님 환영합니다.", loginedMember.getName()), "/");

	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {

		rq.logout();

		return Ut.jsReplace("S-1", Ut.f("로그아웃 되었습니다."), "/");

	}

}
