package org.goodomen.hiddenpiece.controller;


import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuctionBoardController {
	private final AuctionBoardService auctionBoardService;
	
	@RequestMapping("findAuctionBoardPostDetail")
	public String findAuctionBoardPostDetail(long postNo, Model model) {
		AuctionBoardPostVO postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
		model.addAttribute("postVO", postVO);
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
	
}
