package com.lgdx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgdx.entity.Board;
import com.lgdx.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/boardList.do")
	public String boadList(Model model) {
		List<Board> list = service.boardList();
		System.out.println(list.toString());
		model.addAttribute("list", list);
		return "boardList";
	}

	// 게시글 상세보기 기능
	@GetMapping("/boardContents.do")
	public String boardContents(@RequestParam("idx") Long idx, Model model) {
		System.out.println("게시글 상세보기 기능");
		service.boardCount(idx);
		Board vo = service.boardContents(idx);
		model.addAttribute("vo", vo);
		model.addAttribute("ln", "\n");
		return "boardContents";
	}
	
	// 게시글 작성 페이지 이동
	@GetMapping("/boardInsert.do")
	public String boardInsert() {
		System.out.println("게시글 작성 페이지 이동");
		return "boardInsert";
	}
	
	// 게시글 작성 기능
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) {
		System.out.println("게시글 작성 기능");
		service.boardInsert(vo);
		return "redirect:/boardList.do";
	}
	
	// 게시글 삭제기능
	@GetMapping("/boardDelete.do")
	public String boardDelete(@RequestParam("idx") Long idx) {
		System.out.println("게시글 삭제 기능");
		service.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	
	// 게시글 수정 페이지로 이동기능
	@GetMapping("/boardUpdate.do")
	public String boardUpdate(@RequestParam("idx") Long idx, Model model) {
		System.out.println("게시글 수정페이지 이동"); 
		Board vo = service.boardContents(idx);
		model.addAttribute("vo",vo);
		return "boardUpdate";
	}
	// 게시글 수정 기능
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		System.out.println("게시글 수정 기능");
		System.out.println(vo.toString());
		service.boardUpdate(vo);
		return "redirect:/boardContents.do?idx=" + vo.getIdx();
	}
}
