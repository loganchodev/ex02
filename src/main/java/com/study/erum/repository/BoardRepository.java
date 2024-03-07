package com.study.erum.repository;
// 'com.study.erum.repository' 패키지 선언

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.study.erum.dto.BoardDTO;
import lombok.RequiredArgsConstructor;

@Repository // 스프링 컨테이너가 이 클래스를 데이터 접근 계층의 컴포넌트로 관리
@RequiredArgsConstructor // Lombok이 final이나 @NonNull 필드에 대한 생성자를 자동으로 만들어줌

public class BoardRepository {

   private final SqlSessionTemplate sql; // SQL 쿼리 실행을 위한 MyBatis의 SqlSessionTemplate 객체

   public int save(BoardDTO boardDTO) {
      // 게시글 저장 메서드. BoardDTO 객체를 받아서 데이터베이스에 저장
      return sql.insert("Board.insert", boardDTO); // 'Board.insert' 매핑된 쿼리를 실행해서 게시글 저장
   }
   
   public List<BoardDTO> findAll(){
       // 게시글 전체 목록 조회 메서드
	   return sql.selectList("Board.findAll"); // 'Board.findAll' 매핑된 쿼리를 실행해서 게시글 목록 가져옴
   }
   
   public BoardDTO findById(Long id) {
       // ID를 기준으로 특정 게시글 조회 메서드
	   return sql.selectOne("Board.findById", id); // 'Board.findById' 매핑된 쿼리를 실행해서 특정 게시글 정보 가져옴
   }

   public void updateHits(Long id) {
       // 게시글의 조회수를 업데이트하는 메서드
	   sql.update("Board.updateHits", id); // 'Board.updateHits' 매핑된 쿼리를 실행해서 조회수 업데이트
   }

   public void delete(Long id) {
       // ID를 기준으로 게시글 삭제 메서드
	   sql.delete("Board.delete", id); // 'Board.delete' 매핑된 쿼리를 실행해서 특정 게시글 삭제
   }

   public void update(BoardDTO boardDTO) {
       // 게시글 업데이트 메서드. BoardDTO 객체를 받아서 해당 게시글 정보를 업데이트
	   sql.update("Board.update", boardDTO); // 'Board.update' 매핑된 쿼리를 실행해서 게시글 정보 업데이트
   } 

} // 클래스 종료
