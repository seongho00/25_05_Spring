package com.example.demo.vo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.demo.util.Ut;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

// @Autowired사용을 위한 코드
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {

	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;

	private HttpServletRequest req;
	private HttpServletResponse resp;

	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		this.req.setAttribute("rq", this);
	}

	public void printHistoryBack(String msg) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		println("<script>");
		if (!Ut.isEmpty(msg)) {
			println("alert('" + msg.replace("'", "\\'") + "');");
		}
		println("history.back();");
		println("</script>");

	}

	public void historyBack() throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		println("<script>");
		println("history.back();");
		println("</script>");

	}

	private void println(String str) throws IOException {
		print(str + "\n");

	}

	private void print(String str) throws IOException {
		resp.getWriter().append(str);
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}

	public void initBeforeActionInterceptor() {
		System.err.println("initBeforeActionInterceptor 실행됨");
	}

	public void printReplace(String msg, String path) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		println("<script>");
		if (!Ut.isEmpty(msg)) {
			println("alert('" + msg + "');");
		}
		println("location.replace('" + path + "');");
		println("</script>");

	}

	public String historyBackOnView(String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "usr/common/js";
	}

	public String getCurrentUri() {
		String currentUri = req.getRequestURI();
		String queryString = req.getQueryString();
			
		if (currentUri != null && queryString != null) {
			
		}
		return currentUri + "?" + queryString;

	}

}