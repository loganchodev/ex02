<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save</title>
<link rel="stylesheet" href="<c:url value='/resources/css/index.css'/>" />
</head>
<body>
	<div id="wrap">
		<h2>Hello Springframework!</h2>
		<div id="container">
			<button><a href="<c:url value='/board/save'/>">글작성</a></button>
			<button><a href="<c:url value='/board/'/>">글목록</a></button>
			<button><a href="<c:url value='/board/paging'/>">페이징 목록</a></button>
		</div>
	</div>
</body>
</html>
