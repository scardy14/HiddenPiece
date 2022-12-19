package org.goodomen.hiddenpiece.freeboard.test;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.mapper.FreeBoardMapper;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
public class FreeBoardTest {
	FreeBoardMapper freeBoardMapper;
	@Autowired
	public FreeBoardTest(FreeBoardMapper freeBoardMapper) {
		super();
		this.freeBoardMapper = freeBoardMapper;
	}
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
	
}