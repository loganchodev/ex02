<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- JSP 페이지 기본 설정: 응답 컨텐츠 타입과 문자 인코딩, 사용 언어 지정 -->

<html>
<head>
    <title>detail.jsp</title>
    <!-- 페이지 타이틀 설정 -->
</head>
<body>
<table>
    <!-- 게시글 상세 정보를 표시하는 테이블 -->
    <tr>
        <th>id</th>
        <!-- 'id' 제목의 테이블 헤더 -->
        <td>${board.id}</td>
        <!-- 게시글의 ID를 표시하는 테이블 데이터 -->
    </tr>
    <tr>
        <th>writer</th>
        <!-- '작성자' 제목의 테이블 헤더 -->
        <td>${board.boardWriter}</td>
        <!-- 게시글의 작성자를 표시하는 테이블 데이터 -->
    </tr>
    <tr>
        <th>date</th>
        <!-- '작성일' 제목의 테이블 헤더 -->
        <td>${board.boardCreatedTime}</td>
        <!-- 게시글의 작성일을 표시하는 테이블 데이터 -->
    </tr>
    <tr>
        <th>hits</th>
        <!-- '조회수' 제목의 테이블 헤더 -->
        <td>${board.boardHits}</td>
        <!-- 게시글의 조회수를 표시하는 테이블 데이터 -->
    </tr>
    <tr>
        <th>title</th>
        <!-- '제목' 제목의 테이블 헤더 -->
        <td>${board.boardTitle}</td>
        <!-- 게시글의 제목을 표시하는 테이블 데이터 -->
    </tr>
    <tr>
        <th>contents</th>
        <!-- '내용' 제목의 테이블 헤더 -->
        <td>${board.boardContents}</td>
        <!-- 게시글의 내용을 표시하는 테이블 데이터 -->
    </tr>
</table>
<button onclick="listFn()">목록</button>
<!-- '목록' 버튼, 클릭 시 listFn() 함수 실행 -->
<button onclick="updateFn()">수정</button>
<!-- '수정' 버튼, 클릭 시 updateFn() 함수 실행 -->
<button onclick="deleteFn()">삭제</button>
<!-- '삭제' 버튼, 클릭 시 deleteFn() 함수 실행 -->
</body>
<script>
    // 페이지 내 스크립트 정의
    const listFn = () => {
        // 목록 버튼 클릭 시 실행되는 함수, 게시판 목록 페이지로 이동
        location.href = "/board/";
    }
    const updateFn = () => {
        // 수정 버튼 클릭 시 실행되는 함수, 현재 게시글의 수정 페이지로 이동
        const id = '${board.id}';
        location.href = "/board/update?id=" + id;
    }
    const deleteFn = () => {
        // 삭제 버튼 클릭 시 실행되는 함수, 현재 게시글의 삭제 요청을 보냄
        const id = '${board.id}';
        location.href = "/board/delete?id=" + id;
    }

</script>
</html>
