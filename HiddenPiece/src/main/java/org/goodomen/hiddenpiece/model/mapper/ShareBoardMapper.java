package org.goodomen.hiddenpiece.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.ShareBoardVO;
@Mapper
public interface ShareBoardMapper {

	int findShareCount();

	int findShareCountTag(CriteriaAndIdVO cri);

	List<Map<String, Object>> findShareList(CriteriaAndIdVO cri);
	
	List<Map<String, Object>> findShareListTag(CriteriaAndIdVO cri);

	void writeSharePost(ShareBoardVO shareboardVO);

	ShareBoardVO findShareBoardDetail(String postNo);

}
