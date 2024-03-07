package com.study.erum.service;
// 'com.study.erum.service' 패키지 선언, 서비스 계층 위치

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.study.erum.dto.BoardDTO;
import com.study.erum.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service // 스프링 서비스 계층으로 등록, 스프링 빈으로 관리
@RequiredArgsConstructor // Lombok, final이나 @NonNull 필드에 대한 생성자 자동 생성

public class BoardService {

    private final BoardRepository boardRepository; // BoardRepository 의존성 주입

    public int save(BoardDTO boardDTO) {
        // 게시글 저장 기능
        return boardRepository.save(boardDTO); // 게시글 저장하고 결과 반환
    }
    
    public List<BoardDTO> findAll() {
        // 모든 게시글 조회 기능
        return boardRepository.findAll(); // 모든 게시글 조회 후 반환
    }
    
    public BoardDTO findById(Long id) {
        // ID로 게시글 조회 기능
        return boardRepository.findById(id); // 해당 ID 게시글 조회 후 반환
    }

    public void updateHits(Long id) {
        // 게시글 조회수 업데이트 기능
        boardRepository.updateHits(id); // 조회수 1 증가
    }

    public void delete(Long id) {
        // 게시글 삭제 기능
        boardRepository.delete(id); // 해당 ID 게시글 삭제
    }

    public void update(BoardDTO boardDTO) {
        // 게시글 정보 업데이트 기능
        boardRepository.update(boardDTO); // 게시글 업데이트
    }

    public List<BoardDTO> pageList(int page) {
        // 페이징 처리된 게시글 목록 조회 기능
        int pageLimit = 3; // 페이지당 게시글 수
        int pagingStart = (page - 1) * pageLimit; // 페이징 시작 위치 계산
        Map<String, Integer> pagingParams = new HashMap<>(); // 페이징 파라미터 저장 Map
        pagingParams.put("start", pagingStart); // 시작 위치
        pagingParams.put("limit", pageLimit); // 한 페이지당 게시글 수
        return boardRepository.pagingList(pagingParams); // 페이징 처리된 게시글 목록 조회
    } 

} // 클래스 종료
