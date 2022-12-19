package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;

public interface FreeBoardService {

	ArrayList<FreeBoardVO> findFreeBoardPostList();

	FreeBoardVO findFreeBoardPostDetail(long postNo);

	void writePost(FreeBoardVO freeBoardVO);

	void updatePost(FreeBoardVO freeBoardVO);

	void deletePost(long postNo);
}
