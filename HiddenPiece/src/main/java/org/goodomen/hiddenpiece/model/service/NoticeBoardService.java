package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.vo.NoticeBoardCriteria;
import org.goodomen.hiddenpiece.model.vo.NoticeBoardVO;

public interface NoticeBoardService {

	ArrayList<NoticeBoardVO> findNoticeBoardPostList();

	int noticeBoardListCnt();

	List<Map<String, Object>> boardList(NoticeBoardCriteria ncri);

	NoticeBoardVO findNoticeBoardPostDetail(long postNo);

	void updateHits(long postNo);

	void writePost(NoticeBoardVO noticeBoardVO);

	void updatePost(NoticeBoardVO noticeBoardVO);

	void deletePost(long postNo);

}
