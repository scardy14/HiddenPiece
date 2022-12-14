package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.controller.AuctionBoardPostVO;

public interface AuctionBoardService {

	AuctionBoardPostVO findAuctionBoardPostDetail(long postNo);

	ArrayList<AuctionBoardPostVO> findAuctionBoardPostList();

}
