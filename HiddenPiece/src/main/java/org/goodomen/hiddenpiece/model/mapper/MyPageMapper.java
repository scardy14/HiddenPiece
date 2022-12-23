package org.goodomen.hiddenpiece.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;

@Mapper
public interface MyPageMapper {

	int findBiddingCountFromBidList(String id);

	List<Map<String, Object>> findBiddingListFromBidList(CriteriaAndIdVO cri);

	List<Map<String, Object>> findBiddingListFromBidListTag(CriteriaAndIdVO cri);

	int findfindBiddingCountFromBidListTag(CriteriaAndIdVO cri);

	int findSellingCountFromBidList(String id);

	int findSellingCountFromBidListTag(CriteriaAndIdVO cri);

	List<Map<String, Object>> findSellingListFromBidList(CriteriaAndIdVO cri);

	List<Map<String, Object>> findSellingListFromBidListTag(CriteriaAndIdVO cri);

}
