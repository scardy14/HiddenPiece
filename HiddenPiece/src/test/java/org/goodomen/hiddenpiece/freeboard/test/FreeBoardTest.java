package org.goodomen.hiddenpiece.freeboard.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.mapper.FreeBoardMapper;
import org.goodomen.hiddenpiece.model.service.MyPageService;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.goodomen.hiddenpiece.model.vo.PagingAndId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
public class FreeBoardTest {
	FreeBoardMapper freeBoardMapper;
	MyPageService mypageService;
	@Autowired
	public FreeBoardTest(FreeBoardMapper freeBoardMapper) {
		super();
		this.freeBoardMapper = freeBoardMapper;
	}
	public 
	@Test
	void mapper() {
		Assertions.assertNotNull(freeBoardMapper);
	}
	@Test
	void freeBoardPostList() {
		ArrayList<FreeBoardVO> list=freeBoardMapper.findFreeBoardPostList();
		for(FreeBoardVO vo:list)
			log.debug("list:{}",vo);
	}
	
	@Test
	void writePost() {
		FreeBoardVO freeBoardVO=new FreeBoardVO(3L, "왜안되", "2022-12-19", "뭐가문제", 0, 1, "hunjin");
		freeBoardMapper.writePost(freeBoardVO);
	}

	@Test
	void updatePost() {
		FreeBoardVO freeBoardVO=new FreeBoardVO(2, "글", "2022-12-19", "글수정", 0, 1, "hunjin");
		freeBoardMapper.updatePost(freeBoardVO);
	}
	@Test 
	void deletePost() {
		long postNo=48;
		freeBoardMapper.deletePost(postNo);
	}
	@Test
	void updateHits() {
		long postNo=49;
		freeBoardMapper.updateHits(postNo);
	}

}
