package org.goodomen.hiddenpiece.controller;

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
		/*HttpSession session = request.getSession();
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");*/
		
		freeBoardService.writePost(freeBoardVO);
		System.out.println(freeBoardVO);
		return "freeboard/writePost-ok";
	}	
}
