package org.goodomen.hiddenpiece.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.ShareboardService;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.PagingAndId;
import org.goodomen.hiddenpiece.model.vo.ShareBoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class ShareBoardController {
	private final ShareboardService shareboardService;
	
	@RequestMapping("ShareBoardPostList")
	public String shareBoardPostList(CriteriaAndIdVO cri, Model model) {
		PagingAndId paging = new PagingAndId();
		int shareCount;
		List<Map<String, Object>> shareList = null;
		if(cri.getTag()==null||cri.getTag().equals("0")) {
			cri.setTag("0");
			shareCount = shareboardService.findShareCount();
		}else {
			shareCount = shareboardService.findShareCountTag(cri);
		} 
		paging.setCri(cri);
		paging.setTotalCount(shareCount);
		shareList = shareboardService.findShareList(cri);
		model.addAttribute("shareList", shareList);
		model.addAttribute("paging", paging);
		return "shareboard/shareboard";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("findShareBoardDetail")
	public String findShareBoardDetail(long postNo,Model model,  HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			ArrayList<Long> shareBoardList = (ArrayList<Long>) session.getAttribute("shareBoardList");
			if (shareBoardList.size() == 0 || !shareBoardList.contains(postNo)) {
				shareboardService.addHits(postNo);
				shareBoardList.add(postNo);
				session.setAttribute("shareBoardList", shareBoardList);
			}
		}
		ShareBoardVO shareboardVO = shareboardService.findShareBoardDetail(postNo);
		model.addAttribute("postVO",shareboardVO);
		return "shareboard/shareboarddetail";
	}
	
	//////////////////////////////////////////////////////////////
	@RequestMapping("moveShareBoardWriteForm")
	public String moveShareBoardWriteForm() {
		return "shareboard/write-shareform";
	}
	@PostMapping("writeShareBoard")
	public String writeShareBoard(ShareBoardVO shareboardVO, @RequestParam("image") MultipartFile file) {
		SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String photoname=shareboardVO.getId()+nowTime.format(now)+file.getOriginalFilename();
		shareboardVO.setPhoto(photoname);
		long result = shareboardService.writeSharePost(shareboardVO);
	    try(
	      // 윈도우일 경우 
	      FileOutputStream fos = new FileOutputStream("C:/kosta250/HiddenPieceGit/HiddenPiece/HiddenPiece/src/main/resources/static/shareboardimg/" + photoname);
	      InputStream is = file.getInputStream();
	    ){
	      int readCount = 0;
	      byte[] buffer = new byte[2048];
	      while((readCount = is.read(buffer)) != -1){
	      fos.write(buffer,0,readCount);
	    }
	    }catch(Exception ex){
	    	result = 0;
	    	throw new RuntimeException("file Save Error");
	    }
	    if(result == 1) {
	    	return "redirect:writeOk";
	    } else {
	    	return "redirect:writeFail";
	    }
	}
	@RequestMapping("writeOk")
	public String writeOk() {
		return"shareboard/write-ok";
	}
	@RequestMapping("writeFail")
	public String writeFail() {
		return "shareboard/write-fail";
	}
	//////////////////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////////////////
	@RequestMapping("moveShareboardUpdate")
	public String shardboardUpdate(long postNo, Model model, HttpServletRequest request) {
		ShareBoardVO shareboardVO = shareboardService.findShareBoardDetail(postNo);
		model.addAttribute("postVO",shareboardVO);
		return "shareboard/update-form";
	}
	@PostMapping("updateShareboard")
	public String updateShareboard (ShareBoardVO shareboardVO) {
		long result = shareboardService.updateShareBoard(shareboardVO);
		if(result==1) {
			return "redirect:updateok";
		} else {
			return "redirect:updatefail";
		}
	}
	@RequestMapping("updateok")
	public String updateOk() {
		return "shareboard/update-ok";
	}
	@RequestMapping("updatefail")
	public String updateFail() {
		return "shareboard/update-fail";
	}
	//////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////
	@RequestMapping("shareboardDelete")
	public String shareboardDelete(long postNo) {
		long result = shareboardService.deleteShareboard(postNo);
		if(result ==1) {
			return"redirect:deleteok";
		} else {
			return"redirect:deletefail";
		}
	}
	@RequestMapping("deleteok")
	public String deleteOk() {
		return "shareboard/delete-ok";
	}
	@RequestMapping("deletefail")
	public String deleteFail() {
		return "shareboard/delete-fail";
	}
	//////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////
	@RequestMapping("finishShare")
	public String finishShare(long postNo) {
		long result = shareboardService.finishShare(postNo);
		if(result==1) {
			return "redirect:finishOk";
		} else {
			return "redirect:finishFail";
		}
	}
	@RequestMapping("finishOk")
	public String finishOk() {
		return "shareboard/finish-ok";
	}
	@RequestMapping("finishFail")
	public String finishFail() {
		return "shareboard/finish-fail";
	}
	//////////////////////////////////////////////////////////////
}
