package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public int writeArticle(int memberId, String title, String body, String boardId);

	public void deleteArticle(int id);

	public void modifyArticle(int id, String title, String body);

	public int getLastInsertId();

	public Article getArticleById(int id);

	public List<Article> getArticles();

	public Article getForPrintArticle(int loginedMemberId);

	public List<Article> getForPrintArticles(int boardId, int limitFrom, int limitTake, String searchKeywordTypeCode,
			String searchKeyword);

	public int getArticleCount(int boardId, String searchKeywordTypeCode, String searchKeyword);

	public int increaseHitCount(int id);

	public int getArticleHitCount(int id);

	public int increaseGoodReactionPoint(String relTypeCode, int relId);

	public int decreaseGoodReactionPoint(String relTypeCode, int relId);

	public int increaseBadReactionPoint(String relTypeCode, int relId);

	public int decreaseBadReactionPoint(String relTypeCode, int relId);

	public int getGoodRP(int relId);

	public int getBadRP(int relId);

	public int getReplyGoodRP(int relId);

	public int getReplyBadRP(int relId);

}
