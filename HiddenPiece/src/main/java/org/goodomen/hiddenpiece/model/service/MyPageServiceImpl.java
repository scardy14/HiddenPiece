package org.goodomen.hiddenpiece.model.service;

import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.mapper.MyPageMapper;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.springframework.stereotype.Service;

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
	public int findBiddingCountFromBidListTag(CriteriaAndIdVO cri) {
		return myPageMapper.findfindBiddingCountFromBidListTag(cri);
	}

	@Override
	public List<Map<String, Object>> findBiddingListFromProductList(CriteriaAndIdVO cri) {
		if(cri.getTag().equals("0")) {
			return myPageMapper.findBiddingListFromBidList(cri);
		} else {
			return myPageMapper.findBiddingListFromBidListTag(cri);
		}
		
	}

	@Override
	public int findSellingCountFromBidList(String id) {
		return myPageMapper.findSellingCountFromBidList(id);
	}

	@Override
	public int findSellingCountFromBidListTag(CriteriaAndIdVO cri) {
		return myPageMapper.findSellingCountFromBidListTag(cri);
	}

	@Override
	public List<Map<String, Object>> findSellingListFromProductList(CriteriaAndIdVO cri) {
		if(cri.getTag().equals("0")) {
			return myPageMapper.findSellingListFromBidList(cri);
		} else {
			return myPageMapper.findSellingListFromBidListTag(cri);
		}
	}

	

	

}
