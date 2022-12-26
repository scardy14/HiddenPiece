package org.goodomen.hiddenpiece.model.service;

import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.mapper.ShareBoardMapper;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.ShareBoardVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShareboardServiceImpl implements ShareboardService{
	private final ShareBoardMapper shareboardMapper;
	@Override
	public int findShareCount() {
		return shareboardMapper.findShareCount();
	}

	@Override
	public int findShareCountTag(CriteriaAndIdVO cri) {
		return shareboardMapper.findShareCountTag(cri);
	}

	@Override
	public List<Map<String, Object>> findShareList(CriteriaAndIdVO cri) {
		if(cri.getTag().equals("0")) {
			return shareboardMapper.findShareList(cri);
		} else {
			return shareboardMapper.findShareListTag(cri);
		}		
	}

	@Override
	public long writeSharePost(ShareBoardVO shareboardVO) {
		return shareboardMapper.writeSharePost(shareboardVO);
	}

	@Override
	public ShareBoardVO findShareBoardDetail(long postNo) {
		return shareboardMapper.findShareBoardDetail(postNo);
	}

	@Override
	public long updateShareBoard(ShareBoardVO shareboardVO) {
		return shareboardMapper.updateShareboardVO(shareboardVO);
	}

	@Override
	public long deleteShareboard(long postNo) {
		return shareboardMapper.deleteShareboard(postNo);
	}

	@Override
	public long finishShare(long postNo) {
		return shareboardMapper.finishShare(postNo);
	}

	@Override
	public void addHits(long postNo) {
		shareboardMapper.addHits(postNo);
	}


}
