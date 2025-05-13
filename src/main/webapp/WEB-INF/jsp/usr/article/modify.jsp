<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE MODIFY"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">

	<div class="mx-auto">
		<form action="doModify" method="POST">
			<input type="hidden" name="id" value="${article.id}" />
			<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th style="text-align: center;">ID</th>
						<td style="text-align: center;">${article.id}</td>
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
						<td style="text-align: center;">
							<input type="text" name="title" value="${article.title }" autocomplete="off" />
						</td>

					</tr>
					<tr>
						<th style="text-align: center;">Body</th>

						<td style="text-align: center;">
							<input type="text" name="body" value="${article.body }" autocomplete="off" />
						</td>


					</tr>
					<tr>
						<th style="text-align: center;">Writer</th>
						<td style="text-align: center;">${article.extra__writer }</td>
					</tr>
					<th></th>
					<td style="text-align: center;">
						<input type="submit" value="수정" />
					</td>
				</tbody>


			</table>
		</form>
	</div>


</section>

<%@ include file="../common/foot.jspf"%>