package org.goodomen.hiddenpiece.model.service;

import java.util.ArrayList;

import org.goodomen.hiddenpiece.controller.AuctionBoardCommentVO;
import org.goodomen.hiddenpiece.model.vo.FreeBoardCommentVO;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;

public interface FreeBoardService {

	ArrayList<FreeBoardVO> findFreeBoardPostList();

	FreeBoardVO findFreeBoardPostDetail(long postNo);

	void writePost(FreeBoardVO freeBoardVO);

	void updatePost(FreeBoardVO freeBoardVO);

	void deletePost(long postNo);

	void updateHits(long postNo);

	void writeComment(FreeBoardCommentVO commentVO);

	void changeCommentStatus(long commentNo);

	String selectCommentByCommentNo(long commentNo);

	void updateComment(FreeBoardCommentVO commentVO);

	ArrayList<FreeBoardCommentVO> findFreeBoardCommentListByPostNo(long postNo);
}
