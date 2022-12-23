package org.goodomen.hiddenpiece.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
import org.goodomen.hiddenpiece.model.service.MemberService;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardLikesVO;
import org.goodomen.hiddenpiece.model.vo.AuctionBoardPostVO;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.MemberVO;
import org.goodomen.hiddenpiece.model.vo.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuctionBoardController {
	private final AuctionBoardService auctionBoardService;
	private final MemberService memberService;
	
	// 경매게시판 상세보기
	@SuppressWarnings("unchecked")
	@RequestMapping("findAuctionBoardPostDetail")
	public String findAuctionBoardPostDetail(long postNo, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		AuctionBoardPostVO postVO = null;
		if(session!=null) {
			MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
			ArrayList<Long> auctionBoardList = (ArrayList<Long>) session.getAttribute("auctionBoardList");
			if(auctionBoardList.size()==0||!auctionBoardList.contains(postNo)) {
				auctionBoardService.addHits(postNo);
				auctionBoardList.add(postNo);
				session.setAttribute("auctionBoardPostList", auctionBoardList);
			}
			postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
			AuctionBoardLikesVO likesVO = new AuctionBoardLikesVO(memberVO.getId(), postNo);
			if(memberService.checkWishlist(likesVO)>0) {
				postVO.setLike(false);
			}
			else {
				postVO.setLike(true);
			}
		} else {
			postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
		}
		ArrayList<AuctionBoardCommentVO> commentList = auctionBoardService.findAuctionBoardCommentListByPostNo(postNo);
		model.addAttribute("postVO", postVO);
		model.addAttribute("commentList", commentList);
		return "auctionboard/detail";
	}
	
	// 경매게시판 글 작성 폼 화면 이동
	@RequestMapping("moveAuctionBoardPostForm")
	public String moveAuctionBoardPostForm() {
		return "auctionboard/write-form";
	}
	
	// 경매게시판 글 작성
	@PostMapping("writeAuctionBoardPost")
	public String writeAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO, @RequestParam("image") MultipartFile file) {
		SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		auctionBoardPostVO.setEndDate(auctionBoardPostVO.getEndDate().substring(0, 10) + " " +auctionBoardPostVO.getEndDate().substring(11, 16));
		auctionBoardPostVO.setPhoto(auctionBoardPostVO.getId()+nowTime.format(now)+file.getOriginalFilename());
		auctionBoardService.writeAuctionBoardPost(auctionBoardPostVO);

		//////////////////////////////////////////////////////////////////
		//System.out.println("파일 이름 : " + file.getOriginalFilename());
	    //System.out.println("파일 크기 : " + file.getSize());

	    try(
	      // 윈도우일 경우
	      FileOutputStream fos = new FileOutputStream("C:/kosta250/HiddenPiece/HiddenPiece/HiddenPiece/src/main/resources/static/auctionboardimg/" +auctionBoardPostVO.getId()+nowTime.format(now)+ file.getOriginalFilename());
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
		return "auctionboard/write-ok";
	}
	
	// 경매게시판 댓글 작성
	@ResponseBody
	@RequestMapping("writeComment")
	public String writeComment(Model model, AuctionBoardCommentVO commentVO) {
		String result = "1";
		auctionBoardService.writeComment(commentVO);
		return result;
	}
	
	// 경매게시판 댓글 삭제(상태 0으로 변경)
	@ResponseBody
	@RequestMapping("changeCommentStatus")
	public void changeCommentStatus(long commentNo) {
		auctionBoardService.changeCommentStatus(commentNo);
	}
	
	// 경매게시판 댓글 조회
	@ResponseBody
	@RequestMapping("selectCommentByCommentNo")
	public String selectCommentByCommentNo(long commentNo) {
		String content = auctionBoardService.selectCommentByCommentNo(commentNo);
		return content;
	}
	
	// 경매게시판 댓글 수정
	@ResponseBody
	@RequestMapping("updateComment")
	public void updateComment(AuctionBoardCommentVO commentVO) {
		auctionBoardService.updateComment(commentVO);
	}
	
	// 찜하기 버튼 눌르기
	@ResponseBody
	@RequestMapping("addToWishlist")
	public String addToWishlist(String id,long postNo, AuctionBoardPostVO postVO) {
		postVO.setLike(true);
		AuctionBoardLikesVO likesVO = new AuctionBoardLikesVO(id, postNo);
		auctionBoardService.addToWishlist(likesVO);
		return "ok";
	}
	
	//경매게시판 글 수정 폼으로 이동
	@RequestMapping("moveAuctionBoardPostUpdateForm")
	public String moveAuctionBoardPostUpdateForm(Model model, long postNo) {
		AuctionBoardPostVO postVO = auctionBoardService.findAuctionBoardPostDetail(postNo);
		model.addAttribute("postVO", postVO);
		return "auctionboard/update-form";
	}
	
	//경매게시판 글 삭제
	@PostMapping("AuctionBoardPostDelete")
	public String moveAuctionBoardPostDelete(long postNo) {
		int result = auctionBoardService.deleteAuctionBoardPost(postNo);
		return "auctionboard/delete-ok";
	}
	
	//경매게시판 글 수정
	@PostMapping("updateAuctionBoardPost")
	public String updateAuctionBoardPost(AuctionBoardPostVO auctionBoardPostVO) {
		int result = auctionBoardService.updateAuctionBoardPost(auctionBoardPostVO);
		return "auctionboard/update-ok";
	}
	//경매게치판 입찰
	@RequestMapping("bid")
	public String bid(AuctionBoardPostVO auctionBoardPostVO,long bidPrice, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		auctionBoardPostVO.setCurrentPrice(bidPrice);
		int result = auctionBoardService.bidAuctionBoardPost(auctionBoardPostVO);
		long newPoint = memberService.findPoint(auctionBoardPostVO.getId());
		MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
		memberVO.setPoint(newPoint);
		session.setAttribute("mvo", memberVO);
		return "redirect:bidMove";
	}
	
	@RequestMapping("bidMove")
	public String bidMove() {
		return "auctionboard/bid-ok";
	}
	
	@RequestMapping("buy")
	public String buy(AuctionBoardPostVO auctionBoardPostVO, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		int result = auctionBoardService.buyAuctionBoardPost(auctionBoardPostVO);
		long newPoint = memberService.findPoint(auctionBoardPostVO.getId());
		MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
		memberVO.setPoint(newPoint);
		session.setAttribute("mvo", memberVO);
		return "redirect:buymove";
	}
	
	@RequestMapping("buymove")
	public String buymove() {
		return "auctionboard/buy-ok";
	}
	
	 //경매게시판으로 이동
	@RequestMapping("searchPostByKeyword")
	public String searchPostByKeyword(@RequestParam HashMap<String, Object> mapList, Model model, HttpServletRequest request,Criteria cri){
		if(!mapList.get("pageIndex").equals("")) { 
			cri.setPage(Integer.valueOf((String)mapList.get("pageIndex"))); 
			}
		if(!mapList.containsKey("searchKeyword") || mapList.get("searchKeyword").equals("")) {
			mapList.put("searchKeyword", "");
		}
		if(mapList.containsKey("status")) {
			String sstatusText =mapList.get("status").toString();

			int auctionBoardListCnt = auctionBoardService.searchAuctionBoardListCnt(cri);
			
			Paging paging = new Paging();
			paging.setCri(cri);
			paging.setTotalCount(auctionBoardListCnt);
			model.addAttribute("paging", paging);
			HttpSession session = request.getSession(false);
			
			if(session!=null) {
				MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
				String id = memberVO.getId();
				cri.setLoginId(id);
				ArrayList<AuctionBoardPostVO> myWishlist = memberService.selectMyWishlist(id);
				cri.setSearchKeyword(mapList.get("searchKeyword").toString());
				List<Map<String,Object>> auctionBoardList =auctionBoardService.searchPostByKeyword(cri);
				for(int i=0; i<auctionBoardList.size(); i++) {
					if(myWishlist.contains(auctionBoardList.get(i))){
						((AuctionBoardPostVO) auctionBoardList.get(i)).setLike(true);
					}
				}
				model.addAttribute("postList", auctionBoardList);
			}
			
			else {
				cri.setSearchKeyword(mapList.get("searchKeyword").toString());
				List<Map<String,Object>> auctionBoardList =auctionBoardService.searchPostByKeyword(cri);
				model.addAttribute("postList", auctionBoardList);
			}
		}
		model.addAttribute("mapList", mapList);
		
		return "shop2";
	}
	
}
