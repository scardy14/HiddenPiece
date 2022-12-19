package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.AccountVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;

public interface MemberService {

	int registerMember(MemberVO memberVO);

	String findId(String email, String address, String name, String tel);

	String findPassword(String id, String address, String name, String tel);

	MemberVO login(MemberVO memberVO);


	void updateMember(MemberVO memberVO);


	void deleteMember(String id);

	int checkId(String id);

	MemberVO findMemberByAccount(String accountNo);

	AccountVO findAccountInfoByAccountNo(String accountNo);

	ArrayList<AuctionBoardPostVO> selectMyWishlist(String id);

	void deleteFromWishlist(AuctionBoardLikesVO likesVO);

	int checkWishlist(AuctionBoardLikesVO likesVO);

	ArrayList<AuctionBoardPostVO> selectComparedMyWishlist(String id);
}
