package org.goodomen.hiddenpiece.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.FreeBoardVO;

@Mapper
public interface FreeBoardMapper {

	public ArrayList<FreeBoardVO> findFreeBoardPostList();

	public FreeBoardVO findFreeBoardPostDetail(long postNo);

	public void writePost(FreeBoardVO freeBoardVO);
	
	public FreeBoardVO findTitleAndContent(long postNo);
	
	public void updatePost(FreeBoardVO freeBoardVO);

	public void deletePost(long postNo);

	

	
}
