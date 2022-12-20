package org.goodomen.hiddenpiece.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.controller.AuctionBoardCommentVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.Criteria;


@Mapper
public interface AuctionBoardMapper {
	public AuctionBoardPostVO findAuctionBoardPostDetail(long postNo);

	public ArrayList<AuctionBoardPostVO> findAuctionBoardPostList();

	public ArrayList<AuctionBoardCommentVO> findAuctionBoardCommentListByPostNo(long postNo);

	public void writeComment(AuctionBoardCommentVO commentVO);

	public void changeCommentStatus(long commentNo);

	public String selectCommentByCommentNo(long commentNo); 

	public int writeAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);

	public void updateComment(AuctionBoardCommentVO commentVO);

	public void addToWishlist(AuctionBoardLikesVO likesVO);

	public int deleteAuctionBoardPost(long postNo);

	public int updateAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);

	public void bidAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);
	
	public void updateMemberPoint(AuctionBoardPostVO auctionBoardPostVO);

	public void reverseBidAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);

	public int auctionBoardListCnt();

	public List<Map<String, Object>> boardList(Criteria cri);
	
	public String findAuctionBoardPostNowId(AuctionBoardPostVO auctionBoardPostVO);

	public void buyAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO);

	public void updateMemberPointbuy(AuctionBoardPostVO auctionBoardPostVO);

	public void addHits(long postNo);
	

}
