package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;

public interface MemberService {

	int registerMember(MemberVO memberVO);

	MemberVO login(MemberVO memberVO);

	ArrayList<AuctionBoardPostVO> selectMyWishlist(String id);
}
