<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE WRITE"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="doWriteComment" method="POST">
			<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<input type="hidden" name="id" value="${id }"/>
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