package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.mapper.AuctionBoardMapper;
import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.goodomen.hiddenpiece.model.vo.AccountVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
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
		String password=memberMapper.findPassword(id, address, name, tel);
		return password;
	}

	@Override
	public MemberVO login(MemberVO memberVO) {
		return memberMapper.login(memberVO);
	}


	@Override
	public void updateMember(MemberVO memberVO) {
		long newPoint = memberMapper.findPointbyId(memberVO.getId());
		memberVO.setPoint(newPoint);
		memberMapper.updateMember(memberVO);
		
	}
	
	@Override
	public void deleteMember(String id) {
		memberMapper.deleteMember(id);
	}
	

	@Override
	public ArrayList<AuctionBoardPostVO> selectMyWishlist(String id) {
		ArrayList<AuctionBoardPostVO> list = memberMapper.selectMyWishlist(id);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> selectComparedMyWishlist(String id, Criteria cri) {
		ArrayList<AuctionBoardPostVO> myWishlist = memberMapper.selectMyWishlist(id);
		List<Map<String, Object>>  auctionBoardPostList= auctionBoardMapper.boardList(cri);
		for(int i=0; i<auctionBoardPostList.size(); i++) {
			if(myWishlist.contains(auctionBoardPostList.get(i))){
				((AuctionBoardPostVO) auctionBoardPostList.get(i)).setLike(true);
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
		String id=memberMapper.findId(email, address, name, tel);
		return id;		
	}

	@Override
	public void exchangePoint(long balance, String name) {
		memberMapper.exchangePoint(balance, name);
		System.out.println("MemberServiceImpl exchangePoint  ");
	}

	@Override
	public void depositPoint(long balance, String accountNo, String bank) {
		memberMapper.withdrawPoint(balance, accountNo, bank);	
		System.out.println("MemberServiceImpl withdrawPoint ");
		
		
	}
	
	@Override
	public void depositAccount(long point, String accountNo, String bank) {
		memberMapper.depositAccount(point, accountNo, bank);
	}

	@Override
	public void withdrawPoint(long point, String name, String id) {
		memberMapper.withdrawPoint(point, name, id);
	}

	@Override
	public long findPointbyId(String id) {
		return memberMapper.findPointbyId(id);
	}
	@Override
	public long findPoint(String id) {
		return memberMapper.findPoint(id);

	}

	@Override
	public ArrayList<AuctionBoardPostVO> selectComparedMyWishlist(String id) {
		return null;
	}

	@Override
	public ArrayList<AuctionBoardPostVO> findAuctionBoardStatus1ById(String id) {
		return memberMapper.findAuctionBoardStatus1ById(id);
	}

	@Override
	public ArrayList<AuctionBoardPostVO> findAuctionBoardStatus0ById(String id) {
		return memberMapper.findAuctionBoardStatus0ById(id);
	}

	@Override
	public ArrayList<FreeBoardVO> findFreeBoardStatus0ById(String id) {
		return memberMapper.findFreeBoardStatus0ById(id);
	}

	@Override
	public ArrayList<FreeBoardVO> findFreeBoardStatus1ById(String id) {
		return memberMapper.findFreeBoardStatus1ById(id);
	}

	@Override
	public MemberVO findMemberById(String id) {
		return (MemberVO) memberMapper.findMemberById(id);
	}

}

