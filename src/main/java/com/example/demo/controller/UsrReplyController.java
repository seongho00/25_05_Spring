package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.ReplyService;
import com.example.demo.util.Ut;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrReplyController {

	@Autowired
	private Rq rq;

	@Autowired
	private ReplyService replyService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/reply/doWriteReply")
	@ResponseBody
	public String doWriteReply(int articleId, @RequestParam(defaultValue = "") String replyBody) {

		if (replyBody.trim().equals("")) {
			return Ut.jsHistoryBack("F-1", "내용 입력 필요");
		}
		ResultData usersReplyReactionRd = replyService.writeReply(rq.getLoginedMemberId(), articleId, replyBody);

//		return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg(), "goodRP", goodRP, "badRP", badRP);
		return Ut.jsReplace(usersReplyReactionRd.getResultCode(), usersReplyReactionRd.getMsg(),
				"/usr/article/detail?id=" + articleId);
	}

}
