package org.goodomen.hiddenpiece.model.service;

import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.springframework.ui.Model;

public interface MyPageService {

	int findBiddingCountFromBidList(String id);

	List<Map<String, Object>> findBiddingListFromProductList(CriteriaAndIdVO cri);

	void findSellingProductList(String id,Criteria cri, Model model);

	void findEndProductList(String id,Criteria cri, Model model);

	

}
