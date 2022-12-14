package org.goodomen.hiddenpiece.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;

@Mapper
public interface AuctionBoardMapper {
	public AuctionBoardPostVO findAuctionBoardPostDetail(long postNo); 
}
