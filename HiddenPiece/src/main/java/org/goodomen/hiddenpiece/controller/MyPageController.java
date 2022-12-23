package org.goodomen.hiddenpiece.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.goodomen.hiddenpiece.model.service.MyPageService;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.PagingAndId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService mypageService;
	
	@RequestMapping("buyingMyPage")
	public String BiddingProduct(CriteriaAndIdVO cri, Model model) {
		PagingAndId paging = new PagingAndId();
		int biddingCount;
		List<Map<String, Object>> biddingList = null;
		if(cri.getTag()==null||cri.getTag().equals("0")) {
			cri.setTag("0");
			biddingCount = mypageService.findBiddingCountFromBidList(cri.getId());
		}else {
			biddingCount = mypageService.findBiddingCountFromBidListTag(cri);
		} 
		paging.setCri(cri);
		paging.setTotalCount(biddingCount);
		biddingList = mypageService.findBiddingListFromProductList(cri);
		
		model.addAttribute("biddingList", biddingList);
		model.addAttribute("paging", paging);
		return "mypage/buying-Page";
	}
	
	@RequestMapping("sellingMyPage")
	public String SellingProduct(CriteriaAndIdVO cri, Model model) {
		PagingAndId paging = new PagingAndId();
		int sellingCount;
		List<Map<String, Object>> sellingList = null;
		if(cri.getTag()==null||cri.getTag().equals("0")) {
			cri.setTag("0");
			sellingCount = mypageService.findSellingCountFromBidList(cri.getId());
		}else {
			sellingCount = mypageService.findSellingCountFromBidListTag(cri);
		} 
		paging.setCri(cri);
		paging.setTotalCount(sellingCount);
		sellingList = mypageService.findSellingListFromProductList(cri);
		
		System.out.println(paging);
		
		model.addAttribute("sellingList", sellingList);
		model.addAttribute("paging", paging);
		return "mypage/selling-Page";
	}
}
