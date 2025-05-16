package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public int writeArticle(String title, String body, int memberId, String boardId);

	public Article getArticleById(int id);

	public void deleteArticle(int id);

	public void modifyArticle(int id, String title, String body);

	public List<Article> getArticles(int limitFrom, int viewArticleCount);

	public int getLastInsertId();

	public List<Article> getArticlesByBoardId(int boardId, int limitFrom, int viewArticleCount, String keyword,
			String keywordType);

	public int getTotalArticleCount(int boardId, String keyword, String keywordType);

	public void setArticleHitCount(int hitCount, int id);

	public Article getForPrintArticle(int id);

}
