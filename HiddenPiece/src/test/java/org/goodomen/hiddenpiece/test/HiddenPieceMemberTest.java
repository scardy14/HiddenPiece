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
	private MemberMapper memberMapper;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public HiddenPieceMemberTest(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	
	@Test
	public void login() {
		MemberVO memberVO = new MemberVO("yerin0110", "a", null, null, null, null, 0, null, null, null);
		MemberVO resultVO = memberMapper.login(memberVO);
		logger.debug("login vo: {}", resultVO);
	}
	@Test
	public void findId() {
		String email = "jyhong20@naver.com";
		String address = "상일동";
		String name = "홍주영";
		String tel = "01063462516";
		String id = memberMapper.findId(email,address,name,tel);
		System.out.println(id);
	}
	
	@Test
	public void findPassword() {
		String id = "scardy";
		String email = "jyhong20@naver.com";
		String name = "홍주영";
		String tel = "01063462516";
		String password = memberMapper.findPassword(id,email,name,tel);
		System.out.println(password);
	}
	@Test
	public void registerMember() {
		MemberVO memberVO=new MemberVO("jaja5","a","000@gamil.com","01083219231","111115","안양",0,"봉구","문주노","1");
		memberMapper.registerMember(memberVO);
		System.out.println("회원가입완료");
	}
	
	@Test
	public void deleteMember() {
		String id = "yerin0110";
		memberMapper.deleteMember(id);
		System.out.println("회원탈퇴가 완료되었습니다");
	}
	

	
}
