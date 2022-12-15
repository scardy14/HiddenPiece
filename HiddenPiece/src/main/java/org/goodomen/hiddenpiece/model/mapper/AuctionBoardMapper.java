package org.goodomen.hiddenpiece.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.controller.AuctionBoardCommentVO;
import org.goodomen.hiddenpiece.controller.AuctionBoardPostVO;

@Mapper
public interface AuctionBoardMapper {
	public AuctionBoardPostVO findAuctionBoardPostDetail(long postNo);

	public ArrayList<AuctionBoardPostVO> findAuctionBoardPostList();

	public ArrayList<AuctionBoardCommentVO> findAuctionBoardCommentListByPostNo(long postNo);

	public void writeComment(AuctionBoardCommentVO commentVO);

	public void changeCommentStatus(long commentNo);

	public String selectCommentByCommentNo(long commentNo); 
}
