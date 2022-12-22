package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.mapper.NoticeBoardMapper;
import org.goodomen.hiddenpiece.model.vo.NoticeBoardCriteria;
import org.goodomen.hiddenpiece.model.vo.NoticeBoardVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl implements NoticeBoardService{
	private final NoticeBoardMapper noticeBoardMapper;
	@Override
	public ArrayList<NoticeBoardVO> findNoticeBoardPostList() {
		return noticeBoardMapper.findNoticeBoardPostList();
	}
	@Override
	public int noticeBoardListCnt() {
		return noticeBoardMapper.noticeBoardListCnt();
	}
	@Override
	public List<Map<String,Object>> boardList(NoticeBoardCriteria ncri){
		return noticeBoardMapper.boardList(ncri);
	}
	@Override
	public NoticeBoardVO findNoticeBoardPostDetail(long postNo) {
		return noticeBoardMapper.findNoticeBoardPostDetail(postNo);
	}
	@Override
	public void updateHits(long postNo) {
		noticeBoardMapper.updateHits(postNo);
	}
	@Override
	public void writePost(NoticeBoardVO noticeBoardVO) {
		noticeBoardMapper.writePost(noticeBoardVO);
	}
	@Override
	public void updatePost(NoticeBoardVO noticeBoardVO) {
		noticeBoardMapper.updatePost(noticeBoardVO);
	}
	@Override
	public void deletePost(long postNo) {
		noticeBoardMapper.deletePost(postNo);
	}
}
