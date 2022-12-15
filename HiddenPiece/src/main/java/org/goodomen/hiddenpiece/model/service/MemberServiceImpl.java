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
	/*
	@Override
	public MemberVO login(MemberVO memberVO, HttpServletRequest request) {
		return memberMapper.login(memberVO);
	}
	*/
	@Override
	public MemberVO login(MemberVO memberVO) {
		return memberMapper.login(memberVO);
	}
}
