package org.goodomen.hiddenpiece.controller;

import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.service.MyPageService;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.Paging;
import org.goodomen.hiddenpiece.model.vo.PagingAndId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService mypageService;
	
	@RequestMapping("biddingProduct")
	public String BiddingProduct(CriteriaAndIdVO cri, Model model) {
		int biddingCount = mypageService.findBiddingCountFromBidList(cri.getId());
		PagingAndId paging = new PagingAndId();
		paging.setCri(cri);
		paging.setTotalCount(biddingCount);
		List<Map<String, Object>> list = mypageService.findBiddingListFromProductList(cri);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		return "";
	}
	@RequestMapping("sellingProduct")
	public String SellingProduct(String id,Criteria cri, Model model) {
		mypageService.findSellingProductList(id,cri,model);
		return "";
	}
	@RequestMapping("endProduct")
	public String EndProduct(String id,Criteria cri, Model model) {
		mypageService.findEndProductList(id,cri,model);
		return "";
	}
}
