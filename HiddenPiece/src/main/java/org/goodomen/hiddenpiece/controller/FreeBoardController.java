package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.FreeBoardService;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.FreeBoardCommentVO;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FreeBoardController {
	private final FreeBoardService freeBoardService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("findFreeBoardPostDetail")
	public String findFreeBoardPostDetail(long postNo, Model model, HttpServletRequest request) {
		HttpSession session=request.getSession(false); 
		FreeBoardVO postVO=null;
		if(session!=null) {
			ArrayList<Long> freeBoardList = (ArrayList<Long>) session.getAttribute("freeBoardList"); //세션의 읽은목록리스트를 가지고옴
			if(freeBoardList.size()==0 || !freeBoardList.contains(postNo)) { //만약 읽은목록리스트의 크기가 0이거나 freeBoardList가 postNo를 포함하고있다면
				freeBoardService.updateHits(postNo);//조회수 증가하고
				freeBoardList.add(postNo);//읽은목록리스트에 글번호 추가하고
				session.setAttribute("freeBoardList", freeBoardList);//읽은목록리스트를 세션에 저장
			} //위에 해당되지 않으면 읽은목록리스트에 글번호가 존재하므로 번호추가&조회수증가를 할 필요가 없음
			postVO = freeBoardService.findFreeBoardPostDetail(postNo);
		}
		else {
			postVO=freeBoardService.findFreeBoardPostDetail(postNo);
		}
		// 자유게시판 댓글 조회
		ArrayList<FreeBoardCommentVO> commentList=freeBoardService.findFreeBoardCommentListByPostNo(postNo);
		model.addAttribute("postVO", postVO);
		model.addAttribute("commentList", commentList);
		return "freeboard/freeBoardPostDetail";
	}
	@RequestMapping("moveFreeBoardPostWriteForm")
	public String moveFreeBoardPostWriteForm() {
		return "freeboard/freeBoardWritePostForm";
	}
	@RequestMapping("writePost")
	public String writePost(FreeBoardVO freeBoardVO) {
		freeBoardService.writePost(freeBoardVO);
		System.out.println(freeBoardVO);
		return "freeboard/writePost-ok";
	}	
	
	@RequestMapping("freeBoardUpdatePostForm")
	public String moveFreeBoardPostUpdateForm(long postNo, Model model) {
		model.addAttribute("postVO", freeBoardService.findFreeBoardPostDetail(postNo));
		return "freeboard/freeBoardUpdatePostForm";
	}
	@RequestMapping("updatePost")
	public String updatePost(FreeBoardVO freeBoardVO) {
		freeBoardService.updatePost(freeBoardVO);
		return "redirect:freeBoardPostList";
	}	
	
	@RequestMapping("freeBoardDeletePostForm")
	public String moveFreeBoardPostDeleteForm() {
		return "freeboard/freeBoardDeletePostForm";
	}
	@RequestMapping("deletePost")
	public String deletePost(long postNo) {
		freeBoardService.deletePost(postNo);
		return "redirect:freeBoardPostList";
	}		
	
	// 자유게시판 댓글 작성
		@ResponseBody
		@RequestMapping("writeFreeBoardComment")
		public String writeComment(Model model, FreeBoardCommentVO commentVO) {
			String result = "1";
			System.out.println(commentVO);
			freeBoardService.writeComment(commentVO);
			return result;
		}
		
		// 자유게시판 댓글 삭제(상태 0으로 변경)
		@ResponseBody
		@RequestMapping("changeFreeBoardCommentStatus")
		public void changeCommentStatus(long commentNo) {
			freeBoardService.changeCommentStatus(commentNo);
		}
		
		// 자유게시판 댓글 조회
		@ResponseBody
		@RequestMapping("selectFreeBoardCommentByCommentNo")
		public String selectCommentByCommentNo(long commentNo) {
			String content = freeBoardService.selectCommentByCommentNo(commentNo);
			return content;
		}
		
		// 자유게시판 댓글 수정
		@ResponseBody
		@RequestMapping("updateFreeBoardComment")
		public void updateComment(FreeBoardCommentVO commentVO) {
			freeBoardService.updateComment(commentVO);
		}
}
