package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {

	// new ArticleService를 대체
	@Autowired
	ArticleService articleservice;

	// 액션 메서드 (컨트롤러 메서드)
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(String title, String body) {

		int id = articleservice.writeArticle(body, title);

		return id + "번 게시글 생성됨";
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		return articleservice.articles;
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id) {

		Article article = articleservice.getArticleById(id);

		if (article == null) {
			return id + "번 게시글은 없습니다.";
		}

		return article;

	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = articleservice.getArticleById(id);

		if (article == null) {
			return id + "번 게시글은 없습니다.";
		}

		articleservice.removeArticle(article);

		return id + "번 게시글이 삭제되었습니다.";

	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		Article article = articleservice.getArticleById(id);

		if (article == null) {
			return id + "번 게시글은 없습니다.";
		}

		articleservice.modifyArticle(article, title, body);

		return id + "번 게시글이 수정되었습니다. " + article;

	}

}
