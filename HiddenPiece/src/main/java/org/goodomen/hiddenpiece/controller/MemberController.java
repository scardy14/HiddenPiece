package org.goodomen.hiddenpiece.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.service.MemberServiceImpl;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberMapper memberMapper;
	private final MemberService memberService;

	@PostMapping("login")
	public String login(MemberVO memberVO, HttpServletRequest request) {
		memberVO = memberService.login(memberVO);
		if (memberVO == null) {
			return "member/login-fail";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("mvo", memberVO);
			return "redirect:/";
		}
	}

	@PostMapping("logout") 
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if(session!=null)
			session.invalidate();
		return "redirect:/";
	}

	
	@RequestMapping("findIdForm")
	public String findIdForm() {
		return "member/findId-form";		
	}
	@RequestMapping("findPasswordForm")
	public String findPasswordForm() {
		return "member/findPassword-form";		
	}

	@PostMapping("registerMember")
	public String register(MemberVO memberVO) {
		System.out.println(memberVO);
		memberService.registerMember(memberVO);
		return "member/register-result";
	}

	
	@PostMapping("findId")
	public String findId(String email,String name,String address,String tel) {
		String viewName = null;
				String id = memberService.findId(email, address, name, tel);
		System.out.println(id);
		if(id==null) {
			viewName = "member/findId-fail";
		}else {		
			viewName = "redirect:findIdresult?id="+id;
		}
		return viewName;
	}
	@RequestMapping("findIdresult")
	public String findIdresult(String id,Model model) {
		model.addAttribute("id", id);
		return "member/findId-result";
	}
	@PostMapping("findPassword")
	public String findPassword(String id,String email,String name,String tel) {
		String viewName = null;
		String password = memberService.findPassword(id, email, name, tel);
		if(password==null) {
			viewName = "member/findPassword-fail";
		}else {
			viewName = "redirect:findPasswordresult?password="+password;
		}
		return viewName;
	}
	@RequestMapping("findPasswordresult")
	public String findPasswordresult(String password,Model model) {
		model.addAttribute("password", password);
		return "member/findPassword-result";
	}
	
	@RequestMapping("deleteMemberForm")
	public String deleteMemberForm() {
		return "mypage/deleteMember-form";
	}
	
	@RequestMapping("deleteMember")
	public String deleteMember(HttpServletRequest request,String password) {
		String viewPath = null;
		HttpSession session = request.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
		System.out.println(memberVO.getPassword());
		System.out.println(password);
		if(memberVO.getPassword().equals(password)) {
			memberService.deleteMember(memberVO.getId());
			System.out.println(memberVO);
			session.invalidate();
			viewPath = "mypage/deleteMember-result";
		}else {
			viewPath = "mypage/deleteMember-fail";
		}	
		return viewPath;
	}
}
