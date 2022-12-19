package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String findAuctionBoardPostDetail(long postNo, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		AuctionBoardPostVO postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
		if(session!=null) {
			MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
			ArrayList<AuctionBoardPostVO> selectComparedMyWishlist = memberService.selectComparedMyWishlist(memberVO.getId());//내가 찜한 리스트와 총 게시물 리스트를 비교해 setLike된 리스트
			for(int i=0; i<selectComparedMyWishlist.size(); i++) {
				if (selectComparedMyWishlist.get(i).getPostNo()==postNo) {
						postVO.setLike(true);
						break;
				}
			}
		}
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
	public void addToWishlist(String id,long postNo, AuctionBoardPostVO postVO) {
		postVO.setLike(true);
		System.out.println(postVO);
		AuctionBoardLikesVO likesVO = new AuctionBoardLikesVO(id, postNo);
		auctionBoardService.addToWishlist(likesVO);
	}
}
