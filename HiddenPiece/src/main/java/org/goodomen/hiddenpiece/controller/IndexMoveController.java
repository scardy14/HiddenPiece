package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.FreeBoardService;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.service.NoticeBoardService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.goodomen.hiddenpiece.model.vo.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexMoveController {
	private final FreeBoardService freeBoardService;
	private final NoticeBoardService noticeBoardService;
	private final MemberService memberService;

	// 홈으로 이동 
	@RequestMapping(value={"/","index","home","//"})
	public String indexMove(Model model) {
		model.addAttribute("viewPage","index");
		return "index";
	}
	
	@RequestMapping("needLogin")
	public String needLogin() {
		return "needLogin";
	}
	
	@RequestMapping("layout")
	public String layoutmove() {
		return "layout";
	}

	// 경매게시판 게시글 작성
	@RequestMapping("auctionboardwrite")
	public String auctionBoardWriteMove() {
		return "auctionwriteform2";
	}
	
	// 
	@RequestMapping("exchangePoint")
	public String exchangePoint() {
		return "mypage/exchangePointForm";
	}
	
	//
	@RequestMapping("testCheckOut")
	public String testCheckOut() {
		return "checkout2";
	}
	
	//
	@RequestMapping("registerForm")
	public String registerForm() {
		return "member/register-form";
	}
	
	//
	@RequestMapping("registerMember")
	public String registerMember() {
		return "member/register-result";
	}
	
	//
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
		return "/mypage/wishlist";
	}
	
	//
	@RequestMapping("myInfo")
	public String myInfo() {
		return "mypage/myInfo";		
	}
	
	//
	@RequestMapping("adminForm")
	public String adminForm(Model model) {
		int result = memberService.totalCountMember();
		model.addAttribute("memberCount", result);
		return "admin/admin-form";
	}
	
	//
	@RequestMapping("freeBoardPostList")
	public String freeboard(Criteria cri,Model model) {
		cri.setPerPageNum(5);
		int freeBoardListCnt = freeBoardService.freeBoardListCnt();
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(freeBoardListCnt);
		List<Map<String, Object>> list = freeBoardService.boardList(cri);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		return "freeboard/freeBoardPostList";
	}
	
	//
	@RequestMapping("freeboarddetail")
	public String freeBoardDetailMove() {
		return "freeBoardPostDetail";
	}
	
	// 공지게시판 목록 보기
	@RequestMapping("noticeBoardPostList")
	public String noticeboard(Criteria cri,Model model) {
		cri.setPerPageNum(5);
		int noticeBoardListCnt = noticeBoardService.noticeBoardListCnt();
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(noticeBoardListCnt);
		List<Map<String, Object>> list = noticeBoardService.boardList(cri);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		return "noticeboard/noticeBoardPostList";
	}
	
	// 공지게시판 상세 글보기
	@RequestMapping("noticeboarddetail")
	public String noticeBoardDetailMove() {
		return "noticeBoardPostDetail";
	}
}

