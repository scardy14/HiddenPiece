package org.goodomen.hiddenpiece.test;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class WishlistUnitTest {
	private MemberMapper memberMapper;

	@Autowired
	public WishlistUnitTest(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	
	@Test
	void findAuctionBoardPostList() {
		ArrayList<AuctionBoardPostVO> list =memberMapper.selectMyWishlist("yerin0110");
		for(AuctionBoardPostVO vo:list)
		log.debug("list:{}", vo);
	}
	
	@Test
	void deleteFromWishlist() {
		AuctionBoardLikesVO likesVO = new AuctionBoardLikesVO("yerin0110",3);
		memberMapper.deleteFromWishlist(likesVO);
		ArrayList<AuctionBoardPostVO> list =memberMapper.selectMyWishlist("yerin0110");
		for(AuctionBoardPostVO vo:list)
			log.debug("list:{}", vo);
	}
	
	@Test
	void checkWishlist(){
		AuctionBoardLikesVO likesVO = new AuctionBoardLikesVO("yerin0110",3);
		long result = memberMapper.checkWishlist(likesVO);
		System.out.println(result);
	}
	
}