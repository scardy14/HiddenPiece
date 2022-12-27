package org.goodomen.hiddenpiece.test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Slf4j
public class AuctionBoardUnitTest {
	/*
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
	
	@Test
	void findAuctionBoardCommentListByPostNo() {
		long postNo = 1;
		ArrayList<AuctionBoardCommentVO> list = auctionBoardMapper.findAuctionBoardCommentListByPostNo(postNo);
		for(AuctionBoardCommentVO vo:list)
		log.debug("list:{}", vo);
	}
	
	@Test
	void writeComment() {
		AuctionBoardCommentVO commentVO = new AuctionBoardCommentVO(1, 3, "yerin0110", "2022-12-15 13:00:00.0","댓글", "1");
		auctionBoardMapper.writeComment(commentVO);
	}
	
	@Test
	void changeCommentStatus() {
		auctionBoardMapper.changeCommentStatus(22);
	}
	
	@Test
	void selectCommentByCommentNo() {
		String content = auctionBoardMapper.selectCommentByCommentNo(22);
		log.debug("content: {}", content);
	}
	
	@Test
	void updateComment() {
		AuctionBoardCommentVO commentVO = new AuctionBoardCommentVO(1, 44, "yerin0110", "2022-12-15 13:00:00.0","댓글22", "1");
		auctionBoardMapper.updateComment(commentVO);
	}
	*/
}
