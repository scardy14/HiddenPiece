package org.goodomen.hiddenpiece.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.Criteria;
import org.goodomen.hiddenpiece.model.vo.NoticeBoardCriteria;
import org.goodomen.hiddenpiece.model.vo.NoticeBoardVO;

@Mapper
public interface NoticeBoardMapper {

	public ArrayList<NoticeBoardVO> findNoticeBoardPostList();

	public int noticeBoardListCnt();

	public List<Map<String, Object>> boardList(Criteria cri);

	public NoticeBoardVO findNoticeBoardPostDetail(long postNo);

	public void updateHits(long postNo);

	public void writePost(NoticeBoardVO noticeBoardVO);

	public void updatePost(NoticeBoardVO noticeBoardVO);

	public void deletePost(long postNo);

}
