package org.goodomen.hiddenpiece.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.goodomen.hiddenpiece.model.vo.AccountVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.goodomen.hiddenpiece.model.vo.ShareBoardVO;

@Mapper
public interface MemberMapper {

	MemberVO login(MemberVO memberVO);

	int registerMember(MemberVO memberVO);
	
	String findId(String email, String address, String name, String tel);

	String findPassword(String id, String email, String name, String tel);

	void updateMember(MemberVO memberVO);

	Object findMemberById(String id);
	
	ArrayList<AuctionBoardPostVO> selectMyWishlist(String id);

	void deleteFromWishlist(AuctionBoardLikesVO likesVO);

	int checkWishlist(AuctionBoardLikesVO likesVO);

	void deleteMember(String id);

	int checkId(String id);

	MemberVO findMemberByAccount(String accountNo);

	AccountVO findAccountInfoByAccountNo(String accountNo);


	AccountVO findAccountInfobyAccountNo(AccountVO accountVO);

	void depositPoint(long balance, String accountNo, String bank);

	void exchangePoint(long balance, String name);

	void depositAccount(long point, String accountNo, String bank);

	void withdrawPoint(long point, String name, String id);

	long findPointbyId(String id);

	long findPoint(String id);

	ArrayList<AuctionBoardPostVO> findAuctionBoardStatus1ById(String id);

	ArrayList<AuctionBoardPostVO> findAuctionBoardStatus0ById(String id);

	ArrayList<FreeBoardVO> findFreeBoardStatus0ById(String id);

	ArrayList<FreeBoardVO> findFreeBoardStatus1ById(String id);

	int totalCountMember();

	void updateMemberStatus(String status, String id);

	long findBalanceByAccountNo(String accountNo);

	ArrayList<ShareBoardVO> findShareBoardStatus1ById(String id);

	ArrayList<ShareBoardVO> findShareBoardStatus0ById(String id);
}
