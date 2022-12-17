package org.goodomen.hiddenpiece.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.AccountVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;

@Mapper
public interface MemberMapper {

	MemberVO login(MemberVO memberVO);

	int registerMember(MemberVO memberVO);
	
	String findId(String email, String address, String name, String tel);

	String findPassword(String id, String email, String name, String tel);

	int checkId(String id);

	MemberVO findMemberByAccount(String accountNo);

	AccountVO findAccountInfoByAccountNo(String accountNo);


	
}
