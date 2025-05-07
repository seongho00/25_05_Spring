package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.vo.Article;

@Service
public class ArticleService {
	int id;
	public List<Article> articles;

	public ArticleService() {

		this.id = 0;
		this.articles = new ArrayList<>();
		CreateTestData();

	}

	private void CreateTestData() {
		for (int i = 1; i <= 10; i++) {
			writeArticle("제목" + i, "내용" + i);
		}
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void removeArticle(Article article) {

		articles.remove(article);

	}

	public void modifyArticle(Article article, String title, String body) {

		article.setTitle(title);
		article.setBody(body);

	}

	public int writeArticle(String title, String body) {
		articles.add(new Article(++id, title, body));
		return id;
	}

}
