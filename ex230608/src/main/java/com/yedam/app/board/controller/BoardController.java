package com.yedam.app.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {

	@Autowired
	BoardService boardservice;
	
	// 전체조회 : URI - boardList, RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
//		List<BoardVO> list = boardservice.getBoardList();
//		model.addAttribute("boardList", list);
		model.addAttribute("boardList", boardservice.getBoardList());
		return "board/boardList";
	}

	
	// 단건조회 : URI - boardInfo, RETURN - board/boardInfo
	@GetMapping("boardInfo")
	public String boardInfo(Model model, BoardVO vo) {
		model.addAttribute("board", boardservice.getBoardInfo(vo));
		return "board/boardInfo";
	}
	
	// 등록 - 페이지 : URI - boardInsert, RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		return "board/boardInsert";
	}

	
	// 등록 - 처리 : URI - boardInsert, RETURN - 전체조회 다시 호출
	@PostMapping("boardInsert")
	public String boardInsert(BoardVO vo, Model model) {
		boardservice.insertBoardInfo(vo);
		return "redirect:boardList";
	}

	
	// 수정 - 페이지 : URI - boardUpdate, RETURN - board/boardUpdate
	@GetMapping("boardUpdate")
	public String boardUpdateForm(BoardVO vo, Model model) {
		model.addAttribute("board", boardservice.getBoardInfo(vo));
		return "board/boardUpdate";
	}

	// 수정 - 처리 : URI - boardUpdate, RETURN - 성공여부만 반환
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdate(BoardVO vo) {
		boolean result = false;
		int boardNo = boardservice.updateBoardInfo(vo);
		if(boardNo > -1) {
			result = true;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("bno", boardNo);
		
		return map;
	}

	
	// 삭제 : URI - boardDelete, RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelete(@RequestParam(required = false, defaultValue = "0", name="bno") int bno) {		
		// @RequestParam을 쓰는 순간 데이터는 무조건 들어와야한다. (default required 속성이 true 임)
		// 오류가 나지 않기 위해 required 속성을 false로 지정 가능
		// 값이 들어오지 않았을 시 defaultValue 0으로 지정 (String 같은 참조 타입이라 원래는 null 값이 들어감)
		// name 속성은 넘어오는 값과 서비스 코드에 쓰려는 변수 이름을 다르게 쓰고 싶을 때, name 속성으로 받아오는 이름 그대로 쓰고, 매개변수 이름은 다르게 지정 가능
		boardservice.deleteBoardInfo(bno);
		
		return "redirect:boardList";
	}

	
}
