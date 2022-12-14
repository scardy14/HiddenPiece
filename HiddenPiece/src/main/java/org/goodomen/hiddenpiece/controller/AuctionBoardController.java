package org.goodomen.hiddenpiece.controller;

import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuctionBoardController {
	private final AuctionBoardService auctionBoardService;
	
	@RequestMapping("findAuctionBoardPostDetail")
	public String findAuctionBoardPostDetail(long postNo, Model model) {
		AuctionBoardPostVO postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
		model.addAttribute("postVO",postVO);
		return "auctionboard/detail2";
	}
	@RequestMapping("writeAuctionBoardPost")
	public String writeAuctionBoardPost() {
		return "auctionboard/write-form";
	}
	
}
