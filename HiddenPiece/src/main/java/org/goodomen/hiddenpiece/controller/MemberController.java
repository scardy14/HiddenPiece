package org.goodomen.hiddenpiece.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.springframework.stereotype.Controller;
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
	
	@PostMapping("registerMember")
	public String register(MemberVO memberVO) {
		System.out.println(memberVO);
		memberService.registerMember(memberVO);
		return "member/register-result";
	}
	
	@RequestMapping("deleteFromWishlist")
	public String deleteFromWishlist(AuctionBoardLikesVO likesVO) {
		memberService.deleteFromWishlist(likesVO);
		return "redirect:deleteFromWishlistResult";
	}
	
	@RequestMapping("deleteFromWishlistResult")
	public String deleteFromWishlistResult() {
		return "/wishlist";
	}
	
	// 찜 목록에 있는지 확인
	@RequestMapping("checkToWishlist")
	public long checkToWishlist(AuctionBoardLikesVO likesVO) {
		System.out.println(likesVO);
		long result = memberService.checkWishlist(likesVO);
		System.out.println(result);
		return result;
	}
	
	// 찜목록에서 삭제
	
}
