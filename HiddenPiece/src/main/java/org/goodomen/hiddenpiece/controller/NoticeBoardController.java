package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.NoticeBoardService;
import org.goodomen.hiddenpiece.model.vo.NoticeBoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeBoardController {
	private final NoticeBoardService noticeBoardService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("findNoticeBoardPostDetail")
	public String findNoticeBoardPostDetail(long postNo, Model model, HttpServletRequest request) {
		HttpSession session=request.getSession(false); 
		NoticeBoardVO postVO=null;
		if(session!=null) {
			ArrayList<Long> noticeBoardList = (ArrayList<Long>) session.getAttribute("noticeBoardList"); //세션의 읽은목록리스트를 가지고옴
			if(noticeBoardList.size()==0 || !noticeBoardList.contains(postNo)) { //만약 읽은목록리스트의 크기가 0이거나 freeBoardList가 postNo를 포함하고있다면
				noticeBoardService.updateHits(postNo);//조회수 증가하고
				noticeBoardList.add(postNo);//읽은목록리스트에 글번호 추가하고
				session.setAttribute("noticeBoardList", noticeBoardList);//읽은목록리스트를 세션에 저장
			} //위에 해당되지 않으면 읽은목록리스트에 글번호가 존재하므로 번호추가&조회수증가를 할 필요가 없음
			postVO = noticeBoardService.findNoticeBoardPostDetail(postNo);
		}
		else {
			postVO = noticeBoardService.findNoticeBoardPostDetail(postNo);
		}
		model.addAttribute("postVO", postVO);
		return "noticeboard/noticeBoardPostDetail";
	}
	// 글 작성
	@RequestMapping("moveNoticeBoardPostWriteForm")
	public String moveNoticeBoardPostWriteForm() {
		return "noticeboard/noticeBoardWritePostForm";
	}
	@RequestMapping("writeNoticeBoardPost")
	public String writeNoticeBoardPost(NoticeBoardVO noticeBoardVO) {
		noticeBoardService.writePost(noticeBoardVO);
		return "redirect:noticeBoardPostList";
	}

	// 글 수정
	@RequestMapping("noticeBoardUpdatePostForm")
	public String moveNoticeBoardPostUpdateForm(long postNo, Model model) {
		model.addAttribute("postVO", noticeBoardService.findNoticeBoardPostDetail(postNo));
		return "noticeboard/noticeBoardUpdatePostForm";
	}
	@RequestMapping("updateNoticeBoardPost")
	public String updateNoticeBoardPost(NoticeBoardVO noticeBoardVO) {
		noticeBoardService.updatePost(noticeBoardVO);
		return "redirect:noticeBoardPostList";
	}
	// 글 삭제
	@RequestMapping("noticeBoardDeletePostForm")
	public String moveNoticeBoardPostDeleteForm() {
		return "noticeboard/noticeBoardDeletePostForm";
	}
	@RequestMapping("deleteNoticeBoardPost")
	public String deleteNoticeBoardPost(long postNo) {
		noticeBoardService.deletePost(postNo);
		return "redirect:noticeBoardPostList";
	}	
}
