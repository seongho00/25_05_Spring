package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {

	// new ArticleService를 대체
	@Autowired
	ArticleService articleService;

	// 액션 메서드 (컨트롤러 메서드)
	@RequestMapping("/usr/article/writePage")
	public String writePage() {
		return "/usr/article/write";

	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");

		if (Ut.isEmptyOrNull(title)) {
			return Ut.jsHistoryBack("F-1", "제목을 입력하세요");
		}

		if (Ut.isEmptyOrNull(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력하세요");
		}

		int memberId = rq.getLoginedMemberId();
		int id = articleService.writeArticle(body, title, memberId);
		Article article = articleService.getArticleById(id);
		return Ut.jsReplace("S-1", Ut.f("%d번 게시글이 작성되었습니다.", id), "../article/list");
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model) {

		List<Article> articles = articleService.getArticles();

		model.addAttribute("articles", articles);

		return "/usr/article/list";

	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {

		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getForPrintArticleById(rq.getLoginedMemberId(), id);
		if (rq.getLoginedMemberId() == article.getMemberId()) {
			article.setUserCanModify(true);
		}
		model.addAttribute("article", article);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());

		return "/usr/article/detail";
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {

		Rq rq = (Rq) req.getAttribute("rq");
		if (rq.isLogined() == false) {
			return Ut.jsReplace("F-1", "로그인 후 이용해주세요", "../member/loginPage");
		}
		Article article = articleService.getArticleById(id);

		if (article == null) {
//			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없습니다", id));
		}

		ResultData userCanDeleteRd = articleService.userCanDelete(rq.getLoginedMemberId(), article);

		if (userCanDeleteRd.isFail()) {
			return Ut.jsHistoryBack(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg());
		}
		if (userCanDeleteRd.isSuccess()) {
			articleService.deleteArticle(id);
		}

//		return ResultData.from(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg());
		return Ut.jsReplace(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg(), "../article/list");

	}

	@RequestMapping("/usr/article/modifyPage")
	public String showModifyPage(HttpServletRequest req, Model model, int id) {

		Article article = articleService.getArticleById(id);
		// 게시글 존재
		// 권한체크

		model.addAttribute("id", id);

		return "/usr/article/modify";

	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");

		int loginedMemberId = rq.getLoginedMemberId();

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}

		ResultData userCanModifyRd = articleService.userCanModify(loginedMemberId, article);
		if (userCanModifyRd.isSuccess()) {
			articleService.modifyArticle(id, title, body);
		}

		Article modifyArticle = articleService.getArticleById(id);

		return Ut.jsReplace(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg(), "../article/list");

	}

}
