package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.mapper.FreeBoardMapper;
import org.goodomen.hiddenpiece.model.vo.FreeBoardCommentVO;
import org.goodomen.hiddenpiece.model.vo.FreeBoardCriteria;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{
	private final FreeBoardMapper freeBoardMapper;
	@Override
	public ArrayList<FreeBoardVO> findFreeBoardPostList() {
		return freeBoardMapper.findFreeBoardPostList();
	}
	@Override
	public FreeBoardVO findFreeBoardPostDetail(long postNo) {
		return freeBoardMapper.findFreeBoardPostDetail(postNo);
	}
	@Override
	public void writePost(FreeBoardVO freeBoardVO) {
		freeBoardMapper.writePost(freeBoardVO);
	}
	@Override
	public void updatePost(FreeBoardVO freeBoardVO) {
		freeBoardMapper.updatePost(freeBoardVO);
	}
	@Override
	public int deletePost(long postNo) {
		return freeBoardMapper.deletePost(postNo);
	}
	@Override
	public void updateHits(long postNo) {
		freeBoardMapper.updateHits(postNo);
	}
	@Override
	public void writeComment(FreeBoardCommentVO commentVO) {
		freeBoardMapper.writeComment(commentVO);
	}
	@Override
	public void changeCommentStatus(long commentNo) {
		freeBoardMapper.changeCommentStatus(commentNo);
	}
	@Override
	public String selectCommentByCommentNo(long commentNo) {
		return freeBoardMapper.selectCommentByCommentNo(commentNo);
	}

	@Override
	public void updateComment(FreeBoardCommentVO commentVO) {
		freeBoardMapper.updateComment(commentVO);
	}
	@Override
	public ArrayList<FreeBoardCommentVO> findFreeBoardCommentListByPostNo(long postNo) {
		return freeBoardMapper.findFreeBoardCommentListByPostNo(postNo);
	}
	@Override
	public int freeBoardListCnt() {
		return freeBoardMapper.freeBoardListCnt();
	}
	@Override
	public List<Map<String, Object>> boardList(FreeBoardCriteria fcri) {
		return freeBoardMapper.boardList(fcri);
	}
}
