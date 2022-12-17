package org.goodomen.hiddenpiece.model.service;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper memberMapper;	
	
	@Override
	public int registerMember(MemberVO memberVO) {
		return memberMapper.registerMember(memberVO);
	}


	@Override
	public String findId(String email, String address, String name, String tel) {
		return memberMapper.findId(email, address, name, tel);
	}

	@Override
	public String findPassword(String id, String address, String name, String tel) {
		return memberMapper.findPassword(id, address, name, tel);
	}
	


	@Override
	public MemberVO login(MemberVO memberVO) {
		return memberMapper.login(memberVO);
	}


	@Override
	public int checkId(String id) {
		return memberMapper.checkId(id);
	}

}
