package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
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

	@Override
	public ArrayList<AuctionBoardPostVO> selectMyWishlist(String id) {
		ArrayList<AuctionBoardPostVO> list = memberMapper.selectMyWishlist(id);
		return list;
	}

	@Override
	public void deleteFromWishlist(AuctionBoardLikesVO likesVO) {
		memberMapper.deleteFromWishlist(likesVO);
	}

	@Override
	public long checkWishlist(AuctionBoardLikesVO likesVO) {
		long result = memberMapper.checkWishlist(likesVO);
		return result;
	}
}