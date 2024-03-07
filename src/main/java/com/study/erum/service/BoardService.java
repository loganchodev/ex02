package com.study.erum.service;
// 'com.study.erum.service' 패키지 선언. 서비스 계층이 위치하는 곳

import java.util.List;
import org.springframework.stereotype.Service;
import com.study.erum.dto.BoardDTO;
import com.study.erum.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service // 스프링에 이 클래스가 서비스 계층임을 알리고, 스프링이 이 클래스의 빈을 관리하게 함
@RequiredArgsConstructor // Lombok이 final이나 @NonNull 필드에 대한 생성자를 자동으로 만들어줌. 의존성 주입 용이

public class BoardService {

   private final BoardRepository boardRepository; // 데이터 접근 계층과의 통신을 위해 BoardRepository 주입

   public int save(BoardDTO boardDTO) {
      // 게시글 저장 기능. BoardDTO 객체를 받아서 DB에 저장하고, 저장 결과를 반환
      return boardRepository.save(boardDTO); // BoardRepository의 save 메서드를 호출하여 게시글 저장
   }
   
   public List<BoardDTO> findAll() {
       // 모든 게시글 조회 기능. DB에서 게시글 리스트를 조회하고 반환
       return boardRepository.findAll(); // BoardRepository의 findAll 메서드를 호출하여 게시글 리스트 조회
   }
   
   public BoardDTO findById(Long id) {
       // ID를 기준으로 특정 게시글 조회 기능. 해당 ID의 게시글 정보를 조회하고 반환
       return boardRepository.findById(id); // BoardRepository의 findById 메서드를 호출하여 특정 게시글 조회
   }

   public void updateHits(Long id) {
       // 게시글의 조회수를 업데이트하는 기능. 특정 ID의 게시글 조회수 증가
	   boardRepository.updateHits(id); // BoardRepository의 updateHits 메서드를 호출하여 조회수 업데이트
   }

   public void delete(Long id) {
       // ID를 기준으로 특정 게시글 삭제 기능. 해당 ID의 게시글을 DB에서 삭제
	   boardRepository.delete(id); // BoardRepository의 delete 메서드를 호출하여 특정 게시글 삭제
   }

   public void update(BoardDTO boardDTO) {
       // 게시글 업데이트 기능. BoardDTO 객체를 받아서 해당 내용으로 게시글 정보 업데이트
	   boardRepository.update(boardDTO); // BoardRepository의 update 메서드를 호출하여 게시글 정보 업데이트
   }

} // 클래스 종료
