package com.example.HRmarster.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HRmarster.model.MemberDTO;
import com.example.HRmarster.model.MemberMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	private MemberMapper mapper;
	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@GetMapping("/main")
	public String main(HttpSession session) {
		if((int)session.getAttribute("sessionKey") == 0) {
			return "/admin/main";
		}else {
			return "/member/main";
		}
	}

	
	@PostMapping("login.go")
	public void login(@RequestParam("user_id") String user_id, @RequestParam("user_pwd") String user_pwd, HttpServletResponse response, HttpSession session) throws IOException {
		int loginCheck = 0;
		
		MemberDTO getLogin = this.mapper.doLogin(user_id);
		
		if(getLogin != null) {
			if(getLogin.getMember_pwd().equals(user_pwd)) {
				loginCheck = 1;
				session.setAttribute("sessionName", getLogin.getMember_name());
				session.setAttribute("sessionKey", getLogin.getMember_key());
				session.setMaxInactiveInterval(1800);
			}else {
				loginCheck = -1;
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(loginCheck > 0) {
				out.print("<script> alert('"+session.getAttribute("sessionName")+"님 환영합니다'); location.href='main'; </script>");
		}else if(loginCheck < 0){
			out.print("<script> alert('비밀번호를 확인해주세요.'); history.back(); </script>");
		}else {
			out.print("<script> alert('없는 아이디 입니다.'); history.back(); </script>");
		}
		
	}
}
