package org.goodomen.hiddenpiece.model.service;

import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.ShareBoardVO;

public interface ShareboardService {

	int findShareCount();

	int findShareCountTag(CriteriaAndIdVO cri);

	List<Map<String, Object>> findShareList(CriteriaAndIdVO cri);

	int writeSharePost(ShareBoardVO shareboardVO);

	ShareBoardVO findShareBoardDetail(String postNo);

	int updateShareBoard(ShareBoardVO shareboardVO);

	int deleteShareboard(long postNo);

	int finishShare(long postNo);

	void addHits(String postNo);

}
