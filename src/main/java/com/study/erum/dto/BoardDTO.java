package com.study.erum.dto;
// 'com.study.erum.dto' 패키지에 속한 클래스 선언

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// Lombok 라이브러리에서 제공하는 Getter, Setter, ToString 어노테이션을 임포트

import java.sql.Timestamp;
// java.sql 패키지의 Timestamp 클래스를 임포트. SQL TIMESTAMP 데이터 타입과 호환되는 시간값을 표현

@Getter
// 모든 필드에 대한 getter 메서드를 자동으로 생성하는 Lombok 어노테이션
@Setter
// 모든 필드에 대한 setter 메서드를 자동으로 생성하는 Lombok 어노테이션
@ToString
// 클래스의 toString 메서드를 자동으로 생성하는 Lombok 어노테이션. 클래스의 정보를 문자열로 쉽게 출력할 수 있게 해줌

public class BoardDTO {
    // BoardDTO 클래스 시작

    private Long id;
    // 게시글의 고유 식별자(ID). 데이터베이스의 Primary Key에 해당할 수 있음
    private String boardWriter;
    // 게시글 작성자의 이름 또는 사용자 ID
    private String boardPass;
    // 게시글에 대한 접근 또는 수정을 위한 비밀번호
    private String boardTitle;
    // 게시글의 제목
    private String boardContents;
    // 게시글의 내용
    private int boardHits;
    // 게시글의 조회수. 게시글이 조회될 때마다 증가
    private Timestamp boardCreatedTime;
    // 게시글이 생성된 시간. 데이터베이스의 TIMESTAMP 데이터 타입과 호환됨

    // 클래스 종료
}
