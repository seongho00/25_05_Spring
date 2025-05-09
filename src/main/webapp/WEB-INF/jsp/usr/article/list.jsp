<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="com.example.demo.vo.Article"%>

<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
	<h1>게시글 목록</h1>

	<table style="border-collapse: collapse; border-color: green;" border="1px">
		<thead>
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>수정날짜</th>
				<th>작성자</th>
				<th>제목</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Article article : articles) {
			%>
			<tr style="text-align: center;">
				<td><%=article.getId()%>번
				</td>
				<td><%=article.getRegDate()%></td>
				<td><%=article.getUpdateDate()%></td>
				<td><%=article.getName()%></td>
				<td>
					<a href="detail?id=<%=article.getId()%>"><%=article.getTitle()%></a>
				</td>
				<td><%=article.getBody()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

</body>
</html>