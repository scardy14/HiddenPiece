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
		if(boardName!=null) {
			model.addAttribute(boardName, findList);
		}
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



















