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
import com.study.erum.dto.PageDTO;
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
            return "redirect:/board/paging"; // 게시글 페이징 페이지로 리다이렉션
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
	// 기본 경로(/board)에 대한 GET 요청을 처리하는 메서드
	public String findById(@RequestParam("id") Long id,
	                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	                       Model model) {
	    // 'id'와 'page' 파라미터를 받아옴. 'page'는 선택적이며 기본값은 1
	    boardService.updateHits(id); // 해당 게시글의 조회수를 업데이트
	    BoardDTO boardDTO = boardService.findById(id); // 주어진 'id'로 게시글 상세 정보 조회
	    model.addAttribute("board", boardDTO); // 조회된 게시글 정보를 모델에 추가
	    model.addAttribute("page", page); // 현재 페이지 번호를 모델에 추가
	    return "detail"; // 게시글 상세 정보를 보여주는 뷰로 이동
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
    // '/paging' 경로의 GET 요청을 처리. 페이지네이션을 구현한 페이지로 이동시킴
	public String paging(Model model, 
	                     @RequestParam(value = "page", required = false, defaultValue = "1") 
	                  	 int page) {
		// 요청에서 'page' 파라미터 받음. 없으면 기본값 1 사용. 페이지 번호 나타냄
		System.out.println("page = " + page); // 현재 페이지 번호 서버 콘솔에 출력. 디버깅용
		List<BoardDTO> pagingList = boardService.pagingList(page); // 해당 페이지의 게시글 목록 조회
		System.out.println("pageList =" + pagingList); // 조회된 페이지 리스트 콘솔에 출력. 디버깅용
		PageDTO pageDTO = boardService.pagingParam(page); // 페이징 처리를 위한 파라미터 설정
		model.addAttribute("boardList", pagingList); // 조회된 게시글 목록 모델에 추가
		model.addAttribute("paging", pageDTO); // 페이지네이션 정보 모델에 추가
		return "paging"; // 페이지네이션 처리된 페이지로 이동. 실제 로직 결과 'paging' 뷰에 전달
	}
       
} // 클래스 종료
