package org.goodomen.hiddenpiece.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.service.MemberServiceImpl;
import org.goodomen.hiddenpiece.model.vo.AccountVO;

import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

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
			memberVO = memberService.login(memberVO);
			return "member/login-fail";
		}
		else {
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
	

	@RequestMapping("deleteFromWishlist")
	public String deleteFromWishlist(AuctionBoardLikesVO likesVO) {
		memberService.deleteFromWishlist(likesVO);
		return "redirect:deleteFromWishlistResult";
	}

	@ResponseBody
	@RequestMapping("ajaxIdCheck")
	public String ajaxIdCheck(String id) {
		String message = null;
		int result = memberService.checkId(id);
		if(result == 1) {
			message = "중복되는 아이디입니다 아이디를 변경해주세요";
		}else {
			message = "사용가능한 아이디입니다";
		}
		return message;
	}
	
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping("ajaxAccountCheck")
	public String ajaxAccountCheck(String accountNo) {
		String message = null;
		AccountVO accountVO = memberService.findAccountInfoByAccountNo(accountNo);
		MemberVO memberVO = memberService.findMemberByAccount(accountNo);
		if(memberVO != null && accountVO!=null) {
			message = " 이미 사용중인 계좌번호입니다";
		}else if(memberVO == null && accountVO!=null) {
			message = "사용 가능한 계좌번호입니다 ";
		}else if(accountVO == null){
			message = "일치하는 계좌번호가 없습니다";
		}
		return message;
	}
	

	@RequestMapping("deleteFromWishlistResult")
	public String deleteFromWishlistResult() {
		return "/wishlist";
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

	// 찜 목록에 있는지 확인
	@RequestMapping("checkToWishlist")
	public long checkToWishlist(AuctionBoardLikesVO likesVO) {
		System.out.println(likesVO);
		long result = memberService.checkWishlist(likesVO);
		System.out.println(result);
		return result;
	}
	
	// 찜목록에서 삭제
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

	@RequestMapping("updateMemberForm")
	public String updatememberForm() {
		return "member/update-form";		
	}
	@PostMapping("updateMember")
	public String updateMember(MemberVO memberVO,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		memberService.updateMember(memberVO);
		session.setAttribute("mvo",memberVO);
		return "redirect:updateMemberResult";		
	}
	
	@RequestMapping("updateMemberResult")
	public String updateMemberResult() {
		return "member/update-result";
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
