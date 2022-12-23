package org.goodomen.hiddenpiece.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
		shareboardService.writeSharePost(shareboardVO);
		//////////////////////////////////////////////////////////////////
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
	      throw new RuntimeException("file Save Error");
	    }
	   ////////////////////////////////////////////////////////////////////
		return "redirect:ShareBoardPostList";
	}
}
