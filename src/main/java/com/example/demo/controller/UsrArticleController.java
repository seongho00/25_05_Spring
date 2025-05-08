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

@Controller
public class UsrArticleController {

	// new ArticleService를 대체
	@Autowired
	ArticleService articleService;

	// 액션 메서드 (컨트롤러 메서드)
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doAdd(String title, String body) {

		int id = articleService.writeArticle(body, title);
		Article article = articleService.getArticleById(id);
		return ResultData.from("S-4", Ut.f("%d번 게시글입니다", id), article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData getArticles() {

		return ResultData.from("S-5", Ut.f("게시글입니다"), articleService.getArticles());
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글입니다", id), article);

	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-2", Ut.f("%d번 게시글은 없습니다", id));
		}

		articleService.deleteArticle(id);

		return ResultData.from("S-2", Ut.f("%d번 게시글이 삭제되었습니다.", id), article);

	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-3", Ut.f("%d번 게시글은 없습니다", id));
		}

		articleService.modifyArticle(id, title, body);

		return ResultData.from("S-3", Ut.f("%d번 게시글이 수정되었습니다.", id), article);

	}

}
