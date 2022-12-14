package org.goodomen.hiddenpiece.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.MyPageService;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
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
	public String BiddingProduct(CriteriaAndIdVO cri, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		MemberVO memberVO =  (MemberVO) session.getAttribute("mvo");
		String id = memberVO.getId();
		PagingAndId paging = new PagingAndId();
		int biddingCount;
		List<Map<String, Object>> biddingList = null;
		if(cri.getTag()==null || cri.getTag().equals("0")) {
			cri.setTag("0");
			biddingCount = mypageService.findBiddingCountFromBidList(id);
		}else {
			biddingCount = mypageService.findBiddingCountFromBidListTag(cri);
		} 
		paging.setCri(cri);
		paging.setTotalCount(biddingCount);
		cri.setId(id);
		biddingList = mypageService.findBiddingListFromProductList(cri);
		model.addAttribute("biddingList", biddingList);
		model.addAttribute("paging", paging);
		return "mypage/buying-Page";
	}
	
	@RequestMapping("sellingMyPage")
	public String SellingProduct(CriteriaAndIdVO cri, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
		String id = memberVO.getId();
		
		PagingAndId paging = new PagingAndId();
		int sellingCount;
		List<Map<String, Object>> sellingList = null;
		cri.setId(id);
		if(cri.getTag()==null||cri.getTag().equals("0")) {
			cri.setTag("0");
			sellingCount = mypageService.findSellingCountFromBidList(cri.getId());
		}else { //1,2,3
			sellingCount = mypageService.findSellingCountFromBidListTag(cri);
		} 
		
		paging.setCri(cri);
		paging.setTotalCount(sellingCount);
		sellingList = mypageService.findSellingListFromProductList(cri);
		
		model.addAttribute("sellingList", sellingList);
		model.addAttribute("paging", paging);
		return "mypage/selling-Page";
	}
}
