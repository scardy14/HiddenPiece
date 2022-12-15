package org.goodomen.hiddenpiece.model.service;


import java.util.ArrayList;

import org.goodomen.hiddenpiece.controller.AuctionBoardCommentVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;


public interface AuctionBoardService {

	AuctionBoardPostVO findAuctionBoardPostDetail(long postNo);

	ArrayList<AuctionBoardPostVO> findAuctionBoardPostList();


	ArrayList<AuctionBoardCommentVO> findAuctionBoardCommentListByPostNo(long postNo);

	void writeComment(AuctionBoardCommentVO commentVO);

	void changeCommentStatus(long commentNo);

	String selectCommentByCommentNo(long commentNo);

	int writeAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);


}
