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
					</tr>
				</c:forEach>


			</tbody>



		</table>

		<div style="text-align: center;">
			<a href="../article/list?boardId=0&page=${page - 1}"><</a>
			<c:forEach begin="${(viewPage - 1) * 10 +1}" end="${(viewPage) * 10}" varStatus="status">
				<a style="padding: 5px;" href="../article/list?boardId=0&page=${status.count }">${status.index}</a>
			</c:forEach>
			<a href="../article/list?boardId=0&page=${page + 1}"">></a>
		</div>

		<form action="list">
			<input type="hidden" value="0" name="page"/>
			<select class="select select-primary" name="boardId">
				<option value="1" disabled selected>게시판 선택</option>
				<option value="0">전체</option>
				<option value="1">공지사항</option>
				<option value="2">자유</option>
				<option value="3">QnA</option>
			</select>

			<input type="text" name="keyword"/>
			<button>검색하기</button>
		</form>
	</div>
</section>


<%@ include file="../common/foot.jspf"%>
