package org.goodomen.hiddenpiece.model.service;

import org.goodomen.hiddenpiece.model.mapper.AuctionBoardMapper;
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

}
