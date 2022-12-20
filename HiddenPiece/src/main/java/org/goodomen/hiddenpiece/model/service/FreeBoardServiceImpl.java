package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.mapper.FreeBoardMapper;
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
	public void deletePost(long postNo) {
		freeBoardMapper.deletePost(postNo);
	}
	@Override
	public void updateHits(long postNo) {
		freeBoardMapper.updateHits(postNo);
	}
}
