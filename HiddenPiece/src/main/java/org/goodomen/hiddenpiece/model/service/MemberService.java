package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;

public interface MemberService {

	int registerMember(MemberVO memberVO);


	String findId(String email, String address, String name, String tel);

	String findPassword(String id, String address, String name, String tel);

	MemberVO login(MemberVO memberVO);


	ArrayList<AuctionBoardPostVO> selectMyWishlist(String id);

	void deleteFromWishlist(long postNo);
}
