package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.vo.Article;

@Service
public class ArticleService {

	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		CreateTestData();
	}

	private void CreateTestData() {
		for (int i = 1; i <= 10; i++) {
			articleRepository.writeArticle("제목" + i, "내용" + i);
		}
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);

	}

	public void removeArticle(int id) {
		articleRepository.removeArticle(id);

	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);

	}

	public int writeArticle(String title, String body) {

		return articleRepository.writeArticle(title, body);

	}

	public List<Article> getArticles() {

		return articleRepository.getArticles();
	}

}
