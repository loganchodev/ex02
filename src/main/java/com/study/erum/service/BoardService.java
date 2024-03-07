package com.study.erum.service;
// 서비스 계층의 위치를 나타내는 패키지 선언

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.study.erum.dto.BoardDTO;
import com.study.erum.dto.PageDTO;
import com.study.erum.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service // 스프링 빈으로 등록되며, 서비스 계층을 나타냄
@RequiredArgsConstructor // final이나 @NonNull 필드에 대한 생성자를 Lombok이 자동 생성

public class BoardService {

    private final BoardRepository boardRepository; // BoardRepository에 대한 의존성 주입

    public int save(BoardDTO boardDTO) {
        // 게시글 저장 기능. BoardDTO를 받아 저장 후 결과를 반환
        return boardRepository.save(boardDTO);
    }
    
    public List<BoardDTO> findAll() {
        // 모든 게시글 조회 기능. 저장된 게시글 리스트 반환
        return boardRepository.findAll();
    }
    
    public BoardDTO findById(Long id) {
        // 특정 ID로 게시글 조회 기능. 해당 ID의 게시글 정보 반환
        return boardRepository.findById(id);
    }

    public void updateHits(Long id) {
        // 게시글 조회수 업데이트 기능. 주어진 ID의 게시글 조회수 증가
        boardRepository.updateHits(id);
    }

    public void delete(Long id) {
        // 게시글 삭제 기능. 주어진 ID의 게시글을 삭제
        boardRepository.delete(id);
    }

    public void update(BoardDTO boardDTO) {
        // 게시글 업데이트 기능. 주어진 BoardDTO 정보로 게시글 정보 업데이트
        boardRepository.update(boardDTO);
    }

    int pageLimit = 3; // 한 페이지에 표시될 게시글 수
    int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수
    
    public List<BoardDTO> pagingList(int page) {
        // 페이징 처리된 게시글 목록 조회 기능
    	int pagingStart = (page - 1) * pageLimit; // 현재 페이지에서 시작하는 게시글의 인덱스
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart); // 시작 인덱스
        pagingParams.put("limit", pageLimit); // 페이지당 게시글 수
        List<BoardDTO> pagingList = boardRepository.pagingList(pagingParams); // 페이징 처리하여 게시글 목록 조회
    
        return pagingList;
    } 
    
    public PageDTO pagingParam(int page) {
        // 페이징 처리에 필요한 파라미터 설정 기능
    	// 전체 글 갯수 조회
        int boardCount = boardRepository.boardCount();
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit -1; // 표시될 마지막 페이지 번호 계산
        
        if(endPage > maxPage) {
            endPage = maxPage; // 마지막 페이지 번호가 최대 페이지 수를 초과하지 않도록 조정
        }
        
        PageDTO pageDTO = new PageDTO(); // 페이지 관련 정보 담을 객체 생성
        
        pageDTO.setPage(page); // 현재 페이지
        pageDTO.setMaxPage(maxPage); // 최대 페이지 수
        pageDTO.setStartPage(startPage); // 시작 페이지
        pageDTO.setEndPage(endPage); // 끝 페이지
        
        return pageDTO; // 페이지 관련 정보를 담은 객체 반환
    }

} // 클래스 종료
