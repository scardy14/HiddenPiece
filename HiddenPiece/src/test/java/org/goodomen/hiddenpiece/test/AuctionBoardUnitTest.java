package org.goodomen.hiddenpiece.test;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.controller.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.mapper.AuctionBoardMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class AuctionBoardUnitTest {
	AuctionBoardMapper auctionBoardMapper;
	
	@Autowired
	public AuctionBoardUnitTest(AuctionBoardMapper auctionBoardMapper) {
		super();
		this.auctionBoardMapper = auctionBoardMapper;
	}
	@Test
	void mapper() {
		Assertions.assertNotNull(auctionBoardMapper);
	}
	
	@Test
	void findAuctionBoardPostList() {
		ArrayList<AuctionBoardPostVO> list =auctionBoardMapper.findAuctionBoardPostList();
		for(AuctionBoardPostVO vo:list)
		log.debug("list:{}", vo);
	}
}
