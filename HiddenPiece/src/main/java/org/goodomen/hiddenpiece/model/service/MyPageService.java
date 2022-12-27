package org.goodomen.hiddenpiece.model.service;

import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;

public interface MyPageService {

	int findBiddingCountFromBidList(String id);
	
	int findBiddingCountFromBidListTag(CriteriaAndIdVO cri);

	List<Map<String, Object>> findBiddingListFromProductList(CriteriaAndIdVO cri);

	int findSellingCountFromBidList(String id);

	int findSellingCountFromBidListTag(CriteriaAndIdVO cri);

	List<Map<String, Object>> findSellingListFromProductList(CriteriaAndIdVO cri);

	

}
