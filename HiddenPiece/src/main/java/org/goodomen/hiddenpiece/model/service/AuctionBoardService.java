package org.goodomen.hiddenpiece.model.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.controller.AuctionBoardCommentVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.Criteria;


public interface AuctionBoardService {

	AuctionBoardPostVO findAuctionBoardPostDetail(long postNo);

	ArrayList<AuctionBoardPostVO> findAuctionBoardPostList();

	ArrayList<AuctionBoardCommentVO> findAuctionBoardCommentListByPostNo(long postNo);

	void writeComment(AuctionBoardCommentVO commentVO);

	void changeCommentStatus(long commentNo);

	String selectCommentByCommentNo(long commentNo);

	int writeAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);

	void updateComment(AuctionBoardCommentVO commentVO);

	void addToWishlist(AuctionBoardLikesVO auctionBoardLikesVO);

	int deleteAuctionBoardPost(long postNo);

	int updateAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);

	int bidAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVOe);

	int auctionBoardListCnt();

	List<Map<String, Object>> boardList(Criteria cri); 

	List<Map<String, Object>> searchPostByKeyword(Criteria cri);

	int buyAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);

	void addHits(long postNo);

	int searchAuctionBoardListCnt(Criteria cri);

}
