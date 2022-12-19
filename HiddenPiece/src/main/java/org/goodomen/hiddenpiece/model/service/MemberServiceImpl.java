package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.mapper.AuctionBoardMapper;
import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.AccountVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper memberMapper;	
	private final AuctionBoardMapper auctionBoardMapper;
	
	@Override
	public int registerMember(MemberVO memberVO) {
		return memberMapper.registerMember(memberVO);
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
	public void updateMember(MemberVO memberVO) {
		memberMapper.updateMember(memberVO);
		
	}

	@Override
	public ArrayList<AuctionBoardPostVO> selectMyWishlist(String id) {
		ArrayList<AuctionBoardPostVO> list = memberMapper.selectMyWishlist(id);
		return list;
	}
	
	@Override
	public ArrayList<AuctionBoardPostVO> selectComparedMyWishlist(String id) {
		ArrayList<AuctionBoardPostVO> myWishlist = memberMapper.selectMyWishlist(id);
		ArrayList<AuctionBoardPostVO> auctionBoardPostList= auctionBoardMapper.findAuctionBoardPostList();
		for(int i=0; i<auctionBoardPostList.size(); i++) {
			if(myWishlist.contains(auctionBoardPostList.get(i))){
				auctionBoardPostList.get(i).setLike(true);
			}
		}
		return auctionBoardPostList;
	}

	@Override
	public void deleteFromWishlist(AuctionBoardLikesVO likesVO) {
		memberMapper.deleteFromWishlist(likesVO);
	}

	@ResponseBody
	@Override
	public int checkWishlist(AuctionBoardLikesVO likesVO) {
		return memberMapper.checkWishlist(likesVO);
	}

	@Override
	public void deleteMember(String id) {
		memberMapper.deleteMember(id);
	}
	
	@Override
	public int checkId(String id) {
		return memberMapper.checkId(id);
	}

	@Override
	public MemberVO findMemberByAccount(String accountNo) {
		return memberMapper.findMemberByAccount(accountNo);
	}

	@Override
	public AccountVO findAccountInfoByAccountNo(String accountNo) {
		return memberMapper.findAccountInfoByAccountNo(accountNo);
	}

	@Override
	public String findId(String email, String address, String name, String tel) {
		return memberMapper.findId(email, address, name, tel);
	}

}

