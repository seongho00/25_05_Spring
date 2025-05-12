package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Service
public class ArticleService {

	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);

	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);

	}

	public int writeArticle(String title, String body, int memberId) {

		articleRepository.writeArticle(title, body, memberId);

		return articleRepository.getLastInsertId();

	}

	public List<Article> getArticles() {

		return articleRepository.getArticles();
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);

	}

	public Article getForPrintArticleById(int loginedMemberId, int id) {
		Article article = articleRepository.getArticleById(id);

		updateForPrintData(loginedMemberId, article);

		return article;

	}

	private void updateForPrintData(int loginedMemberId, Article article) {
		if (article == null) {
			return;
		}

		ResultData userCanModifyRd = userCanModify(loginedMemberId, article);
		article.setUserCanModify(userCanModifyRd.isSuccess());
		article.setUserCanDelete(userCanModifyRd.isSuccess());
	}

	public ResultData userCanModify(int loginedMemberId, Article article) {

		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A", Ut.f("%d번 게시글에 대한 권한 없음", article.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글을 수정함", article.getId()));
	}
	
	public ResultData userCanDelete(int loginedMemberId, Article article) {

		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A", Ut.f("%d번 게시글에 대한 권한 없음", article.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글을 삭제함", article.getId()));
	}

}
