package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.goodomen.hiddenpiece.model.vo.ShareBoardVO;
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
	}

	@Override
	public void depositPoint(long balance, String accountNo, String bank) {
		memberMapper.depositPoint(balance, accountNo, bank);		
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

	@Override
	public int totalCountMember() {
		return memberMapper.totalCountMember();
	}

	@Override
	public void updateMemberStatus(String statusMember, String id) {
		memberMapper.updateMemberStatus(statusMember, id);
	}
	   @Override
	   public int accountCheck(String accountNo) {
	      int result = 0;
	      AccountVO accountVO = memberMapper.findAccountInfoByAccountNo(accountNo);
	      MemberVO memberVO = memberMapper.findMemberByAccount(accountNo);
	      if(memberVO != null && accountVO!=null) {
	         result = 2; //계좌가 이미 등록되어있음
	      }else if(memberVO == null && accountVO!=null) {
	         result = 1; //사용 가능
	      }else if(accountVO == null){   
	         result = 0; // 사용 불가 계좌 없음
	      }
	     return result;
	   }

	@Override
	public long findBalanceByAccountNo(String accountNo) {
		return memberMapper.findBalanceByAccountNo(accountNo);
	}

	@Override
	public ArrayList<ShareBoardVO> findShareBoardStatus0ById(String id) {
		return memberMapper.findShareBoardStatus0ById(id);
	}

	@Override
	public ArrayList<ShareBoardVO> findShareBoardStatus1ById(String id) {
		return memberMapper.findShareBoardStatus1ById(id);
	}

	@Override
	public HashMap<String, ?> findResult(MemberVO memberVO, String id, String status, String board) {
		HashMap<String, Object> list = new HashMap<>();
		String viewPath;
		String boardName;
		ArrayList<?> findList = new ArrayList<>();
		
		
		if(memberVO == null ) {//분기점 티어1:아이디가 없으면 무조건 실패로
			viewPath = "admin/FindBoardListbyId-fail";
		} else if(status.equals("0")) { // 분기점 티어1:조건값 두 개중 하나라도 입력 안하면 false
			viewPath = "admin/FindBoardList-failbyStatus";
		}else if(board.equals("no")) {
			viewPath = "admin/FindBoardList-failbyBoard";
		}else {//분기점 티어1: 모든값을 정상적으로 입력
			if(board.equals("auction")) { //분기점 티어2: 경매게시판 
				if(status.equals("1")) { //분기점 티어3
					findList  = findAuctionBoardStatus0ById(id);
					boardName = "auctionList";
					viewPath="admin/AuctionBoardStatus0-result";
				} else { //분기점 티어3
					findList = findAuctionBoardStatus1ById(id);
					boardName = "auctionList";
					viewPath = "admin/AuctionBoardStatus1-result";
				}
			} else if(board.equals("free")) {//분기점 티어2: 자유게시판
				if(status.equals("1")) { //분기점 티어3
					findList = findFreeBoardStatus0ById(id);
					boardName = "freeList";
					viewPath = "admin/FreeBoardStatus0-result";
				} else { //분기점 티어3
					findList = findFreeBoardStatus1ById(id);
					boardName = "freeList";
					viewPath = "admin/FreeBoardStatus1-result";
				}
			}else {
				if(status.equals("1")) {
					findList = findShareBoardStatus0ById(id);
					boardName = "shareList";
					viewPath = "admin/ShareBoardStatus0-result";
				}else {
					findList = findShareBoardStatus1ById(id);
					boardName = "shareList";
					viewPath = "admin/ShareBoardStatus1-result";
				}
			}
		}
		list.put("viewPath", viewPath);
		list.put("boardName", boardName);
		list.put("findList", findList);
		
		return list;
	}


}

