package org.goodomen.hiddenpiece.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	//private final MemberMapper memberMapper;
	private final MemberService memberService;

	@PostMapping("login")
	public String login(MemberVO memberVO, HttpServletRequest request) {
		MemberVO vo = memberService.login(memberVO);
		if (vo == null) {
			return "member/login-fail";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("mvo", vo);
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

	@RequestMapping("deleteFromWishlist")
	public String deleteFromWishlist(long postNo) {
		memberService.deleteFromWishlist(postNo);
		return "redirect:deleteFromWishlistResult";
	}
	
	@RequestMapping("deleteFromWishlistResult")
	public String deleteFromWishlistResult() {
		return "/wishlist";
	}

}
