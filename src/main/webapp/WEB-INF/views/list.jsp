<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 페이지의 콘텐츠 타입과 문자 인코딩 설정, 사용 언어를 Java로 설정 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSTL 코어 태그 라이브러리를 페이지에 포함시키기 위한 태그립 선언 -->

<html>
<head>
    <title>list</title>
    <!-- 페이지 타이틀을 'list'로 설정 -->
    <link rel="stylesheet" href="<c:url value='/resources/css/list.css'/>" />
</head>
<body>
<div id="wrap">
	<table>
	    <!-- 게시글 목록을 표시하기 위한 테이블 생성 -->
	    <tr>
	        <th>id</th>
	        <th>title</th>
	        <th>writer</th>
	        <th>date</th>
	        <th>hits</th>
	        <!-- 테이블의 헤더: 각각 게시글의 ID, 제목, 작성자, 작성일, 조회수를 나타냄 -->
	    </tr>
	    <c:forEach items="${boardList}" var="board">
	        <!-- boardList 객체에 담긴 게시글 목록을 순회하기 위한 JSTL forEach 태그. 각 게시글을 'board' 변수에 할당 -->
	        <tr>
	            <td>${board.id}</td>
	            <!-- 게시글의 ID를 표시 -->
	            <td>
	                <a href="/board?id=${board.id}">${board.boardTitle}</a>
	                <!-- 게시글 제목을 링크로 표시, 클릭 시 해당 게시글의 상세 페이지로 이동. 게시글의 ID 값을 쿼리 파라미터로 전달 -->
	            </td>
	            <td>${board.boardWriter}</td>
	            <!-- 게시글 작성자 표시 -->
	            <td>${board.boardCreatedTime}</td>
	            <!-- 게시글 작성일 표시 -->
	            <td>${board.boardHits}</td>
	            <!-- 게시글 조회수 표시 -->
	        </tr>
	    </c:forEach>
	</table>
</div>
</body>
</html>
