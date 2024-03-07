package com.study.erum.repository;
// 'com.study.erum.repository' 패키지 선언

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.study.erum.dto.BoardDTO;
import lombok.RequiredArgsConstructor;

@Repository // 스프링이 이 클래스를 데이터 접근 계층의 컴포넌트로 관리
@RequiredArgsConstructor // final 필드나 @NonNull 필드에 대한 생성자를 자동으로 생성

public class BoardRepository {

    private final SqlSessionTemplate sql; // SQL 쿼리 실행을 위한 MyBatis SqlSessionTemplate 객체 주입

    public int save(BoardDTO boardDTO) {
        // 게시글 저장 기능. BoardDTO 객체를 매개변수로 받아 DB에 저장
        return sql.insert("Board.insert", boardDTO); // 'Board.insert' 쿼리 실행, 저장된 행의 수 반환
    }
    
    public List<BoardDTO> findAll(){
        // 게시글 전체 목록 조회 기능
        return sql.selectList("Board.findAll"); // 'Board.findAll' 쿼리 실행, 결과 리스트 반환
    }
    
    public BoardDTO findById(Long id) {
        // 특정 ID의 게시글 조회 기능
        return sql.selectOne("Board.findById", id); // 'Board.findById' 쿼리 실행, 단일 게시글 반환
    }

    public void updateHits(Long id) {
        // 게시글 조회수 업데이트 기능
        sql.update("Board.updateHits", id); // 'Board.updateHits' 쿼리 실행, 조회수 증가
    }

    public void delete(Long id) {
        // 게시글 삭제 기능
        sql.delete("Board.delete", id); // 'Board.delete' 쿼리 실행, 특정 게시글 삭제
    }

    public void update(BoardDTO boardDTO) {
        // 게시글 정보 업데이트 기능
        sql.update("Board.update", boardDTO); // 'Board.update' 쿼리 실행, 게시글 정보 업데이트
    }

    public List<BoardDTO> pagingList(Map<String, Integer> pagingParams) {
        // 페이지에 따른 게시글 목록 조회 기능
        return sql.selectList("Board.pagingList", pagingParams); // 'Board.pagingList' 쿼리 실행, 페이징 처리된 게시글 목록 반환
    } 
    
    public int boardCount() {
    	return sql.selectOne("Board.boardCount");
    }

} // 클래스 종료
