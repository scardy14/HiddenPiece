package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexMoveController {
	private final AuctionBoardService auctionBoardService;
	
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
	public String auctionBoardMove(Model model) {
		ArrayList<AuctionBoardPostVO> auctionBoardPostList =  auctionBoardService.findAuctionBoardPostList();
		model.addAttribute("postList", auctionBoardPostList);
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
	
}

