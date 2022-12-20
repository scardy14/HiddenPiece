package org.goodomen.hiddenpiece.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.FreeBoardService;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FreeBoardController {
	private final FreeBoardService freeBoardService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("findFreeBoardPostDetail")
	public String findFreeBoardPostDetail(long postNo, Model model, HttpServletRequest request) {
		HttpSession session=request.getSession(false); 
		if(session!=null) {
			ArrayList<Long> freeBoardList = (ArrayList<Long>) session.getAttribute("freeBoardList"); //세션의 읽은목록리스트를 가지고옴
			if(freeBoardList.size()==0 || !freeBoardList.contains(postNo)) { //만약 읽은목록리스트의 크기가 0이거나 freeBoardList가 postNo를 포함하고있다면
				freeBoardService.updateHits(postNo);//조회수 증가하고
				freeBoardList.add(postNo);//읽은목록리스트에 글번호 추가하고
				session.setAttribute("freeBoardList", freeBoardList);//읽은목록리스트를 세션에 저장
			} //위에 해당되지 않으면 읽은목록리스트에 글번호가 존재하므로 번호추가&조회수증가를 할 필요가 없음
		}
		model.addAttribute("postVO", freeBoardService.findFreeBoardPostDetail(postNo));
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
	/*
	@RequestMapping("freeBoardUpdatePostForm")
	public String moveFreeBoardPostUpdateForm(long postNo, Model model) {
		model.addAttribute("postVO", freeBoardService.findFreeBoardPostDetail(postNo));
		return "freeboard/freeBoardUpdatePostForm";
	}
	@RequestMapping("updatePost")
	public String updatePost(long postNo,FreeBoardVO freeBoardVO) {
		System.out.println(postNo);
		freeBoardService.updatePost(freeBoardVO);
		return 
	
	@RequestMapping("updatePost")
	public String updatePost(long postNo,FreeBoardVO freeBoardVO) {
		System.out.println(postNo);
		freeBoardService.updatePost(freeBoardVO);
		return "redirect:updatePostResult";
	}	
	
	@RequestMapping("updatePostResult")
	public String updatePostResult(Model model) {
		return "freeboard/updatePost-ok";
	}
	*/
	
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
}
