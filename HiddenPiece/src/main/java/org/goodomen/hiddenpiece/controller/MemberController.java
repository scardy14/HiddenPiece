package org.goodomen.hiddenpiece.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AccountVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
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
	
	@ResponseBody
	@RequestMapping("deleteFromWishlist")
	public String deleteFromWishlist(AuctionBoardLikesVO likesVO) {
		System.out.println(likesVO+"여기");
		memberService.deleteFromWishlist(likesVO);
		return "redirect:deleteFromWishlistResult";
	}

	@ResponseBody
	@RequestMapping("ajaxIdCheck")
	public int ajaxIdCheck(String id) {
		int result = memberService.checkId(id);
		return result;
	}
	
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping("ajaxAccountCheck")
	public int ajaxAccountCheck(String accountNo) {
		int result = 0;
		AccountVO accountVO = memberService.findAccountInfoByAccountNo(accountNo);
		MemberVO memberVO = memberService.findMemberByAccount(accountNo);
		if(memberVO != null && accountVO!=null) {
			result = 2; //계좌가 이미 등록되어있음
		}else if(memberVO == null && accountVO!=null) {
			result = 1; //사용 가능
		}else if(accountVO == null){
			result = 0; // 사용 불가 계좌 없음
		}
		return result;
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
	@ResponseBody
	@RequestMapping("checkToWishlist")
	public int checkToWishlist(AuctionBoardLikesVO likesVO) {
		System.out.println(likesVO+"check");
		int result = memberService.checkWishlist(likesVO);
		System.out.println(result);
		return result;
	}
	
	// 찜목록에서 삭제
	@ResponseBody
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
	
	@RequestMapping("exchangePointForm")
	public String exchangePointForm() {
		return "mypage/exchangePointForm";		
	}
	
	@PostMapping("exchangePoint")
	public String exchangePoint(long balance,String accountNo,String bank,String name,HttpServletRequest request) {		
		String viewPath=null;
		HttpSession session=request.getSession(false);
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");		
		AccountVO accountVO=memberService.findAccountInfoByAccountNo(memberVO.getAccountNo());
		System.out.println(balance+" "+ accountNo+" "+bank+" "+name);
		System.out.println(accountVO+" "+memberVO);
		if(memberVO.getName().equals(name) && memberVO.getAccountNo().equals(accountNo) && accountVO.getBank().equals(bank)	&& accountVO.getBalance()>=balance) {
		System.out.println(memberVO);
		System.out.println(accountVO);
		memberService.withdrawPoint(balance, accountNo ,bank);
		memberService.exchangePoint(balance,name);
		System.out.println(memberVO);
		System.out.println(accountVO);
		
		viewPath="redirect:exchangePointResult";		
		}else {
		viewPath="mypage/exchangePoint-fail";
		}
		return viewPath;
	}
	
	@RequestMapping("exchangePointResult")
	public String exchangePointResult() {
		return "mypage/exchangePoint-result";
	}
}



	
	

