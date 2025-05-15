<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE ${board.code }"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-24 text-xl px-4">
	<div class="mx-auto">


		<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<thead>
				<tr>
					<th style="text-align: center;">ID</th>
					<th style="text-align: center;">Board</th>
					<th style="text-align: center;">Registration Date</th>
					<th style="text-align: center;">Update Date</th>
					<th style="text-align: center;">Title</th>
					<th style="text-align: center;">Writer</th>
					<th style="text-align: center;">Views</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles }">
					<tr>
						<td style="text-align: center;">${article.id}</td>
						<td style="text-align: center;">${article.extra__boardName }</td>
						<td style="text-align: center;">${article.regDate.substring(0,10)}</td>
						<td style="text-align: center;">${article.updateDate.substring(0,10)}</td>
						<td style="text-align: center;">
							<a class="hover:underline" href="detail?id=${article.id }">${article.title }</a>
						</td>
						<td style="text-align: center;">${article.extra__writer }</td>
						<td style="text-align: center;">${article.views }</td>
					</tr>
				</c:forEach>
				<c:if test="${empty articles }">
					<tr>
						<td colspan="4" style="text-align: center;">게시글이 없습니다</td>
					</tr>
				</c:if>

			</tbody>



		</table>

		<div class="flex justify-center ">
			<div class="btn-group join">
				<a class="join-item btn"
					href="../article/list?boardId=${param.boardId }&page=${page - 1}&keyword=${param.keyword}&keywordType=${param.keywordType}"><</a>
				<c:forEach begin="${(viewPage - 1) * 10 +1}" end="${endPage}" varStatus="status">
					<a class="join-item btn ${page == status.index ? 'btn-active' : '' }"
						href="../article/list?boardId=${param.boardId }&page=${status.index }&keyword=${param.keyword}&keywordType=${param.keywordType}">${status.index}</a>
				</c:forEach>
				<a class="join-item btn"
					href="../article/list?boardId=${param.boardId }&page=${page + 1}&keyword=${param.keyword}&keywordType=${param.keywordType}">></a>
			</div>

		</div>
		<div class="ml-auto">
			<form action="list">
				<input type="hidden" value="0" name="page" />
				<select class="select select-primary" name="boardId">
					<option value="0" selected>전체</option>
					<option value="1">공지사항</option>
					<option value="2">자유</option>
					<option value="3">QnA</option>
				</select>

				<select class="select select-primary" name="keywordType">
					<option value="title" selected>제목</option>
					<option value="body">내용</option>
					<option value="writer">작성자</option>
				</select>

				<input type="text" name="keyword" autocomplete="off" />
				<button>검색하기</button>
			</form>
		</div>

	</div>
</section>


<%@ include file="../common/foot.jspf"%>
