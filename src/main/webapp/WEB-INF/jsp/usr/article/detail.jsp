<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="com.example.demo.vo.Article"%>

<%
Article article = (Article) request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
	<h1>게시글 상세보기</h1>


	<div>번호 : <%=article.getId()%>번</div>
	<div>등록 날짜 : <%=article.getRegDate()%></div>
	<div>수정 날짜 : <%=article.getUpdateDate()%></div>
	<div>작성자 : <%=article.getName()%></div>
	<div>제목 : <%=article.getTitle()%></div>
	<div>내용 : <%=article.getBody()%></div>


	<a href="list">리스트로 가기</a>

</body>
</html>