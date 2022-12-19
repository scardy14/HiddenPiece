package org.goodomen.hiddenpiece.model.service;

import org.goodomen.hiddenpiece.model.vo.AccountVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;

public interface MemberService {

	int registerMember(MemberVO memberVO);

	String findId(String email, String address, String name, String tel);

	String findPassword(String id, String address, String name, String tel);

	MemberVO login(MemberVO memberVO);

	void deleteMember(String id);

	int checkId(String id);

	MemberVO findMemberByAccount(String accountNo);

	AccountVO findAccountInfoByAccountNo(String accountNo);

}
