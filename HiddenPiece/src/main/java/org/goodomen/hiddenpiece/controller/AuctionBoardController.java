package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
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
	
	@RequestMapping("findAuctionBoardPostDetail")
	public String findAuctionBoardPostDetail(long postNo, Model model) {
		AuctionBoardPostVO postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
		ArrayList<AuctionBoardCommentVO> commentList = auctionBoardService.findAuctionBoardCommentListByPostNo(postNo);
		model.addAttribute("postVO", postVO);
		model.addAttribute("commentList", commentList);
		return "auctionboard/detail2";
	}
	@RequestMapping("moveAuctionBoardPostForm")
	public String moveAuctionBoardPostForm() {
		return "auctionboard/write-form";
	}	
	@PostMapping("writeAuctionBoardPost")
	public String writeAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO) {
		auctionBoardPostVO.setEndDate(auctionBoardPostVO.getEndDate().substring(0, 10) + " " +auctionBoardPostVO.getEndDate().substring(11, 16));
		int result = auctionBoardService.writeAuctionBoardPost(auctionBoardPostVO);
		return "auctionboard/write-ok";
	}
	@RequestMapping("moveAuctionBoardPostUpdateForm")
	public String moveAuctionBoardPostUpdateForm(Model model, long postNo) {
		AuctionBoardPostVO postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
		model.addAttribute("postVO", postVO);
		return "auctionboard/update-form";
	}
	
	@PostMapping("moveAuctionBoardPostDelete")
	public String moveAuctionBoardPostDelete() {
		return "auctionboard/delete-form";
	}	
	
	@ResponseBody
	@RequestMapping("writeComment")
	public String writeComment(Model model, AuctionBoardCommentVO commentVO) {
		String result = "1";
		auctionBoardService.writeComment(commentVO);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("changeCommentStatus")
	public void changeCommentStatus(long commentNo) {
		auctionBoardService.changeCommentStatus(commentNo);
	}

	@ResponseBody
	@RequestMapping("selectCommentByCommentNo")
	public void selectCommentByCommentNo(long commentNo) {
		auctionBoardService.selectCommentByCommentNo(commentNo);
	}
}
