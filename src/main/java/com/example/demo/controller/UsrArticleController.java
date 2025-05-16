package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import com.example.demo.service.LikeService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.Like;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrArticleController {

	@Autowired
	private Rq rq;

	// new ArticleService를 대체
	@Autowired
	ArticleService articleService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private CommentService commentService;

	// 액션 메서드 (컨트롤러 메서드)
	@RequestMapping("/usr/article/writePage")
	public String writePage() {
		return "/usr/article/write";

	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body, String boardId) {

		if (Ut.isEmptyOrNull(title)) {
			return Ut.jsHistoryBack("F-1", "제목을 입력하세요");
		}

		if (Ut.isEmptyOrNull(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력하세요");
		}
		if (Ut.isEmptyOrNull(boardId)) {
			return Ut.jsHistoryBack("F-3", "boardId을 입력하세요");
		}

		int memberId = rq.getLoginedMemberId();

		int id = articleService.writeArticle(body, title, memberId, boardId);

		return Ut.jsReplace("S-1", Ut.f("%d번 게시글이 작성되었습니다.", id), "../article/list?boardId=0&page=1");
	}

	@RequestMapping("/usr/article/writeCommentPage")
	public String writeCommentPage(Model model, int id) {

		model.addAttribute("id", id);
		return "/usr/article/writeComment";

	}

	@RequestMapping("/usr/article/doWriteComment")
	@ResponseBody
	public String doWriteComment(int id, String body) {

		if (Ut.isEmptyOrNull(body)) {
			return Ut.jsHistoryBack("F-1", "내용을 입력하세요");
		}
		int loginedMemberId = rq.getLoginedMemberId();

		commentService.writeComment(id, loginedMemberId);
		int memberId = rq.getLoginedMemberId();

		return Ut.jsReplace("S-1", Ut.f("%d번 댓글이 작성되었습니다.", id), "../article/list?boardId=0&page=1");
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model, @RequestParam(defaultValue = "0") int boardId,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "all") String keywordType) throws IOException {

		if (page <= 0) {
			page = 1;
		}

		int viewArticleCount = 10;
		int viewPageCount = 10;

		int limitFrom = (page - 1) * viewArticleCount;

		int totalCnt = articleService.getTotalArticleCount(boardId, keyword, keywordType);

		int totalPage = (int) Math.ceil(totalCnt / (double) viewArticleCount);

		int viewPage = (int) Math.ceil(page / (double) viewPageCount);

		int endPage = viewPage * 10 < totalPage ? viewPage * 10 : totalPage;

		if (page > totalPage - 1) {
			page = totalPage - 1;
		}

		Board board = boardService.getBoardById(boardId);

		List<Article> articles = articleService.getArticlesByBoardId(boardId, limitFrom, viewArticleCount, keyword,
				keywordType);

		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		model.addAttribute("viewPage", viewPage);
		model.addAttribute("endPage", endPage);

		return "/usr/article/list";

	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id, HttpServletRequest req) {

		Article article = articleService.getForPrintArticleById(rq.getLoginedMemberId(), id);
		if (rq.getLoginedMemberId() == article.getMemberId()) {
			article.setUserCanModify(true);
		}
		int likeCount = likeService.getLikeCountByArticleId(id);

		Like like = likeService.existLikeById(id, rq.getLoginedMemberId());

		if (like == null) {
			article.setUserCanLike(true);
		}

		articleService.setArticleViews(article.getViews() + 1, id);

		model.addAttribute("article", article);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		model.addAttribute("likeCount", likeCount);

		return "/usr/article/detail";
	}

	@RequestMapping("/usr/article/like")
	@ResponseBody
	public int like(Model model, HttpServletRequest req) throws IOException {

		int articleId = Integer.parseInt(req.getParameter("articleId"));
		int memberId = Integer.parseInt(req.getParameter("memberId"));

		if (req.getParameter("check").equals("1")) {
			likeService.setLike(articleId, memberId);

		}
		if (req.getParameter("check").equals("0")) {
			likeService.deleteLike(articleId, memberId);

		}
		int likeCount = likeService.getLikeCountByArticleId(articleId);

		return likeCount;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		if (rq.isLogined() == false) {
			return Ut.jsReplace("F-1", "로그인 후 이용해주세요", "../member/loginPage");
		}
		Article article = articleService.getArticleById(id);

		if (article == null) {

			return Ut.jsHistoryBack("F-2", Ut.f("%d번 게시글은 없습니다", id));
		}

		ResultData userCanDeleteRd = articleService.userCanDelete(rq.getLoginedMemberId(), article);

		if (userCanDeleteRd.isFail()) {
			return Ut.jsHistoryBack(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg());
		}
		if (userCanDeleteRd.isSuccess()) {
			articleService.deleteArticle(id);
		}

		return Ut.jsReplace(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg(), "../article/list");

	}

	@RequestMapping("/usr/article/modifyPage")
	public String showModifyPage(Model model, int id) throws IOException {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			rq.printHistoryBack(Ut.f("%d번 게시글은 없습니다", id));
		}

		model.addAttribute("article", article);
		model.addAttribute("id", id);

		return "/usr/article/modify";

	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		int loginedMemberId = rq.getLoginedMemberId();

		Article article = articleService.getArticleById(id);

		if (Ut.isEmptyOrNull(title)) {
			return Ut.jsHistoryBack("F-1", "제목 입력해");
		}
		if (Ut.isEmptyOrNull(body)) {
			return Ut.jsHistoryBack("F-1", "내용 입력해");
		}

		ResultData userCanModifyRd = articleService.userCanModify(loginedMemberId, article);
		if (userCanModifyRd.isSuccess()) {
			articleService.modifyArticle(id, title, body);
		}

		Article modifyArticle = articleService.getArticleById(id);

		return Ut.jsReplace(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg(), "../article/detail?id=" + id);

	}

}
