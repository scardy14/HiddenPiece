package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.controller.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.mapper.AuctionBoardMapper;
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


}
