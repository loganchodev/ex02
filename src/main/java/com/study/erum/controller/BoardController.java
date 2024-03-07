package com.study.erum.controller;
// 'com.study.erum.controller' 패키지에 속한 클래스 선언

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.study.erum.dto.BoardDTO;
import com.study.erum.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller // 이 클래스가 컨트롤러로서 클라이언트의 요청을 처리, 스프링 MVC에서 관리
@RequestMapping("/board") // 이 컨트롤러 내의 모든 메서드의 요청 URL이 '/board'로 시작
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성, 의존성 주입을 위해 사용

public class BoardController {

    private final BoardService boardService; // BoardService에 대한 의존성 주입, 서비스 계층과의 통신을 위해 사용

    @GetMapping("/save")
    // '/board/save' URL에 대한 GET 요청을 처리, 게시글 작성 폼 페이지로 이동
    public String saveForm() {
        return "save"; // 게시글 작성 폼을 제공하는 view 이름 반환
    }

    @PostMapping("/save")
    // '/board/save' URL에 대한 POST 요청을 처리, 게시글 데이터를 받아 저장
    public String save(@ModelAttribute BoardDTO boardDTO) {
        // 폼 데이터를 BoardDTO 객체로 바인딩
        int saveResult = boardService.save(boardDTO); // 게시글 저장 처리 후 결과 저장

        if (saveResult > 0) {
            // 저장 성공 시
            return "redirect:/board/"; // 게시글 목록 페이지로 리다이렉션
        } else {
            // 저장 실패 시
            return "save"; // 게시글 작성 폼 페이지로 이동
        }
    }
    
    @GetMapping("/")
    // 게시글 목록 페이지 요청 처리
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list"; // 게시글 목록을 보여주는 view 반환
    }
    
    @GetMapping
    // 게시글 상세 조회 요청 처리
    public String findById(@RequestParam("id") Long id, Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "detail"; // 게시글 상세 정보를 보여주는 view 반환
    }
    
    @GetMapping("/delete")
    // '/delete' 경로에 대한 GET 요청을 처리하는 메서드를 정의합니다. 이 경로는 게시글 삭제 요청을 처리.
    public String delete(@RequestParam("id") Long id) {
        // 클라이언트로부터 'id' 파라미터를 받아 Long 타입의 변수 id에 저장.
        boardService.delete(id);
        // boardService의 delete 메서드를 호출하여, 받아온 id에 해당하는 게시글을 삭제.
        return "redirect:/board/";
        // 삭제 작업 후에는 사용자를 게시글 목록 페이지('/board/')로 리다이렉트.
    }
    
    @GetMapping("/update")
    // '/update' 경로에 대한 GET 요청을 처리하는 메서드를 정의합니다. 이 경로는 게시글 수정 폼 페이지를 요청할 때 사용.
    public String updateForm(@RequestParam("id") Long id, Model model) {
        // 클라이언트로부터 'id' 파라미터를 받아 Long 타입의 변수 id에 저장.
        BoardDTO boardDTO = boardService.findById(id);
        // boardService의 findById 메서드를 호출하여, 받아온 id에 해당하는 게시글의 데이터를 조회.
        model.addAttribute("board", boardDTO);
        // 조회한 게시글 데이터(boardDTO)를 "board"라는 이름으로 Model 객체에 추가.
        // 이 데이터는 뷰에서 사용될 수 있음.
        return "update";
        // "update"라는 이름의 뷰를 반환. 이는 게시글 수정을 위한 폼 페이지를 렌더링하는 뷰의 이름.       
    }
    @PostMapping("/update")
    // '/update' 경로로 POST 요청이 들어오면 실행되는 메서드임
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
	    // 클라이언트에서 보낸 폼 데이터를 BoardDTO 객체로 바인딩해서 받아옴
	    boardService.update(boardDTO);
	    // 받아온 BoardDTO 객체를 이용해 게시글 업데이트 처리함
	    BoardDTO dto = boardService.findById(boardDTO.getId());
	    // 업데이트된 게시글 다시 조회해서 dto에 저장함
	    model.addAttribute("board", dto);
	    // 모델에 "board"라는 이름으로 업데이트된 게시글 정보를 추가함
	    return "detail";
	    // 업데이트된 게시글의 상세 정보 페이지를 보여주는 뷰 이름을 반환함
	    // return "redirect:/board?id="+boardDTO.getId(); // 이 코드는 조회수 증가를 위해 리다이렉트하는 방법을 보여줌
	}
    
    // /board/paging?page=2
    // 처음 페이지 요청은 1페이지를 보여줌
    @GetMapping("/paging")
	// '/paging' 경로에 대한 GET 요청을 처리하는 메서드임. 페이지네이션을 위한 경로 설정
	public String paging(Model model, 
	                     @RequestParam(value = "page", required = false, defaultValue = "1") 
	                     int page) {
	// 요청에서 'page' 파라미터를 받아옴. 파라미터가 없으면 기본값으로 1을 사용
	// 'page' 파라미터는 조회하고자 하는 페이지 번호를 나타냄
	     System.out.println("page = " + page);
	     // 서버 콘솔에 현재 페이지 번호 출력. 디버깅 용도로 사용됨
	     // 해당 페이지에서 보여줄 글 목록
	     List<BoardDTO> pagingList = boardService.pageList(page);
	     System.out.println("pageList =" + pagingList);
	     return "index";
	     // 'index' 뷰를 반환. 실제 페이지네이션 로직은 여기서 구현하고, 그 결과를 'index' 페이지에 전달할 예정
	}
}
