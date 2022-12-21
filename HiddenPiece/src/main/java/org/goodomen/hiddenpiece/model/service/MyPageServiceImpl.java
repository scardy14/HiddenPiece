package org.goodomen.hiddenpiece.model.service;

import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.mapper.MyPageMapper;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{
	private final MyPageMapper myPageMapper;
	@Override
	public int findBiddingCountFromBidList(String id) {
		return myPageMapper.findBiddingCountFromBidList(id);
	}

	@Override
	public List<Map<String, Object>> findBiddingListFromProductList(CriteriaAndIdVO cri) {
		return myPageMapper.findBiddingListFromBidList(cri);
	}

	@Override
	public void findSellingProductList(String id,Criteria cri, Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findEndProductList(String id,Criteria cri, Model model) {
		// TODO Auto-generated method stub
		
	}

	

}
