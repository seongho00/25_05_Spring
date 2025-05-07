package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.vo.Article;

@Component
public class ArticleRepository {
	int id;
	public List<Article> articles;

	public ArticleRepository() {
		this.id = 0;
		this.articles = new ArrayList<>();
	}

	public int writeArticle(String title, String body) {
		articles.add(new Article(++id, title, body));

		return id;
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;

	}

	public void removeArticle(int id) {
		Article article = getArticleById(id);
		articles.remove(article);
	}

	public void modifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);
		article.setTitle(title);
		article.setBody(body);

	}

	public List<Article> getArticles() {
		return articles;
	}

}
