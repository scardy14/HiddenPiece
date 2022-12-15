package org.goodomen.hiddenpiece.model.service;


import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;

import java.util.ArrayList;


public interface AuctionBoardService {

	AuctionBoardPostVO findAuctionBoardPostDetail(long postNo);

	ArrayList<AuctionBoardPostVO> findAuctionBoardPostList();

	int writeAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);

}
