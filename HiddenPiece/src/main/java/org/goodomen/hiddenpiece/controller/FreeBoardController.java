package org.goodomen.hiddenpiece.controller;

import org.goodomen.hiddenpiece.model.service.FreeBoardService;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FreeBoardController {
	private final FreeBoardService freeBoardService;
	
	@RequestMapping("findFreeBoardPostDetail")
	public String findFreeBoardPostDetail(long postNo, Model model) {
		FreeBoardVO postVO=freeBoardService.findFreeBoardPostDetail(postNo);
		model.addAttribute("postVO", postVO);
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
