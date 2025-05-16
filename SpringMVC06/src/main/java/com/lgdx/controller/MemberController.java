package com.lgdx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lgdx.entity.Member;
import com.lgdx.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// 로그인 페이지 이동
	@GetMapping("/login.do")
	public String login() {
		System.out.println("로그인 페이지 이동");
		return "login";
	}
	// 로그인 기능
	@PostMapping("/login.do")
	public String login(Member vo, HttpSession session) {
		Member info =  service.login(vo);
		System.out.println(info);
		
		if(info != null) {
			session.setAttribute("info", info);
		}
		return "redirect:/boardList.do";
	}
	
	// 회원가입 페이지 이동
	@GetMapping("/join.do")
	public String join() {
		return "join";
	}
	
	// 회원가입 기능
	@PostMapping("/join.do")
	public String join(Member vo) {
		System.out.println("회원 가입 기능");
		service.join(vo);
		return "redirect:/login.do";
	}
	
	// 로그아웃 기능
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃");
		session.invalidate();
		return "redirect:/boardList.do";
	} 
}
