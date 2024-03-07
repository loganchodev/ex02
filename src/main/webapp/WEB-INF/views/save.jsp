<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 현재 페이지가 Java를 사용하며, 컨텐츠 타입이 'text/html'이고, 문자 인코딩이 'UTF-8'임을 선언 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 문서의 문자 인코딩을 UTF-8로 설정 -->
<title>save</title>
<!-- 브라우저 탭에 표시될 페이지 제목을 'save'로 설정 -->
</head>
<body>
    <form action="/board/save" method="post">
    <!-- 사용자 입력 데이터를 '/board/save' 경로로 POST 방식으로 전송할 폼을 정의 -->
        <input type="text" name="boardWriter" placeholder="작성자"/> <br> <br>
        <!-- 작성자 이름을 입력받는 텍스트 필드. '작성자'라는 플레이스홀더 텍스트를 포함 -->
        
        <input type="text" name="boardPass" placeholder="비밀번호"/> <br> <br>
        <!-- 비밀번호를 입력받는 텍스트 필드. '비밀번호'라는 플레이스홀더 텍스트를 포함 -->
        
        <input type="text" name="boardTitle" placeholder="제목"/> <br> <br>
        <!-- 제목을 입력받는 텍스트 필드. '제목'이라는 플레이스홀더 텍스트를 포함 -->
        
        <textarea name="boardContents" cols="23" rows="10" placeholder="내용을 입력하세요"></textarea> <br> <br>
        <!-- 내용을 입력받는 텍스트 영역. 여러 줄의 텍스트를 입력할 수 있으며, '내용을 입력하세요'라는 플레이스홀더 텍스트를 포함 -->
        
        <input type="submit" value="작성">
        <!-- 폼을 제출하는 버튼. '작성'이라는 레이블을 포함 -->
        
    </form>
</body>
</html>
