package org.goodomen.hiddenpiece;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HiddenPieceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private MemberMapper memberMapper;

	@Autowired
	public HiddenPieceTest(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Test
	public void login() {
		MemberVO memberVO = new MemberVO("yerin0110", "a", null, null, null, null, 0, null, null, 0, 0);
		MemberVO resultVO = memberMapper.login(memberVO);
		logger.debug("login vo: {}", resultVO);
	}

}
