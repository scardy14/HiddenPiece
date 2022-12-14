package org.goodomen.hiddenpiece.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexMoveController {
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
	public String auctionBoardMove() {
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
}
