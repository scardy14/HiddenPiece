package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.controller.AuctionBoardCommentVO;
import org.goodomen.hiddenpiece.model.mapper.AuctionBoardMapper;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuctionBoardServiceImpl implements AuctionBoardService {
	private final AuctionBoardMapper auctionBoardMapper;
	@Override
	public AuctionBoardPostVO findAuctionBoardPostDetail(long postNo) {
		return auctionBoardMapper.findAuctionBoardPostDetail(postNo);
	}
	
	@Override
	public ArrayList<AuctionBoardPostVO> findAuctionBoardPostList() {
		return auctionBoardMapper.findAuctionBoardPostList();
	}
	
	@Override
	public ArrayList<AuctionBoardCommentVO> findAuctionBoardCommentListByPostNo(long postNo) {
		return auctionBoardMapper.findAuctionBoardCommentListByPostNo(postNo);
	}
	
	@Override
	public void writeComment(AuctionBoardCommentVO commentVO) {
		auctionBoardMapper.writeComment(commentVO);
	}
	
	@Override
	public int writeAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO) {
		return auctionBoardMapper.writeAuctionBoardPost(auctionBoardPostVO);
	}

	@Override
	public void changeCommentStatus(long commentNo) {
		auctionBoardMapper.changeCommentStatus(commentNo);
	}

	@Override
	public String selectCommentByCommentNo(long commentNo) {
		return auctionBoardMapper.selectCommentByCommentNo(commentNo);
	}

	@Override
	public void updateComment(AuctionBoardCommentVO commentVO) {
		auctionBoardMapper.updateComment(commentVO);
	}

	@Override
	public void addToWishlist(AuctionBoardLikesVO likesVO) {
		auctionBoardMapper.addToWishlist(likesVO);
	}



}
