package org.goodomen.hiddenpiece;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HiddenPieceTest {
	private MemberMapper memberMapper;

	@Autowired
	public HiddenPieceTest(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}


}
