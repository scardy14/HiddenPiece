package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
import org.goodomen.hiddenpiece.model.service.FreeBoardService;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexMoveController {
	private final AuctionBoardService auctionBoardService;
	private final FreeBoardService freeBoardService;
	private final MemberService memberService;

	@RequestMapping(value={"/","index","home","//"})
	public String indexMove(Model model) {
		model.addAttribute("viewPage","index");
		return "index2";
	}
	
	@RequestMapping("layout")
	public String layoutmove() {
		return "layout";
	}
	
	@RequestMapping("auctionboard")
	public String auctionBoardMove(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
			ArrayList<AuctionBoardPostVO> myWishlist = memberService.selectMyWishlist(memberVO.getId());
			ArrayList<AuctionBoardPostVO> auctionBoardPostList =  auctionBoardService.findAuctionBoardPostList();
			model.addAttribute("postList", auctionBoardPostList);
			model.addAttribute("myWishlist", myWishlist);
		}
		else {
			ArrayList<AuctionBoardPostVO> auctionBoardPostList =  auctionBoardService.findAuctionBoardPostList();
			model.addAttribute("postList", auctionBoardPostList);
		}
		return "shop2";
	}
	
	@RequestMapping("auctionboarddetail")
	public String auctionBoardDetailMove() {
		return "detail2";
	}
	
	@RequestMapping("auctionboardwrite")
	public String auctionBoardWriteMove() {
		return "auctionwriteform2";
	}
	
	@RequestMapping("exchangePoint")
	public String exchangePoint() {
		return "mypage/exchangePointForm";
	}
	
	@RequestMapping("exchangePointResult")
	public String exchangePointResult() {
		return "mypage/exchangePoint-result";
	}
	
	@RequestMapping("testCheckOut")
	public String testCheckOut() {
		return "checkout2";
	}
	
	@RequestMapping("registerForm")
	public String registerForm() {
		return "member/register-form";
	}
	
	@RequestMapping("registerMember")
	public String registerMember() {
		return "member/register-result";
	}
	
	@RequestMapping("loginForm")
	public String loginForm() {
		return "member/login-form";
	}

	//찜 목록 페이지로 이동
	@RequestMapping("wishlist")
	public String wishlist(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		ArrayList<AuctionBoardPostVO> list = memberService.selectMyWishlist(memberVO.getId());
		model.addAttribute("mywishlist", list);
		return "/wishlist";
	}
	
	@RequestMapping("myInfo")
	public String myInfo() {
		return "mypage/myInfo";		
	}
	
	@RequestMapping("freeBoardPostList")
	public String freeboard(Model model) {
		MemberVO memberVO=new MemberVO();
		ArrayList<FreeBoardVO> list = freeBoardService.findFreeBoardPostList();
		model.addAttribute("memberVO",memberVO);
		model.addAttribute("list", list);
		return "freeboard/freeBoardPostList";
	}
	@RequestMapping("freeboarddetail")
	public String freeBoardDetailMove() {
		return "freeBoardPostDetail";
	}
}

