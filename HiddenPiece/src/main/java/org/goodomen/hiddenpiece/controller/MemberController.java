package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;

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
		ArrayList<Long> freeBoardList = new ArrayList<>();
		ArrayList<Long> auctionBoardList = new ArrayList<>();
		if (memberVO == null) {
			return "member/login-fail";
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("mvo", memberVO);
			session.setAttribute("freeBoardList", freeBoardList);
			session.setAttribute("auctionBoardList", auctionBoardList);
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
	
	@PostMapping("findId")
	public String findId(String email,String name,String address,String tel,Model model) {
		String id = memberService.findId(email, address, name, tel);
		if(id==null) {
			return "member/findId-fail";
		}else {
			model.addAttribute("id",id);
			return "member/findId-result";
		}
	}
	
	@ResponseBody
	@RequestMapping("findIdresult")
	public String findIdresult(String id,Model model) {
		model.addAttribute("id", id);
		return "member/findId-result";
	}
	
	
	@RequestMapping("findPasswordForm")
	public String findPasswordForm() {
		return "member/findPassword-form";		
	}
	@PostMapping("findPassword")
	public String findPassword(String id,String email,String name,String tel,Model model) {
		String password = memberService.findPassword(id, email, name, tel);
		if(password==null) {
			return "member/findPassword-fail";
		}else {
			model.addAttribute("password",password);
			return "member/findPassword-result";
		}
	}
	
	@RequestMapping("findPasswordresult")
	public String findPasswordresult(String password,Model model) {
		model.addAttribute("password", password);
		return "member/findPassword-result";
	}
	
	
	@PostMapping("registerMember")
	public String register(MemberVO memberVO) {
		memberService.registerMember(memberVO);
		return "member/register-result";
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
		if(memberVO.getPassword().equals(password)) {
			memberService.deleteMember(memberVO.getId());
			session.invalidate();
			viewPath = "mypage/deleteMember-result";
		}else {
			viewPath = "mypage/deleteMember-fail";
		}	
		return viewPath;
	}
	
	@ResponseBody
	@RequestMapping("deleteFromWishlist")
	public String deleteFromWishlist(AuctionBoardLikesVO likesVO) {
		memberService.deleteFromWishlist(likesVO);
		return "redirect:deleteFromWishlistResult";
	}

	@ResponseBody
	@RequestMapping("ajaxIdCheck")
	public int ajaxIdCheck(String id) {
		int result = memberService.checkId(id);
		return result;
	}
	
	
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
	
	

	// 찜 목록에 있는지 확인
	@ResponseBody
	@RequestMapping("checkToWishlist")
	public int checkToWishlist(AuctionBoardLikesVO likesVO) {
		int result = memberService.checkWishlist(likesVO);
		return result;
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
		if(memberVO.getName().equals(name) && memberVO.getAccountNo().equals(accountNo) && accountVO.getBank().equals(bank)	&& accountVO.getBalance()>=balance) {
		memberService.depositPoint(balance, memberVO.getAccountNo(), bank);
		memberService.exchangePoint(balance, name);
		long newPoint = memberService.findPointbyId(memberVO.getId());
		memberVO.setPoint(newPoint);	
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

	@RequestMapping("transferAccountForm")
	public String transferAccountForm() {
		return "mypage/transferToAccount-Form";
	}
	
	@PostMapping("transferToAccount")
	public String transferToAccount(HttpServletRequest request, long point, String name, String accountNo, String bank) {
		String viewPath = null;
		HttpSession session = request.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
		if(memberVO.getName().equals(name) && memberVO.getAccountNo().equals(accountNo) && memberVO.getPoint()>=point) {
			memberService.withdrawPoint(point, name, memberVO.getId());
			memberService.depositAccount(point, accountNo, bank);
			long newPoint = memberService.findPointbyId(memberVO.getId());
			memberVO.setPoint(newPoint);	
			viewPath = "redirect:transferToAccountResult";			
		}else {	
			viewPath = "mypage/transferToAccount-fail";
		}	
		return viewPath;
	}
	
	@RequestMapping("transferToAccountResult")
	public String transferToAccountResult(HttpServletRequest request) {
		return "mypage/transferToAccount-result";
	}

}



	
	

