package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {

	int id;
	List<Article> articles;

	public UsrArticleController() {
		this.id = 0;
		this.articles = new ArrayList<>();
		CreateTestData();

	}

	// 서비스 메서드
	private void CreateTestData() {
		for (int i = 1; i <= 10; i++) {
			doAdd("제목" + i, "내용" + i);
		}
	}

	// 액션 메서드 (컨트롤러 메서드)
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(String title, String body) {

		articles.add(new Article(++id, title, body));

		return id + "번 게시글 생성됨";
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		return articles;
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id) {

		for (Article article : articles) {
			if (article.getId() == id) {

				return article;
			}
		}
		return id + "번 게시글은 없습니다.";
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return id + "번 게시글이 삭제되었습니다.";
			}
		}

		return id + "번 게시글은 없습니다.";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		for (Article article : articles) {
			if (article.getId() == id) {
				article.setTitle(title);
				article.setBody(body);
				return id + "번 게시글이 수정되었습니다. " + article;
			}
		}
		return id + "번 게시글은 없습니다.";
	}

}
