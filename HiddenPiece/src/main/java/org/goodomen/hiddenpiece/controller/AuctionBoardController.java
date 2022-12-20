package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuctionBoardController {
	private final AuctionBoardService auctionBoardService;
	private final MemberService memberService;
	
	// 경매게시판 상세보기
	@RequestMapping("findAuctionBoardPostDetail")
	public String findAuctionBoardPostDetail(long postNo, Model model) {
		AuctionBoardPostVO postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
		ArrayList<AuctionBoardCommentVO> commentList = auctionBoardService.findAuctionBoardCommentListByPostNo(postNo);
		model.addAttribute("postVO", postVO);
		model.addAttribute("commentList", commentList);
		return "auctionboard/detail2";
	}
	
	// 경매게시판 글 작성 폼 화면 이동
	@RequestMapping("moveAuctionBoardPostForm")
	public String moveAuctionBoardPostForm() {
		return "auctionboard/write-form";
	}
	
	// 경매게시판 글 작성
	@RequestMapping("writeAuctionBoardPost")
	public String writeAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO) {
		auctionBoardPostVO.setEndDate(auctionBoardPostVO.getEndDate().substring(0, 10) + " " +auctionBoardPostVO.getEndDate().substring(11, 16));
		auctionBoardService.writeAuctionBoardPost(auctionBoardPostVO);
		return "auctionboard/write-ok";
	}
	
	// 경매게시판 댓글 작성
	@ResponseBody
	@RequestMapping("writeComment")
	public String writeComment(Model model, AuctionBoardCommentVO commentVO) {
		String result = "1";
		auctionBoardService.writeComment(commentVO);
		return result;
	}
	
	// 경매게시판 댓글 삭제(상태 0으로 변경)
	@ResponseBody
	@RequestMapping("changeCommentStatus")
	public void changeCommentStatus(long commentNo) {
		auctionBoardService.changeCommentStatus(commentNo);
	}
	
	// 경매게시판 댓글 조회
	@ResponseBody
	@RequestMapping("selectCommentByCommentNo")
	public String selectCommentByCommentNo(long commentNo) {
		String content = auctionBoardService.selectCommentByCommentNo(commentNo);
		return content;
	}
	
	// 경매게시판 댓글 수정
	@ResponseBody
	@RequestMapping("updateComment")
	public void updateComment(AuctionBoardCommentVO commentVO) {
		auctionBoardService.updateComment(commentVO);
	}
	
	// 찜하기 버튼 눌르기
	@ResponseBody
	@RequestMapping("addToWishlist")
	public void addToWishlist(String id,long postNo) {
		AuctionBoardLikesVO likesVO = new AuctionBoardLikesVO(id, postNo);
		auctionBoardService.addToWishlist(likesVO);
	}
	//경매게시판 글 수정 폼으로 이동
	@RequestMapping("moveAuctionBoardPostUpdateForm")
	public String moveAuctionBoardPostUpdateForm(Model model, long postNo) {
		AuctionBoardPostVO postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
		model.addAttribute("postVO", postVO);
		return "auctionboard/update-form";
	}
	//경매게시판 글 삭제
	@PostMapping("AuctionBoardPostDelete")
	public String moveAuctionBoardPostDelete(long postNo) {
		int result = auctionBoardService.deleteAuctionBoardPost(postNo);
		return "auctionboard/delete-ok";
	}
	//경매게시판 글 수정
	@PostMapping("updateAuctionBoardPost")
	public String updateAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO) {
		auctionBoardPostVO.setEndDate(auctionBoardPostVO.getEndDate().substring(0, 10) + " " +auctionBoardPostVO.getEndDate().substring(11, 16));
		System.out.println(auctionBoardPostVO);
		int result = auctionBoardService.updateAuctionBoardPost(auctionBoardPostVO);
		return "auctionboard/update-ok";
	}
	@RequestMapping("bidMove")
	public String bidMove(long postNo, String id, long bidPrice) {
		return "redirect:bid?postNo="+postNo+"&id="+id+"&bidPrice="+bidPrice;
	}
	@RequestMapping("bid")
	public String bid(AuctionBoardPostVO auctionBoardPostVO,long bidPrice) {
		auctionBoardPostVO.setCurrentPrice(bidPrice);
		//int result = auctionBoardService.bidAuctionBoardPost(auctionBoardPostVO);
		System.out.println(auctionBoardPostVO);
		return "auctionboard/bid-ok";
	}
	
}
