<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE WRITE"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="doWrite" method="POST">
			<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th style="text-align: center;">게시판 입력</th>
						<td style="text-align: center;">
							<select class="select select-primary" name="boardId">
								<option value="" disabled selected>Pick a text editor</option>
								<option value="1">공지사항</option>
								<option value="2">자유</option>
								<option value="3">QnA</option>
							</select>
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">제목</th>
						<td style="text-align: center;">
							<input class="input input-primary input-sm" name="title" type="text" autocomplete="off" placeholder="제목" />
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">내용</th>
						<td style="text-align: center;">
							<input class="input input-primary input-sm" name="body" type="text" autocomplete="off" placeholder="내용" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td style="text-align: center;">
							<button class="btn btn-primary">작성</button>
						</td>
					</tr>
				</tbody>
			</table>

		</form>
	</div>


</section>

<%@ include file="../common/foot.jspf"%>