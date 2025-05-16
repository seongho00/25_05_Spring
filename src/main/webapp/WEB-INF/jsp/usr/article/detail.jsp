<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>
<script>
	const articleId = "${article.id}";
	const loginMemberId = "${loginedMemberId}";
</script>


<script>
	function toggleLike() {
		if (loginMemberId == "0") {
			alert("로그인 후 이용해 주세요.");
			return;
		}

		let check;
		if ($('input[id= ' + "like-box" + ']').is(':checked')) {
			check = 1;
		} else {
			check = 0;
		}

		let data = {
			"articleId" : articleId,
			"memberId" : loginMemberId,
			"check" : check
		};

		$.get('like', {
			articleId : articleId,
			memberId : loginMemberId,
			check : check,
			ajaxMode : 'Y'
		}, function(data) {

			$('.article-detail__like-good-count').html(
					data.extra__goodReactionPoint);
			$('.article-detail__like-bad-count').html(
					data.extra__badReactionPoint);
			$('.article-detail__like-sum-count').html(
					data.extra__sumReactionPoint);
		}, 'json');

	};
</script>
<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<tbody>
				<tr>
					<th style="text-align: center;">ID</th>
					<td style="text-align: center;">${article.id}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Board</th>
					<td style="text-align: center;">${article.extra__boardName}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Registration Date</th>
					<td style="text-align: center;">${article.regDate}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Update Date</th>
					<td style="text-align: center;">${article.updateDate}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Member ID</th>
					<td style="text-align: center;">${article.memberId }</td>
				</tr>
				<tr>
					<th style="text-align: center;">Title</th>
					<td style="text-align: center;">${article.title }</td>
				</tr>
				<tr>
					<th style="text-align: center;">Body</th>
					<td style="text-align: center;">${article.body }</td>
				</tr>
				<tr>
					<th style="text-align: center;">Writer</th>
					<td style="text-align: center;">${article.extra__writer }</td>
				</tr>
				<tr>
					<th style="text-align: center;">hitCount</th>
					<td style="text-align: center;">${article.hitCount }</td>
				</tr>
				<tr>
					<th style="text-align: center;">Like/Dislike</th>
					<td style="text-align: center;">
						<span class="article-detail__like-good-count">${article.extra__goodReactionPoint } </span>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">Dislike</th>
					<td style="text-align: center;">
						<span class="article-detail__like-bad-count"> ${article.extra__badReactionPoint }</span>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">Sum</th>
					<td style="text-align: center;">
						<span class="article-detail__like-sum-count">${article.extra__sumReactionPoint } </span>
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>

			</tbody>
		</table>
		<div class="btns flex">
			<c:if test="${article.userCanModify}">   
				<button type="button" onclick="location.replace('modifyPage?id=${article.id}');">수정하기</button>
			</c:if>
			<c:if test="${article.userCanDelete}">  
				<button type="button" onclick="location.replace('doDelete?id=${article.id}');">삭제하기</button>
			</c:if>
			<button type="button" onclick="history.back();">뒤로가기</button>
			<div class="flex-grow"></div>
			<button type="button" onclick="location.replace('writeCommentPage?id=${article.id}');">댓글쓰기</button>

		</div>


		<div>
			<form action="">
				<label class="heart-checkbox ">
					<input class="like-box" type="checkbox" id="like-box" name="like-box" onClick="toggleLike()" />
					<span class="heart "></span>
				</label>
			</form>
		</div>


		<c:forEach var="comment" items="${comments }">
			<div>
				<tr>
					<td style="text-align: center;">${comment.id}</td>
					<td style="text-align: center;">${comment.regDate.substring(0,10)}</td>
					<td style="text-align: center;">${comment.updateDate.substring(0,10)}</td>
					<td style="text-align: center;">${comment.body }</td>


				</tr>
			</div>
		</c:forEach>


	</div>
</section>

<%@ include file="../common/foot.jspf"%>