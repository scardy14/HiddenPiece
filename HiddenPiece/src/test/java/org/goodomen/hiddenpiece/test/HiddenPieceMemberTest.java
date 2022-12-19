package org.goodomen.hiddenpiece.test;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HiddenPieceMemberTest {
	private Logger logger=LoggerFactory.getLogger(getClass());
	private MemberMapper memberMapper;
	
	@Autowired
	public HiddenPieceMemberTest(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	@Test
	 public void registerMember() {
		MemberVO memberVO=new MemberVO("jaja5","b","000@gamil.com","01083219231","111115","안양",0,"봉구","문주노","1");
		memberMapper.registerMember(memberVO);
		System.out.println("회원가입완료");
	}
	   @Test
	   public void updateMember() {
	      MemberVO memberVO = new MemberVO("jaja","aa","000@naver.com","01012341234","111111","수원",1000,"봉식","아이유","1");
	      logger.debug("수정전 member {}",memberMapper.findMemberById(memberVO.getId()));
	      memberMapper.updateMember(memberVO);
	      logger.debug("수정후 member {}",memberMapper.findMemberById(memberVO.getId()));
	   }
	
}
