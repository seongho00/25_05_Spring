<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<tbody>
				<form action="doWrite">
					<input type="hidden" name="id" value="${id }" />
					<label for="">
						제목 :
						<input type="text" name="title" />
					</label>
					<label for="">
						내용 :
						<input type="text" name="body" />
					</label>

					<button type="submit">글쓰기</button>
				</form>
			</tbody>
		</table>

	</div>

	</div>
</section>

<%@ include file="../common/foot.jspf"%>