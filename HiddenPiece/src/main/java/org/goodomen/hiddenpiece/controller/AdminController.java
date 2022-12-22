package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	// private final MemberMapper memberMapper;
	private final MemberService memberService;
	
	@RequestMapping("adminSearchBoard")
	public String adminSearchBoard(String id,String status,String board, Model model) {
		String viewPath = null;
		System.out.println(id);
		if(id != null && status.equals("1") && board.equals("auction")) {
			ArrayList<AuctionBoardPostVO> auctionBoardlist  = memberService.findAuctionBoardStatus0ById(id);
			model.addAttribute("auctionList", auctionBoardlist);
			viewPath="admin/AuctionBoardStatus0-result";
		}else if(id != null && status.equals("2") && board.equals("auction")) {
			ArrayList<AuctionBoardPostVO> auctionBoardlist  = memberService.findAuctionBoardStatus1ById(id);
			model.addAttribute("auctionList", auctionBoardlist);
			viewPath = "admin/AuctionBoardStatus1-result";
		}
		else if(id != null && status.equals("1") && board.equals("free")) {
		ArrayList<FreeBoardVO> freeboardList  = 	memberService.findFreeBoardStatus0ById(id);
		model.addAttribute("freeList", freeboardList);
		viewPath = "admin/FreeBoardStatus0-result";
	}
		else if(id != null && status.equals("2") && board.equals("free")) {
			ArrayList<FreeBoardVO> freeboardList  = 	memberService.findFreeBoardStatus1ById(id);
			model.addAttribute("freeList", freeboardList);
			viewPath = "admin/FreeBoardStatus1-result";
		}
		
		
		return viewPath;
	}
}
