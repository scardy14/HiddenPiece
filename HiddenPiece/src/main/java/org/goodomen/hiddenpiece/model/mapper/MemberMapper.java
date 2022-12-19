package org.goodomen.hiddenpiece.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;

@Mapper
public interface MemberMapper {

	MemberVO login(MemberVO memberVO);

	int registerMember(MemberVO memberVO);
	
	String findId(String email, String address, String name, String tel);

	String findPassword(String id, String email, String name, String tel);

	ArrayList<AuctionBoardPostVO> selectMyWishlist(String id);

	void deleteFromWishlist(AuctionBoardLikesVO likesVO);

	int checkWishlist(AuctionBoardLikesVO likesVO);
}
