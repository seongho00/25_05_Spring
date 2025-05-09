package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {

	// new ArticleService를 대체
	@Autowired
	ArticleService articleService;

	// 액션 메서드 (컨트롤러 메서드)
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpServletRequest request, String title, String body) {
		HttpSession session = request.getSession();
		if (Ut.isEmptyOrNull(title)) {
			return ResultData.from("F-1", "제목을 입력하세요");
		}

		if (Ut.isEmptyOrNull(body)) {
			return ResultData.from("F-2", "내용을 입력하세요");
		}

		if (session.getAttribute("loginedMember") == null) {
			return ResultData.from("F-3", "로그인 후 이용해주세요");
		}

		int memberId = (int) session.getAttribute("loginedMemberId");
		int id = articleService.writeArticle(body, title, memberId);
		Article article = articleService.getArticleById(id);
		return ResultData.from("S-1", Ut.f("%d번 게시글입니다", id), article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {

		return ResultData.from("S-1", Ut.f("게시글입니다"), articleService.getArticles());
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글입니다", id), article);

	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Article> doDelete(HttpServletRequest request, int id) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginedMember") == null) {
			return ResultData.from("F-2", "로그인 후 이용해주세요");
		}
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}

		if (article.getMemberId() != (int) session.getAttribute("loginedMemberId")) {
			return ResultData.from("F-3", Ut.f("권한이 없습니다."));
		}
		articleService.deleteArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 게시글이 삭제되었습니다.", id), article);

	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpServletRequest request, int id, String title, String body) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginedMember") == null) {
			return ResultData.from("F-2", "로그인 후 이용해주세요");
		}
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}

		if (article.getMemberId() != (int) session.getAttribute("loginedMemberId")) {
			return ResultData.from("F-3", Ut.f("권한이 없습니다."));
		}

		articleService.modifyArticle(id, title, body);
		Article modifyArticle = articleService.getArticleById(id);

		return ResultData.from("S-1", Ut.f("%d번 게시글이 수정되었습니다.", id), modifyArticle);

	}

}
