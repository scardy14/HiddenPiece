package org.goodomen.hiddenpiece.model.service;

import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.ShareBoardVO;

public interface ShareboardService {

	int findShareCount();

	int findShareCountTag(CriteriaAndIdVO cri);

	List<Map<String, Object>> findShareList(CriteriaAndIdVO cri);

	long writeSharePost(ShareBoardVO shareboardVO);

	long updateShareBoard(ShareBoardVO shareboardVO);

	long deleteShareboard(long postNo);

	long finishShare(long postNo);

	void addHits(long postNo);

	ShareBoardVO findShareBoardDetail(long postNo);

}
