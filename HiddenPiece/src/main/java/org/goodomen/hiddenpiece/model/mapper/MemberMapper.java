package org.goodomen.hiddenpiece.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.AccountVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;

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

}
