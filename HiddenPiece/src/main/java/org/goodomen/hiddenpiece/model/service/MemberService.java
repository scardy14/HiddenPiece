package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.vo.AccountVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
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

	void exchangePoint(long balance, String name);

	List<Map<String, Object>> selectComparedMyWishlist(String id, Criteria cri);

	void depositPoint(long balance, String accountNo, String bank);

	ArrayList<AuctionBoardPostVO> selectComparedMyWishlist(String id);

	void depositAccount(long point, String accountNo, String bank);

	void withdrawPoint(long point, String name, String id);

	long findPointbyId(String id);

	long findPoint(String id);

	ArrayList<AuctionBoardPostVO> findAuctionBoardStatus1ById(String id);

	ArrayList<AuctionBoardPostVO> findAuctionBoardStatus0ById(String id);

	ArrayList<FreeBoardVO> findFreeBoardStatus0ById(String id);
	
	ArrayList<FreeBoardVO> findFreeBoardStatus1ById(String id);

	MemberVO findMemberById(String id);

}
