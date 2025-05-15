<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>


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
					<th style="text-align: center;">Views</th>
					<td style="text-align: center;">${article.views }</td>
				</tr>
				<tr>
					<th style="text-align: center;">Like</th>
					<td style="text-align: center;">${likeCount }</td>
				</tr>
				<tr>
					<td></td>
				</tr>

			</tbody>
		</table>
		<div class="btns">
			<c:if test="${article.userCanModify}">   
				<button type="button" onclick="location.replace('modifyPage?id=${article.id}');">수정하기</button>
			</c:if>
			<c:if test="${article.userCanDelete}">  
				<button type="button" onclick="location.replace('doDelete?id=${article.id}');">삭제하기</button>
			</c:if>
			<button type="button" onclick="history.back();">뒤로가기</button>
		</div>

		<script>
			const articleId = "${article.id}";
			const loginMemberId = "${loginedMemberId}";
		</script>

		<div>
			<form action="test">
				<label class="heart-checkbox ${article.userCanLike ? '' : 'heart-active' }">
					<input type="checkbox" name="like" value="${article.userCanLike ? '1' : '0'}" class="likeCheckBox" />
					<span class="heart "></span>
				</label>
			</form>
		</div>



	</div>
</section>

<%@ include file="../common/foot.jspf"%>