package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.mapper.AuctionBoardMapper;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardCommentVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@ResponseBody
	@Override
	public void addToWishlist(AuctionBoardLikesVO likesVO) {
		auctionBoardMapper.addToWishlist(likesVO);
	}

	@Override
	public int deleteAuctionBoardPost(long postNo) {
		return auctionBoardMapper.deleteAuctionBoardPost(postNo);
		
	}

	@Override
	public int updateAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO) {
		return auctionBoardMapper.updateAuctionBoardPost(auctionBoardPostVO);
	}

	@Override
	@Transactional
	public int bidAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO) {
		int result = 0;
		try {
			if(auctionBoardMapper.findAuctionBoardPostNowId(auctionBoardPostVO)!=null) {
				auctionBoardMapper.reverseBidAuctionBoardPost(auctionBoardPostVO);
			}
			if(auctionBoardMapper.checkBidList(auctionBoardPostVO)!=1) {
				auctionBoardMapper.addBidList(auctionBoardPostVO);
			}
			auctionBoardMapper.bidAuctionBoardPost(auctionBoardPostVO);
			auctionBoardMapper.updateMemberPoint(auctionBoardPostVO);
			result = 1;
		} catch (Exception e){
			result = 0;
		} 
		return result;
	}

	@Override
	public int auctionBoardListCnt() {
		return auctionBoardMapper.auctionBoardListCnt();
	}

	@Override
	public List<Map<String, Object>> boardList(Criteria cri) {
		return auctionBoardMapper.boardList(cri);
	}
	@Override
	@Transactional
	public int buyAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO) {
		int result = 0;
		try {
			if(auctionBoardMapper.findAuctionBoardPostNowId(auctionBoardPostVO)!=null) {
				auctionBoardMapper.reverseBidAuctionBoardPost(auctionBoardPostVO);
			}
			if(auctionBoardMapper.checkBidList(auctionBoardPostVO)!=1) {
				auctionBoardMapper.addBidList(auctionBoardPostVO);
			}
			auctionBoardMapper.buyAuctionBoardPost(auctionBoardPostVO);
			auctionBoardMapper.updateMemberPointbuy(auctionBoardPostVO);
			result = 1;
		} catch(Exception e) {
			result = 0;
		}
		return result;
	}

	@Override
	public void addHits(long postNo) {
		auctionBoardMapper.addHits(postNo);
	}

	@Override
	public List<Map<String, Object>> searchPostByKeyword(Criteria cri) {
		return auctionBoardMapper.searchPostByKeyword(cri);
	}

	@Override
	public int searchAuctionBoardListCnt(Criteria cri) {
		return auctionBoardMapper.searchAuctionBoardListCnt(cri);
	}

	@Override
	public void updatePostStatusTo2() {
		auctionBoardMapper.updatePostStatusTo2();		
	}

	@Override
	public void updatePostStatusTo3() {
		auctionBoardMapper.updatePostStatusTo3();
	}


}
