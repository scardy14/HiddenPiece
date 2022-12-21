package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
import org.goodomen.hiddenpiece.model.service.FreeBoardService;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.FreeBoardCriteria;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.goodomen.hiddenpiece.model.vo.Paging;
import org.goodomen.hiddenpiece.model.vo.Paging2;
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
		return "index3";
	}
	
	@RequestMapping("layout")
	public String layoutmove() {
		return "layout";
	}
	
	/*
	@RequestMapping("auctionboard")
	public String auctionBoardMove(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
			ArrayList<AuctionBoardPostVO> selectComparedMyWishlist = memberService.selectComparedMyWishlist(memberVO.getId());//내가 찜한 리스트와 총 게시물 리스트를 비교해 setLike된 리스트 
			model.addAttribute("postList", selectComparedMyWishlist);
		}
		else {
			ArrayList<AuctionBoardPostVO> auctionBoardPostList =  auctionBoardService.findAuctionBoardPostList(); //전체 리스트 
			model.addAttribute("postList", auctionBoardPostList);
		}
		return "shop2";
	}
	*/
	
	@RequestMapping("auctionboard")
	public String auctionBoardMove(Criteria cri, Model model, HttpServletRequest request) {
		//전체 글 개수
		int auctionBoardListCnt = auctionBoardService.auctionBoardListCnt();
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(auctionBoardListCnt);
		HttpSession session = request.getSession(false);
		if(session!=null) {
			MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
			List<Map<String, Object>> selectComparedMyWishlist = memberService.selectComparedMyWishlist(memberVO.getId(), cri);//내가 찜한 리스트와 총 게시물 리스트를 비교해 setLike된 리스트 
			model.addAttribute("postList", selectComparedMyWishlist);
			model.addAttribute("paging", paging);
		}
		/* else {
			ArrayList<AuctionBoardPostVO> auctionBoardPostList =  auctionBoardService.findAuctionBoardPostList(); //전체 리스트 
			model.addAttribute("postList", auctionBoardPostList);
		}*/
		
		else {
			List<Map<String, Object>> list = auctionBoardService.boardList(cri);
			model.addAttribute("postList", list);
			model.addAttribute("paging", paging);
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
	public String freeboard(FreeBoardCriteria fcri,Model model) {
		int freeBoardListCnt = freeBoardService.freeBoardListCnt();
		Paging2 paging = new Paging2();
		paging.setCri(fcri);
		paging.setTotalCount(freeBoardListCnt);
		List<Map<String, Object>> list = freeBoardService.boardList(fcri);
		System.out.println(list);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		return "freeboard/freeBoardPostList";
	}
	
	@RequestMapping("freeboarddetail")
	public String freeBoardDetailMove() {
		return "freeBoardPostDetail";
	}
}

