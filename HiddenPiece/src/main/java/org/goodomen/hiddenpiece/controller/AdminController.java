package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.goodomen.hiddenpiece.model.vo.ShareBoardVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	// private final MemberMapper memberMapper;
	private final MemberService memberService;
	
	@RequestMapping("adminSearchBoard")
	public String adminSearchBoard(String id,String status,String board, Model model) {
		MemberVO memberVO = memberService.findMemberById(id);
		HashMap<String,?> list = memberService.findResult(memberVO,id,status,board);
		
		String viewPath = (String) list.get("viewPath");
		String boardName = (String) list.get("boardName");
		ArrayList<?> findList = (ArrayList<?>) list.get("findList");
		model.addAttribute(boardName, findList);
		/*
		if(memberVO == null ) {//분기점 티어1:아이디가 없으면 무조건 실패로
			viewPath = "admin/FindBoardListbyId-fail";
		} else if(status.equals("0")) { // 분기점 티어1:조건값 두 개중 하나라도 입력 안하면 false
			viewPath = "admin/FindBoardList-failbyStatus";
		}else if(board.equals("no")) {
			viewPath = "admin/FindBoardList-failbyBoard";
		}else {//분기점 티어1: 모든값을 정상적으로 입력
			if(board.equals("auction")) { //분기점 티어2: 경매게시판 
				if(status.equals("1")) { //분기점 티어3
					ArrayList<AuctionBoardPostVO> auctionBoardlist  = memberService.findAuctionBoardStatus0ById(id);
					model.addAttribute("auctionList", auctionBoardlist);
					viewPath="admin/AuctionBoardStatus0-result";
				} else { //분기점 티어3
					ArrayList<AuctionBoardPostVO> auctionBoardlist  = memberService.findAuctionBoardStatus1ById(id);
					model.addAttribute("auctionList", auctionBoardlist);
					viewPath = "admin/AuctionBoardStatus1-result";
				}
			} else if(board.equals("free")) {//분기점 티어2: 자유게시판
				if(status.equals("1")) { //분기점 티어3
					ArrayList<FreeBoardVO> freeboardList  = 	memberService.findFreeBoardStatus0ById(id);
					model.addAttribute("freeList", freeboardList);
					viewPath = "admin/FreeBoardStatus0-result";
				} else { //분기점 티어3
					ArrayList<FreeBoardVO> freeboardList  = 	memberService.findFreeBoardStatus1ById(id);
					model.addAttribute("freeList", freeboardList);
					viewPath = "admin/FreeBoardStatus1-result";
				}
			}else {
				if(status.equals("1")) {
					ArrayList<ShareBoardVO> shareboardList = memberService.findShareBoardStatus0ById(id);
					model.addAttribute("shareList", shareboardList);
					viewPath = "admin/ShareBoardStatus0-result";
				}else {
					ArrayList<ShareBoardVO> shareboardList = memberService.findShareBoardStatus1ById(id);
					model.addAttribute("shareList", shareboardList);
					viewPath = "admin/ShareBoardStatus1-result";
				}
			}
		}
		*/
		return viewPath;
	}
	@RequestMapping("ajaxFindMemberVO")
	@ResponseBody
	public ResponseEntity<?> ajaxFindMemberVO(String memberId, Model model) {

		MemberVO memberVO = memberService.findMemberById(memberId);
		if(memberVO != null) {

			return new ResponseEntity<>(memberVO,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("회원이 존재하지 않습니다",HttpStatus.NOT_FOUND);
			
		}
	}
	@RequestMapping("adminSearchMember")
	public String adminSearchMember(String memberId, String statusMember) {
		System.out.println(memberId);
		MemberVO memberVO = memberService.findMemberById(memberId);
		if(memberVO != null) {
		memberService.updateMemberStatus(statusMember,memberId);
		return "admin/ChangeMemberStatus-result";
		}else {
			return "admin/ChangeMemberStatus-fail";
		}
	}
	
	
}



















