package org.goodomen.hiddenpiece.test;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HiddenPieceMemberTest {
	private MemberMapper memberMapper;
	
	@Autowired
	public HiddenPieceMemberTest(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	@Test
	public void registerMember() {
		MemberVO memberVO=new MemberVO("jaja5","a","000@gamil.com","01083219231","111115","안양",0,"봉구","문주노","1");
		memberMapper.registerMember(memberVO);
		System.out.println("회원가입완료");
	}
	
	
	
	
	
	
}
